package leetcode;

import java.util.LinkedList;
import java.util.List;

public class Number_78 {
    /**
     * 78.子集（subsets）
     *
     *给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     *
     * 说明：解集不能包含重复的子集。
     *
     * 示例:
     *
     * 输入: nums = [1,2,3]
     * 输出:
     * [
     *   [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/subsets
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    List<List<Integer>> res;
    public List<List<Integer>> subsets(int[] nums) {
        res=new LinkedList<>();
        res.add(new LinkedList<>());
        dfs(nums,0,nums.length-1,new LinkedList<Integer>(),true);
        return res;
    }
    //从start开始向后找 flag=true时表示为寻找开头 为false 相当于在子查询中 这样有利于不重复
    private void dfs(int[] nums, int start, int end,LinkedList list,boolean flag) {
        if (start>end){
            return;
        }
        list.add(nums[start]);
        res.add(new LinkedList<>(list));
        List tmp=new LinkedList(list);
        for(int i=start+1;i<=end;i++){
            dfs(nums,i,end,list,false);
            list=new LinkedList(tmp);
        }
        list.clear();
        if (flag){
            dfs(nums,start+1,end,list,true);
        }
    }
}
