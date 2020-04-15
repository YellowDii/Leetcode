package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个单词列表，只返回可以使用在键盘同一行的字母打印出来的单词。
 *
 *  
 *
 * 示例：
 *
 * 输入: ["Hello", "Alaska", "Dad", "Peace"]
 * 输出: ["Alaska", "Dad"]
 *  
 *
 * 注意：
 *
 * 你可以重复使用键盘上同一字符。
 * 你可以假设输入的字符串将只包含字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/keyboard-row
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_500 {
    //记得大写字母也需要加入对应值中
    public String[] findWords(String[] words) {
        Map<Integer,String> m=new HashMap<Integer,String>();
        String s1="qwertyuiopQWERTYUIOP";
        String s2="asdfghjklASDFGHJKL";
        String s3="zxcvbnmZXCVBNM";
        m.put(1,s1);
        m.put(2,s2);
        m.put(3,s3);
        List<String> list=new ArrayList<String>();
        int index=0;

        for (String s:words){
            int tmp=1;
            boolean flag=true;
            for (int i=0;i<s.length();i++){
                if (i==0&&m.get(1).contains(s.charAt(i)+"")){
                    tmp=1;
                }else if (i==0&&m.get(2).contains(s.charAt(i)+"")){
                    tmp=2;
                }else if (i==0&&m.get(3).contains(s.charAt(i)+"")){
                    tmp=3;
                }else if (i!=0){
                    if(!m.get(tmp).contains(s.charAt(i)+"")){
                        index++;
                        flag=false;
                        break;
                    }
                }
            }
            if (flag){
                list.add(s);
                index++;
            }
        }
        String[] result=new String[list.size()];
        for (int i=0;i<list.size();i++){
            result[i]=list.get(i);
        }
        return result;
    }
}
