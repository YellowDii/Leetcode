package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Number_128 {
    /**
     * 128.最长连续序列（longest-consecutive-sequence）
     *
     * 给定一个未排序的整数数组，找出最长连续序列的长度。
     *
     * 要求算法的时间复杂度为 O(n)。
     *
     * 示例:
     *
     * 输入: [100, 4, 200, 1, 3, 2]
     * 输出: 4
     * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //首先想到排序后 然后再找 但这样要O（nlogn）
    // 不过实际测试时 这个反而快些 应该是数据量还不够大的原因 没法体现差别
    public int longestConsecutive(int[] nums) {
        if (nums==null||nums.length<1){
            return 0;
        }
        Arrays.sort(nums);
        int res=1;
        int cur=1;
        for (int i=1;i<nums.length;i++){
            if (nums[i]-nums[i-1]==1){
                cur++;
            }else if (nums[i]-nums[i-1]==0){
                continue;
            }else {
                res=Math.max(cur,res);
                cur=1;
            }
        }
        return Math.max(cur,res);
    }
    //利用HashSet
    public int longestConsecutive2(int[] nums){
        if (nums==null||nums.length<1){
            return 0;
        }
        int res=1;
        Set<Integer> set=new HashSet<>();
        for (int num:nums){
            set.add(num);
        }
        for (int num:set){
            if (!set.contains(num-1)){
                int cur_num=num;
                int cur_len=1;
                while (set.contains(cur_num+1)){
                    cur_num++;
                    cur_len++;
                }
                res=Math.max(res,cur_len);
            }
        }
        return res;
    }
}
