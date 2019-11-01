package test;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 输入:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * 输出: ["1->2->5", "1->3"]
 *
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_257 {
      //Definition for a binary tree node.
      class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
     }
    //递归做法
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new LinkedList<String>();
        construct_path(root,"",list);
        return list;
    }
    public void construct_path(TreeNode node,String path,List<String> list){
          if (node!=null){
              path+=node.val+"";
              if (node.left==null&&node.right==null)
                  list.add(path);
              else {
                  path+="->";
                  construct_path(node.left,path,list);
                  construct_path(node.right,path,list);
              }
          }
    }
    //利用栈做迭代
    public List<String> binaryTreePaths2(TreeNode root) {
        LinkedList<String> paths = new LinkedList();
        if (root == null)
            return paths;

        Stack<TreeNode> node_stack = new Stack<TreeNode>();
        Stack<String> path_stack = new Stack<String>();
        node_stack.push(root);
        path_stack.push(root.val+"");
        TreeNode node;
        String path;
        while (!node_stack.isEmpty()) {
            node = node_stack.pop();
            path = path_stack.pop();
            if ((node.left == null) && (node.right == null))
                paths.push(path);
            if (node.left != null) {
                node_stack.push(node.left);
                path_stack.push(path + "->" + node.left.val);
            }
            if (node.right != null) {
                node_stack.push(node.right);
                path_stack.push(path + "->" + node.right.val);
            }
        }
        return paths;
    }

}
