package leetcode;

public class Number_169 {
    /**
     * 169.多数元素(mojority-element)
     *
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     *
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: [3,2,3]
     * 输出: 3
     * 示例 2:
     *
     * 输入: [2,2,1,1,1,2,2]
     * 输出: 2
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/majority-element
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //因为多数元素肯定> n/2 如果碰到相同+1 不同-1 <0就换值 最后>0肯定就存在
    public int majorityElement(int[] nums) {
        if (nums.length==1){
            return nums[0];
        }
        int count=1;
        int cur=nums[0];
        for (int i=1;i<nums.length;i++){
            if (count>0){
                if (cur==nums[i]){
                    count++;
                }else {
                    count--;
                }
            }else {
                cur=nums[i];
                count=1;
            }
        }
        return cur;
    }
}
