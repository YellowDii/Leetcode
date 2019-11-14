package DynamicPrograming;
/*
486.预测赢家（predict-the-winner）
给定一个表示分数的非负整数数组。 玩家1从数组任意一端拿取一个分数，随后玩家2继续从剩余数组任意一端拿取分数，然后玩家1拿，……。
每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。

给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。

示例 1:

输入: [1, 5, 2]
输出: False
解释: 一开始，玩家1可以从1和2中进行选择。
如果他选择2（或者1），那么玩家2可以从1（或者2）和5中进行选择。如果玩家2选择了5，那么玩家1则只剩下1（或者2）可选。
所以，玩家1的最终分数为 1 + 2 = 3，而玩家2为 5。
因此，玩家1永远不会成为赢家，返回 False。
示例 2:

输入: [1, 5, 233, 7]
输出: True
解释: 玩家1一开始选择1。然后玩家2必须从5和7中进行选择。无论玩家2选择了哪个，玩家1都可以选择233。
最终，玩家1（234分）比玩家2（12分）获得更多的分数，所以返回 True，表示玩家1可以成为赢家。
注意:

1.  1 <= 给定的数组长度 <= 20.
2.  数组里所有分数都为非负数且不会大于10000000。
3.  如果最终两个玩家的分数相等，那么玩家1仍为赢家。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/predict-the-winner
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_486 {
    String predict_the_winner="486.预测赢家（predict-the-winner）\n";
    //dp[i][j]表示从i到j 先手能得到的最大分数
    //dp[i][j]=sum(nums[i:j])-min(dp[i][j-1],dp[i+1][j]); 确定i、j后 sum永不变，其次对手也是"聪明的"，所以对手的分数也是dp中取 另 要让自己得到最多就要让对方得到的少
    //该方法只要1ms
    public boolean PredictTheWinner(int[] nums) {
        int len=nums.length;
        int[][] dp=new int[len][len];
        int[][] sum=new int[len][len];
        for (int i=0;i<len;i++){
            for (int j=i;j<len;j++){
                if (j==i)
                    sum[i][j]=nums[i];
                else
                    sum[i][j]=nums[j]+sum[i][j-1];
            }
        }
        for (int i=0;i<len;i++){
            dp[i][i]=nums[i];
        }
        //斜着遍历 随对角线向右上角偏移
        for (int offset=1;offset<len;offset++){
            for (int i=0;i<len&&i+offset<len;i++){
                int j=i+offset;
                dp[i][j]=sum[i][j]-Math.min(dp[i][j-1],dp[i+1][j]);
            }
        }
        int point=sum[0][len-1]; //大于等于这个数 返回true
        if ((point&1)==1){
            //奇数
            point=point/2+1;
        }else
            point=point/2;
        return dp[0][len-1]>=point;
    }
}
