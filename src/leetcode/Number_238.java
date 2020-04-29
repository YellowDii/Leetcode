package leetcode;

public class Number_238 {
    /**
     * 238.除自身以外数组的乘积（product-of-array-except-self）
     *
     * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，
     * 其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
     *
     * 示例:
     *
     * 输入: [1,2,3,4]
     * 输出: [24,12,8,6]
     *  
     *
     * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
     *
     * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
     *
     * 进阶：
     * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/product-of-array-except-self
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //不要使用除法....
    //可以用left[] 记录从左到右的乘积
    //right[] 记录从右到左的乘积
    // result[i]=left[i-1]*right[i+1];
    public int[] productExceptSelf(int[] nums) {
        int len=nums.length;
        int[] left=new int[len];
        left[0]=nums[0];
        for (int i=1;i<len;i++){
            left[i]=left[i-1]*nums[i];
        }
        int[] right=new int[len];
        right[len-1]=nums[len-1];
        for (int i=len-2;i>=0;i--){
            right[i]=right[i+1]*nums[i];
        }
        int[] result=new int[len];
        for (int i=0;i<len;i++){
            if (i==0){
                result[i]=right[i+1];
            }else if (i==len-1){
                result[i]=left[i-1];
            }else {
                result[i]=left[i-1]*right[i+1];
            }
        }
        return result;
    }
    //如果是线性空间的话 这个思路需要灵机一动
    public int[] productExceptSelf2(int[] nums){
        int len=nums.length;
        int[] result=new int[len];
        int k=1;
        //使每个结果等于left[i-1]
        for (int i=0;i<len;i++){
            result[i]=k;
            k*=nums[i];
        }
        //现在要乘right[i+1]了
        k=1;
        for (int i=len-1;i>=0;i--){
            result[i]*=k;
            k*=nums[i];
        }
        return result;
    }
}
