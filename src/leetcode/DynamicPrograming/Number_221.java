package leetcode.DynamicPrograming;

public class Number_221 {
    /**
     * 221.最大正方形（maximal-square）
     *
     * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
     *
     * 示例:
     *
     * 输入:
     *
     * 1 0 1 0 0
     * 1 0 1 1 1
     * 1 1 1 1 1
     * 1 0 0 1 0
     *
     * 输出: 4
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximal-square
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //可以暴力法 先从边长length开始 但应该比较慢

    //动态规划
    //dp[i][j]表示（i,j）位置的最大正方形边长
    //对于每一个1 都有dp[i][j]=min(min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1;
    public int maximalSquare(char[][] matrix) {
        int rows=matrix.length;
        int cols=rows>0?matrix[0].length:0;
        int[][] dp=new int[rows+1][cols+1];//dp[i][j]映射的是matrix[i-1][j-1]
        int side=0;
        for (int i=1;i<=rows;i++){
            for (int j=1;j<=cols;j++){
                if (matrix[i-1][j-1]=='1'){
                    dp[i][j]=Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1;
                    side=Math.max(side,dp[i][j]);
                }
            }
        }
        return side*side;
    }
    //空间优化之后
    public int maximalSquare2(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[] dp = new int[cols + 1];
        int maxsqlen = 0, prev = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                int temp = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }
        return maxsqlen * maxsqlen;
    }

}
