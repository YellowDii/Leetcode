package leetcode;

public class Mian_56 {
    /**
     * 面试题56 - I. 数组中数字出现的次数
     *
     * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。
     * 请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [4,1,4,6]
     * 输出：[1,6] 或 [6,1]
     * 示例 2：
     *
     * 输入：nums = [1,2,10,4,1,4,3,3]
     * 输出：[2,10] 或 [10,2]
     *  
     *
     * 限制：
     *
     *   2 <= nums <= 10000
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //这个是剑指offer中的题
    //得用异或做
    public int[] singleNumbers(int[] nums) {
        int n=0;
        for (int i:nums){
            n^=i;
        }
        //然后通过这两个不同数的不同位进行分组 重复数不影响分组 因为它们必然会分到同一组 从而被异或为0
        int div=1;
        while ((div&n)==0){
            div<<=1;
        }
        int a=0,b=0;
        for (int i:nums){
            if ((div&i)==0){
                a^=i;
            }else {
                b^=i;
            }
        }
        return new int[]{a,b};
    }
}
