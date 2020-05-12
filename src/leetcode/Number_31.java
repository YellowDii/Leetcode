package leetcode;

import java.util.Arrays;

public class Number_31 {
    /**
     * 31.下一个排列（next-permutation）
     *
     * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
     *
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     *
     * 必须原地修改，只允许使用额外常数空间。
     *
     * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/next-permutation
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public void nextPermutation(int[] nums) {
        if (nums==null||nums.length<1){
            return;
        }
        int len=nums.length;
        for (int i=len-2;i>=0;i--){
            for (int j=len-1;j>i;j--){
                if (nums[i]<nums[j]){
                    int tmp=nums[i];
                    nums[i]=nums[j];
                    nums[j]=tmp;
                    sorted(nums,i+1,len);
                    return;
                }
            }
        }
        sorted(nums,0,len);
        return;
    }
    //将nums 排序 从l开始 到r结束 左闭右开
    private void sorted(int[] nums,int l,int r) {
        Arrays.sort(nums,l,r);
    }
}
