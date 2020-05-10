package leetcode;

import java.util.*;

public class Number_49 {
    /**
     * 49.字母异位词分组（group-anagrams）
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     *
     * 示例:
     *
     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出:
     * [
     *   ["ate","eat","tea"],
     *   ["nat","tan"],
     *   ["bat"]
     * ]
     * 说明：
     *
     *  1.所有输入均为小写字母。
     *  2.不考虑答案输出的顺序。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/group-anagrams
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> lists=new LinkedList<>();
        if (strs==null||strs.length<1){
            return lists;
        }
        Map<Word,List<String>> map=new HashMap<>();
        for (String s:strs){
            Word word=new Word(s);
            if (map.containsKey(word)){
                map.get(word).add(s);
            }else {
                List<String> l=new LinkedList<>();
                l.add(s);
                map.put(word,l);
            }
        }
        for (List<String> tmp:map.values()){
            lists.add(tmp);
        }
        return lists;
    }
    class Word {
        private final int[] chars;
        Word(String s){
            chars=new int[26];
            for (char c:s.toCharArray()){
                chars[c-'a']++;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Word word = (Word) o;
            return Arrays.equals(chars, word.chars);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(chars);
        }
    }
}
