package leetcode.DynamicPrograming;

/*
474.一和零（ones-and-zeroes）
在计算机界中，我们总是追求用有限的资源获取最大的收益。

现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。

你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。

注意:

给定 0 和 1 的数量都不会超过 100。
给定字符串数组的长度不会超过 600。
示例 1:

输入: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
输出: 4

解释: 总共 4 个字符串可以通过 5 个 0 和 3 个 1 拼出，即 "10","0001","1","0" 。
示例 2:

输入: Array = {"10", "0", "1"}, m = 1, n = 1
输出: 2

解释: 你可以拼出 "10"，但之后就没有剩余数字了。更好的选择是拼出 "0" 和 "1" 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/ones-and-zeroes
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_474 {
    //dfs+剪枝  有点像买礼包问题
    int min; //表示剩下最少的数组个数
    String[] strings;
    int index;//当前遍历到的位置

    //该方法太慢了
    public int findMaxForm(String[] strs, int m, int n) {
        min = strs.length;
        strings = strs;
        index = 0;
        dfs(strs.length, 0, 0, m, n);
        return strs.length - min;
    }

    //k当前指向的数组
    void dfs(int remain, int buy, int k, int m, int n) {
        //当支配的0、1不够时
        if (m < 0 && n < 0) {
            min = Math.min(min, remain);
            return;
        }
        if (k >= strings.length || index >= strings.length) {
            min = Math.min(min, remain);
            return;
        }
        int m_cur = 0;//当前指向的字符串0的个数
        int n_cur = 0;//当前指向的字符串1的个数
        String s = strings[k];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                m_cur++;
            } else {
                n_cur++;
            }
        }
        //从当前index遍历 拼该数组
        if (index + 1 <= min && buy - index - 1 > -min && m >= m_cur && n >= n_cur) {
            dfs(remain - 1, buy + 1, k + 1, m - m_cur, n - n_cur);
        }
        //从当前index遍历 不拼
        if (index + 1 <= min & buy - index - 1 > -min)
            dfs(remain, buy, k + 1, m, n);
        index++;
        if (index + 1 <= min & buy - index - 1 > -min)
            dfs(remain, buy, k + 1, m, n);
        index--;
    }

    //三维dp
    //dp[i][j][k]表示用j个0 k个1 表示s[0..i]能够拼最多的数组个数
    //自底向下 dp[i][j][k]=max(dp[i-1][j][k],1+dp[i-1][j-mi][k-ni])  mi表示第i个字符串中0的个数 ni表示1的个数
    public int findMaxForm2(String[] strs, int m, int n) {
        int[][][] dp = new int[strs.length][m + 1][n + 1];
        for (int i = 0; i < strs.length; i++) {
            int mi = 0;
            int ni = 0;
            for (int j = 0; j < strs[i].length(); j++) {
                if (strs[i].charAt(j) == '0')
                    mi++;
                else
                    ni++;
            }
            for (int j = m; j >= 0; j--) {
                for (int k = n; k >= 0; k--) {
                    if (j >= mi && k >= ni) {
                        if (i == 0)
                            dp[i][j][k] = 1;
                        else
                            dp[i][j][k] = Math.max(dp[i - 1][j][k], 1+dp[i-1][j - mi][k - ni]);
                    } else {
                        if (i == 0)
                            dp[i][j][k] = 0;
                        else
                            dp[i][j][k] = dp[i - 1][j][k];
                    }
                }
            }
        }
        return dp[strs.length-1][m][n];
    }
    //上述还可以优化 我们发现dp[i][][]只与dp[i-1][][]有关
    //所以可以去掉第一维
    public int findMaxForm3(String[] strs, int m, int n) {
        if (strs.length==0||(m==0&&n==0)){
            return 0;
        }
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < strs.length; i++) {
            int mi = 0;
            int ni = 0;
            for (int j = 0; j < strs[i].length(); j++) {
                if (strs[i].charAt(j) == '0')
                    mi++;
                else
                    ni++;
            }
            for (int j=m;j>=mi;j--){
                for (int k=n;k>=ni;k--){
                    dp[j][k]=Math.max(dp[j][k],1+dp[j-mi][k-ni]);
                }
            }
        }
        return dp[m][n];
    }
}
