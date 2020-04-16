package SwordOffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Test039 {
    /**
     * 题目：
     * 求二叉树深度
     */
    private static class BinaryTreeNode {
        int val;
        BinaryTreeNode left;
        BinaryTreeNode right;

        public BinaryTreeNode() {
        }

        public BinaryTreeNode(int val) {
            this.val = val;
        }
    }

    //递归
    public static int deep(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = deep(root.left);
        int right = deep(root.right);
        return Math.max(left + 1, right + 1);
    }

    //层次遍历
    public static int deep2(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<BinaryTreeNode> list=new LinkedList<>();
        list.add(root);
        int count=1;
        //start~~end 代表着一层
        int start=0,end=list.size();
        while (!list.isEmpty()){
            BinaryTreeNode node=list.poll();
            start++;
            if (node.left!=null){
                list.add(node.left);
            }
            if (node.right!=null){
                list.add(node.right);
            }
            if(start==end){
                end=list.size();
                start=0;
                count++;
            }
        }
        return count;
    }
}
