package DynamicPrograming;
/*
72.编辑距离 （edit-distance）
给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。

你可以对一个单词进行如下三种操作：

插入一个字符
删除一个字符
替换一个字符
示例 1:

输入: word1 = "horse", word2 = "ros"
输出: 3
解释:
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')
示例 2:

输入: word1 = "intention", word2 = "execution"
输出: 5
解释:
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/edit-distance
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_72 {
    //动态规划 ，有点类似  1143.最长公共子串
    //dp[i][j]表示word1[0:i]与word2[0:j]的最少操作数 包含尾巴
    //if(word1[i]==word2[j]) dp[i][j]=dp[i-1][j-1]
    //if(word[i]!=word2[j])  dp[i][j]=min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1])+1
    //如果i==j dp[i][j]<=i+1
    //当然还要考虑边界情况
    public int minDistance(String word1, String word2) {
        if (word1.length()==0){
            return word2.length();
        }
        if (word2.length()==0){
            return word1.length();
        }
        int n1=word1.length();
        int n2=word2.length();
        int[][] dp=new int[n1][n2];
        dp[0][0]=word1.charAt(0)==word2.charAt(0)?0:1;
        for (int j=1;j<n2;j++){
            if (word1.charAt(0)==word2.charAt(j)){
                //字母c 跟字符串S比较时的特殊情况 如果包含该字母c（不管包含多少个该子母c） 最小操作数=S.length-1
                //不包含的最小操作数为S.length
                dp[0][j]=dp[0][j-1];
                while (++j<n2){
                    dp[0][j]=dp[0][j-1]+1;
                }
            }
            else
                dp[0][j]=dp[0][j-1]+1;
        }
        for (int i=1;i<n1;i++){
            if (word1.charAt(i)==word2.charAt(0)){
                dp[i][0]=dp[i-1][0];
                while (++i<n1){
                    dp[i][0]=dp[i-1][0]+1;
                }
            }
            else
                dp[i][0]=dp[i-1][0]+1;
        }
        for (int i=1;i<n1;i++){
            for (int j=1;j<n2;j++){
                if (word1.charAt(i)==word2.charAt(j)){
                    dp[i][j]=dp[i-1][j-1];
                }else {
                    if (i==j)
                        dp[i][j]=Math.min(Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1,i+1);
                    else
                        dp[i][j]=Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1;
                }
            }
        }
        return dp[n1-1][n2-1];
    }
}
