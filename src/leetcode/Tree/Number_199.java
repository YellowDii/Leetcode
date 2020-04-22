package leetcode.Tree;

import java.util.*;

public class Number_199 {
    /**
     * 199.二叉树的右视图(binary-tree-right-side-view)
     *
     * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     *
     * 示例:
     *
     * 输入: [1,2,3,null,5,null,4]
     * 输出: [1, 3, 4]
     * 解释:
     *
     *    1            <---
     *  /   \
     * 2     3         <---
     *  \     \
     *   5     4       <---
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int value){
            this.val=value;
        }
    }

    //这个有bug  将范围内的null也算节点的话 它算完全二叉树 不应该这个思想
    //应该遍历一层 返回最后边的不为null 参考方法2
    public static List<Integer> rightSideView(TreeNode root) {
        //其实在每层的[left:end]之间 从后向前遍历的第一个非null就是右视图
        List<TreeNode> list=new ArrayList<>();
        List<Integer> result=new ArrayList<>();
        inList(root,list);
        int start=0,end=1;//[start,end) 表示一层
        int layers=0,len=list.size();
        while (len>0){
            len/=2;
            layers++;
        }
        for (int i=0;i<layers;i++){
            int j=end-1>list.size()-1?list.size()-1:end-1;
            for (;j>=start;j--){
                TreeNode k=list.get(j);
                if (k!=null){
                    result.add(k.val);
                    break;
                }
            }
            start=end;
            end=(end+1)*2-1;
        }
        return result;
    }

    public static void inList(TreeNode head,List<TreeNode> list){
        Queue<TreeNode> queue=new LinkedList<>();
        if (head!=null){
            queue.add(head);
        }
        while (!queue.isEmpty()){
            TreeNode cur=queue.poll();
            list.add(cur);
            if (cur!=null){
                queue.add(cur.left);
                queue.add(cur.right);
            }
        }
    }
    //从右向左的层次遍历 然后找右边的点
    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if(root == null){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            //获取当前层的节点数量
            int size = queue.size();
            //遍历当前层所有的节点 直到一层遍历完才进行下一层
            for(int i = 0; i<size; i++){
                TreeNode temp = queue.poll();
                //将当前层第一个节点放入结果中
                if(i == 0)result.add(temp.val);
                //从右到左来记录下一层
                if(temp.right != null){
                    queue.add(temp.right);
                }
                if(temp.left != null){
                    queue.add(temp.left);
                }
            }

        }
        return result;
    }

}
