package SwordOffer;

public class Test028 {
    /**
     * 题目：输入一个字符串，打印出该字符事中字符的所有排列。例如输入字符串abc。
     * 则打印出由字符a、b、c 所能排列出来的所有字符串abc、acb、bac、bca、cab和cba。
     */
    public static void permutation(char[] chars) {
        if (chars == null || chars.length < 1) {
            return;
        }
        permutation(chars, 0);
    }

    private static void permutation(char[] chars, int begin) {
        if (chars.length - 1 == begin) {
            System.out.print(new String(chars) + " ");
        } else {
            char tmp;
            for (int i = begin; i < chars.length; i++) {
                tmp = chars[begin];
                chars[begin] = chars[i];
                chars[i] = tmp;

                //固定了begin位置 然后递归
                permutation(chars, begin + 1);
            }
        }
    }

    public static void main(String[] args) {
        char[] c1 = {'a', 'b', 'c', 'd', 'h', 'b', 'a'};
        permutation(c1);
        System.out.println();

        char[] c2 = {'a', 'b', 'c', 'd'};
        permutation(c2);
    }
}
