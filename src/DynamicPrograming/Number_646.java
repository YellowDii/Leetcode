package DynamicPrograming;

import java.util.Arrays;
import java.util.Comparator;

/*
646.最长数对链（maximum-length-of-pair-chain）
给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。

现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。

给定一个对数集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。

示例 :

输入: [[1,2], [2,3], [3,4]]
输出: 2
解释: 最长的数对链是 [1,2] -> [3,4]
注意：

给出数对的个数在 [1, 1000] 范围内。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximum-length-of-pair-chain
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_646 {
    String name="646.最长数对链（maximum-length-of-pair-chain）";
    //动态规划 先进行排序
    // dp[i]表示尾巴为排序后的数组pairs[i]为尾巴的个数
    public int findLongestChain(int[][] pairs) {
        int n = pairs.length;
        if (n == 0) return 0;
        //先按照第一个元素排序，然后按照按照第二个元素从小到大排序
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });
        int res = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (pairs[i][0] > pairs[j][1]) {
                    dp[i] = dp[j] + 1;
                    break;
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

}
