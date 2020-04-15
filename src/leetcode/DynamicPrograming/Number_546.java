package leetcode.DynamicPrograming;
/*
546.移除盒子(remove-boxes)
给出一些不同颜色的盒子，盒子的颜色由数字表示，即不同的数字表示不同的颜色。
你将经过若干轮操作去去掉盒子，直到所有的盒子都去掉为止。每一轮你可以移除具有相同颜色的连续 k 个盒子（k >= 1），这样一轮之后你将得到 k*k 个积分。
当你将所有盒子都去掉之后，求你能获得的最大积分和。

示例 1：
输入:

[1, 3, 2, 2, 2, 3, 4, 3, 1]
输出:

23
解释:

[1, 3, 2, 2, 2, 3, 4, 3, 1]
----> [1, 3, 3, 4, 3, 1] (3*3=9 分)
----> [1, 3, 3, 3, 1] (1*1=1 分)
----> [1, 1] (3*3=9 分)
----> [] (2*2=4 分)
 

提示：盒子的总数 n 不会超过 100。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/remove-boxes
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_546 {
    String remove_boxes="546.移除盒子(remove-boxes)";
    //动态规划 。。。。 想法错了，只考虑了少数情况， 想了2小时没想到二维dp怎么做
    public int removeBoxes1(int[] boxes) {
        int len=boxes.length;
        if (len==0)
            return 0;
        int[] dp=new int[len];
        dp[0]=1;
        int res=0;
        for (int i=1;i<len;i++){
            int j=0;boolean flag=true;
            while (j<=i){
                //每次遍历结果只改变一次
                if (boxes[j]==boxes[i]){
                    if (flag){
                        res+=(boxes[j]+1)*(boxes[j]+1)-boxes[j]*boxes[j];
                        flag=false;
                    }
                    boxes[j]++;
                }
                j++;
            }
        }
        return res;
    }
    //三维dp  参考官方解答 太难想了
    public int removeBoxes(int[] boxes) {
        int[][][] dp = new int[100][100][100];
        return calculatePoints(boxes, dp, 0, boxes.length - 1, 0);
    }
    /*
    对于元素 \text{dp[l][r][k]}dp[l][r][k]，ll 表示起始下标，rr 表示结束下表，kk 表示与第 r 个元素相似的元素个数，可以在最后一起合并并得到最终的分数存入 \text{dp[l][r][k]}dp[l][r][k] 中。
    现在考虑如何填上 dpdp 数组。考虑上面提到的子序列，我们先对 \text{dp[l][r][k]}dp[l][r][k] 赋初值，考虑合并最后 k + 1k+1 个相似元素，
    然后处理之后的剩余数组。因此，初值为 {dp[l][r][k]} = dp[l][r-1][0] + (k+1)*(k+1)dp[l][r][k]=dp[l][r−1][0]+(k+1)∗(k+1)。
    然后我们合并所有后缀相似元素，所以数值 0 是因为第 r-1 元素之后不存在相似元素。
    但上面的情况不是唯一情况，我们可以考虑一个更优解法，如果找到一些 boxes[l:r]boxes[l:r] 中的相似元素和尾部元素一起合并。
    最后，dp的更新式为：dp[l][r][k]=max(dp[l][r][k],dp[l][i][k+1]+dp[i+1][r-1][0])。
    最后，dp[0][n-1][0] 就是最后的结果。

作者：LeetCode
链接：https://leetcode-cn.com/problems/remove-boxes/solution/yi-chu-he-zi-by-leetcode/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    private int calculatePoints(int[] boxes, int[][][] dp, int l, int r, int k) {
        if (l > r) return 0;
        if (dp[l][r][k] != 0) return dp[l][r][k];
        while (r > l && boxes[r] == boxes[r - 1]) {
            r--;
            k++;
        }
        dp[l][r][k] = calculatePoints(boxes, dp, l, r - 1, 0) + (k + 1) * (k + 1);
        for (int i = l; i < r; i++) {
            if (boxes[i] == boxes[r]) {
                dp[l][r][k] = Math.max(dp[l][r][k], calculatePoints(boxes, dp, l, i, k + 1) + calculatePoints(boxes, dp, i + 1, r - 1, 0));
            }
        }
        return dp[l][r][k];
    }

}
