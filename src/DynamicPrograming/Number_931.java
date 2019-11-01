package DynamicPrograming;
/*

动态规划系列
931，下降路径最小和
给定一个方形整数数组 A，我们想要得到通过 A 的下降路径的最小和。

下降路径可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列。

 

示例：

输入：[[1,2,3],[4,5,6],[7,8,9]]
输出：12
解释：
可能的下降路径有：
[1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
[2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
[3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
和最小的下降路径是 [1,4,7]，所以答案是 12。

 

提示：

1 <= A.length == A[0].length <= 100
-100 <= A[i][j] <= 100

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-falling-path-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_931 {
    //很明显的动态规划，dp[i][j]表示到下标为[i][j]的最小路径和
    //肯定是由dp[i-1][j] dp[i-1][j+1] dp[i-1][j-1]三数得来，（注意边界）
    //所以dp[i][j]=min(dp[i-1][j],dp[i-1][j-1],dp[i-1][j+1])+A[i][j] 0<j<A.length-1
    public int minFallingPathSum(int[][] A) {
        int n=A.length;
        int[][] dp=new int[n][n];
        for (int i=0;i<n;i++){
            dp[0][i]=A[0][i];
        }
        for (int i=1;i<n;i++){
            for (int j=0;j<n;j++){
                if (j>0&&j<n-1){
                    dp[i][j]=Math.min(Math.min(dp[i-1][j],dp[i-1][j-1]),dp[i-1][j+1])+A[i][j];
                }else if (j==0){
                    dp[i][j]=Math.min(dp[i-1][j],dp[i-1][j+1])+A[i][j];
                }else
                    dp[i][j]=Math.min(dp[i-1][j],dp[i-1][j-1])+A[i][j];
            }
        }
        int min=dp[n-1][0];
        for (int i=1;i<n;i++){
            if (dp[n-1][i]<min){
                min=dp[n-1][i];
            }
        }
        return min;
    }
}
