package leetcode.DynamicPrograming;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
392.判断子序列（is-subsequence）
给定字符串 s 和 t ，判断 s 是否为 t 的子序列。

你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。

字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。

示例 1:
s = "abc", t = "ahbgdc"

返回 true.

示例 2:
s = "axc", t = "ahbgdc"

返回 false.

后续挑战 :

如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？

致谢:

特别感谢 @pbrother 添加此问题并且创建所有测试用例。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/is-subsequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_392 {
    String is_subsequence="392.判断子序列（is-subsequence）\n";
    //做麻烦了 该方法要110+ ms
    //用HashMap反而麻烦了 直接用26个list
    public boolean isSubsequence(String s, String t) {
        Map map=new HashMap<Character, ArrayList<Integer>>();
        char[] chars=t.toCharArray();
        for (int i=0;i<chars.length;i++){
            char c=chars[i];
            List list=(ArrayList<Integer>)map.get(c);
            if (list==null){
                List l=new ArrayList<Integer>();
                l.add(i);
                map.put(c,l);
            }else {
                list.add(i);
                map.put(c,list);
            }
        }
        char[] chars1=s.toCharArray();
        int cur=-1;
        List<Integer> list;
        for (int i=0;i<chars1.length;i++){
            list=(ArrayList<Integer>)map.get(chars1[i]);
            if (list==null)
                return false;
            else {
                int j=0;
                //这里可以用二分查找优化
                while (j<list.size()){
                    if (list.get(j)<=cur){
                        j++;
                    }else {
                        cur=list.get(j);
                        break;
                    }
                }
                if (j==list.size())
                    return false;
            }
        }
        return true;
    }
    //17ms  直接遍历即可
    public boolean isSubsequence2(String s,String t){
        if(s.length()==0)
            return true;
        int a=0;
        int len=s.length();
        for (int i=0;i<t.length();i++){
            if (s.charAt(a)==t.charAt(i))
                a++;
            if (a==len)
                return true;
        }
        return false;
    }
}
