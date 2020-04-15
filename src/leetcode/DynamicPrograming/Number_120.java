package leetcode.DynamicPrograming;

import java.util.List;

/**
 * 动态规划系列
 * 120. 三角形最小路径和
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 * 例如，给定三角形：
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_120 {
    //将每一行的最小数相加
    //错误解答！！！！ >.<
    // 题目条件没读清，它是路径，每一步只能移动到下一行中相邻的结点上。
//    public int minimumTotal(List<List<Integer>> triangle) {
//        int sum=0;
//        for (List<Integer> l:triangle){
//            int min=l.get(0);
//            for (int n:l){
//                if (n<min)
//                    min=n;
//            }
//            sum+=min;
//        }
//        return sum;
//    }

    //动态规划
    //dp[i][j]=min(dp[i-1][j]+triangle[i][j],dp[i-1][j-1]+triangle[i][j])
    //当处在左边界与有边界需要单独处理，相当与只跟[i-1][j]（左边界）或[i-1][j-1]（右边界）相邻
    public int minimumTotal2(List<List<Integer>> triangle){
        int len=triangle.size();
        int[][] dp=new int[len][len];
        dp[0][0]=triangle.get(0).get(0);
        //计算dp数组
        for (int i=1;i<len;i++){
            for (int j=0;j<i+1;j++){
                int n=triangle.get(i).get(j);
                if (j==i)
                    dp[i][j]=dp[i-1][j-1]+n;
                else if (j==0)
                    dp[i][j]=dp[i-1][j]+n;
                else
                    dp[i][j]=Math.min(dp[i-1][j]+n,dp[i-1][j-1]+n);
            }
        }
        //取最终结果
        int res=dp[len-1][0];
        for (int i=1;i<len;i++){
            res=res>dp[len-1][i]?dp[len-1][i]:res;
        }
        return res;
    }
    //递归地向上搜索
    //需要注意的是当向上搜索时，如果碰到左上方与右上方相等时的情况。
    //这个处理方式在倒数第二个测试用例会超时，需要进行优化
    public int minimumTotal3(List<List<Integer>> triangle){
        int len=triangle.size();
        int[] res=new int[len];
        //自底向上搜索
        for (int i=0;i<len;i++){
            res[i]=triangle.get(len-1).get(i);
        }
        for (int k=0;k<len;k++){
            int i=len-1;//记录行数
            if (i>=1){
               res[k]+=count(i,k,triangle);
            }
        }
        int min=res[0];
        for (int i=1;i<res.length;i++){
            if (min>res[i])
                min=res[i];
        }
        return min;
    }
    //向上计算路径最小值,不包括triangle[i][j]
    public int count(int i,int j,List<List<Integer>> triangle){
        if (i==1)
            return triangle.get(0).get(0);
        if (i>1){
            if (j==0){
                return triangle.get(i-1).get(j)+count(i-1,j,triangle);
            }
            else if (j==i){
                return triangle.get(i-1).get(--j)+count(i-1,j,triangle);
            }else {
                int left=triangle.get(i-1).get(j-1);
                int right=triangle.get(i-1).get(j);
                return Math.min(left+count(i-1,j-1,triangle),right+count(i-1,j,triangle));
            }
        }
        return 0;
    }

    //继续动态规划
    //可以自底向上也可以自顶向下  自底向上更简便点
    //这样空间消耗就是O(n)
    public int minimumTotal(List<List<Integer>> triangle){
        //与方法二类似 但是我们需要压缩dp数组空间
        //所以需要随时修改，以便我们自底向上的逐行遍历
        //dp[i]代表
        int row =triangle.size();
        int[] dp=new int[row];
        for (int level=0;level<=row-1;level++){
            for (int i=level;i>=0;i--){
                int n=triangle.get(level).get(i);
                if (i==0)
                    dp[i]=dp[i]+n;
                else if (i==level)
                    dp[i]=dp[i-1]+n;
                else {
                    dp[i]=Math.min(dp[i],dp[i-1])+n;
                }
            }
        }
        int min=dp[0];
        for (int i:dp){
            if (min>i)
                min=i;
        }
        return min;
    }
}
