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
    //滑动窗口 思路是对的 但好像有点bug....
    public String minWindow(String s, String t) {
        //记录t的信息
        Map<Character,Integer> map=new HashMap<>();
        for (char c:t.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        int left=0,right=0;
        char[] chars=s.toCharArray();
        int res=Integer.MAX_VALUE;//长度
        String res_s=new String();
        boolean flag=false;
        while (left<=right&&right<chars.length){
            //初始找right 只找一次
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
                for (int i=right+1;i<chars.length;i++){
                    if (map.containsKey(chars[i])){
                        right=i;
                        map.put(chars[right],map.get(chars[right])-1);
                        break;
                    }
                }
            }
            //如果right到头了
            if (right>=chars.length-1&&map.get(chars[left])>=0) {
                break;
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

    public String minWindow2(String s, String t) {
        int M = s.length();
        int N = t.length();

        if (M * N == 0 || M < N) {
            return "";
        }

        Map<Character, Integer> tCount = new HashMap<>();
        for (int i = 0; i < N; i++) {
            tCount.merge(t.charAt(i), 1, Integer::sum);
        }

        int left = 0;
        int right = 0;
        // 目标字符串包含的不同字符的数量
        int required = tCount.size();
        // 当前窗口已经满足的不同字符的数量
        int formed = 0;
        // 保存当前的满足条件的最短子串，第一位表示长度，第二位表示起始下标，第三位表示终止下标。
        int[] ans = {-1, -1, -1};
        Map<Character, Integer> windowCount = new HashMap<>();

        while (right < M) {
            char c = s.charAt(right);

            // 遇到t中不包含的字符无需处理
            if (!tCount.containsKey(c)) {
                right++;
                continue;
            }

            windowCount.merge(c, 1, Integer::sum);
            if (windowCount.get(c).equals(tCount.get(c))) {
                formed++;
            }

            while (formed == required && left <= right) {
                // 更新最短子串
                if (ans[0] == -1 || ans[0] > right - left + 1) {
                    ans[0] = right - left + 1;
                    ans[1] = left;
                    ans[2] = right;
                }

                c = s.charAt(left);
                left++;

                // 遇到t中不包含的字符无需处理
                if (!tCount.containsKey(c)) {
                    continue;
                }

                windowCount.merge(c, -1, Integer::sum);
                if (windowCount.get(c) < tCount.get(c)) {
                    formed--;
                }
            }
            right++;
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
    //跟上面思路一样 但是使用int[] 存储 因为测试用例的ASCII码<256
    public String minWindow3(String s, String t) {
        if(s==null||t==null||s.length()<1||t.length()<1)return "";
        int i=0,j=0,left=0,right=0;
        int minLen = s.length() + 1;
        int count = t.length();
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        int[] map = new int[256];
        for(char c:tc)
            map[c]++;
        while(i<sc.length){
            if(map[sc[i]] > 0)
                count--;
            map[sc[i]]--;
            i++;
            while(count == 0){
                if(minLen > i - j){
                    minLen = i - j;
                    left = j;
                    right = i;
                }
                map[sc[j]]++;
                if(map[sc[j]] > 0)
                    count++;
                j++;
            }
        }
        return minLen == s.length() + 1 ? "" : s.substring(left,right);
    }
}
