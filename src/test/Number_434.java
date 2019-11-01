package test;

/**
 * 434.字符串中的单词数
 * 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
 * <p>
 * 请注意，你可以假定字符串里不包括任何不可打印的字符。
 * <p>
 * 示例:
 * <p>
 * 输入: "Hello, my name is John"
 * 输出: 5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-segments-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_434 {
    //该方法遇到了测试用例", , , ,        a, eaefa"
    //预期输出为6......
    //该题是要求用空格隔开的那些词的数量，词不仅包括字母，也包括符号
    public int countSegments(String s) {
        int len = 0, count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (ischar(s.charAt(i)))
                len++;
            else if (len != 0) {
                len = 0;
                count++;
            }
        }
        return len == 0 ? count : ++count;
    }

    boolean ischar(char c) {
        if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || c == '\'' || c == '-')
            return true;
        else
            return false;
    }

    public int countSegments2(String s) {
        int segmentCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if ((i == 0 || s.charAt(i - 1) == ' ') && s.charAt(i) != ' ') {
                segmentCount++;
            }
        }
        return segmentCount;
    }

    //将之前的方法稍微修改,判断字符不是空格
    public int countSegments3(String s) {
        int len = 0, count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ')
                len++;
            else if (len != 0) {
                len = 0;
                count++;
            }
        }
        return len == 0 ? count : ++count;
    }
}
