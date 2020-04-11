package SwordOffer;

public class Test018 {
    /**
     * 二叉树的树结点
     */
    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }

    /**
     * 输入两棵二叉树A和B，判断B是不是A的子结构。
     * 该方法是在A树中找到一个与B树的根节点相等的元素的结点，
     * 从这个相等的结点开始判断树B是不是树A的子结构，如果找到其的一个就返回，
     * 否则直到所有的结点都找完为止。
     * @param root1 A
     * @param root2 B
     * @return true or false
     */
    public static boolean hasSubtree(BinaryTreeNode root1, BinaryTreeNode root2){
        if (root1==root2){
            return true;
        }
        if (root2==null){
            return true;
        }
        if (root1==null){
            return false;
        }
        boolean res=false;
        if (root1.value==root2.value){
            res=match(root1,root2);
        }
        //如果匹配直接返回
        if (res){
            return res;
        }
        //不匹配
        return hasSubtree(root1.left,root2)||hasSubtree(root1.right,root2);
    }

    private static boolean match(BinaryTreeNode root1, BinaryTreeNode root2) {
        //一一比较
        if (root1==root2){
            return true;
        }
        if (root2==null){
            return true;
        }
        if (root1==null){
            return false;
        }
        if (root1.value==root2.value){
            return match(root1.left,root2.left)&&match(root1.right,root2.right);
        }
        return false;
    }
}
