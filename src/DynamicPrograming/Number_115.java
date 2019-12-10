package DynamicPrograming;

import java.util.Arrays;

/*
115.不同的子序列（distinct-subsequences）

给定一个字符串 S 和一个字符串 T，计算在 S 的子序列中 T 出现的个数。

一个字符串的一个子序列是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）

示例 1:

输入: S = "rabbbit", T = "rabbit"
输出: 3
解释:

如下图所示, 有 3 种可以从 S 中得到 "rabbit" 的方案。
(上箭头符号 ^ 表示选取的字母)

rabbbit
^^^^ ^^
rabbbit
^^ ^^^^
rabbbit
^^^ ^^^
示例 2:

输入: S = "babgbag", T = "bag"
输出: 5
解释:

如下图所示, 有 5 种可以从 S 中得到 "bag" 的方案。
(上箭头符号 ^ 表示选取的字母)

babgbag
^^ ^
babgbag
^^    ^
babgbag
^    ^^
babgbag
  ^  ^^
babgbag
    ^^^

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/distinct-subsequences
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_115 {
    String distinct_subsequences="115.不同的子序列（distinct-subsequences）";
    //解题思路： 其实就是寻找s中有多少个不同的t
    //我们可以先用一个dp记录满足t的子序列  递增子序列变形 不顾现在不是递增是满足t的顺序的序列
    //例如 babgbag dp={1,2,1,3,1,2,3}
    public int numDistinct(String s, String t) {
        int len=s.length();
        int[] dp=new int[len];
        for (int i=0;i<len;i++){
            if (s.charAt(i)==t.charAt(0))
                dp[i]=1;
        }
        for (int i=1;i<t.length();i++){
            for (int j=0;j<s.length();j++){
                if (s.charAt(j)==t.charAt(i)){
                    for (int k=0;k<j;k++){
                        if (s.charAt(k)==t.charAt(i-1)){
                            dp[j]=i+1;
                            break;
                        }
                    }
                }
            }
        }
        //然后求满足要求子序列个数
        //这时候要重构dp
        int[] dp_res=new int[len];
        //dp_res初试状态
        for (int i=0;i<len;i++){
            if (dp[i]==1){
                dp_res[i]=1;
            }
        }
        for (int i=1;i<len;i++){
            int count=0;
            for (int j=0;j<i;j++) {
                if (dp[j]==dp[i]-1){
                    dp_res[i]+=dp_res[j];
                }
                if (dp[j]==dp[i])
                    count++;
            }
            //其中很多细节还要处理！！！！！
            //气死我了 有bug  没办法处理t中有连续字段的情况
        }
        int res=0;
        for (int i=0;i<len;i++){
            if (dp[i]==t.length()){
                res+=dp_res[i];
            }
        }
        return res;
    }
    //答案的dp思路
    public int numDistinct2(String s, String t) {
        int[][] dp = new int[t.length() + 1][s.length() + 1];
        for (int j = 0; j < s.length() + 1; j++) dp[0][j] = 1;
        for (int i = 1; i < t.length() + 1; i++) {
            for (int j = 1; j < s.length() + 1; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                else dp[i][j] = dp[i][j - 1];
            }
        }
        return dp[t.length()][s.length()];
    }
    /**
     * 列主序 先构造字典 就不用遍历t了
     * 这样就优化成了答案上的2ms的了
     * @param s
     * @param t
     * @return
     */
    public int numDistinct4(String s, String t) {
        // dp[0]表示空串
        int[] dp = new int[t.length() + 1];
        // dp[0]永远是1，因为不管S多长，都只能找到一个空串，与T相等
        dp[0] = 1;

        //t的字典
        int[] map = new int[128];
        Arrays.fill(map, -1);

        //从尾部遍历的时候可以遍历 next类似链表 无重复值时为-1，
        //有重复时例如从rabbit的b开始索引在map[b] = 2 next[2] 指向下一个b的索引为3
        // for (int j = t.length() - 1; j >= 0; j--) {
        //     if (t.charAt(j) == s.charAt(i)) {
        //        dp[j + 1] += dp[j];
        //     }
        // }
        //这段代码的寻址就可以从map[s.charAt(i)] 找到索引j 在用next[j] 一直找和 s.charAt(i)相等的字符 其他的就可以跳过了
        //所以这个代码的优化 关键要理解 上面的一维倒算
        int[] nexts = new int[t.length()];
        for(int i = 0 ; i < t.length(); i++){
            int c = t.charAt(i);
            nexts[i] = map[c];
            map[c] = i;
        }

        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            for(int j = map[c]; j >= 0; j = nexts[j]){
                dp[j + 1] += dp[j];
            }
        }
        return dp[t.length()];
    }
}
