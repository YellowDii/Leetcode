package leetcode.DynamicPrograming;

public class Number_152 {
    /**
     * 152.乘积最大子数组（maximum-product-subarray）
     *
     * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字）。
     *
     * 示例 1:
     *
     * 输入: [2,3,-2,4]
     * 输出: 6
     * 解释: 子数组 [2,3] 有最大乘积 6。
     * 示例 2:
     *
     * 输入: [-2,0,-1]
     * 输出: 0
     * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 遍历数组时计算当前最大值，不断更新
     * 令imax为当前最大值，则当前最大值为 imax = max(imax * nums[i], nums[i])
     * 由于存在负数，那么会导致最大的变最小的，最小的变最大的。因此还需要维护当前最小值imin，imin = min(imin * nums[i], nums[i])
     * 当负数出现时则imax与imin进行交换再进行下一步计算
     * 时间复杂度：O(n)O(n)
     *
     * 作者：guanpengchn
     * 链接：https://leetcode-cn.com/problems/maximum-product-subarray/solution/hua-jie-suan-fa-152-cheng-ji-zui-da-zi-xu-lie-by-g/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    //精妙...
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for(int i=0; i<nums.length; i++){
            if(nums[i] < 0){
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax*nums[i], nums[i]);
            imin = Math.min(imin*nums[i], nums[i]);

            max = Math.max(max, imax);
        }
        return max;
    }
    //正常动态规划

    /**
     * maxDP[i + 1] = max(maxDP[i] * A[i + 1], A[i + 1],minDP[i] * A[i + 1])
     * minDP[i + 1] = min(minDP[i] * A[i + 1], A[i + 1],maxDP[i] * A[i + 1])
     * dp[i + 1] = max(dp[i], maxDP[i + 1])
     *
     * @param nums
     * @return
     */
    public int maxProduct2(int[] nums) {
        int p = nums[0];
        int maxP = nums[0];
        int minP = nums[0];
        for(int i = 1; i < nums.length; i++) {
            int t = maxP;
            //考虑了当前为0的情况 比较的每个数都乘nums[i]了 有0时要重新开始比较了
            maxP = Math.max(Math.max(maxP * nums[i], nums[i]), minP *nums[i]);
            minP = Math.min(Math.min(t * nums[i], nums[i]), minP * nums[i]);
            p = Math.max(maxP, p);
        }
        return p;

    }
}
