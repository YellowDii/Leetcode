package leetcode.DynamicPrograming;

/*
1227.飞机座位分配概率
有 n 位乘客即将登机，飞机正好有 n 个座位。第一位乘客的票丢了，他随便选了一个座位坐下。

剩下的乘客将会：

如果他们自己的座位还空着，就坐到自己的座位上，

当他们自己的座位被占用时，随机选择其他座位
第 n 位乘客坐在自己的座位上的概率是多少？

 

示例 1：

输入：n = 1
输出：1.00000
解释：第一个人只会坐在自己的位置上。
示例 2：

输入: n = 2
输出: 0.50000
解释：在第一个人选好座位坐下后，第二个人坐在自己的座位上的概率是 0.5。
 

提示：

1 <= n <= 10^5

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/airplane-seat-assignment-probability
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_1227 {
    //动态规划 dp[i]表示人数为i个人时，i号乘客坐上i的概率
    // dp[i] 分为以下情况： ① 第一个人选择1号座位，则以后的乘客都能对应上座位，此概率为 1/i
    //                   ② 第一个人没有选择1号座位，且到i-1个人为止，i位置都没有被占座
    //  第二种情况要细致分析一下， 第一次选座有i-2种情况，即选择k号座 (2<=k<=i-1)，
    //  那么第i号坐i座的问题相当于从[1:k],[k:i]这i-1个位置使i-1坐i-1位置（不过现在的 i-1号 位置是 i号，i-1号人是2~i号人） 即dp[i-1]
    public double nthPersonGetsNthSeat(int n) {
        double[] dp=new double[n+1];
        dp[1]=1;
        for (int i=2;i<=n;i++)
            dp[i]=(1+(i-2)*dp[i-1])/(double)i;
        return dp[n];
    }
}
