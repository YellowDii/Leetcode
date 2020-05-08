package leetcode.Tree;

import java.util.*;

public class Number_94 {
    /**
     * 94.二叉树的中序遍历（binary-tree-inorder-traversal）
     * 给定一个二叉树，返回它的中序 遍历。
     *
     * 示例:
     *
     * 输入: [1,null,2,3]
     *    1
     *     \
     *      2
     *     /
     *    3
     *
     * 输出: [1,3,2]
     * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    //写的不美观.....
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res=new LinkedList<>();
        if (root==null){
            return res;
        }
        Map<TreeNode,Boolean> map_visited=new HashMap<>();//保存已经输出的节点
        Stack<TreeNode> stack=new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()){
            TreeNode cur=stack.pop();
            if (map_visited.containsKey(cur)){
                continue;
            }
            if (cur.left==null||map_visited.containsKey(cur.left)){
                res.add(cur.val);
                map_visited.put(cur,true);
                if (cur.right!=null&&!map_visited.containsKey(cur.right)){
                    stack.push(cur.right);
                }
            }else {
                if (cur.right!=null&&!map_visited.containsKey(cur.right)){
                    stack.push(cur.right);
                }
                if (!map_visited.containsKey(cur)){
                    stack.push(cur);
                }
                if (!map_visited.containsKey(cur.left)){
                    stack.push(cur.left);
                }
            }
        }
        return res;
    }
    //官方写的比我美观多了
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }
    //Morris遍历
    public List<Integer> inorderTraversal3(TreeNode root){
        List<Integer> res=new LinkedList<>();
        TreeNode cur=root;
        TreeNode pre;
        while (cur!=null){
            if (cur.left==null){
                res.add(cur.val);
                cur=cur.right;
            }else {
                TreeNode tmp=cur.left;
                pre=cur;
                cur=cur.left;
                pre.left=null;
                while (tmp.right!=null){
                    tmp=tmp.right;
                }
                tmp.right=pre;
            }
        }
        return res;
    }

}
