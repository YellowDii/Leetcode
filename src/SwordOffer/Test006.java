package SwordOffer;

public class Test006 {
    /**
     * 题目：
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二节树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     */

    /**
     * 二叉树节点类
     */
    public static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode(int value){
            this.value=value;
        }
    }

    /**
     * 递归构造函数
     * @param preorder 前序
     * @param ps 前序 遍历开始位置
     * @param pe 前序 遍历结束为止
     * @param inorder 中序
     * @param is 中序 遍历开始位置
     * @param ie 中序 遍历结束为止
     * @return
     */
    public static TreeNode construct(int[] preorder,int ps,int pe,int[] inorder,int is,int ie){
        if (ps>pe){
            return null;
        }
        //前序第一个就是根节点
        int value=preorder[ps];
        //然后在中序找根节点,从is开始
        int index=is;
        while (index<=ie&&inorder[index]!=value){
            index++;
        }
        if (index>ie){
            throw new RuntimeException("invalid input");
        }
        TreeNode root=new TreeNode(value);
        //先确定元素个数，左子树元素个数为index-is+1 个
        //因为是左子树 所以前序的开头在ps基础上+1即可，pe根据个数来算
        //中序的开头依然为is 尾部为index-1
        root.left=construct(preorder, ps + 1, ps + index - is, inorder, is, index - 1);
        //元素个数为ie-index
        //右子树 前序尾部为pe，所以开头为pe-ie+index-1 也可以写成ps-ie+index+1
        //中序尾部为ie，开头为index+1
        root.right=construct(preorder, ps + index - is + 1, pe, inorder, index + 1, ie);
        return root;
    }
    //构造函数
    public  static TreeNode construct(int[] preorder,int[] inorder){
        if (preorder == null || inorder == null || preorder.length != inorder.length || inorder.length < 1) {
            return null;
        }

        return construct(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }
    //重写一遍
    public static TreeNode construct2(int[] preorder,int ps,int pe,int[] inorder,int is,int ie){
        if (ps>pe){
            return null;
        }
        TreeNode root=new TreeNode(preorder[ps]);
        int index=is;
        while (index<=ie&&inorder[index]!=root.value){
            index++;
        }
        root.left=construct2(preorder,ps+1,ps+index-is,inorder,is,index-1);
        root.right=construct2(preorder,ps+index-is+1,pe,inorder,index+1,ie);
        return root;
    }
}
