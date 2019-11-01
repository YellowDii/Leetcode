package test;

/**
 * 389.找不同
 *
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 *
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 *
 * 请找出在 t 中被添加的字母。
 *
 *  
 *
 * 示例:
 *
 * 输入：
 * s = "abcd"
 * t = "abcde"
 *
 * 输出：
 * e
 *
 * 解释：
 * 'e' 是那个被添加的字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-difference
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_389 {
    //该题与771题（宝石与石头）相似
    //在S中出现一次+1，t中出现一次-1，最后为-1的就是多余的字符
    public char findTheDifference(String s, String t) {
        int[] memoS=new int[26];
        for (int i=0;i<s.length();i++){
            char c=s.charAt(i);
            memoS[c-'a']++;
        }
        for (int i=0;i<t.length();i++){
            char c=t.charAt(i);
            memoS[c-'a']--;
        }
        for (int i=0;i<26;i++){
            if (memoS[i]==-1)
                return (char)( 'a'+i);
        }
        return '!';
    }
    //换成foreach
    public char findTheDifference2(String s, String t) {
        int[] memoS=new int[26];
        for (char c:s.toCharArray()){
            memoS[c-'a']++;
        }
        for (char c:t.toCharArray()){
            memoS[c-'a']--;
        }
        for (int i=0;i<26;i++){
            if (memoS[i]==-1)
                return (char)( 'a'+i);
        }
        return '!';
    }
    //用亦或的特性，当（a xor b）xor b =a,这里xor指亦或运算
    //按位亦或运算： 对等长二进制模式按位或二进制数的每一位执行逻辑按位异或操作. 操作的结果是如果某位不同则该位为1, 否则该位为0
    //所以亦或两次相同值，结果不变
    public char findTheDifference3(String s, String t) {
        int result=0;
        for (char c:s.toCharArray()){
            result ^= c;
        }
        for (char c:t.toCharArray()){
            result ^= c;
        }
        return (char)result;
    }
}
