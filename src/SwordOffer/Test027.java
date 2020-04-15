package SwordOffer;

public class Test027 {
    /**
     * 题目：输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
     * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
     */
    /**
     * 二叉树的树结点
     */
    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }

    /**
     * 转换函数
     * @param root
     * @return 返回双向链表的头结点
     */
    public static BinaryTreeNode convert(BinaryTreeNode root){
        // 用于保存处理过程中的双向链表的尾结点
        BinaryTreeNode[] lastNode=new BinaryTreeNode[1];
        convertNode(root,lastNode);

        BinaryTreeNode head=lastNode[0];
        while (head!=null&&head.left!=null){
            head=head.left;
        }
        return head;
    }
    /**
     * 链表表转换操作
     *
     * @param node     当前的根结点
     * @param lastNode 已经处理好的双向链表的尾结点，使用一个长度为1的数组，类似C++中的二级指针
     */
    private static void convertNode(BinaryTreeNode node, BinaryTreeNode[] lastNode) {
        if (node!=null){
            if (node.left!=null){
                convertNode(node.left,lastNode);
            }
            node.left=lastNode[0];
            if (lastNode[0]!=null){
                lastNode[0].right=node;
            }
            //更新尾结点
            lastNode[0]=node;
            if (node.right!=null){
                convertNode(node.right,lastNode);
            }
        }
    }
}
