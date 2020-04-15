package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
290.单词规律
给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。

这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。

示例1:

输入: pattern = "abba", str = "dog cat cat dog"
输出: true
示例 2:

输入:pattern = "abba", str = "dog cat cat fish"
输出: false
示例 3:

输入: pattern = "aaaa", str = "dog cat cat dog"
输出: false
示例 4:

输入: pattern = "abba", str = "dog dog dog dog"
输出: false
说明:
你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。    

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/word-pattern
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_290 {
    //需要注意pattern=abba，str=cat cat cat cat 返回false
    //需要做一个扫描 确保map的每个value都不同
    //单独做扫描 代价较高，我们可以使用备忘录，确保每次添加新单词时不会重复
    //split方法可以自己写一个
    public boolean wordPattern(String pattern, String str) {
        Map<Character,String> map=new HashMap<>();
        Map<String,Boolean> memo=new HashMap<>();
        String[] words=str2Word(str);
        if (words.length!=pattern.length())
            return false;
        for (int i=0;i<pattern.length();i++){
            String cur=map.get(pattern.charAt(i));
            if (cur!=null&!words[i].equals(cur)){
                return false;
            }else if (cur==null){
                if (memo.get(words[i])!=null)
                    return false;
                memo.put(words[i],true);
                map.put(pattern.charAt(i),words[i]);
            }
        }
        return true;
    }
    public String[] str2Word(String str){
        return str.split(" ");
    }
    public String[] str2WordBySpace(String str){
        int len=0,cur=0;
        int start=0,end=0;
        List<String> list=new ArrayList<>();
        for (int i=0;i<str.length();i++){
            char c=str.charAt(i);
            if (c!=' '&&len==0&&i!=str.length()-1){
                start=i;
                len++;
                continue;
            }else if (i==str.length()-1){
                if (c==' ')
                    end=i;
                else {
                    if (len==0){
                        start=i;
                        len++;
                    }
                    end=str.length();
                }
            } else if (c!=' '){
                continue;
            }else if (c==' '&&len!=0){
                end=i;
            }else if(c==' '&&len==0){
                continue;
            }
            list.add(str.substring(start,end));
            len=0;
        }
        return list.toArray(new String[list.size()]);
    }
}
