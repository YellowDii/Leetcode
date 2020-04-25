package leetcode.DynamicPrograming;

import java.util.Arrays;

public class Number_322 {
    /**
     * 322.零钱兑换（coin-change）
     *
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
     * 如果没有任何一种硬币组合能组成总金额，返回 -1。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: coins = [1, 2, 5], amount = 11
     * 输出: 3
     * 解释: 11 = 5 + 5 + 1
     * 示例 2:
     *
     * 输入: coins = [2], amount = 3
     * 输出: -1
     *  
     *
     * 说明:
     * 你可以认为每种硬币的数量是无限的。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/coin-change
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //类似完全背包问题
    //但还是有很大差别 它求的是最少数量
    //转换思路
    /**
     * 依然动态规划 F（S）=min（ F（S-C））+1
     * 其中S代表金额总数 C代表某面值 C从c0 ~cn   n=coins.length-1
     *
     */
    public int coinChange(int[] coins, int amount) {
        if (amount<0){
          return 0;
        }
        int[] counts=new int[amount+1];
        return countMin(coins,amount,counts);
    }

    private int countMin(int[] coins, int goal,int[] counts) {
        if (goal<0){
            return -1;
        }
        if (goal==0){
            return 0;
        }
        if (counts[goal]!=0){
            return counts[goal];
        }
        int result=Integer.MAX_VALUE;
        for (int i=0;i<coins.length;i++){
            int ans_i=countMin(coins,goal-coins[i],counts);
            if (ans_i>=0&&ans_i<result){
                result=ans_i+1;
            }
        }
        counts[goal]=(result==Integer.MAX_VALUE)?-1:result;
        return counts[goal];
    }
    //尽量选最大面值的dfs +剪枝 有点贪心的思想

    private int ans = Integer.MAX_VALUE;
    public int coinChange2(int[] coins, int amount) {
        if (amount < 1 || coins == null || coins.length == 0) return 0;
        Arrays.sort(coins);
        dfs(coins, amount, coins.length - 1, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /**
     * @param coins 待选的硬币面值
     * @param amount 需要凑够的金额
     * @param coinIdx 当前选择的硬币面值的索引
     * @param count 目前已选的硬币数量
     */
    private void dfs(int[] coins, int amount, int coinIdx, int count) {
        /*
        整体策略：优先尽可能多地选择较大面值的硬币（假设要凑够的金额是amount，当前正在选择的硬币面值是coin）
        ① 如果凑够了amount，说明得到了一个潜在答案，计算出目前能凑够amount的最少硬币数量ans，剪枝
        ② 如果没凑够amount
            (1) 如果coin是最小面值，说明这个凑法不合理，剪枝
            (2) 如果(目前已选择的硬币数量 + 1) >= ans，说明继续往下凑，硬币数量不会小于ans，剪枝
            (3) 否则尝试选择面值比coin小的硬币去凑剩余的金额
            (4) 减少面值为coin的硬币数量，进入
        */
        for (int c = amount / coins[coinIdx]; c >= 0; c--) {  // 最大要的硬币数,减减
            int remain = amount - c * coins[coinIdx]; //剩余要凑的金额
            int curCount = count + c;   // 需要的硬币数
            if (remain == 0) {
                // 已经优先用面值较大的硬币了
                // 如果用面值较小的硬币，凑出来的数量只会更多
                // 所以直接剪枝，没必要尝试减少大面值硬币的数量，用小面值的硬币去凑
                ans = Math.min(ans, curCount);
                return;
            }

            // 已经是最小面值了，如果还凑不够amount，说明不可能凑出这个数目，直接剪枝    [5,6]   3
            if (coinIdx == 0) return;

            // 继续往下凑，硬币数量不会小于ans，直接剪枝   (继续凑，硬币肯定增加，如果大于以求出的硬币数，舍)
            if (curCount + 1 >= ans) return;

            // 选择较小的面值凑够剩余的金额
            dfs(coins, remain, coinIdx - 1, curCount);
        }
    }
}
