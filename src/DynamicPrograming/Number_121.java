package DynamicPrograming;

/*
121.买卖股票的最最佳时机（best-time-to-buy-and-sell-stock）
给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。

注意你不能在买入股票前卖出股票。

示例 1:

输入: [7,1,5,3,6,4]
输出: 5
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
示例 2:

输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_121 {
    String best_time_to_buy_and_sell_stock = "121.买卖股票的最最佳时机（best-time-to-buy-and-sell-stock）";

    //动态规划 dp[i][j]表示第i天 j=0表示没有持股 j=1表示持股了
    //套用714中的模板
    public int maxProfit(int[] prices) {
        if (prices.length == 0)
            return 0;
        if (prices.length == 1)
            return 0;
        if (prices.length == 2)
            return prices[1] - prices[0] > 0 ? prices[1] - prices[0] : 0;
        int K = 1;//表示最大交易次数
        int[][][] dp = new int[prices.length][K + 1][2];
        dp[0][1][0] = 0;
        dp[0][1][1] = -prices[0];
        dp[0][0][0] = 0;
        for (int i = 1; i < prices.length; i++) {
            for (int k = 1; k <= K; k++) {
                for (int j = 0; j <= 1; j++) {
                    if (j == 0) {
                        //休息 卖股
                        dp[i][k][j] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                    } else {
                        //休息 买股  注意买股需要从k-1的不持股状态转移 因为买股了要想赚必须卖，所以交易次数要增加
                        dp[i][k][j] = Math.max(dp[i - 1][k][j], dp[i - 1][k - 1][0] - prices[i]);
                    }
                }
            }
        }
        return dp[prices.length - 1][K][0];
    }
    //优化下
    public int maxProfit2(int[] prices) {
        if(prices.length <= 1)
            return 0;
        int min = prices[0], max = 0;
        for(int i = 1; i < prices.length; i++) {
            max = Math.max(max, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return max;
    }
}
