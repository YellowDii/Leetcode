package leetcode.DynamicPrograming;

import java.util.Arrays;

public class Number_494 {
    /**
     * 494.目标和（target-sum）
     *
     * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
     *
     * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
     *
     * 示例 1:
     *
     * 输入: nums: [1, 1, 1, 1, 1], S: 3
     * 输出: 5
     * 解释:
     *
     * -1+1+1+1+1 = 3
     * +1-1+1+1+1 = 3
     * +1+1-1+1+1 = 3
     * +1+1+1-1+1 = 3
     * +1+1+1+1-1 = 3
     *
     * 一共有5种方法让最终目标和为3。
     * 注意:
     *
     *  1.数组非空，且长度不会超过20。
     *  2.初始的数组的和不会超过1000。
     *  3.保证返回的最终结果能被32位整数存下。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/target-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //相当于找子数组和为 （sum-s）/2
    //方法有bug  会找多了 还得细想
    /**
    public int findTargetSumWays(int[] nums, int S) {
        int sum=0;
        for (int i:nums){
            sum+=i;
        }
        int goal=(sum-S)/2;
        //要用dfs了
        Arrays.sort(nums);
        int[] result=new int[1];
        //因为是非负整数 得考虑为0情况
        int count0=0;
        for (int i:nums){
            if (i==0){
                count0++;
            }else {
                break;
            }
        }
        int k=(int)Math.pow(2,count0);
        if (sum==S&&count0==0){
            return 1;
        }else if (sum==S&&count0!=0){
            return k*1;
        }
        count(result,nums,count0,goal);
        return k*result[0];
    }

    private void count(int[] result,int[] nums,int start, int goal) {
        if (start>=nums.length||goal<0){
            return;
        }
        for (int i=start;i<nums.length;i++){
            if (nums[i]<goal){
                count(result,nums,i+1,goal-nums[i]);
                count(result,nums,i+1,goal);
            }else if (nums[i]==goal){
                result[0]++;
            }else {
                break;
            }
        }
    }
     **/
    //动态规划
    /**
     * 这道题也是一个常见的背包问题，我们可以用类似求解背包问题的方法来求出可能的方法数。
     *
     * 我们用 dp[i][j] 表示用数组中的前 i 个元素，组成和为 j 的方案数。考虑第 i 个数 nums[i]，它可以被添加 + 或 -，因此状态转移方程如下：
     *
     * dp[i][j] = dp[i - 1][j - nums[i]] + dp[i - 1][j + nums[i]]
     * 也可以写成递推的形式：
     *
     * dp[i][j + nums[i]] += dp[i - 1][j]
     * dp[i][j - nums[i]] += dp[i - 1][j]
     */

}
