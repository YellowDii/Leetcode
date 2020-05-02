package leetcode.DynamicPrograming;

import java.util.Arrays;

public class Number_198 {
    /**
     * 198.打家劫舍（house-robber）
     *
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
     *
     * 示例 1:
     *
     * 输入: [1,2,3,1]
     * 输出: 4
     * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     * 示例 2:
     *
     * 输入: [2,7,9,3,1]
     * 输出: 12
     * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/house-robber
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //这个判断奇数偶数即可
    // 。。。。。 一测试 思路错了 还是用动态规划把..
    public int rob(int[] nums) {
        int a=0,b=0;
        for (int i=0;i<nums.length;i++){
            if ((i&1)==0){
                a+=nums[i];
            }else {
                b+=nums[i];
            }
        }
        return Math.max(a,b);
    }

    //dfs +
    int max;
    public int rob2(int[] nums) {
        int[] dp=new int[nums.length];
        Arrays.fill(dp,-1);
        max=0;
        dfs(0,dp,nums);
        return max;
    }

    private int dfs(int i,int[] dp,int[] nums) {
        if (i>=nums.length){
            return 0;
        }
        if (dp[i]!=-1){
            return dp[i];
        }
        int ans=Math.max(nums[i]+dfs(i+2,dp,nums),nums[i]+dfs(i+3,dp,nums));
        max=Math.max(ans,dfs(i+1,dp,nums));
        dp[i]=max;
        return dp[i];
    }
    //动态规划
    //dp[k]=max(dp[k-2]+nums[k],dp[k-1])
    public int rob3(int[] num) {
        int prevMax = 0;
        int currMax = 0;
        for (int x : num) {
            int temp = currMax;
            currMax = Math.max(prevMax + x, currMax);
            prevMax = temp;
        }
        return currMax;
    }

}
