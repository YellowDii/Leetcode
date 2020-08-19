package leetcode;

import java.util.Arrays;

/**
 * 300.最长上升子序列
 *
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 *
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_300 {
    //方法1 暴力法 递归
    //超出时间限制
    public int lengthOfLIS1(int[] nums) {
        return lengthofLIS1(nums, Integer.MIN_VALUE, 0);
    }
    //prev用来记录当前子序列最大值
    //需要遍历所有子序列，所以方法中每次都需要比较子序列能够加入nums[pos]的与不加入nums[pos]的情况
    //即分别记录taken与notaken的情况 然后返回max
    //所以该方法时间复杂度为 O(2的n次方)
    public int lengthofLIS1(int[] nums, int prev, int pos) {
        if (pos == nums.length) {
            return 0;
        }
        int taken = 0;
        if (nums[pos] > prev) {
            taken = 1 + lengthofLIS1(nums, nums[pos], pos + 1);
        }
        int nottaken = lengthofLIS1(nums, prev, pos + 1);
        return Math.max(taken, nottaken);
    }

    //方法2 方法1+备忘录，备忘录memo[i+1][j]用来记载每个max值
    public int lengthOfLIS2(int[] nums){
        //备忘录
        int[][] memo=new int[nums.length+1][nums.length];
        for (int[] a:memo){
            Arrays.fill(a,-1);
        }
        return lengthOfLIS2(nums,-1,0,memo);
    }
    //这次preindex是用来记录下标的
    public int lengthOfLIS2(int[] nums,int preindex,int pos,int[][] memo){
        if (pos==nums.length)
            return 0;
        if (memo[preindex+1][pos]>0)
            return memo[preindex+1][pos];
        int taken=0;
        if (preindex<0||nums[pos]>nums[preindex])
            taken=1+lengthOfLIS2(nums,pos,pos+1,memo);
        int notaken=lengthOfLIS2(nums,preindex,pos+1,memo);
        memo[preindex+1][pos]=Math.max(taken,notaken);
        return memo[preindex+1][pos];
    }
    //方法3 动态规划
    //dp[i]记载从0~i的最长上升子序列长短，首先需要找到dp[0]~dp[i-1]中最大的，记为max(dp[j])
    // 如果nums[i]>max(dp[j])对应最大子序列中的最大值，dp[i]=max(dp[j])+1;
    public int lengthOfLIS3(int[] nums){
        if (nums.length==0||nums.length==1)
            return nums.length;
        int[] dp=new int[nums.length];
        dp[0]=1;
        int maxans = 1;
        for (int i = 1; i < dp.length; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                //这步相当于找max(dp[j])，思路是只要nums[j]>nums[i]，最大值就要将dp[j]作为预选，然后筛选最大的
                // 后续的代码保证了:只有当 nums[i]>nums[j]，且dp[j]就是要找的max才会让maxans改变
                if (nums[i] > nums[j]) {
                    maxval = Math.max(maxval, dp[j]);
                }
            }
            dp[i] = maxval + 1;
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }
    //二分查找
    //例子：input: [0, 8, 4, 12, 2]
    //dp: [0]
    //dp: [0, 8]
    //dp: [0, 4]
    //dp: [0, 4, 12]
    //注意Arrays.binarySearch：

    /**
     * Arrays.binarySearch(Object[] a,Object key)
     * 不带start与end的
     * a: 要搜索的数组
     *
     * key：要搜索的值
     *
     * 如果key在数组中，则返回搜索值的索引；否则返回-1或“-”（插入点）。插入点是索引键将要插入数组的那一点，即第一个大于该键的元素的索引。
     *
     * 技巧：
     *
     * [1] 搜索值不是数组元素，且在数组范围内，从1开始计数，得“ - 插入点索引值”；
     *
     * [2] 搜索值是数组元素，从0开始计数，得搜索值的索引值；
     *
     * [3] 搜索值不是数组元素，且大于数组内元素，索引值为 – (length + 1);
     *
     * [4] 搜索值不是数组元素，且小于数组内元素，索引值为 – 1。
     *
     * 例如：int arr [] =newint[]{1,3,4,5,8,9};
     *
     *         Arrays.sort(arr);
     *
     *        int index1 = Arrays.binarySearch(arr,6);
     *
     *        int index2 = Arrays.binarySearch(arr,4);
     *
     *        int index3 = Arrays.binarySearch(arr,0);
     *
     *        int index4 = Arrays.binarySearch(arr,10);
     *
     *         System.out.println("index1 = "+ index1 +", index2 = " + index2 +
     *
     *                         ", index3 = " + index3 +", index4 = "+ index4);
     *
     * 结果：index1= -5, index2 = 2, index3 = -1, index4 = -7
     */
    public int lengthOfLIS4(int[] nums){
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }
    //自己写一个二分查询
    public int lengthOfLIS5(int[] nums){
        int len=nums.length;
        if(len==0||len==1)
            return len;
        //最长上升子序列最后一个位置的下标
        int end=0;
        int[] dp=new int[len];
        for (int i=0;i<len;i++){
            int cur=nums[i];
            int left=0,right=end;
            while (left<right){
                int mid=(left+right)>>1;
                if (dp[mid]>=cur){
                    right=mid;
                }else
                    left=mid+1;
            }
            if (left==end)
                end++;
            dp[left]=cur;
        }
        return end;
    }
    //在写一遍
    public int lengthOfLIS6(int[] nums){
        if (nums==null||nums.length<1){
            return 0;
        }
        int len=nums.length;
        if (len==0||len==1){
            return len;
        }
        int end=0;
        int[] d=new int[len];
        int left=0,right=end,mid=0,cur=nums[0];
        for (int i=0;i<len;i++){
            cur=nums[i];
            left=0;
            right=end;
            while (left<right){
                mid=(left+right)>>1;
                if (d[mid]>=cur){
                    right=mid;
                }else {
                    left=mid+1;
                }
            }
            if (left==end){
                end++;
            }
            d[left]=cur;
        }
        return end;
    }
}
