package DynamicPrograming;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 动态规划系列
 * 647.回文子串
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 *
 * 示例 1:
 *
 * 输入: "abc"
 * 输出: 3
 * 解释: 三个回文子串: "a", "b", "c".
 * 示例 2:
 *
 * 输入: "aaa"
 * 输出: 6
 * 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
 * 注意:
 *
 * 输入的字符串长度不会超过1000。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindromic-substrings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_647 {
    //暴力法
    public int countSubstrings(String s){
        int len=s.length();
        int ans=0;
        for (int i=0;i<len;i++){
            for (int j=i;j<len;j++){
                if (isPalindrome(s.substring(i,j+1)))
                  ans++;
            }
        }
        return ans;
    }
    //暴力法改进
    public int countSubstrings2(String s){
        int len=s.length();
        int ans=0;
        for (int i=0;i<len;i++){
            for (int j=i;j<len;j++){
                if (s.charAt(i)==s.charAt(j)){
                    if (isPalindrome(s.substring(i,j+1)))
                        ans++;
                }
            }
        }
        return ans;
    }
    boolean isPalindrome(String s){
        if(s.isEmpty())
            return false;
        int len=s.length();
        for (int i=0;i<=len/2;i++){
            if (s.charAt(i)!=s.charAt(len-1-i)){
                return false;
            }
        }
        return true;
    }
    //用HashMap 记录每个字符出现的位置
    //事实发现这个内存消耗变多，速度却没有提升，思想还是方法2。。。。。
    //很失败 我犯傻了
    public int countSubstrings_bad(String s){
        HashMap<Character,ArrayList<Integer>> map = new HashMap<>();
        int ans=0;
        for (int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if (map.get(c)==null){
                List l = new ArrayList<Integer>();
                l.add(i);
                map.put(c, (ArrayList<Integer>) l);
                ans++;
            }else {
                ArrayList<Integer> l = map.get(c);
                l.add(i);
                for (Integer j:l){
                    if (isPalindrome(s.substring(j,i+1)))
                        ans++;
                }
                map.put(c,(ArrayList<Integer>)l);
            }
        }
        return ans;
    }
    //动态规划+备忘录
    //men[i][j]就是判断s[i:j]是不是回文
    //dp[i][j]代表s[i:j]的回文子串数量 最终返回s[0][len-1]
    //dp[i][j]=dp[i][j-1]+dp[i+1][j]-dp[i+1][j-1]+(mem[i][j]?1:0);
    public int countSubstrings3(String s){
        int len=s.length();
        int[][] dp=new int[len][len];
        boolean[][] mem=new boolean[len][len];
        for (int i=0;i<len;i++){
            dp[i][i]=1;
            mem[i][i]=true;
        }
        for (int offset=1;offset<len;offset++){
            for (int i=0;i+offset<len;i++){
                int j=i+offset;
                if (offset>=2){
                    if (s.charAt(i)!=s.charAt(j)){
                        dp[i][j]=dp[i][j-1]+dp[i+1][j]-dp[i+1][j-1];
                    }else {
                        if (mem[i+1][j-1])
                            mem[i][j]=true;
                        dp[i][j]=dp[i][j-1]+dp[i+1][j]-dp[i+1][j-1]+(mem[i][j]?1:0);
                    }
                }else{
                    if (s.charAt(i)==s.charAt(j))
                        mem[i][j]=true;
                    dp[i][j]=dp[i][j-1]+dp[i+1][j]+(mem[i][j]?1:0);
                }
            }
        }
        return dp[0][len-1];
    }
    //中心扩展
    public int countSubstrings4(String s){
        int res=0;
        for (int i=0;i<s.length();i++){
            //两种情况 奇数串、偶数串
            res+=countPalindrome(s,i,i);
            res+=countPalindrome(s,i,i+1);
        }
        return res;
    }
    public int countPalindrome(String s,int left,int right){
        int count=0;
        while (left>=0&&right<s.length()&&s.charAt(left--)==s.charAt(right++)){
            count++;
        }
        return count;
    }
}
