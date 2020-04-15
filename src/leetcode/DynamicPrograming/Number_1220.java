package leetcode.DynamicPrograming;

/*
1220.统计元音字母序列的数目 （count-vowels-permutation）
给你一个整数 n，请你帮忙统计一下我们可以按下述规则形成多少个长度为 n 的字符串：

字符串中的每个字符都应当是小写元音字母（'a', 'e', 'i', 'o', 'u'）
每个元音 'a' 后面都只能跟着 'e'
每个元音 'e' 后面只能跟着 'a' 或者是 'i'
每个元音 'i' 后面 不能 再跟着另一个 'i'
每个元音 'o' 后面只能跟着 'i' 或者是 'u'
每个元音 'u' 后面只能跟着 'a'
由于答案可能会很大，所以请你返回 模 10^9 + 7 之后的结果。

 

示例 1：

输入：n = 1
输出：5
解释：所有可能的字符串分别是："a", "e", "i" , "o" 和 "u"。
示例 2：

输入：n = 2
输出：10
解释：所有可能的字符串分别是："ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" 和 "ua"。
示例 3：

输入：n = 5
输出：68
 

提示：

1 <= n <= 2 * 10^4

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/count-vowels-permutation
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_1220 {
    String name="1220.统计元音字母序列的数目 （count-vowels-permutation）";
    //每个字母后面跟着的不一样，
    //dp[i][j] 对应长度i，末尾为c[j]的元素 ，c={a,e,i,o,u}
    //注意 3*MOD > Integer.MAX_VALUE:2147483647 >2*MOD
    //所以两数想加时就要进行摸运算 ，三数相加可能超过int上限
    //执行较慢 17ms  击败51%
    // 空间消耗较少 击败100%
    int mod=(int)Math.pow(10,9)+7;
    public int countVowelPermutation(int n) {
        int[][] dp=new int[n+1][5];
//        int mod=(int)Math.pow(10,9)+7;
        for (int i=0;i<5;i++){
            dp[1][i]=1;
        }
        for (int i=1;i<n;i++){
            dp[i+1][0]+=((dp[i][1]+dp[i][2])%mod +dp[i][4])%mod;
            dp[i+1][1]+=(dp[i][0]+dp[i][2])%mod;
            dp[i+1][2]+=(dp[i][1]+dp[i][3])%mod;
            dp[i+1][3]+= dp[i][2]%mod;
            dp[i+1][4]+=(dp[i][2]+dp[i][3])%mod;
        }
        int res=(((dp[n][0]+dp[n][1])%mod+(dp[n][2]+dp[n][3])%mod)%mod+dp[n][4])%mod;
        return res;
    }
    //矩阵运算 快速幂法 迅速求出C*A^（n-1） 比下面的要复杂点，可以看Main函数中 countmi函数
    /*
    快速幂算法——可迅速求出a^b。其主要理论依据如下：

        1，当b为偶数时，a^b可以转为a^2的b/2次方。

        2，当b为奇数时，a^b可以转为a^2的b/2次方，再乘以a。
     */
    public int countVowelPermutation2(int n) {
        long ans = 0;
        int[][] cur = {{1, 1, 1, 1, 1}};
        int[][] mul;
        int[][] matrix = {{0, 1, 0, 0, 0},
                {1, 0, 1, 0, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 1, 0, 1},
                {1, 0, 0, 0, 0}};

        n=n-1;
        while (n!=0){
            if((n&1)==1){
                cur=mutiple(cur,matrix);
            }
            matrix=mutiple(matrix,matrix);
            n>>=1;
        }
        for (int i = 0; i < 5; i++) {
            ans = (ans + cur[0][i]) % mod;
        }

        return (int) ans;
    }
    //相乘
    public int[][] mutiple(int[][] a,int[][] b){
        int[][] temp;
        int m=a.length;
        int n=b[0].length;
        int l=a[0].length;
        long t=0;
        temp=new int[m][n];
        for(int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                for(int k=0;k<l;k++){
//                   a 和 b 都是int 型，相乘也会是int（会超范围） 所以先全部转换成long
                    t=((long) a[i][k]*(long) b[k][j])%mod;
                    temp[i][j]=(temp[i][j]+(int) t)%mod;
                }
            }
        }
        return temp;
    }
}
