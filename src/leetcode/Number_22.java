package leetcode;

import java.util.LinkedList;
import java.util.List;

public class Number_22 {
    /**
     * 22.括号生成（generate-parenthesis）
     *
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * 
     *
     * 示例：
     *
     * 输入：n = 3
     * 输出：[
     *        "((()))",
     *        "(()())",
     *        "(())()",
     *        "()(())",
     *        "()()()"
     *      ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/generate-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //回溯
    public List<String> generateParenthesis(int n) {
        List<String> ans=new LinkedList<>();
        if (n<1){
            return ans;
        }
        StringBuilder cur_s=new StringBuilder("(");
        dfs(n-1,n,cur_s,ans);
        return ans;
    }

    /**
     *
     * @param leftP 剩余左括号数量
     * @param rightP 剩余右括号数量
     * @param cur_s 当前组合
     * @param ans 结果集
     */
    private void dfs(int leftP, int rightP, StringBuilder cur_s, List<String> ans) {
        if (leftP==0&&rightP==0){
            ans.add(new String(cur_s));
        }
        //剩余的左括号多一些 证明右括号生成多了
        if (leftP>rightP){
            return;
        }
        if (leftP>=1){
            dfs(leftP-1,rightP,cur_s.append("("),ans);
            cur_s.deleteCharAt(cur_s.length()-1);
        }
        if (rightP>=1){
            dfs(leftP,rightP-1,cur_s.append(")"),ans);
            cur_s.deleteCharAt(cur_s.length()-1);
        }
    }
    //将上面的StringBuilder改成数组实现会更快些
}
