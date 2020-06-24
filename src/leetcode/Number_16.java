package leetcode;

import java.util.Arrays;

public class Number_16 {
    /**
     * 16.最接近的三数之和（3sum-closest）
     *
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。 
     *
     * 示例：
     *
     * 输入：nums = [-1,2,1,-4], target = 1
     * 输出：2
     * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
     *  
     *
     * 提示：
     *
     * 3 <= nums.length <= 10^3
     * -10^3 <= nums[i] <= 10^3
     * -10^4 <= target <= 10^4
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/3sum-closest
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //其实还是三数之和为0的变形
    //排序 + 双指针
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int len=nums.length;
        int best=1000000;//写成Integer.MAX_VALUE会有问题 加减后取绝对值会出问题
        for (int i=0;i<len;i++){
            if (i>0&&nums[i]==nums[i-1]){
                continue;
            }
            int left=i+1,right=len-1;
            while (left<right){
                int sum=nums[i]+nums[left]+nums[right];
                if (sum==target){
                    return target;
                }
                if (sum<target){
                    left++;
                    while (left<right&&nums[left]==nums[left-1]){
                        left++;
                    }
                }else {
                    right--;
                    while (left<right&&nums[right]==nums[right+1]){
                        right--;
                    }
                }
                if (Math.abs(best-target)>Math.abs(sum-target)){
                    best=sum;
                }
            }
        }
        return best;
    }
}
