package SwordOffer;

public class Test046 {
    /**
     * 题目描述
     * 求1+2+3+…+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）
     *
     * 累加不能用循环的话，那就试试递归吧。
     *
     * 判断递归的终止条件不能用 if 和 switch，那就用短路与代替。
     *
     * (n > 0) && (sum += Sum_Solution(n-1))>0
     *
     * 只有满足n > 0的条件，&&后面的表达式才会执行。
     */
    public static int sum_Solution(int n){
        int sum=n;
        boolean t=(n > 0) && (sum += sum_Solution(n-1))>0;
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(sum_Solution(10));
    }
}
