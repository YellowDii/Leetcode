package SwordOffer;

public class Test009 {
    /**
     *写一个函数，输入n，求斐波那契（Fibonacci) 数列的第n项
     */
    public static long fibonacci(int n){
        // 当输入非正整数的时候返回0
        if (n <= 0) {
            return 0;
        }

        // 输入1或者2的时候返回1
        if (n == 1 || n == 2) {
            return 1;
        }
        // 记录第n-2个 的Fibonacci数的值
        long prePre = 1;
        // 记录第n-1个 的Fibonacci数的值
        long pre = 1;
        // 记录第n个 的Fibonacci数的值
        long current = 2;

        // 求解第n个的Fibonacci数的值
        for (int i = 3; i <= n ; i++) {
            current = prePre + pre;
            prePre = pre;
            pre = current;
        }

        // 返回所求的结果
        return current;
    }
}
