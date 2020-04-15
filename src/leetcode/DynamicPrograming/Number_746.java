package leetcode.DynamicPrograming;

/*
746.使用最小花费爬楼梯 (min-cost-climbing-stairs)
数组的每个索引做为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。

每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。

您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。

示例 1:

输入: cost = [10, 15, 20]
输出: 15
解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。
 示例 2:

输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
输出: 6
解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。
注意：

    1.cost 的长度将会在 [2, 1000]。
    2.每一个 cost[i] 将会是一个Integer类型，范围为 [0, 999]。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/min-cost-climbing-stairs
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_746 {
    String min_cost_climbing_stairs="746.使用最小花费爬楼梯 (min-cost-climbing-stairs)";
    //动态规划 dp[n]表示爬到n阶花费最少的代价
    //比较dp[cost.length]，dp[cost.length+1]即可
    //dp[n]=min(dp[n-1]+cost[n],dp[n-2]+cost[n])
    public int minCostClimbingStairs(int[] cost) {
        int len=cost.length;
        int[] dp=new int[len+2];
        dp[0]=cost[0];
        dp[1]=cost[1];
        int[] cost2=new int[len+2];
        int k=0;
        for (int co:cost){
            cost2[k++]=co;
        }
        for (int i=2;i<len+2;i++){
            dp[i]=Math.min(dp[i-1]+cost2[i],dp[i-2]+cost2[i]);
        }
        return Math.min(dp[len],dp[len+1]);
    }
}
