package leetcode;

/**
 * 在一一共有n冻房屋，装修小工Q需要把这些房屋都粉刷为红色，绿色或者蓝色
 * 为了整体的协调性，小Q需要保证相邻的两栋房屋都是不一样的颜色，对于第i栋楼，与它相邻的房屋分别是第i-1栋和第i+1栋，第1栋和第n栋不相邻
 * 第i栋房屋  小Q粉刷为红色需要r[i]的代价，粉刷为绿色需要g[i]的代价，蓝色需要b[i]的代价
 * 现在小Q想知道粉刷完所有的房屋  并且保证相邻的房屋不是同一颜色所需要的最小代价是多少
 *
 * 输入描述
 * 输入的一行包括一个正整数n(1<=n<=20),表示房屋的栋数
 * 接下来的n行 每行三个整数r[i],g[i],b[i](1<=r[i],g[i],b[i]<=1000)
 *
 *     输出一个整数 表示最少需要的代价
 *
 *     实例：
 *     输入
 *     4
 *     100 77 73
 *     41 74 83
 *     9 91 93
 *     50 16 31
 *     输出
 *     172
 *
 *     说明 依次染为蓝绿红绿
 */
public class Test001 {
    //主方法 n代表输入值 array代表后面的颜色代价 r g b
    public int best(int n,int[][] array){
        TreeNode node1=new TreeNode(array[0][0],0);
        TreeNode node2=new TreeNode(array[0][1],1);
        TreeNode node3=new TreeNode(array[0][2],2);
        TreeRoot t=new TreeRoot(node1,node2,node3);
        build(node1,0,array,n-1);
        build(node2,0,array,n-1);
        build(node3,0,array,n-1);
        return t.countMin();
    }
    public void build(TreeNode node,int layer,int[][] array,int maxlayer){
        if (node==null)
            return;
        if (layer<maxlayer){
            node.left=new TreeNode(array[layer+1][(node.color+1)%3],(node.color+1)%3);
            node.right=new TreeNode(array[layer+1][(node.color+2)%3],(node.color+2)%3);
            build(node.left,layer+1,array,maxlayer);
            build(node.right,layer+1,array,maxlayer);
            return;
        }else {
            node.left=null;
            node.right=null;
            return;
        }
    }
    class TreeRoot{
        TreeNode node1;
        TreeNode node2;
        TreeNode node3;

        public TreeRoot(TreeNode node1, TreeNode node2, TreeNode node3) {
            this.node1 = node1;
            this.node2 = node2;
            this.node3 = node3;
        }

        public int countMin(){
            return Math.min(Math.min(node1.countMin(),node2.countMin()),node3.countMin());
        }

    }
    class TreeNode{
        int value;
        int color;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value,int color) {
            this.value = value;
            this.color = color;
        }

        public int countMin(){
            if (left==null||right==null)
                return value;
            return Math.min(value+left.countMin(),value+right.countMin());
        }

    }
}
