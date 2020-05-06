package leetcode.Tree;

public class Number_572 {
    /**
     * 572.另一个树的子树（subtree-of-another-tree）
     * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。
     * s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
     *
     * 示例 1:
     * 给定的树 s:
     *
     *      3
     *     / \
     *    4   5
     *   / \
     *  1   2
     * 给定的树 t：
     *
     *    4
     *   / \
     *  1   2
     * 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
     *
     * 示例 2:
     * 给定的树 s：
     *
     *      3
     *     / \
     *    4   5
     *   / \
     *  1   2
     *     /
     *    0
     * 给定的树 t：
     *
     *    4
     *   / \
     *  1   2
     * 返回 false。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/subtree-of-another-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            this.val=x;
        }
    }
    //可以两个前序遍历后 看两个数组有没有相同段 也可以递归
    //有点慢
    public boolean isSubtree(TreeNode s, TreeNode t) {
        //两个都非空
        boolean same=false;
        if (s!=null&&t!=null){
            if (s.val==t.val){
                same=isSame(s.left,t.left)&&isSame(s.right,t.right);
            }
            same=same||isSubtree(s.left,t)||isSubtree(s.right,t);
        }else if (s==null&&t==null){
            same=true;
        }else {
            same=false;
        }
        return same;
    }

    private boolean isSame(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2 == null;
        }

        if (root2 == null) {
            return false;
        }

        return root1.val == root2.val &&
                isSame(root1.left, root2.left) && isSame(root1.right, root2.right);
    }

    //加上节点数判断 要快很多
    private boolean isFoundSame = false;
    private int tCount;

    // 递归计算二叉树s每棵子树的节点数，并同时寻找是否有子树与t相等
    private int calcCount(TreeNode root, TreeNode t) {
        if (isFoundSame) {
            return 0;
        }

        if (root == null) {
            return 0;
        }
        //计算root为根的子树的节点数
        int count = calcCount(root.left, t) + calcCount(root.right, t) + 1;
        if (count == tCount && isSame(root, t)) {
            isFoundSame = true;
            return 0;
        }
        return count;
    }

    // 计算二叉树t的节点个数
    private int calcTCount(TreeNode root) {
        return root == null ? 0 : calcTCount(root.left) + calcTCount(root.right) + 1;
    }

    public boolean isSubtree2(TreeNode s, TreeNode t) {
        tCount = calcTCount(t);
        calcCount(s, t);
        return isFoundSame;
    }
}
