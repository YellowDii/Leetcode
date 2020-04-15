package leetcode.DynamicPrograming;

/*
246.丑数 Ⅱ（ugly-number-ii）
编写一个程序，找出第 n 个丑数。

丑数就是只包含质因数 2, 3, 5 的正整数。

示例:

输入: n = 10
输出: 12
解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
说明:  

1.  1是丑数。
2.  n不超过1690。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/ugly-number-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_246 {
    String ugly_number_ii="246.丑数 Ⅱ（ugly-number-ii）\n";
    //如何确定没进入dp的三个最小丑数 3个下标对应*2 *3 *5 的指针
    //dp(i)表示第i个丑数
    public int nthUglyNumber(int n){
        int[] dp=new int[n+1];
        dp[1]=1;
        int index_2=1,index_3=1,index_5=1;
        int value2=0,value3=0,value5=0;
        for (int i=2;i<=n;i++){
            //每次比较目前最小的三个还没进入dp的丑数
            value2=2*dp[index_2];
            value3=3*dp[index_3];
            value5=5*dp[index_5];
            dp[i]=Math.min(Math.min(value2,value3),value5);
            if (dp[i]==value2)
                index_2++;
            if (dp[i]==value3)
                index_3++;
            if (dp[i]==value5)
                index_5++;
        }
        return dp[n];
    }

}
