package leetcode.DynamicPrograming;

/**
 * 动态规划系列
 * 712.两个字符串的最小ASCII删除和
 *
 * 给定两个字符串s1, s2，找到使两个字符串相等所需删除字符的ASCII值的最小和。
 *
 * 示例 1:
 *
 * 输入: s1 = "sea", s2 = "eat"
 * 输出: 231
 * 解释: 在 "sea" 中删除 "s" 并将 "s" 的值(115)加入总和。
 * 在 "eat" 中删除 "t" 并将 116 加入总和。
 * 结束时，两个字符串相等，115 + 116 = 231 就是符合条件的最小和。
 * 示例 2:
 *
 * 输入: s1 = "delete", s2 = "leet"
 * 输出: 403
 * 解释: 在 "delete" 中删除 "dee" 字符串变成 "let"，
 * 将 100[d]+101[e]+101[e] 加入总和。在 "leet" 中删除 "e" 将 101[e] 加入总和。
 * 结束时，两个字符串都等于 "let"，结果即为 100+101+101+101 = 403 。
 * 如果改为将两个字符串转换为 "lee" 或 "eet"，我们会得到 433 或 417 的结果，比答案更大。
 * 注意:
 *
 * 0 < s1.length, s2.length <= 1000。
 * 所有字符串中的字符ASCII值在[97, 122]之间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-ascii-delete-sum-for-two-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_712 {
    //动态规划 dp[i][j]表示字符串s1[i:]与s2[j:]达到相等所需要删除的最小ASCII和
    //返回dp[0][0]就是答案了
    //状态转移方程：dp[i][j] = min(dp[i + 1][j] + s1.asciiAtPos(i), dp[i][j + 1] + s2.asciiAtPos(j))
    public int minimumDeleteSum(String s1, String s2) {
        int i1=s1.length();
        int i2=s2.length();
        int[][] dp=new int[i1+1][i2+1];
        dp[i1][i2]=0;
        for (int i=i1-1;i>=0;i--){
            dp[i][i2]=dp[i+1][i2]+(int)s1.charAt(i);
        }
        for (int i=i2-1;i>=0;i--){
            dp[i1][i]=dp[i1][i+1]+(int)s2.charAt(i);
        }
        for (int i=i1-1;i>=0;i--){
            for (int j=i2-1;j>=0;j--){
                if (s1.charAt(i)==s2.charAt(j)){
                    dp[i][j]=dp[i+1][j+1];
                }else {
                    dp[i][j]=Math.min(dp[i+1][j]+(int)s1.charAt(i),dp[i][j+1]+(int)s2.charAt(j));
                }
            }
        }
        return dp[0][0];
    }
    //字符串1的ASCII和+字符串2的ASCII和-2*最终转换的字符串ASCII和 就是返回结果
    //所以要找的最终字符串ASCII越大越好，但并不一定代表越长越好
    //按照上面的分析 等价于求两字符串的最大ASCII和的子序列，是最长子序列变种问题
//    public int minimumDeleteSum2(String s1, String s2) {
//        int i1=s1.length();
//        int i2=s2.length();
//        for ()
//    }
}
