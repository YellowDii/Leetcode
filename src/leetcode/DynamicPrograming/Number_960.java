package leetcode.DynamicPrograming;

import java.util.Arrays;

/*
960.删列造序（delete-columns-to-make-sorted-iii）

给定由 N 个小写字母字符串组成的数组 A，其中每个字符串长度相等。

选取一个删除索引序列，对于 A 中的每个字符串，删除对应每个索引处的字符。

比如，有 A = ["babca","bbazb"]，删除索引序列 {0, 1, 4}，删除后 A 为["bc","az"]。

假设，我们选择了一组删除索引 D，那么在执行删除操作之后，最终得到的数组的行中的每个元素都是按字典序排列的。

清楚起见，A[0] 是按字典序排列的（即，A[0][0] <= A[0][1] <= ... <= A[0][A[0].length - 1]），A[1] 是按字典序排列的（即，A[1][0] <= A[1][1] <= ... <= A[1][A[1].length - 1]），依此类推。

请你返回 D.length 的最小可能值。

 

示例 1：

输入：["babca","bbazb"]
输出：3
解释：
删除 0、1 和 4 这三列后，最终得到的数组是 A = ["bc", "az"]。
这两行是分别按字典序排列的（即，A[0][0] <= A[0][1] 且 A[1][0] <= A[1][1]）。
注意，A[0] > A[1] —— 数组 A 不一定是按字典序排列的。
示例 2：

输入：["edcba"]
输出：4
解释：如果删除的列少于 4 列，则剩下的行都不会按字典序排列。
示例 3：

输入：["ghi","def","abc"]
输出：0
解释：所有行都已按字典序排列。
 

提示：

    1. 1 <= A.length <= 100
    2. 1 <= A[i].length <= 100

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/delete-columns-to-make-sorted-iii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_960 {
    String delete_columns_to_make_sorted_iii="960.删列造序（delete-columns-to-make-sorted-iii）";
    //dp[i]表示所有字段都满足的递增子序列长度
    //例如 abcde abcee acccc dp={1,2,2,2,2}
    //abcde abcaa ： dp={1,2,3,1,1}
    //abcde : dp={1,2,3,4,5}
    public int minDeletionSize(String[] A) {
        int W = A[0].length();
        int[] dp = new int[W];
        Arrays.fill(dp, 1);
        for (int i = W-2; i >= 0; --i)
            //确保需要保留的位置满足所有字段的增序
            search: for (int j = i+1; j < W; ++j) {
                for (String row: A)
                    if (row.charAt(i) > row.charAt(j))
                        continue search;

                dp[i] = Math.max(dp[i], 1 + dp[j]);
            }

        int kept = 0;
        for (int x: dp)
            kept = Math.max(kept, x);
        return W - kept;
    }
    //下面这个好理解点
    // 从头到尾遍历 上面的是从尾巴到头的遍历
    public int minDeletionSize2(String[] A) {
        int m = A[0].length();
        int[] dp = new int[m];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < m; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (compare(A, j, i)) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return m - max;
    }

    private boolean compare(String[] s, int a, int b) {
        for (String word : s) {
            if (word.charAt(a) > word.charAt(b)) {
                return false;
            }
        }
        return true;
    }
}
