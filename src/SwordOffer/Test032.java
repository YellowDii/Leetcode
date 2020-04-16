package SwordOffer;

public class Test032 {
    /**
     * 题目：输入一个整数n求从1 到n这n个整数的十进制表示中1 出现的次数。
     * 归纳法
     * 设N = abcde ,其中abcde分别为十进制中各位上的数字。
     * 如果要计算百位上1出现的次数，它要受到3方面的影响：百位上的数字，百位以下（低位）的数字，百位以上（高位）的数字。
     * ① 如果百位上数字为0，百位上可能出现1的次数由更高位决定。比如：12013，
     * 则可以知道百位出现1的情况可能是：100 ~ 199，1100 ~ 1199，2100 ~ 2199，… 11100~11199，一共1200个。可以看出是由更高位数字（12）决定，并且等于更高位数字（12）乘以当前位数（100）。
     * ② 如果百位上数字为1，百位上可能出现1的次数不仅受更高位影响还受低位影响。比如：12113，
     * 则可以知道百位受高位影响出现的情况是：100 ~ 199，1100 ~ 1199,2100 ~ 2199，…，11100~11199，一共1200个。和上面情况一样，并且等于更高位数字（12）乘以当前位数（100）。
     * 但同时它还受低位影响，百位出现1的情况是：12100 ~12113,一共14个，等于低位数字（13）+1。
     * ③ 如果百位上数字大于1（2 ~ 9），则百位上出现1的情况仅由更高位决定，比如12213，
     * 则百位出现1的情况是：100 ~ 199,1100 ~ 1199，2100 ~ 2199，…，11100 ~ 11199, 12100~12199,一共有1300个，并且等于更高位数字+1（12+1）乘以当前位数（100）。
     * @param n
     * @return
     */
    public int NumberOf1Between1AndN_Solution(int n) {
        int i = 1, count =0;
        int before,after,current;
        while(n/i!=0){
            before = n/i/10;//高位
            after = n%i;//低位
            current = (n/i)%10;//当前位
            if(current==0)
                count += before * i;
            else if(current==1)
                count += before * i  + after + 1;
            else
                count += (before+1) * i;
            i *= 10;
        }
        return count;
    }


    public static int numberOf1Between1AndN(int n) {
        if (n <= 0) {
            return 0;
        }

        String value = n + "";
        int[] numbers = new int[value.length()];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = value.charAt(i) - '0';
        }

        return numberOf1(numbers, 0);
    }

    /**
     * 求0-numbers表的数字中的1的个数
     *
     * @param numbers 数字，如{1, 2, 3, 4, 5}表示数字12345
     * @param curIdx  当前处理的位置
     * @return 1的个数
     */
    private static int numberOf1(int[] numbers, int curIdx) {

        if (numbers == null || curIdx >= numbers.length || curIdx < 0) {
            return 0;
        }
        // 待处理的第一个数字
        int first = numbers[curIdx];

        // 要处理的数字的位数
        int length = numbers.length - curIdx;

        // 如果只有一位且这一位是0返回0
        if (length == 1 && first == 0) {
            return 0;
        }

        // 如果只有一位且这一位不是0返回1
        if (length == 1 && first > 0) {
            return 1;
        }

        // 假设numbers是21345
        // numFirstDigit是数字10000-19999的第一个位中的数目
        int numFirstDigit = 0;
        // 如果最高位不是1，如21345，在[1236, 21345]中，最高位1出现的只在[10000, 19999]中，出现1的次数是10^4方个
        if (first > 1) {
            numFirstDigit = powerBase10(length - 1);
        }
        // 如果最高位是1，如12345，在[2346, 12345]中，最高位1出现的只在[10000, 12345]中，总计2345+1个
        else if (first == 1) {
            numFirstDigit = atoi(numbers, curIdx + 1) + 1;
        }

        // numOtherDigits，是[1346, 21345]中，除了第一位之外（不看21345中的第一位2）的数位中的1的数目
        int numOtherDigits = first * (length - 1) * powerBase10(length - 2);
        // numRecursive是1-1234中1的的数目
        int numRecursive = numberOf1(numbers, curIdx + 1);

        return numFirstDigit + numOtherDigits + numRecursive;
    }

    /**
     * 将数字数组转换成数值，如{1, 2, 3, 4, 5}，i = 2，结果是345
     * @param numbers 数组
     * @param i 开始黑气的位置
     * @return 转换结果
     */
    private static int atoi(int[] numbers, int i) {
        int result = 0;
        for (int j = i; j < numbers.length; j++) {
            result = (result * 10 + numbers[j]);
        }
        return result;
    }

    /**
     * 求10的n次方，假定n不为负数
     * @param n 幂，非负数
     * @return 10的n次方
     */
    private static int powerBase10(int n) {
        int result = 1;
        for (int i = 0; i < n; i++) {
            result *= 10;
        }
        return result;
    }
}
