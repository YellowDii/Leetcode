package leetcode.Tree;

import java.util.HashMap;

public class Number_377 {
    /**
     * 377.打家劫舍（house-robber-iii）
     *
     * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
     *
     * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
     *
     * 示例 1:
     *
     * 输入: [3,2,3,null,3,null,1]
     *
     *      3
     *     / \
     *    2   3
     *     \   \
     *      3   1
     *
     * 输出: 7
     * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
     *
     * 示例 2:
     *
     * 输入: [3,4,5,1,3,null,1]
     *
     *      3
     *     / \
     *    4   5
     *   / \   \
     *  1   3   1
     *
     * 输出: 9
     * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/house-robber-iii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //一开始想着层次遍历 间隔求和 然后发现下面这个例子不行:
    /**
     *      1
     *     / \
     *    2   4
     *    \
     *     6
     * 这个可以选6、4
     */
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int value){
            this.val=value;
        }
    }
    //最容易想到的
    //不过接近1s时间才处理完  要优化
    //可以看到有很多重复子问题
    public int rob(TreeNode root) {
        if (root == null) return 0;

        int money = root.val;
        if (root.left != null) {
            money += (rob(root.left.left) + rob(root.left.right));
        }

        if (root.right != null) {
            money += (rob(root.right.left) + rob(root.right.right));
        }

        return Math.max(money, rob(root.left) + rob(root.right));
    }

    public int rob2(TreeNode root){
        HashMap<TreeNode,Integer> map=new HashMap<>();
        return robber(root,map);
    }

    private int robber(TreeNode root, HashMap<TreeNode, Integer> map) {
        if (root==null){
            return 0;
        }
        if (map.containsKey(root)){
            return map.get(root);
        }
        int money=root.val;
        if (root.left!=null){
            money+=robber(root.left.left,map)+robber(root.left.right,map);
        }
        if (root.right!=null){
            money+=robber(root.right.left,map)+robber(root.right.right,map);
        }
        int result=Math.max(money,robber(root.left,map)+robber(root.right,map));
        map.put(root,result);
        return result;
    }
    /**
     * 再进行优化：
     *
     * 每个节点可选择偷或者不偷两种状态，根据题目意思，相连节点不能一起偷
     *
     * 当前节点选择偷时，那么两个孩子节点就不能选择偷了
     * 当前节点选择不偷时，两个孩子节点只需要拿最多的钱出来就行(两个孩子节点偷不偷没关系)
     * 我们使用一个大小为 2 的数组来表示 int[] res = new int[2] 0 代表不偷，1 代表偷
     * 任何一个节点能偷到的最大钱的状态可以定义为
     *
     * 当前节点选择不偷：当前节点能偷到的最大钱数 = 左孩子能偷到的钱 + 右孩子能偷到的钱
     * 当前节点选择偷：当前节点能偷到的最大钱数 = 左孩子选择自己不偷时能得到的钱 + 右孩子选择不偷时能得到的钱 + 当前节点的钱数
     * 表示为公式如下
     *
     * root[0] = Math.max(rob(root.left)[0], rob(root.left)[1]) + Math.max(rob(root.right)[0], rob(root.right)[1])
     * root[1] = rob(root.left)[0] + rob(root.right)[0] + root.val;
     *
     */
    public int rob3(TreeNode root){
        int[] result=robInternal(root);
        return Math.max(result[0],result[1]);
    }

    private int[] robInternal(TreeNode root) {
        if (root==null){
            return new int[2];
        }
        int[] result=new int[2];
        int[] left=robInternal(root.left);
        int[] right=robInternal(root.right);
        result[0]=Math.max(left[0],left[1])+Math.max(right[0],right[1]);
        result[1]=left[0]+right[0]+root.val;
        return result;
    }
}
