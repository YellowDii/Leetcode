package leetcode;

public class Number_50 {
    /**
     * 50.Pow(x,n)(powx-n)
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
     *
     * 示例 1:
     *
     * 输入: 2.00000, 10
     * 输出: 1024.00000
     * 示例 2:
     *
     * 输入: 2.10000, 3
     * 输出: 9.26100
     * 示例 3:
     *
     * 输入: 2.00000, -2
     * 输出: 0.25000
     * 解释: 2-2 = 1/22 = 1/4 = 0.25
     * 说明:
     *
     * -100.0 < x < 100.0
     * n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/powx-n
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    // 遇到 2^（-2147483648） 输出为无穷 正确为0.0 感觉是n的长度有限？ 官方解答用Long 存的n
    public double myPow(double x, int n) {

        if (n==0){
            return 1;
        }
        boolean flag=true;
        double half;
        if(n<0){
            flag=false;
            half=myPow(x,(-n)/2);
        }else {
            half=myPow(x,n/2);
        }
        double res;
        if ((n&1)==1){
            res=half*half*x;
        }else {
            res=half*half;
        }
        if (flag){
            return res;
        }else {
            return 1.0/res;
        }
    }
    public double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }

    public double myPow2(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

}
