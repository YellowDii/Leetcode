package leetcode.DynamicPrograming;

/*
688."马"在棋盘上的概率（knight-probability-in-chessboard）
已知一个 NxN 的国际象棋棋盘，棋盘的行号和列号都是从 0 开始。即最左上角的格子记为 (0, 0)，最右下角的记为 (N-1, N-1)。 

现有一个 “马”（也译作 “骑士”）位于 (r, c) ，并打算进行 K 次移动。 

如下图所示，国际象棋的 “马” 每一步先沿水平或垂直方向移动 2 个格子，然后向与之相垂直的方向再移动 1 个格子，共有 8 个可选的位置。

 



 

现在 “马” 每一步都从可选的位置（包括棋盘外部的）中独立随机地选择一个进行移动，直到移动了 K 次或跳到了棋盘外面。

求移动结束后，“马” 仍留在棋盘上的概率。

 

示例：

输入: 3, 2, 0, 0
输出: 0.0625
解释:
输入的数据依次为 N, K, r, c
第 1 步时，有且只有 2 种走法令 “马” 可以留在棋盘上（跳到（1,2）或（2,1））。对于以上的两种情况，各自在第2步均有且只有2种走法令 “马” 仍然留在棋盘上。
所以 “马” 在结束后仍在棋盘上的概率为 0.0625。
 

注意：

    1. N 的取值范围为 [1, 25]
    2. K 的取值范围为 [0, 100]
    3. 开始时，“马” 总是位于棋盘上

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/knight-probability-in-chessboard
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_688 {

    double[][][] dp;

    /**
     *
     * @param N NxN 的国际象棋棋盘,最左上角的格子记为 (0, 0)，最右下角的记为 (N-1, N-1)
     * @param K 进行 K 次移动
     * @param r,c 初始位于 (r, c)
     * @return
     */
    public double knightProbability(int N, int K, int r, int c){
        if(K == 0) return 1.0d;
        dp = new double[N][N][K + 1];
        return dfs(N, K, r, c);
    }

    public double dfs(int N, int K, int r, int c) {
        if(dp[r][c][K] - 0.0d > 0.000001d) return dp[r][c][K];
        if(K == 1) return Kis1(N, r, c);
        else {
            double res = 0.0d;
            if(r + 2  < N  && c + 1 < N)    res = res + dfs(N, K - 1, r + 2, c + 1);
            if(r + 2  < N  && c - 1 >= 0)   res = res + dfs(N, K - 1, r + 2, c - 1);
            if(r - 2  >= 0 && c + 1 < N)    res = res + dfs(N, K - 1, r - 2, c + 1);
            if(r - 2  >= 0 && c - 1 >= 0)   res = res + dfs(N, K - 1, r - 2, c - 1);
            if(c + 2  < N  && r + 1 < N)    res = res + dfs(N, K - 1, r + 1, c + 2);
            if(c + 2  < N  && r - 1 >= 0)   res = res + dfs(N, K - 1, r - 1, c + 2);
            if(c - 2  >= 0 && r + 1 < N)    res = res + dfs(N, K - 1, r + 1, c - 2);
            if(c - 2  >= 0 && r - 1 >= 0)   res = res + dfs(N, K - 1, r - 1, c - 2);
            return dp[r][c][K] = res / 8.0d;
        }
    }

    /**
     * 返回走一步时落在界内的概率
     * @param N
     * @param r
     * @param c
     * @return
     */
    private double Kis1(int N, int r, int c) {
        int count = 0;
        if(r + 2  < N  && c + 1 < N)	count++;
        if(r + 2  < N  && c - 1 >= 0)	count++;
        if(r - 2  >= 0 && c + 1 < N)	count++;
        if(r - 2  >= 0 && c - 1 >= 0)	count++;
        if(c + 2  < N  && r + 1 < N)	count++;
        if(c + 2  < N  && r - 1 >= 0)	count++;
        if(c - 2  >= 0 && r + 1 < N)	count++;
        if(c - 2  >= 0 && r - 1 >= 0)	count++;
        return ((double)count) / 8.0d;
    }

}
