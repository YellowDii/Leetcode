package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Number_15 {
    /**
     * 15.三数之和 (3sum)
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     *
     * 注意：答案中不可以包含重复的三元组。
     *
     *  
     *
     * 示例：
     *
     * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     *
     * 满足要求的三元组集合为：
     * [
     *   [-1, 0, 1],
     *   [-1, -1, 2]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/3sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //1.暴力 效率不高 而且不好排除重复的

    //2.先排序 在考虑重复问题

    /**
     * 首先对数组进行排序，排序后固定一个数 nums[i]nums[i]，再使用左右指针指向 nums[i]nums[i]后面的两端，
     * 数字分别为 nums[L]nums[L] 和 nums[R]nums[R]，计算三个数的和 sumsum 判断是否满足为 0，满足则添加进结果集
     * 如果 nums[i]nums[i]大于 0，则三数之和必然无法等于 0，结束循环
     * 如果 nums[i]nums[i] == nums[i-1]nums[i−1]，则说明该数字重复，会导致结果重复，所以应该跳过
     * 当 sumsum == 0 时，nums[L]nums[L] == nums[L+1]nums[L+1] 则会导致结果重复，应该跳过，L++
     * 当 sumsum == 0 时，nums[R]nums[R] == nums[R-1]nums[R−1] 则会导致结果重复，应该跳过，R--
     * 时间复杂度：O(n^2) ,n 为数组长度
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return ans;
        }
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int L=i+1;
            int R=len-1;
            while (L<R){
                int sum=nums[i]+nums[L]+nums[R];
                if (sum==0){
                    ans.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    while (L<R&&nums[L]==nums[L+1]){
                        L++;//去重
                    }
                    while (L<R&&nums[R]==nums[R-1]){
                        R--;//去重
                    }
                    L++;
                    R--;
                }else if (sum<0){
                    L++;
                }else {
                    R--;
                }
            }
        }
        return ans;
    }
}
