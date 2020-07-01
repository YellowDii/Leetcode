package SwordOffer;

import java.util.HashSet;
import java.util.Set;

public class Leet048 {
    /**
     * 048. 最长不含重复字符的子字符串
     *
     * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     *
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     *
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *  
     *
     * 提示：
     *
     * s.length <= 40000
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //暴力
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set=new HashSet<>();
        int maxLen=0;
        char[] chars=s.toCharArray();
        for (int i=0;i<chars.length;i++){
            for (int j=i;j<chars.length;j++) {
                if (set.contains(chars[j])) {
                    maxLen = Math.max(maxLen, set.size());
                    set.clear();
                    break;
                } else {
                    set.add(chars[j]);
                }
            }
        }
        return Math.max(maxLen,set.size());
    }
    //滑动窗口
    public int lengthOfLongestSubstring2(String s) {
        if (s==null||s.length()<1){
            return 0;
        }
        Set<Character> set=new HashSet<>();
        int maxLen=0;
        char[] chars=s.toCharArray();
        int left=0,right=1;
        set.add(chars[left]);
        while (right<chars.length){
            if (set.contains(chars[right])){
                maxLen=Math.max(maxLen,set.size());
                set.remove(chars[left]);
                left++;
            }else {
                set.add(chars[right]);
                right++;
            }
        }
        return Math.max(maxLen,set.size());
    }
}
