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
    int[] dp;
    //dp(i)表示i所需要的最少的完全平方数
    public int numSquares(int n) {
        dp=new int[n+1];
        for (int i=1;i<=Math.sqrt(n);i++){
            dp[i*i]=1;
        }
        return countNum(n);
    }
    //自底向下的搜索，该方法能AC全靠随缘  需要2000+ ms....
    private int countNum(int n) {
        if (n<=0)
            return 0;
        if (dp[n]!=0)
            return dp[n];
        int min=Integer.MAX_VALUE;
        for (int i=1;i<=n/2;i++){
            min=Math.min(min,countNum(i)+countNum(n-i));
        }
        dp[n]=min;
        return dp[n];
    }
    //上面的方法可以优化 上面一次次-1的搜索太慢了 而且没有必要
    //先初始值dp(i)=i，完全平方数设为1
    //然后： dp(i)=min(dp(i)，dp(i-j……2)+1)；
    //需要70+ ms
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
    private int countNum2(int n){
        //要么本就是平方数
        if (dp[n]<n)
            return dp[n];
        for (int j=(int) Math.sqrt(n);j>=1;j--){
            dp[n]=Math.min(dp[n],countNum2(n-j*j)+1);
        }
        return dp[n];
    }
    //最后是数学法
    //四平方定理： 任何一个正整数都可以表示成不超过四个整数的平方之和。 推论：满足四数平方和定理的数n（四个整数的情况），必定满足 n=4^a(8b+7)
    //只需要1ms
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
