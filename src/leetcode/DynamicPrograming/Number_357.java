package leetcode.DynamicPrograming;
/*
357.计算各个位数不同的数字个数（count-numbers-with-unique-digits）
给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10^n 。

示例:

输入: 2
输出: 91
解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/count-numbers-with-unique-digits
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_357 {
    String count_numbers_with_unique_digits="357.计算各个位数不同的数字个数（count-numbers-with-unique-digits）\n";
    //dp(n)表示n位数中 各个位数相同数k  1<k<=n的情况
    //dp(1)=0 dp(2)=9 dp(n)=9+9+9*dp(n-1)+8*dp(n-1)+10*(i-2)*dp[i-1]  不包括含连续的00的情况
    //存在重复情况 不可取
    public int countNumbersWithUniqueDigits(int n) {
        if (n==0)
            return 1;
        if (n==1)
            return 10;
        if (n==2)
            return 9;
        int[] dp=new int[n+1];
        dp[0]=0;dp[1]=0;dp[2]=10;
        for (int i=3;i<=n;i++){
            dp[n]=10*(n-1)*(dp[n-1]-1)+9*dp[n-1]-9*(n-1);
        }
        return (int) Math.pow(10,n)-dp[n];
    }
    //考虑重复情况太麻烦了，直接考虑不重复的情况
    /*
    n=1时 res=10
    n=2时 res=10+9*9  第一位为1~9 第二位为不与第一位相同的 10-1
    n=3时 res=10+9*9+9*9*8
    ....
     */
    public int countNumbersWithUniqueDigits2(int n){
        int[] dp=new int[11];
        dp[0]=1;dp[1]=10;
        if (n<=1)
            return dp[n];
        for (int i=2;i<=n;i++){
            int a=9;
            for (int j=0;j<i-1;j++){
                a*=9-j;
            }
            dp[i]=dp[i-1]+a;
        }
        if (n>10)
            return dp[10];
        return dp[n];
    }
}

