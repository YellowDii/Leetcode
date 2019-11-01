package DynamicPrograming;

/**
 * 动态规划系列
 * 1025.除数博弈
 * 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
 *
 * 最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
 *
 * 选出任一 x，满足 0 < x < N 且 N % x == 0 。
 * 用 N - x 替换黑板上的数字 N 。
 * 如果玩家无法执行这些操作，就会输掉游戏。
 *
 * 只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 false。假设两个玩家都以最佳状态参与游戏。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：2
 * 输出：true
 * 解释：爱丽丝选择 1，鲍勃无法进行操作。
 * 示例 2：
 *
 * 输入：3
 * 输出：false
 * 解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
 *  
 *
 * 提示：
 *
 * 1 <= N <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/divisor-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_1025 {
    /**
     * 单从数学角度分析的话
     * 1.奇数的约数为奇数，偶数的约数可奇可偶，且奇数减去它的约数必为偶数而且还大于2
     * 2.只有N=2的时候，只能选择1，然后游戏结束，所以无论多大，最终回合N=2
     * 3.要使自己N=2，就要让对方一直为奇数，这样对面就没有赢得机会
     * 4.如果N为偶数，爱丽丝先手，则需要一直选1即可，因为鲍勃面临的永远是奇数，而且反馈回来的还永远为偶数
     * 5.如果N为奇数，爱丽丝无论怎样先手，鲍勃面对的都是偶数，鲍勃就只需要一直选1即可。
     *
     */
    //这个方法最简便
    public boolean divisorGame1(int N) {
        return (N%2)==0;
    }

    /**
     * 动态规划，先从dp[1],dp[2]
     * dp[n] 表示N=n时的结果 true为爱丽丝赢
     * 那么dp[1]=false,dp[2]=true
     * 然后往后推，如果i中有约数j，dp[i-j]=false，那么dp[i]=true
     * 如果i中，所有dp[i-j]没有为false情况，那么dp[i]=false;
     */
    public boolean divisorGame2(int N){
        boolean[] dp=new boolean[N+1];
        if (N==1)
            return false;
        dp[1]=false;
        dp[2]=true;
        if (N>=3){
            for (int i=3;i<=N;i++){
                dp[i]=false;
                for (int j=1;j<=i/2;j++){
                    if (i%j==0&&dp[i-j]==false){
                        dp[i]=true;
                        break;
                    }
                }
            }
        }

        return dp[N];
    }
}
