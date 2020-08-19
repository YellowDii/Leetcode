package leetcode.Tree;

import javax.xml.soap.Node;
import java.util.Stack;

public class preorderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    //非递归
    public void preorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        // 存放临时节点
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            // 一直向左子树访问，直到节点为空
            while (root != null) {
                visitNode(root);
                stack.push(root);
                root = root.left;
            }
            // 将节点出栈并访问该节点的右子树
            root = stack.pop().right;
        }
    }

    private void visitNode(TreeNode root) {
        System.out.print(root.val+" ");
    }

    //递归的
    public void preorderTraversal2(TreeNode root){
        if (root!=null){
            visitNode(root);
            preorderTraversal2(root.left);
            preorderTraversal2(root.right);
        }
    }

    //还有Morris
    public  void morrisPre(TreeNode root){
        if (root==null){
            return;
        }
        TreeNode cur = root;
        TreeNode cur_pre = null;//cur节点的左子树的最右节点，即当前节点的前置节点
        while (cur!=null){
            cur_pre=cur.left;
            if (cur_pre!=null){
                while (cur_pre.right!=null&&cur_pre.right!=cur){
                    cur_pre=cur_pre.right;
                }
                if (cur_pre.right == null){
                    cur_pre.right=cur;
                    visitNode(cur);
                    cur=cur.left; //当前node向左孩子移动
                    continue;
                }else {
                    //最右点的右指针已经指向当前节点
                    cur_pre.right=null;
                }
            }else {
                visitNode(cur);
            }
        }
    }
}
