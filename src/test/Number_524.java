package test;


import java.util.*;

/**
 *
 * 524. 通过删除字母匹配到字典里最长单词
 * 给定一个字符串和一个字符串字典，找到字典里面最长的字符串，该字符串可以通过删除给定字符串的某些字符来得到。
 * 如果答案不止一个，返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串。
 *
 * 示例 1:
 *
 * 输入:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 *
 * 输出:
 * "apple"
 * 示例 2:
 *
 * 输入:
 * s = "abpcplea", d = ["a","b","c"]
 *
 * 输出:
 * "a"
 * 说明:
 *
 * 所有输入的字符串只包含小写字母。
 * 字典的大小不会超过 1000。
 * 所有输入的字符串长度不会超过 1000。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_524 {
    //1.暴力法，先找能够转换的，再找最长且字典序较小的 //1.暴力法，先找能够转换的，再找最长且字典序较小的
    //本来以为这算是暴力法，看评论发现属于官方解答的后两种
    //官方解答给出的前两种是遍历删减的所有情况，更加暴力。。。
    public String findLongestWord1(String s, List<String> d) {
        List<String> res=new ArrayList<String>();
        for (String s1:d){
            if (convertOk(s,s1))
                res.add(s1);
        }
        if (res.size()==0)
            return "";
        String result=res.get(0);
        for(String s2:res){
            if (s2.length()>result.length()){
                result=s2;
            }
            else if (s2.length()==result.length()){
                result=s2.compareTo(result)<0?s2:result;
            }
        }

        return result;
    }
    //2.暴力法改进，直接从最长的字符串且字典序较小的进行判断
    public String findLongestWord2(String s, List<String> d) {
        d.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length()>o2.length())
                    return -1;
                if (o1.length()<o2.length())
                    return 1;
                if (o1.length()==o2.length()){
                    if (o1.compareTo(o2)<0)
                        return -1;
                    if (o1.compareTo(o2)>0)
                        return 1;
                }
                return 0;
            }
        });
        for (String s1:d){
            if (convertOk(s,s1))
                return s1;
        }

        return "";
    }
    //对方法1进行改进
    public String findLongestWord3(String s,List<String> d){
        String max_str="";
        for (String s1:d){
            if (convertOk(s,s1)){
                if (s1.length()>max_str.length()||(s1.length()==max_str.length()&&s1.compareTo(max_str)<0))
                    max_str=s1;
            }
        }
        return max_str;
    }

    //s是需要删减的字符串
    public boolean convertOk(String s,String s1){
        int len=s.length(),len1=s1.length();
        int j=0;
        if (len<len1)
            return false;
        for (int i=0;i<len&&j<len1;i++){
            if(s.charAt(i)==s1.charAt(j)){
                j++;
            }
        }
        return j==len1;
    }

}
