package leetcode.DynamicPrograming;
/*
53.最大子序和（maximum-subarray）
给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

示例:

输入: [-2,1,-3,4,-1,2,1,-5,4],
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
进阶:

如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximum-subarray
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_53 {
    //动态规划 dp(i)表示nums数组 0~i中 尾巴下标为i的最大和
    //结果返回max（dp(i)）    0<=i<=nums.length-1
    public int maxSubArray(int[] nums) {
        if (nums.length==0||nums==null)
            return 0;
        int[] dp=new int[nums.length];
        int i=1;
        dp[0]=nums[0];
        int max=dp[0];
        while (i<nums.length){
            dp[i]=Math.max(dp[i-1]+nums[i],nums[i]);
            max=Math.max(max,dp[i]);
            i++;
        }
        return max;
    }
    //上述优化一下 看解答看到的 挺精妙的
    //和为正数 继续加 不为正数 进行比较
    public int maxSubArray2(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0)
                sum += num;
            else
                sum = num;
            res = Math.max(res, sum);
        }
        return res;
    }
}
