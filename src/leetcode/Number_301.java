package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Number_301 {
    /**
     * 301.删除无效的括号(remove-invalid-parentheses)
     *
     * 删除最小数量的无效括号，使得输入的字符串有效，返回所有可能的结果。
     *
     * 说明: 输入可能包含了除 ( 和 ) 以外的字符。
     *
     * 示例 1:
     *
     * 输入: "()())()"
     * 输出: ["()()()", "(())()"]
     * 示例 2:
     *
     * 输入: "(a)())()"
     * 输出: ["(a)()()", "(a())()"]
     * 示例 3:
     *
     * 输入: ")("
     * 输出: [""]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-invalid-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public List<String> removeInvalidParentheses(String s) {
        HashSet<String> set=new HashSet<>();
        int index=0;
        int leftDelete=0;
        int rightDelete=0;
        int leftCount=0;
        //删除最小数量的无效括号 所以先要统计这个最小值 然后来回溯
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftDelete++;
            } else if (c == ')') {
                if (leftDelete > 0) {
                    leftDelete--;
                } else {
                    rightDelete++;
                }
            }
        }
        dfs(s, index, leftCount, leftDelete, rightDelete, set, new StringBuilder());
        ArrayList<String> list = new ArrayList<>();
        list.addAll(set);
        return list;

    }

    private void dfs(String s, int index, int leftCount, int leftDelete, int rightDelete, HashSet<String> set, StringBuilder sb) {
        if (index == s.length()) {
            if (leftDelete == 0 && rightDelete == 0 && leftCount == 0) {
                set.add(sb.toString());

            }
            return;
        }
        char c = s.charAt(index);
        if (c == '(') {
            // 如果是'{',那么要么删除,要么保留.
            // 如果删除
            if (leftDelete > 0) {
                StringBuilder tmp = new StringBuilder(sb);
                dfs(s, index + 1, leftCount, leftDelete - 1, rightDelete, set, tmp);
            }
            // 不删,或者没有可以删除的
            StringBuilder tmp = new StringBuilder(sb);
            tmp.append(c);
            dfs(s, index + 1, leftCount + 1, leftDelete, rightDelete, set, tmp);
        } else if (c == ')') {
            // 如果是'}', 要么删除,要么在前面有'{'的时候保留.否则只能删除
            if (rightDelete > 0) {
                //删除的情况
                StringBuilder tmp = new StringBuilder(sb);
                dfs(s, index + 1, leftCount, leftDelete, rightDelete - 1, set, tmp);
            }
            if (leftCount > 0) {
                //在前面有'{'的时候保留
                StringBuilder tmp = new StringBuilder(sb);
                tmp.append(c);
                dfs(s, index + 1, leftCount - 1, leftDelete, rightDelete, set, tmp);
            } else {
                return;
            }
        } else {
            StringBuilder tmp = new StringBuilder(sb);
            tmp.append(c);
            dfs(s, index + 1, leftCount, leftDelete, rightDelete, set, tmp);
        }

    }
}
