package SwordOffer;

import javax.jms.Session;
import java.util.ArrayList;
import java.util.List;

public class Test025 {
    /**
     * 二叉树的树结点
     */
    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }

    /*
     * 输入一棵二叉树和一个整数， 打印出二叉树中结点值的和为输入整数的所有路径。
     * 从树的根结点开始往下一直到叶销点所经过的结点形成一条路径。
     *
     */
    public static void findPath(BinaryTreeNode root, int expectedSum) {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            findPath(root, 0, expectedSum, list);
        }
    }

    //默认各个节点值都是正数
    private static void findPath(BinaryTreeNode root, int curSum, int expectedSum, List<Integer> result) {
        if (root != null) {
            //入队
            curSum += root.value;
            result.add(root.value);
            //判断
            if (curSum < expectedSum) {
                findPath(root.left, curSum, expectedSum, result);
                findPath(root.right, curSum, expectedSum, result);
            } else if (curSum == expectedSum) {
                if (root.left == null && root.right == null) {
                    System.out.println(result);
                }
            }
            result.remove(result.size() - 1);
        }
    }

    public static void main(String[] args) {
        //            10
        //         /      \
        //        5        12
        //       /\
        //      4  7
        BinaryTreeNode root = new BinaryTreeNode();
        root.value = 10;
        root.left = new BinaryTreeNode();
        root.left.value = 5;
        root.left.left = new BinaryTreeNode();
        root.left.left.value = 4;
        root.left.right = new BinaryTreeNode();
        root.left.right.value = 7;
        root.right = new BinaryTreeNode();
        root.right.value = 12;
        
        // 有两条路径上的结点和为22
        System.out.println("findPath(root, 22);");
        findPath(root, 22);
    }
}
