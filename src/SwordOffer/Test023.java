package SwordOffer;

import java.util.LinkedList;
import java.util.Queue;

public class Test023 {
    /**
     * 二叉树的树结点
     */
    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }
    /**
     * 从上往下打印出二叉树的每个结点，向一层的结点按照从左往右的顺序打印。
     * 例如下的二叉树，
     *       8
     *    /    \
     *   6     10
     *  /  \   / \
     * 5   7  9  11
     * 则依次打印出8、6、10、5、3 、9、11.
     *
     * @param root 树的结点
     */
    public static void printFromToBottom(BinaryTreeNode root) {
        //可以用队列
        if (root!=null){
            Queue<BinaryTreeNode> list=new LinkedList<>();
            list.add(root);
            BinaryTreeNode cur;
            while (!list.isEmpty()){
                cur=list.remove();
                System.out.print(cur.value+" ");
                if (cur.left!=null){
                    list.add(cur.left);
                }
                if (cur.right!=null){
                    list.add(cur.right);
                }
            }
        }
    }
}
