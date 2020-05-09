package leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Number_76 {
    /**
     * 76.最小覆盖子串（minimum-window-substring）
     * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
     *
     * 示例：
     *
     * 输入: S = "ADOBECODEBANC", T = "ABC"
     * 输出: "BANC"
     * 说明：
     *
     * 如果 S 中不存这样的子串，则返回空字符串 ""。
     * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-window-substring
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //滑动窗口
    public String minWindow(String s, String t) {
        //记录t的信息
        Map<Character,Integer> map=new HashMap<>();
        for (char c:t.toCharArray()){
            map.put(c,map.getOrDefault(c,1)+1);
        }
        int left=0,right=0;
        char[] chars=s.toCharArray();
        int res=Integer.MAX_VALUE;//长度
        String res_s=new String();
        boolean flag=false;
        while (left<=right&&right<chars.length){
            while (right<chars.length&&!flag){
                if (right<chars.length&&map.containsKey(chars[right])){
                    map.put(chars[right],map.get(chars[right])-1);
                    if (isFind(map)){
                        flag=true;
                        break;
                    }
                }
                right++;
            }
            //确保left指向的是t中的字符
            while (left<=right&&!map.containsKey(chars[left])){
                left++;
            }
            if (flag&&map.get(chars[left])<0){
                while (map.get(chars[left])<0){
                    //这种情况更新left
                    map.put(chars[left],map.get(chars[left])+1);
                    while (left<=right&&!map.containsKey(chars[left])){
                        left++;
                    }
                    if (res>right-left+1){
                        res=right-left+1;
                        res_s=new String(chars,left,right-left+1);
                    }
                }
            }
            if (flag&&res>right-left+1){
                res=right-left+1;
                res_s=new String(chars,left,right-left+1);
            }
            //重新更新right
            if (flag&&right<chars.length){
                //如果right到头了
                if (right>=chars.length-1) {
                    return res_s;
                }
                for (int i=right+1;i<chars.length;i++){
                    if (map.containsKey(chars[i])){
                        right=i;
                        map.put(chars[right],map.get(chars[right])-1);
                        break;
                    }
                }
            }

        }
        return res_s;
    }

    private boolean isFind(Map<Character, Integer> map) {
        for (Integer i:map.values()){
            if (i>0){
                return false;
            }
        }
        return true;
    }
}
