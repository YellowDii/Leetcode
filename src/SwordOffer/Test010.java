package SwordOffer;

public class Test010 {
    /**
     *
     * 请实现一个函数， 输入一个整数，输出该数二进制表示中1的个数。
     * 例如把9表示成二进制是1001 ，有2位是1. 因此如果输入9，该出2。
     *
     */
    public static int numberOfOne(int n){
        int result=0;
        //int 为32位，比较32次，每次向右移1位 然后&1
        for (int i=0;i<32;i++){
            result+=n&1;
            n>>>=1;// >>>表示不带符号向右移动二进制数
        }
        return result;
    }
    //方法2 效率更高
    public static int numberOfOne2(int n){
        int result = 0;
        // 数字的二进制表示中有多少个1就进行多少次操作
        while (n != 0) {
            result++;
            n = (n - 1) & n;
        }

        return result;
    }
    public static void main(String[] args) {
        int i=Integer.MIN_VALUE;
        int j=i>>1;
        int m=i>>>1;
        System.out.println(Integer.toBinaryString(i)+"   "+i);
        System.out.println(Integer.toBinaryString(j)+"   "+j);
        System.out.println(Integer.toBinaryString(m)+"   "+m);
    }
}
