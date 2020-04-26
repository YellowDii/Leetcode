package leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Number_394 {
    /**
     * 394.字符串解码（decode-string）
     *
     * 给定一个经过编码的字符串，返回它解码后的字符串。
     *
     * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
     *
     * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
     *
     * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
     *
     * 示例:
     *
     * s = "3[a]2[bc]", 返回 "aaabcbc".
     * s = "3[a2[c]]", 返回 "accaccacc".
     * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/decode-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //用栈实现
    public String decodeString(String s) {
        List<String> stack_result=new LinkedList<>();
        List<Integer> stack_multi=new LinkedList<>();
        int multi=0;
        StringBuilder sb=new StringBuilder();
        for (Character c:s.toCharArray()){
            if (c>='0'&&c<='9'){
                multi=multi*10+Integer.parseInt(c+"");
            }else if (c=='['){
                stack_multi.add(multi);
                stack_result.add(sb.toString());
                multi=0;
                sb=new StringBuilder();
            }else if (c==']'){
                int cur_multi=stack_multi.remove(stack_multi.size()-1);
                StringBuilder tmp=new StringBuilder();
                for (int i=0;i<cur_multi;i++){
                    tmp.append(sb);
                }
                sb=new StringBuilder(stack_result.remove(stack_result.size()-1)+tmp);
            }else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    //这个效率更高
    public String decodeString2(String s) {
        return dfs(s,0)[0];
    }

    private String[] dfs(String s, int i){
        StringBuilder res = new StringBuilder();
        int multi = 0;
        while(i < s.length()){
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                multi = multi * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
            }else if(s.charAt(i) == '['){
                String[] tmp = dfs(s, i+1);
                i = Integer.parseInt(tmp[0]);
                while(multi > 0){
                    res.append(tmp[1]);
                    multi--;
                }
            }else if(s.charAt(i) == ']'){
                return new String[]{String.valueOf(i), res.toString()};
            }else{
                res.append(String.valueOf(s.charAt(i)));
            }
            i++;
        }
        return new String[]{res.toString()};
    }
}
