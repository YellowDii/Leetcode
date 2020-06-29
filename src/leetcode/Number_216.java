package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Number_216 {
    /**
     * 216. 组合总和 III(combination-sum-iii)
     *
     * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
     *
     * 说明：
     *
     * 所有数字都是正整数。
     * 解集不能包含重复的组合。 
     * 示例 1:
     *
     * 输入: k = 3, n = 7
     * 输出: [[1,2,4]]
     * 示例 2:
     *
     * 输入: k = 3, n = 9
     * 输出: [[1,2,6], [1,3,5], [2,3,4]]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/combination-sum-iii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //dfs + 剪枝
    List<List<Integer>> ans;
    public List<List<Integer>> combinationSum3(int k, int n) {
        ans=new ArrayList<>();
        if (n<0||n>45||k<1){
            return ans;
        }
        dfs(k,n,1,new ArrayList<>());
        return ans;
    }

    //从i往后遍历
    private void dfs(int k, int goal ,int i, List<Integer> array) {
        if (goal<0||k<0){
            return;
        }
        if (k==0&&goal==0){
            ans.add(new ArrayList<>(array));
            return;
        }
        for (int j=i;j<=9;j++){
            array.add(j);
            dfs(k-1,goal-j,j+1,array);
            array.remove(array.size()-1);
        }
    }
}
