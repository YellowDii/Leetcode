package DynamicPrograming;

/*
1130.叶值得最小代价生成树 （minimum-cost-tree-from-leaf-values）
给你一个正整数数组 arr，考虑所有满足以下条件的二叉树：

每个节点都有 0 个或是 2 个子节点。
数组 arr 中的值与树的中序遍历中每个叶节点的值一一对应。（知识回顾：如果一个节点有 0 个子节点，那么该节点为叶节点。）
每个非叶节点的值等于其左子树和右子树中叶节点的最大值的乘积。
在所有这样的二叉树中，返回每个非叶节点的值的最小可能总和。这个和的值是一个 32 位整数。

 

示例：

输入：arr = [6,2,4]
输出：32
解释：
有两种可能的树，第一种的非叶节点的总和为 36，第二种非叶节点的总和为 32。

    24            24
   /  \          /  \
  12   4        6    8
 /  \               / \
6    2             2   4
 

提示：

2 <= arr.length <= 40
1 <= arr[i] <= 15
答案保证是一个 32 位带符号整数，即小于 2^31。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-cost-tree-from-leaf-values
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_1130 {
    String name = "1130.叶值得最小代价生成树 （minimum-cost-tree-from-leaf-values）";
    //实际上相当于求数之间的乘积，然后求和
    // 例如 6，2，4，7

    //递归 超时
    public int mctFromLeafValues(int[] arr) {
        return count(arr,0,arr.length-1);
    }

    int count(int[] arr, int start, int end) {
        if (start>=end)
            return 0;
        int res=0;

        int min=0; //分支最小和+根节点
        if (end-start==1){
            min=arr[start]*arr[end];
        }
        //算分支最小和
        if (end-start>=2){
            min=Integer.MAX_VALUE;
            for (int i = start ; i <= end - 1; i++) {
                int left=count(arr,start,i);
                int right=count(arr,i+1,end);
                int l_max=0;
                int j=start;
                while (j<=i){
                    l_max=Math.max(l_max,arr[j++]);
                }
                int r_max=0;
                j=i+1;
                while (j<=end){
                    r_max=Math.max(r_max,arr[j++]);
                }
                min=Math.min(min, left+right+l_max*r_max);
            }
        }

        res+=min;
        return res;
    }
    Integer memo[][];
    //用上备忘录 但是仍然很慢 21ms
    public int mctFromLeafValues2(int[] arr) {
        memo=new Integer[arr.length][arr.length];
        return count(arr,0,arr.length-1);
    }
    int count2(int[] arr, int start, int end) {
        if (start>=end)
            return 0;

        if (memo[start][end]!=null)
            return memo[start][end];

        int min=0; //分支最小和+根节点
        if (end-start==1){
            min=arr[start]*arr[end];
            memo[start][end]=min;
            return min;
        }
        //有分支情况
        if (end-start>=2){
            min=Integer.MAX_VALUE;
            for (int i = start ; i <= end - 1; i++) {
                int left=count2(arr,start,i);
                int right=count2(arr,i+1,end);
                int l_max=0;
                int j=start;
                while (j<=i){
                    l_max=Math.max(l_max,arr[j++]);
                }
                int r_max=0;
                j=i+1;
                while (j<=end){
                    r_max=Math.max(r_max,arr[j++]);
                }
                min=Math.min(min, left+right+l_max*r_max);
            }
        }
        memo[start][end]=min;
        return min;
    }
}
