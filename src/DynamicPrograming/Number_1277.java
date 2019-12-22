package DynamicPrograming;

/**
 * 1277.统计全为1的正方形子矩阵（count-square-submatrices-with-all-ones）
 *
 * 给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，请你统计并返回其中完全由 1 组成的 正方形 子矩阵的个数。
 *
 *
 * 示例 1：
 *
 * 输入：matrix =
 * [
 *   [0,1,1,1],
 *   [1,1,1,1],
 *   [0,1,1,1]
 * ]
 * 输出：15
 * 解释：
 * 边长为 1 的正方形有 10 个。
 * 边长为 2 的正方形有 4 个。
 * 边长为 3 的正方形有 1 个。
 * 正方形的总数 = 10 + 4 + 1 = 15.
 * 示例 2：
 *
 * 输入：matrix =
 * [
 *   [1,0,1],
 *   [1,1,0],
 *   [1,1,0]
 * ]
 * 输出：7
 * 解释：
 * 边长为 1 的正方形有 6 个。
 * 边长为 2 的正方形有 1 个。
 * 正方形的总数 = 6 + 1 = 7.
 *  
 *
 * 提示：
 *
 *      1. 1 <= arr.length <= 300
 *      2. 1 <= arr[0].length <= 300
 *      3. 0 <= arr[i][j] <= 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-square-submatrices-with-all-ones
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_1277 {
    String count_square_submatrices_with_all_ones="1277.统计全为1的正方形子矩阵（count-square-submatrices-with-all-ones）";
    //dp[i][j]表示以i、j为右下角的 最大全1正方形边长
    public int countSpace(int[][] matrix){
        int m=matrix.length;
        int n=matrix[0].length;
        int[][] dp=new int[m][n];
        int ans=0;
        //预处理
        for (int i=0;i<m;i++){
            ans+=dp[i][0]=matrix[i][0];
        }
        for (int i=0;i<n;i++){
            ans+=dp[0][i]=matrix[0][i];
        }
        if (matrix[0][0]==1)
            ans--;//怕多算了一次
        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                if (matrix[i][j]==1){
                    ans+=dp[i][j]=Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1;
                }
            }
        }
        return ans;
    }
}
