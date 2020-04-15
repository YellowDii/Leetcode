package leetcode.DynamicPrograming;
/*
343.整数拆分(integer-break)
给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。

示例 1:

输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1。
示例 2:

输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
说明: 你可以假设 n 不小于 2 且不大于 58。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/integer-break
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_343 {
    //dp[i]=max(dp(2)*dp(i-2),dp(3)*dp(i-3), ... ,dp(i/2 取下整)*dp(i-i/2))
    //另外得考虑dp(i)<i 的情况，这时候默认用i代替dp(i)
    //自顶向下 记忆化搜索 方法2是将此想法转化为自底向上
    Integer memo[];//备忘录
    public int integerBreak(int n) {
        memo=new Integer[59];
        memo[0]=0;memo[1]=0;memo[2]=1;memo[3]=2;memo[4]=4;
        return dp(n);
    }
    int dp(int n){
        if (memo[n]!=null)
            return memo[n];
        int ans=0;
        for (int i=2;i<=n/2;i++){
            int left=Math.max(dp(i),i);
            int right=Math.max(dp(n-i),n-i);
            ans=Math.max(ans,left*right);
        }
        memo[n]=ans;
        return ans;
    }
    public int integerBreak2(int n){
        memo[0] = 0;
        memo[1] = 0;
        memo[2] = 1;
        for (int i = 3; i <= n; i++) {
            memo[i]=0;
            for ( int j = 1; j <= i - 1; j++) {
                memo[i] = Math.max(memo[i], Math.max(j * memo[i - j], j * (i - j)));
            }
        }
        return memo[n];
    }
    //数学方法 该题实质为max[f(x)=(n/x)^x]=？ n为正整数
    // 上面函数求导 得知x=e 时值最大 ，e=2.71828... -> 3
    //所以拆分尽量多的3 所以讨论一般情况：n=3k,3k+1,3k+2的情况
    //边界情况：n<=3 max=n-1
    // n=3k  max=3^k k>=2
    // n=3k+1 max=4*3^(k-1) k>=1 4=3+1
    // n=3k+2 max=2*3^k k>=1
    public int integerBreak3(int n) {
        if (n<=3)
            return n-1;
        int k=n/3;
        int r=n%3;
        if (r==0){
            return (int)Math.pow(3,k);
        }else if (r==1){
            return (int)Math.pow(3,k-1)*4;
        }else{
            return  (int)Math.pow(3,k)*2;
        }
    }
}
