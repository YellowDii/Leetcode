package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Number_46 {
    /**
     * 46.全排列（permutations）
     * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
     *
     * 示例:
     *
     * 输入: [1,2,3]
     * 输出:
     * [
     *   [1,2,3],
     *   [1,3,2],
     *   [2,1,3],
     *   [2,3,1],
     *   [3,1,2],
     *   [3,2,1]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/permutations
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //好像这题 剑指offer有
    //回溯法  一般需要多重遍历的 都可用回溯法
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> list=new ArrayList<>();
        for (int i:nums){
            list.add(i);
        }
        track(nums.length,0,res,list);
        return res;
    }

    private void track(int length, int i, List<List<Integer>> res, List<Integer> list) {
        if (i==length){
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for(int j=i;j<length;j++){
            Collections.swap(list,i,j);
            track(length,i+1,res,list);
            Collections.swap(list,i,j);
        }
    }
}
