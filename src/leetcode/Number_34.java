package leetcode;

public class Number_34 {
    /**
     * 34.在排序数组中查找元素的第一个和最后一个位置（find-first-and-last-position-of-element-in-sorted-array）
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     *
     * 你的算法时间复杂度必须是 O(log n) 级别。
     *
     * 如果数组中不存在目标值，返回 [-1, -1]。
     *
     * 示例 1:
     *
     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: [3,4]
     * 示例 2:
     *
     * 输入: nums = [5,7,7,8,8,10], target = 6
     * 输出: [-1,-1]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //二分法
    public int[] searchRange(int[] nums, int target) {
        int[] ans=new int[]{-1,-1};
        int left=0,right=nums.length-1;
        while (left<=right){
            int mid=(right+left)/2;
            if (nums[mid]==target){
                return ans=findAnswer(nums,mid,target);
            }else if (nums[mid]<target){
                left=mid+1;
            }else {
                right=mid-1;
            }
        }
        return ans;
    }

    private int[] findAnswer(int[] nums, int mid,int target) {
        int left=mid,right=mid;
        while (left>=1&&nums[left-1]==target){
            left--;
        }
        while (right<=nums.length-2&&nums[right+1]==target){
            right++;
        }
        return new int[]{left,right};
    }
}
