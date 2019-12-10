package DynamicPrograming;

import java.util.HashSet;
import java.util.Set;

/*
813.最大平均值和的分组（largest-sum-of-averages）

我们将给定的数组 A 分成 K 个相邻的非空子数组 ，我们的分数由每个子数组内的平均值的总和构成。计算我们所能得到的最大分数是多少。

注意我们必须使用 A 数组中的每一个数进行分组，并且分数不一定需要是整数。

示例:
输入:
A = [9,1,2,3,9]
K = 3
输出: 20
解释:
A 的最优分组是[9], [1, 2, 3], [9]. 得到的分数是 9 + (1 + 2 + 3) / 3 + 9 = 20.
我们也可以把 A 分成[9, 1], [2], [3, 9].
这样的分组得到的分数为 5 + 2 + 6 = 13, 但不是最大值.
说明:

    1. 1 <= A.length <= 100.
    2. 1 <= A[i] <= 10000.
    3. 1 <= K <= A.length.
答案误差在 10^-6 内被视为是正确的。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/largest-sum-of-averages
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_813 {
    String largest_sum_of_averages="813.最大平均值和的分组（largest-sum-of-averages）";
    int res;
    public double largestSumOfAverages(int[] A, int K) {
        //先初始化
        double[][] dp=new double[A.length][K+1];
        int k=1;
        for (int i=A.length-1;i>=A.length-K+1;i--){
            if (i!=A.length-1){
                dp[i][k]=A[i]+dp[i+1][k-1];
                k++;
            }else
                dp[i][k++]=A[i];
        }
        for (int i=A.length-1;i>=0;i--){
            dp[i][1]=0;
            for (int j=i;j<A.length;j++){
                dp[i][1]+=A[j];
            }
            dp[i][1]/=(A.length-i);
        }
        return arrange(A,dp,K,0);
    }

    private double arrange(int[] a, double[][] dp, int k, int i) {
        if (i>=a.length)
            return 0;
        if (k<=0||k>a.length-i)
            return 0;
        if (dp[i][k]!=0.0)
            return dp[i][k];
        double res=0;
        double cur_sum=0;
        double cur_avg=0;
        for (int j=i;j<a.length;j++){
            cur_sum+=a[j];
            cur_avg=cur_sum/(j-i+1);
            res=Math.max(cur_avg+arrange(a,dp,k-1,j+1),res);
        }
        return dp[i][k]=res;
    }

    //下面这种效率高一点 。。。 不知道为啥 思路没变
    public double largestSumOfAverages2(int[] A, int K) {
        double[][] dp=new double[A.length][K+1];
        int k=1;
        for (int i=A.length-1;i>=A.length-K+1;i--){
            if (i!=A.length-1){
                dp[i][k]=A[i]+dp[i+1][k-1];
                k++;
            }else
                dp[i][k++]=A[i];
        }
        return arrange2(A,dp,K,0);
    }

    private double arrange2(int[] a, double[][] dp, int k, int i) {
        if (i>=a.length)
            return 0;
        if (k<=0||k>a.length-i)
            return 0;
        if (dp[i][k]!=0.0)
            return dp[i][k];
        double res=0;
        double cur_sum=0;
        double cur_avg=0;
        if (k!=1){
            for (int j=i;j<a.length;j++){
                cur_sum+=a[j];
                cur_avg=cur_sum/(j-i+1);
                res=Math.max(cur_avg+arrange(a,dp,k-1,j+1),res);
            }
        }else {
            for (int j=i;j<a.length;j++){
                cur_sum+=a[j];
                cur_avg=cur_sum/(j-i+1);
            }
            return dp[i][k]=cur_avg;
        }

        return dp[i][k]=res;
    }
}
