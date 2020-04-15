package leetcode.DynamicPrograming;


import java.util.Arrays;

/**
 * 给定一个长度为 n 的非空整数数组，找到让数组所有元素相等的最小移动次数。每次移动可以使 n - 1 个元素增加 1。
 * 示例:
 * 输入:
 * [1,2,3]
 * 输出:
 * 3
 * 解释:
 * 只需要3次移动（注意每次移动会增加两个元素的值）：
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_453 {
    //动态规划
    //先将nums排序，要使i个数字相同，就先要使前面i-1个比自己小的数字相同

    /**
     * 举个例子：
     * 3，11，18，25，32，42
     * 第一步需要8步，变为11，11，26，。。。。。
     * 第二步需要8+7 变为26，26，26，48。。。。
     * 第三步需要8+7+7
     * 第四步需要8+7+7+7
     */
    public int minMoves(int[] nums) {
        int result = 0;
        int move = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            move += nums[i + 1] - nums[i];
            result += move;
        }
        return result;
    }

    //n-1个数+1，等价于某个数-1
    //要求最小移动数，相当于每一个数都-1至最小值
    public int minMoves2(int[] nums) {
        int moves = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            moves += nums[i] - min;
        }
        return moves;
    }
}
