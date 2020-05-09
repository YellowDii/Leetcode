package leetcode;

public class Number_69 {
    /**
     * 69.平方根（sqrtx）
     * 实现 int sqrt(int x) 函数。
     *
     * 计算并返回 x 的平方根，其中 x 是非负整数。
     *
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     *
     * 示例 1:
     *
     * 输入: 4
     * 输出: 2
     * 示例 2:
     *
     * 输入: 8
     * 输出: 2
     * 说明: 8 的平方根是 2.82842...,
     *      由于返回类型是整数，小数部分将被舍去。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sqrtx
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //暴力 emmm 超时了
    public int mySqrt(int x) {
        int i=0;
        while (i*i<x){
            i++;
        }
        if (i*i>x){
            return i-1;
        }
        return i;
    }
    //优化下  还是超时！
    //也存在i*i >32位时 i赋值 溢出bug
    public int mySqrt2(int x) {
        if (x==0){
            return 0;
        }
        int i=1;
        //增长幅度
        while ((long)i*i<x){
            i=i==1?(i+1):(i*i);
        }
        //然后往下减
        while ((long)i*i>x){
            if ((long)(i-1)*(i-1)<=x){
                return i-1;
            }else {
                i--;
            }
        }
        return i;
    }
    public int mySqrt3(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long)mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            }
            else {
                r = mid - 1;
            }
        }
        return ans;
    }
    //牛顿迭代法
    public int mySqrt4(int x) {
        if (x == 0) {
            return 0;
        }

        double C = x, x0 = x;
        while (true) {
            double xi = 0.5 * (x0 + C / x0);
            if (Math.abs(x0 - xi) < 1e-7) {
                break;
            }
            x0 = xi;
        }
        return (int)x0;
    }

    //最快的 exp log 都是内置函数 时间复杂度为O（1）
    public int mySqrt5(int x) {
        if (x == 0) {
            return 0;
        }
        int ans = (int)Math.exp(0.5 * Math.log(x));
        return (long)(ans + 1) * (ans + 1) <= x ? ans + 1 : ans;
    }

}
