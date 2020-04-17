package leetcode;

import java.util.Arrays;
import java.util.HashMap;

public class Number_560 {
    /**
     * 560.和为K的子数组（subarray-sum-equals-k）
     *
     * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
     *
     * 示例 1 :
     *
     * 输入:nums = [1,1,1], k = 2
     * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
     * 说明 :
     *
     *  1.数组的长度为 [1, 20,000]。
     *  2.数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //暴力 400ms左右
    public int subarraySum(int[] nums, int k) {
        int len=nums.length;
        if (nums==null||len<1){
            return 0;
        }
        int count=0;
        for (int i=0;i<len;i++){
            int sum=0;
            for (int j=i;j<len;j++){
                sum+=nums[j];
                if (sum==k){
                    count++;
                }
            }
        }
        return count;
    }
    //归并思想 300ms左右
    //还可以优化 有前半段basesum 也能有后半段basesum
    public int subarraySum2(int[] nums,int k){
        int len=nums.length;
        if (nums==null||len<1){
            return 0;
        }
        return count(nums,0,len-1,k);
    }

    private int count(int[] nums, int start, int end,int k) {
        if (start>end){
            return 0;
        }
        if (start==end){
            return nums[start]==k?1:0;
        }
        int count=0;
        int mid=start+(end-start)/2;
        int basesum=0;
        for (int i=start;i<=mid;i++){
            basesum+=nums[i];
        }
        for (int i=start;i<=mid;i++){
            int sum=basesum;
            if (i>start){
                sum-=nums[i-1];
                basesum=sum;
            }
            for (int j=mid+1;j<=end;j++){
                sum+=nums[j];
                if (sum==k){
                    count++;
                }
            }
        }
        return count(nums,start,mid,k)+count(nums,mid+1,end,k)+count;
    }
    //用哈希+思路转换
    public  int subarraySum3(int[] nums,int k){
        int count = 0, sum = 0;
        HashMap< Integer, Integer > map = new HashMap < > ();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;

    }
}
