package DynamicPrograming;

/*
650.只有两个键的键盘（2-keys-keyboard）
最初在一个记事本上只有一个字符 'A'。你每次可以对这个记事本进行两种操作：

Copy All (复制全部) : 你可以复制这个记事本中的所有字符(部分的复制是不允许的)。
Paste (粘贴) : 你可以粘贴你上一次复制的字符。
给定一个数字 n 。你需要使用最少的操作次数，在记事本中打印出恰好 n 个 'A'。输出能够打印出 n 个 'A' 的最少操作次数。

示例 1:

输入: 3
输出: 3
解释:
最初, 我们只有一个字符 'A'。
第 1 步, 我们使用 Copy All 操作。
第 2 步, 我们使用 Paste 操作来获得 'AA'。
第 3 步, 我们使用 Paste 操作来获得 'AAA'。
说明:

    1.n 的取值范围是 [1, 1000] 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/2-keys-keyboard
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_650 {
    String _2_keys_keyboard = "650.只有两个键的键盘（2-keys-keyboard）";

    //动态规划
    //dp(n)表示打印n个'A'的最少次数
    //dp(1)=0,dp(2)=2,dp(3)=3
    //dp(2n)=dp(n)+2,dp(2n+1)=dp(n+1)+2   n>1 n要变成2n只需要多一步复制粘贴 n+1变成2n+1也是如此
    //只能Copy All 如何取2n+1？
    public int minSteps(int n) {
        int[] dp = new int[n + 1];
        if (n == 1)
            return 0;
        if (n == 2)
            return 2;
        if (n == 3)
            return 3;
        dp[1] = 0;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= n; i++) {
            if ((i & 1) == 1) {
                //为奇数时 找最大因数
                int k=findfactor(i);
                if (k!=1)
                    dp[i]=dp[k]+(i/k-1);
                else {
                    dp[i]=i;
                }
            } else {
                dp[i] = dp[i / 2] + 2;
            }
        }
        return dp[n];
    }

    private int findfactor(int i) {
        for (int j=i/2;j>=1;j--){
            if(i%j==0)
                return j;
        }
        return 1;
    }
    //另一种方法 先判断是不是质数 如果是只能一次一次的粘贴
    //不是 找最大因子k 然后复制 粘贴（n/k-1）次 总共额外操作n/k次
    public int minSteps2(int n){
        if(n==1)
            return 1;
        //不能等于1 i=1相当于质数 应该直接返回n
        for (int i=n/2;i>1;i++){
            if(n%i==0){
                return minSteps2(i)+(n/i);
            }
        }
        return n;
    }
    //用dp+上面的递归
    public int minSteps3(int n){
        int[] dp=new int[n+1];
        return minSteps_(n,dp);
    }

    private int minSteps_(int n,int[] dp) {
        if (n==1){
            dp[1]=1;
            return dp[1];
        }
        if (dp[n]!=0)
            return dp[n];
        for (int i=n/2;i>1;i++){
            if (n%i==0){
                return dp[n]=minSteps_(i,dp);
            }
        }
        return dp[n]=n;
    }
}
