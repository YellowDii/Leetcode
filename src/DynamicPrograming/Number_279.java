package DynamicPrograming;
/*
279.完全平方数（perfect-squares）
给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。

示例 1:

输入: n = 12
输出: 3
解释: 12 = 4 + 4 + 4.
示例 2:

输入: n = 13
输出: 2
解释: 13 = 4 + 9.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/perfect-squares
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_279 {
    String perfect_squares="279.完全平方数（perfect-squares）";
    //dp(n)代表数n所需要的完全平方数最少个数
    //可以用备忘录减少计算 记忆化搜索 该方法能AC随缘。。。 有时候能 ，但是执行时间还是过长
    int[] dp;
    public int numSquares(int n) {
        dp=new int[n+1];
        for (int i=1;i<=Math.sqrt(n);i++){
            dp[i*i]=1;
        }
        return countNum(n);
    }
    //这是自顶向下的
    private int countNum(int n) {
        if (dp[n]!=0)
            return dp[n];
        int min=Integer.MAX_VALUE;
        for (int i=1;i<=n/2;i++){
            min=Math.min(min,countNum(i)+countNum(n-i));
        }
        dp[n]=min;
        return dp[n];
    }
    //上面的计算虽然便利了所有分子情况，但是没必要，可以优化
    //首先dp[i]<=i，因为分为i个1是最大情况，要想情况变小，就要使用尽可能多的平方数
    //dp[i]=min(dp[i],dp[i-j^2]+1]  遍历j即可
    public int numSquares2(int n) {
        dp=new int[n+1];
        for (int i=1;i<=n;i++){
            dp[i]=i;
        }
        for (int i=1;i<=Math.sqrt(n);i++){
            dp[i*i]=1;
        }
        return countNum2(n);
    }
    //这次搜索测试为70ms 上面的要2000ms。。。
    private int countNum2(int n){
        //要么本就是平方数
        if (dp[n]<n)
            return dp[n];
        for (int j=(int) Math.sqrt(n);j>=1;j--){
            dp[n]=Math.min(dp[n],countNum(n-j*j)+1);
        }
        return dp[n];
    }
    //上面的不太好继续优化了，尝试新的思路 通过解题评论知道了四数平方和定理
    //四平方定理： 任何一个正整数都可以表示成不超过四个整数的平方之和。 推论：满足四数平方和定理的数n（四个整数的情况），必定满足 n=4^a(8b+7)
    //这个方法只要1ms  果然 如果能用数学知识解决问题，基本碾压其他方法
    public int numSquares3(int n){
        //压缩
        while (n%4==0){
            n/=4;
        }
        //如果满足公式 返回4
        if (n%8==7)
            return 4;
        //判断压缩后的数能不能由2个平方数或1个构成
        int a=0;
        while (a*a<=n){
            int b=(int) Math.sqrt(n-a*a);
            if (b*b+a*a==n){
                if (a!=0&&b!=0){
                    return 2;
                }else
                    return 1;
            }
            a++;
        }
        return 3;
    }
}
