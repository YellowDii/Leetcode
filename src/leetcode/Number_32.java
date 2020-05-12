package leetcode;

import java.util.Stack;

public class Number_32 {
    /**
     * 32.最长有效括号（longest-valid-parentheses）
     * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
     *
     * 示例 1:
     *
     * 输入: "(()"
     * 输出: 2
     * 解释: 最长有效括号子串为 "()"
     * 示例 2:
     *
     * 输入: ")()())"
     * 输出: 4
     * 解释: 最长有效括号子串为 "()()"
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //面向测试编程 越改越错 还是看下解答吧 我想到的用栈
    public static int longestValidParentheses(String s) {
        int ans=0;
        int cur_len=0;//当前 ） 的个数
        int cur=0;//表示当前（ 的个数
        char[] chars=s.toCharArray();
        int[] len=new int[s.length()];
        for (int i=0;i<chars.length;i++){
            if (chars[i]=='('){
                cur++;
                if (cur_len>0){
                    cur_len--;
                }
            }else {
                if (cur<=0){
                    continue;
                }else {
                    cur--;
                    cur_len++;
                    if (cur>=0){
                        //重置 并更新ans
                        if ((i-cur_len*2>=0)&&len[i-cur_len*2]!=0&&len[i-1]==0){
                            len[i]=len[i-cur_len*2]+cur_len*2;
                            len[i-1]=len[i];
                        }else if((i-cur_len*2>=0)&&len[i-cur_len*2]!=0){
                            len[i]=len[i-cur_len*2]+cur_len*2;
                            len[i-len[i]+1]=len[i];
                        }else {
                            len[i]=cur_len*2;
                            len[i-len[i]+1]=len[i];
                        }
                        ans=Math.max(ans,len[i]);
                        if (cur==0){
                            cur_len=0;
                        }
                    }
                }
            }
        }
        return ans;
    }

    /**
     * else if ((i-cur_len>=0)&&len[i-cur_len]!=0&&cur>=0){
     *                             len[i]=len[i-cur_len]+cur_len*2;
     *                         }else
     *
     */
    public static void main(String[] args) {
        longestValidParentheses("(()())");
    }

    public int longestValidParentheses2(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

    //动态规划 很快
    //考虑 s="(()())"  更好理解
    public int longestValidParentheses3(String s) {
        int maxans = 0;
        int dp[] = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }


}
