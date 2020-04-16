package SwordOffer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test035 {
    /**
     * 题目：
     * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1.
     */
    //1.用哈希
    public static int FirstNotRepeatingChar(String str) {
        int len = str.length();
        if (len == 0)
            return -1;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            if (map.containsKey(str.charAt(i))) {
                int value = map.get(str.charAt(i));
                map.put(str.charAt(i), value + 1);
            } else {
                map.put(str.charAt(i), 1);
            }
        }
        for (int i = 0; i < len; i++) {
            if (map.get(str.charAt(i)) == 1)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(FirstNotRepeatingChar("google")); // l
        System.out.println(FirstNotRepeatingChar("aabccdbd")); // -1
        System.out.println(FirstNotRepeatingChar("abcdefg")); // a
        System.out.println(FirstNotRepeatingChar("gfedcba")); // g
        System.out.println(FirstNotRepeatingChar("zgfezcba")); // g
    }
}
