package leetcode;

import java.util.Arrays;

public class Number_581 {
    /**
     * 581.最短无序连续子数组 (shortest-unsorted-continuous-subarray)
     * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
     *
     * 你找到的子数组应是最短的，请输出它的长度。
     *
     * 示例 1:
     *
     * 输入: [2, 6, 4, 8, 10, 9, 15]
     * 输出: 5
     * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
     * 说明 :
     *
     * 输入的数组长度范围在 [1, 10,000]。
     * 输入的数组可能包含重复元素 ，所以升序的意思是<=。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //先排序 从左到右第一个不一样的就是开头 从右到左第一个不一样的就是尾 相减即可
    public int findUnsortedSubarray(int[] nums) {
        int[] sorted= Arrays.copyOf(nums,nums.length);
        Arrays.sort(sorted);
        int i=0;
        while (i<nums.length&&sorted[i]==nums[i]){
            i++;
        }
        int j=nums.length-1;
        //j>=i  可以少遍历一些
        while (j>=i&&sorted[j]==nums[j]){
            j--;
        }
        return j-i>=0?j-i+1:0;
    }
}
