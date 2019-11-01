package DynamicPrograming;

import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.List;

/**
 * 动态规划系列
 * 95.不同的二叉搜索树 Ⅱ
 *给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 *
 * 示例:
 *
 * 输入: 3
 * 输出:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释:
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_95 {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {val=x;}
    }
    //该题与96题相似，不过他要生成二叉树
    //如果需要计算时间和空间复杂度，我们需要知道n个数对应的结构数
    //结构数为卡特兰数，
    public List<TreeNode> generateTrees(int n) {
        if (n==0){
            return new LinkedList<TreeNode>();
        }
        return generateTrees(1,n);
    }
    public LinkedList<TreeNode> generateTrees(int start,int end){
        //对于总树和来说 要经常进行插入操作 所以用LinkedList
        LinkedList<TreeNode> trees=new LinkedList<TreeNode>();
        if (start > end){
            trees.add(null);
            return trees;
        }

        //选择根节点
        for (int i=start;i<=end;i++){
            //以该节点为根时 左子树的所有情况
            LinkedList<TreeNode> left_trees=generateTrees(start,i-1);
            //以该节点为根时 右子树的所有情况
            LinkedList<TreeNode> right_trees=generateTrees(i+1,end);

            for (TreeNode l:left_trees){
                for (TreeNode r:right_trees){
                    TreeNode current_tree=new TreeNode(i);
                    current_tree.left=l;
                    current_tree.right=r;
                    trees.add(current_tree);
                }
            }
        }

        return trees;
    }
    //可以将上述的递归转为动态规划
    //我们知道相同数量不同数字所构成的结构是一样的，只是数值不一样，所以我们需要一个带偏偏差的克隆
    private TreeNode cloneWithOffset(TreeNode t,int offset){
        if (t==null)
            return null;

        TreeNode node=new TreeNode(t.val+offset);
        node.left = cloneWithOffset(t.left,offset);
        node.right = cloneWithOffset(t.right,offset);
        return node;
    }

    //然后我们需要求得长度为1，2....n的情况，
    //dp[i]表示n=i的所有树的情况
    public  List<TreeNode> generateTrees2(int n){
        LinkedList<TreeNode>[] dp=new LinkedList[n+1];
        dp[0] = new LinkedList<TreeNode>();
        if (n==0){
            return dp[0];
        }
        dp[0].add(null);
        for (int i=1;i<=n;i++){
            dp[i] = new LinkedList<TreeNode>();
            for (int root=1;root<=i;root++){
                int left_num=root-1;
                int right_num=i-root;
                for (TreeNode left_t:dp[left_num]){
                    for (TreeNode right_t:dp[right_num]){
                        //left_t是没有偏差的 一直从1到root
                        TreeNode node=new TreeNode(root);
                        node.left=left_t;
                        //右子树有偏差 偏差为root+1-1=root
                        node.right=cloneWithOffset(right_t,root);
                        dp[i].add(node);
                    }
                }
            }
        }
        return dp[n];
    }
}
