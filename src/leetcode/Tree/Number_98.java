package leetcode.Tree;

import java.util.Stack;

public class Number_98 {
    /**
     * 98.验证二叉搜索树（validate-binary-search-tree）
     *
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     *
     * 假设一个二叉搜索树具有如下特征：
     *
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * 示例 1:
     *
     * 输入:
     *     2
     *    / \
     *   1   3
     * 输出: true
     * 示例 2:
     *
     * 输入:
     *     5
     *    / \
     *   1   4
     *      / \
     *     3   6
     * 输出: false
     * 解释: 输入为: [5,1,4,null,null,3,6]。
     *      根节点的值为 5 ，但是其右子节点值为 4 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     */
    public  class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            this.val=x;
        }
    }
    //这个只能局部检测 还得
    public boolean isValidBST(TreeNode root) {
        return isBST(root,null,null);
    }

    private boolean isBST(TreeNode root, Integer lower, Integer upper) {
        if (root==null){
            return true;
        }
        if (upper!=null&&root.val>=upper){
            return false;
        }
        if (lower!=null&&root.val<=lower){
            return false;
        }
        if (!isBST(root.left,lower,root.val)){
            return false;
        }
        if (!isBST(root.right,root.val,upper)){
            return false;
        }
        return true;
    }
    //中序遍历
    public boolean isValidBST2(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        double inorder = - Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;
        }
        return true;
    }

}
