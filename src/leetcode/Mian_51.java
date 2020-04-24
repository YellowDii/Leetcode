package leetcode;

import java.util.Arrays;

public class Mian_51 {
    /**
     * 面试题51. 数组中的逆序对
     *
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: [7,5,6,4]
     * 输出: 5
     *  
     *
     * 限制：
     *
     * 0 <= 数组长度 <= 50000
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //这个可以用归并排序
    public int reversePairs(int[] nums) {
        if(nums==null||nums.length<1){
            return 0;
        }
        int[] copy=new int[nums.length];
        copy=Arrays.copyOf(nums,nums.length);
        return patitionCount(nums,copy,0,nums.length-1);
    }

    private int patitionCount(int[] nums,int[] copy, int start, int end) {
        if (start>=end){
            return 0;
        }
        int result=0;
        int mid=start+(end-start)/2;
        int left=patitionCount(copy,nums,start,mid);
        int right=patitionCount(copy,nums,mid+1,end);
        int index_l=mid,index_r=end;
        int tail=end;
        while (index_l>=start&&index_r>=mid+1){
            if (nums[index_l]>nums[index_r]){
                result+=index_r-mid;
                copy[tail--]=nums[index_l--];
            }else {
                copy[tail--]=nums[index_r--];
            }
        }
        while (index_l>=start){
            copy[tail--]=nums[index_l--];
        }
        while (index_r>=mid+1){
            copy[tail--]=nums[index_r--];
        }
        return result+left+right;
    }
}
