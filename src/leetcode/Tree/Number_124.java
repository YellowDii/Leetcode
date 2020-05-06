package leetcode.Tree;

public class Number_124 {
    /**
     * 124.二叉树中的最大路径和（binary-tree-maximum-path-sum）
     *
     * 给定一个非空二叉树，返回其最大路径和。
     *
     * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
     *
     * 示例 1:
     *
     * 输入: [1,2,3]
     *
     *        1
     *       / \
     *      2   3
     *
     * 输出: 6
     * 示例 2:
     *
     * 输入: [-10,9,20,null,null,15,7]
     *
     *    -10
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * 输出: 42
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val=x;
        }
    }
    //递归 函数maxVal(node)表示经过node点的左路径和或者右路径和 (哪个大返回哪个 或者为0 代表完全不经过)其中还需要维护最大和max_val
    int max_val=Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxVal(root);
        return max_val;
    }

    private int maxVal(TreeNode root) {
         if (root==null){
             return 0;
         }
         int left_val=Math.max(maxVal(root.left),0);
         int right_val=Math.max(maxVal(root.right),0);
         max_val=Math.max(left_val+right_val+root.val,max_val);
         return root.val+Math.max(left_val,right_val);
    }
}
