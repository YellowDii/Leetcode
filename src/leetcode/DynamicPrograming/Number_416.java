package leetcode.DynamicPrograming;

import java.util.Arrays;

public class Number_416 {
    /**
     * 416.分割等和子集(partition-equal-subset-sum)
     *
     * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     *
     * 注意:
     *
     * 每个数组中的元素不会超过 100
     * 数组的大小不会超过 200
     * 示例 1:
     *
     * 输入: [1, 5, 11, 5]
     *
     * 输出: true
     *
     * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
     *  
     *
     * 示例 2:
     *
     * 输入: [1, 2, 3, 5]
     *
     * 输出: false
     *
     * 解释: 数组不能分割成两个元素和相等的子集.
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //看了解题 才发觉是01背包问题。。。。
    public boolean canPartition2(int[] nums) {
        int goal=0;
        for (int i:nums){
            goal+=i;
        }
        if ((goal&1)==1){
            return false;
        }
        goal>>>=1;
        boolean[] dp=new boolean[goal+1];
        dp[0]=true;
        if (nums[0]<goal){
            dp[nums[0]]=true;
        }
        for (int i=1;i<nums.length;i++){
            for (int j=goal;j>=nums[i];j--){
                if (dp[goal]){
                    return true;
                }
                dp[j]=dp[j]|dp[j-nums[i]];
            }
        }
        return dp[goal];
    }
}
