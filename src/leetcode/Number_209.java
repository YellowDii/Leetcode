package leetcode;

public class Number_209 {
    /**
     * 209.长度最小的子数组（minimum-size-subarray-sum）
     * <p>
     * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。如果不存在符合条件的连续子数组，返回 0。 
     * <p>
     * 示例：
     * <p>
     * 输入：s = 7, nums = [2,3,1,2,4,3]
     * 输出：2
     * 解释：子数组 [4,3] 是该条件下的长度最小的连续子数组。
     *  
     * <p>
     * 进阶：
     * <p>
     * 如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    //可以直接暴力 O(n^2)
    public int minSubArrayLen(int s, int[] nums) {
        int ans = nums.length + 1;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= s) {
                    ans = Math.min(ans, j - i + 1);
                    break;
                }
            }
        }
        return ans > nums.length ? 0 : ans;
    }

    //可以用类似滑动窗口的做法 双指针
    public int minSubArrayLen2(int s, int[] nums) {
        int len = nums.length;
        int size = len + 1;
        //第一步确定窗口规模
        int i = 0, sum = 0;
        while (i < len && sum < s) {
            sum += nums[i];
            i++;
        }
        if (sum >= s) {
            size = Math.min(size, i);
        }

        //寻找最小窗口
        int left = 0, right = i-1;
        while (left < right && right < len) {
            if (sum>=s){
                sum-=nums[left];
                left++;
            }else if (sum<s&&right<len){
                if (right+1>=len){
                    break;
                }
                right++;
                sum+=nums[right];
            }
            if (sum>=s){
                size=Math.min(size,right-left+1);
            }
        }
        return size > len ? 0 : size;
    }
}
