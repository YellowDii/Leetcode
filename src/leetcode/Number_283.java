package leetcode;

public class Number_283 {
    /**
     * 283.移动零（move-zeroes）
     *
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * 示例:
     *
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * 说明:
     *
     *  1.必须在原数组上操作，不能拷贝额外的数组。
     *  2.尽量减少操作次数。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/move-zeroes
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public void moveZeroes(int[] nums) {
        int index_0=0,index_1=0;//分别记录从左到右的0的下标和1的下标
        boolean ready_0=false,ready_1=false;
        for (int i=0;i<nums.length;i++){
            if (nums[i]==0&&!ready_0){
                index_0=i;
                ready_0=true;
            }else if (nums[i]!=0&&!ready_1&&ready_0){//确保前面有0 才记录index_1
                index_1=i;
                ready_1=true;
            }
            if (ready_0&&ready_1){
                ready_1=false;
                nums[index_0]=nums[index_0]^nums[index_1];
                nums[index_1]=nums[index_0]^nums[index_1];
                nums[index_0]=nums[index_0]^nums[index_1];
                while (index_0<nums.length&&nums[index_0]!=0){
                    index_0++;
                }
            }
        }
    }
    //上面思路是交换 这个是平移
    public void moveZeroes2(int[] nums) {
        int now0 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                now0++;
                continue;
            }
            if (now0 > 0){
                nums[i - now0] = nums[i];
            }
        }
        for (int i = nums.length - now0; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
