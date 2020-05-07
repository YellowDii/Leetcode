package leetcode.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Number_102 {
    /**
     * 102.二叉树的层次遍历（binary-tree-level-order-traversal）
     *
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
     *
     *
     * 示例：
     * 二叉树：[3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其层次遍历结果：
     *
     * [
     *   [3],
     *   [9,20],
     *   [15,7]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            this.val=x;
        }
    }
    //emmmm 超时了！！！！
    //极端情况下 比如右子树全为null 层数特别多时
    //我怎么这么蠢 这个题都能超时 重写下...
    private int maxLayer(TreeNode root) {
        if (root==null){
            return 0;
        }
        return Math.max(maxLayer(root.left),maxLayer(root.right))+1;
    }
    public List<List<Integer>> levelOrder2(TreeNode root) {
        Queue<TreeNode> queue=new LinkedList<>();
        List<List<Integer>> res=new LinkedList<>();
        if (root!=null){
            queue.add(root);
            List<Integer> list=new LinkedList<>();
            list.add(root.val);
            res.add(list);
        }
        int count=1;//表达一次遍历几个节点 第二层遍历两个 第三层遍历4个...
        int layers=maxLayer(root);
        while (!queue.isEmpty()&&layers>0){
            List<Integer> list=new LinkedList<>();
            int cur_count=count;//当前层遍历个数
            layers--;
           for (;cur_count>0&&layers>=0;cur_count--){
                TreeNode cur=queue.poll();
                if (cur==null){
                    queue.add(null);
                    queue.add(null);
                    continue;
                }
                //左子树
                if (cur.left!=null){
                    queue.add(cur.left);
                    list.add(cur.left.val);
                }else {
                    queue.add(null);
                }
                //右子树
                if(cur.right!=null) {
                    queue.add(cur.right);
                    list.add(cur.right.val);
                }else {
                    queue.add(null);
                }
            }
            if (list.size()>0){
                res.add(list);
            }
            count*=2;
        }
        return res;
    }
    //上面每次遍历到空节点时 会加两个空左右子节点 既浪费空间又浪费时间
    public List<List<Integer>> levelOrder3(TreeNode root){
        List<List<Integer>> res=new LinkedList<>();
        if (root==null){
            return res;
        }

        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        int levels=0;
        while (!queue.isEmpty()){
            int size=queue.size();
            res.add(new LinkedList<>());
            for (int i=0;i<size;i++){
                TreeNode cur=queue.poll();
                res.get(levels).add(cur.val);
                if (cur.left!=null){
                    queue.add(cur.left);
                }
                if (cur.right!=null){
                    queue.add(cur.right);
                }
            }
            levels++;
        }
        return res;
    }


    //递归
    List<List<Integer>> levels = new ArrayList<List<Integer>>();

    public void helper(TreeNode node, int level) {
        // start the current level
        if (levels.size() == level)
            levels.add(new ArrayList<Integer>());

        // fulfil the current level
        levels.get(level).add(node.val);

        // process child nodes for the next level
        if (node.left != null)
            helper(node.left, level + 1);
        if (node.right != null)
            helper(node.right, level + 1);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return levels;
        helper(root, 0);
        return levels;
    }
}
