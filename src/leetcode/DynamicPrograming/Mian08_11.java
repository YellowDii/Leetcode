package leetcode.DynamicPrograming;

public class Mian08_11 {
    /**
     * 面试题 08.11. 硬币(coin-lcci)
     * 硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。
     * (结果可能会很大，你需要将结果模上1000000007)
     *
     * 示例1:
     *
     *  输入: n = 5
     *  输出：2
     *  解释: 有两种方式可以凑成总金额:
     * 5=5
     * 5=1+1+1+1+1
     * 示例2:
     *
     *  输入: n = 10
     *  输出：4
     *  解释: 有四种方式可以凑成总金额:
     * 10=10
     * 10=5+5
     * 10=5+1+1+1+1+1
     * 10=1+1+1+1+1+1+1+1+1+1
     * 说明：
     *
     * 注意:
     *
     * 你可以假设：
     *
     * 0 <= n (总金额) <= 1000000
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/coin-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 数学法 参考官方解答：
     * https://leetcode-cn.com/problems/coin-lcci/solution/ying-bi-by-leetcode-solution/
     */

    public int waysToChange(int n) {
        int res = 0;
        int mod = 1000000007;
        for(int n25 = 0; n25 <= n / 25; n25++){
            int temp = n - n25 * 25;
            long num = temp / 10 + 1;
            int a = temp / 5 + 1;
            int b = (temp % 10) / 5 + 1;
            res = (int)((res + ((a + b) * num / 2)) % mod);
        }
        return res;
    }
    //动态规划 dp[4][n]空间优化后：
    public int waysToChange2(int n) {
        int[] dp = new int[n + 1];

        int[] coins = new int[]{1,5,10,25};


        //刚好可以用一个硬币凑成的情况，是一种情况
        // while i == coin :
        //dp[i] = dp[i - coin] => dp[0]
        dp[0] = 1;

        /**
         * dp方程：dp[i] += dp[i - coin];
         */

        for(int coin : coins) {
            for(int i = coin; i <= n; i++) {
                dp[i] = (dp[i] + dp[i - coin]) % 1000000007;
            }
        }

        return dp[n];

    }
}
