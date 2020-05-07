package leetcode.Tree;

import java.util.HashMap;

public class Number_105 {
    /**
     * 105.从前序与中序遍历序列构造二叉树（construct-binary-tree-from-preorder-and-inorder-traversal）
     * 根据一棵树的前序遍历与中序遍历构造二叉树。
     *
     * 注意:
     * 你可以假设树中没有重复的元素。
     *
     * 例如，给出
     *
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     * 返回如下的二叉树：
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            this.val=x;
        }
    }
    //递归
    int preIndex=0;
    int[] preOrder;
    int[] inOrder;
    HashMap<Integer,Integer> map;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preOrder=preorder;
        inOrder=inorder;
        map=new HashMap<>();
        int index=0;
        for (int node:inOrder){
            map.put(node,index++);
        }
        return build(0,inOrder.length);
    }

    private TreeNode build(int start, int end) {
        if (end<=start){
            return null;
        }
        TreeNode root=new TreeNode(preOrder[preIndex++]);
        int indexRoot=map.get(root.val);
        root.left=build(start,indexRoot);
        root.right=build(indexRoot+1,end);
        return root;
    }
}
