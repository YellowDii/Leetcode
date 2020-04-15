package leetcode.DynamicPrograming;

import java.util.Arrays;

/*
1039. 多边形三角剖分的最低得分（minimum-score-triangulation-of-polygon）

给定 N，想象一个凸 N 边多边形，其顶点按顺时针顺序依次标记为 A[0], A[i], ..., A[N-1]。


假设您将多边形剖分为 N-2 个三角形。对于每个三角形，该三角形的值是顶点标记的乘积，三角剖分的分数是进行三角剖分后所有 N-2 个三角形的值之和。

返回多边形进行三角剖分后可以得到的最低分。
 

示例 1：

输入：[1,2,3]
输出：6
解释：多边形已经三角化，唯一三角形的分数为 6。
示例 2：图片

输入：[3,7,4,5]
输出：144
解释：有两种三角剖分，可能得分分别为：3*7*5 + 4*5*7 = 245，或 3*4*5 + 3*4*7 = 144。最低分数为 144。
示例 3：图片

输入：[1,3,1,4,1,5]
输出：13
解释：最低分数三角剖分的得分情况为 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13。
 

提示：

    1. 3 <= A.length <= 50
    2. 1 <= A[i] <= 100

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-score-triangulation-of-polygon
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_1039 {
    String minimum_score_triangulation_of_polygon="1039. 多边形三角剖分的最低得分（minimum-score-triangulation-of-polygon）";
    //dp[i][j]表示第i个角到第j个角剖分最小值
    //最后返回dp[0][A.length-1]就可以了
    public int minScoreTriangulation(int[] A) {
        int len=A.length;
        int[][] dp=new int[len][len];
        for (int[] arr:dp){
            Arrays.fill(arr,Integer.MAX_VALUE);
        }
        //只存在两个角的 初始值为0
        for (int i=0;i<len;i++){
            dp[i][(i+1)%len]=0;
        }
        for (int length=2;length<len;length++){
            for (int i=0;i<len;i++){
                int j=(i+length)%len;
                for (int k=(i+1)%len;k!=j;k=(k+1)%len){
                    dp[i][j]=Math.min(dp[i][j],dp[i][k]+dp[k][j]+A[i]*A[k]*A[j]);
                }
            }
        }
        return dp[0][len-1];
    }
    //也可以用dfs+备忘录
    //会非常快
    public int minScoreTriangulation2(int[] A){
        int[][] dist=new int[A.length][A.length];
        return dfs(0,A.length-1,A,dist);
    }

    private int dfs(int left, int right, int[] A, int[][] dist) {
        if (left + 1 == right) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = left + 1; i < right; i++) {
            int lefti = 0, righti = 0;
            //递归左边
            if (dist[left][i] != 0)
                lefti = dist[left][i];
            else
                lefti = dfs(left, i, A, dist);
            //递归右边
            if (dist[i][right] != 0)
                righti = dist[i][right];
            else
                righti = dfs(i, right, A, dist);
            //比较
            ans = Math.min(ans, lefti + righti + A[left] * A[i] * A[right]);
        }
        return dist[left][right] = ans;
    }
}
