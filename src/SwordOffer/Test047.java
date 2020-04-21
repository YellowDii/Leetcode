package SwordOffer;

public class Test047 {
    /**
     * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
     */
    public static int add(int x, int y) {
        int sum;
        int carry;

        do {
            sum = x ^ y;
            // x&y的某一位是1说明，它是它的前一位的进位，所以向左移动一位
            carry = (x & y) << 1;

            x = sum;
            y = carry;
        } while (y != 0);

        return x;
    }
    public static void main(String[] args) {
        System.out.println(add(11, 12) + ", " + (11 + 12));
        System.out.println(add(23, 44)+ ", " + (23 + 44));
        System.out.println(add(169, 925)+ ", " + (169 + 925));
    }
}
