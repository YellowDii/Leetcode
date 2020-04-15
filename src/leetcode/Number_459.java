package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 459.重复的子字符串
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 *
 * 示例 1:
 *
 * 输入: "abab"
 *
 * 输出: True
 *
 * 解释: 可由子字符串 "ab" 重复两次构成。
 * 示例 2:
 *
 * 输入: "aba"
 *
 * 输出: False
 * 示例 3:
 *
 * 输入: "abcabcabcabc"
 *
 * 输出: True
 *
 * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/repeated-substring-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_459 {
    //1.暴力法，该方法能AC，但是用时较长，
    public boolean repeatedSubstringPattern1(String s) {
        if (s.length()==1)
            return false;
        for (int i=1;i<=s.length()/2;i++){
            if (isRepeatedSubStringPattern(s.substring(0,i),s))
                return true;
        }
        return false;
    }

    //2.暴力法(改进)，只判断s.length的因数字串是否为重复字串
    public boolean repeatedSubstringPattern2(String s) {
        int len=s.length();
        if (len==1)
            return false;
        List l=new ArrayList();
        for (int i=1;i<=len/2;i++)
            if (len%i==0)
                l.add(i);

        for (int i=0;i<l.size();i++){
            if (isRepeatedSubStringPattern(s.substring(0,(int)l.get(i)),s))
                return true;
        }
        return false;
    }

    //判断是否为重复子串
    public boolean isRepeatedSubStringPattern(String subs,String s){
        int k=0;
        //如果字串不是s.length的因数，直接返回false
        if (s.length()%subs.length()!=0)
            return false;
        for (int i=0;i<s.length();i++){
            k=i%subs.length();
            if (s.charAt(i)!=subs.charAt(k))
                return false;
        }
        return true;
    }
    //3.比较好的方法，参考评论区1行AC的代码
    // 从s+s入手，去掉s+s串的首尾两字符得到snew
    //如果snew中包含s则返回true，因为无法重复分割的字符串进行首尾相连后，破坏了首尾字段还能进行重复分割
    public boolean repeatedSubstringPattern3(String s) {
        String snew=s+s;
        return snew.substring(1,snew.length()-1).contains(s);
    }
    //4.判断是否为周期串，即满足f（x+T）=f（x）
    //T其实相当于重复字串的长度
    public boolean repeatedSubstringPattern4(String s) {
        int len=s.length();
        int t=0,i=0;
        for (t=1;t<=len/2;t++){
            if (len%t!=0)
                continue;
            for (i=1;i<len&&s.charAt(i%t)==s.charAt(i);i++);
            if (i==len)
                return true;
        }
        return false;
    }
}
