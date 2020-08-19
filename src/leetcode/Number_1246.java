package leetcode;

public class Number_1246 {
    /**
     * 1246. 删除回文子数组(palindrome-removal)
     * 给你一个整数数组?arr，每一次操作你都可以选择并删除它的一个 回文 子数组?arr[i], arr[i+1], ..., arr[j]（ i <= j）。
     *
     * 注意，每当你删除掉一个子数组，右侧元素都会自行向前移动填补空位。
     *
     * 请你计算并返回从数组中删除所有数字所需的最少操作次数。
     *
     * ?
     *
     * 示例 1：
     *
     * 输入：arr = [1,2]
     * 输出：2
     * 示例 2：
     *
     * 输入：arr = [1,3,4,1,5]
     * 输出：3
     * 解释：先删除 [4]，然后删除 [1,3,1]，最后再删除 [5]。
     * ?
     *
     * 提示：
     *
     * 1 <= arr.length <= 100
     * 1 <= arr[i] <= 20
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/palindrome-removal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    /**
     * 经典的区间dp，我们令f(i,j)f(i,j)代表删除区间[i,j][i,j]的最小值（即最小删除次数），那么可得以下递推公式：
     * 1. 如果arr[i]==arr[j] :
     *      f(i,j)=min(f(i+1,j-1),f(i,k)+f(k+1,j)) i<=k<=j
     * 2. 如果arr[i]!=arr[j] :
     *      f(i,j)=min(f(i,k)+f(k+1,j))  i<=k<=j
     */
    public int minimumMoves(int[] arr) {
        if (arr==null||arr.length<1){
            return 0;
        }
        int len=arr.length;
        int[][] dp=new int[len][len];
        for (int i=0;i<len;i++){
            dp[i][i]=1;
        }
        //注意遍历顺序
        for (int j=1;j<len;j++){
            for (int i=j-1;i>=0;i--){
                if (i==j-1){
                    dp[i][j]=arr[i]==arr[j]?1:2;
                    continue;
                }
                int mink=Integer.MAX_VALUE;
                for (int k=i;k<=j;k++){
                    mink=Math.min(mink,dp[i][k]+dp[k+1][j]);
                }
                if (arr[i]==arr[j]){
                    dp[i][j]=Math.min(dp[i+1][j-1],mink);
                }else {
                    dp[i][j]=mink;
                }
            }
        }
        return dp[0][len-1];
    }

    //思路一致 用递归写  记忆化+回溯
    private int[] arr;
    private int[][] memo;

    private int backTrack(int i, int j) {
        if (i == j) {
            return 1;
        }

        if (i == j - 1) {
            // 两个数字
            return arr[i] == arr[j] ? 1 : 2;
        }

        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        int min = Integer.MAX_VALUE;
        if (arr[i] == arr[j]) {
            min = backTrack(i + 1, j - 1);
        }

        for (int k = i; k < j; k++) {
            min = Math.min(min, backTrack(i, k) + backTrack(k + 1, j));
        }

        memo[i][j] = min;
        return min;
    }

    public int minimumMoves2(int[] arr) {
        this.arr = arr;
        int len = arr.length;
        memo = new int[len][len];
        return backTrack(0, len - 1);
    }


}
