package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Number_166 {
    /**
     * 166. ������С��(fraction-to-recurring-decimal)
     *
     * ���������������ֱ��ʾ�����ķ���?numerator �ͷ�ĸ denominator�����ַ�����ʽ����С����
     *
     * ���С������Ϊѭ��С������ѭ���Ĳ������������ڡ�
     *
     * ʾ�� 1:
     *
     * ����: numerator = 1, denominator = 2
     * ���: "0.5"
     * ʾ��?2:
     *
     * ����: numerator = 2, denominator = 1
     * ���: "2"
     * ʾ��?3:
     *
     * ����: numerator = 2, denominator = 3
     * ���: "0.(6)"
     *
     * ��Դ�����ۣ�LeetCode��
     * ���ӣ�https://leetcode-cn.com/problems/fraction-to-recurring-decimal
     * ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
     */
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder fraction = new StringBuilder();
        // If either one is negative (not both)
        if (numerator < 0 ^ denominator < 0) {
            fraction.append("-");
        }
        // Convert to Long or else abs(-2147483648) overflows
        long dividend = Math.abs(Long.valueOf(numerator));
        long divisor = Math.abs(Long.valueOf(denominator));
        fraction.append(String.valueOf(dividend / divisor));
        long remainder = dividend % divisor;
        if (remainder == 0) {
            return fraction.toString();
        }
        fraction.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0) {
            if (map.containsKey(remainder)) {
                fraction.insert(map.get(remainder), "(");
                fraction.append(")");
                break;
            }
            map.put(remainder, fraction.length());
            remainder *= 10;
            fraction.append(String.valueOf(remainder / divisor));
            remainder %= divisor;
        }
        return fraction.toString();
    }

}
