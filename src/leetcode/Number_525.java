package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 525.连续数组
 * 给定一个二进制数组, 找到含有相同数量的 0 和 1 的最长连续子数组（的长度）。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
 * 示例 2:
 *
 * 输入: [0,1,0]
 * 输出: 2
 * 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contiguous-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_525 {
    //暴力法，直接计算每个子序列的0、1长度
    //该方法超时了
    public int findMaxLength1(int[] nums) {
        int maxLen=0;
        for (int start=0;start<nums.length;start++){
            int zeros=0,ones=0;
            for (int end=start;end<nums.length;end++){
                if (nums[end]==0)
                    zeros++;
                else
                    ones++;
                if (zeros==ones)
                    maxLen=Math.max(maxLen,end-start+1);
            }
        }
        return maxLen;
    }
    //方法2
    //遇到0:-1， 遇到1:+1，每次count出现不同数字时记录下来，并且记录下标index。
    //后面遇到相同的count更新maxLen
    public int findMaxLength2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        //初始时没遇到任何数，count=0，下标记为-1
        map.put(0, -1);
        int maxlen = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 1 ? 1 : -1);
            if (map.containsKey(count)) {
                maxlen = Math.max(maxlen, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return maxlen;

    }
}
