package leetcode;

import java.util.Stack;

public class Number_20 {
    /**
     * 20.有效的括号（valid-parentheses）
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     *
     * 示例 1:
     *
     * 输入: "()"
     * 输出: true
     * 示例 2:
     *
     * 输入: "()[]{}"
     * 输出: true
     * 示例 3:
     *
     * 输入: "(]"
     * 输出: false
     * 示例 4:
     *
     * 输入: "([)]"
     * 输出: false
     * 示例 5:
     *
     * 输入: "{[]}"
     * 输出: true
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/valid-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //用栈
    public boolean isValid(String s) {
        boolean flag=true;
        Stack<Character> stack=new Stack<>();
        char[] chars=s.toCharArray();
        for (int i=0;i<chars.length;i++){
            if (chars[i]=='('||chars[i]=='{'||chars[i]=='['){
                stack.push(chars[i]);
            }else {
                if (stack.isEmpty()){
                    flag=false;
                    break;
                }
                char top=stack.pop();
                if (chars[i]==')'&&top!='('){
                    flag=false;
                    break;
                }else if (chars[i]=='}'&&top!='{'){
                    flag=false;
                    break;
                }else if (chars[i]==']'&&top!='['){
                    flag=false;
                    break;
                }
            }
        }
        if (!stack.isEmpty()){
            flag=false;
        }
        return flag;
    }

    public boolean isValid2(String s) {
        char[] charArray = new char[s.length() + 1];

        int p = 1;
        for(char c : s.toCharArray()){
            if(c == '(' || c == '{' || c == '[')
                charArray[p++] = c;
            else{
                p--;
                if(c == ')' && charArray[p] != '(')
                    return false;
                if(c == ']' && charArray[p] != '[')
                    return false;
                if(c == '}' && charArray[p] != '{')
                    return false;
            }
        }
        return p == 1;
    }
}
