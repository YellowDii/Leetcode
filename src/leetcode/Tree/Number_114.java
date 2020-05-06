package leetcode.Tree;

import java.util.LinkedList;
import java.util.List;

public class Number_114 {
    /**
     * 114.二叉树展开为链表（flatten-binary-tree-to-linked-list）
     *
     * 给定一个二叉树，原地将它展开为链表。
     *
     * 例如，给定二叉树
     *
     *     1
     *    / \
     *   2   5
     *  / \   \
     * 3   4   6
     * 将其展开为：
     *
     * 1
     *  \
     *   2
     *    \
     *     3
     *      \
     *       4
     *        \
     *         5
     *          \
     *           6
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){

        }
        TreeNode(int x){
            val=x;
        }
        TreeNode(int x,TreeNode l,TreeNode r){
            val=x;
            this.left=l;
            this.right=r;
        }
    }
    //递归前序遍历
    public void flatten(TreeNode root) {
        treeToLinkedList(root);
    }

    private TreeNode treeToLinkedList(TreeNode root) {
        if (root==null){
            return null;
        }
        TreeNode right=treeToLinkedList(root.right);
        TreeNode left=treeToLinkedList(root.left);
        root.right=left;
        root.left=null;
        TreeNode cur=root;
        while (cur.right!=null){
            cur=cur.right;
        }
        cur.right=right;
        return root;
    }
}
