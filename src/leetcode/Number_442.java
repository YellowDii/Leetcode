package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 442.数组中重复的数据
 * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
 *
 * 找到所有出现两次的元素。
 *
 * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
 *
 * 示例：
 *
 * 输入:
 * [4,3,2,7,8,2,3,1]
 *
 * 输出:
 * [2,3]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-duplicates-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_442 {
    //数组值有限制 所以我们对此可以做文章，对原数组自创索引
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list=new ArrayList<>();
        for (int i:nums){
            int index=(i-1)%nums.length;
            nums[index]+=nums.length;
        }
        for (int i:nums){
            if (nums[i]>2*nums.length){
                list.add(i+1);
            }
        }
        return list;
    }
}
