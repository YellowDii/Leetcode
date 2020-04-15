package SwordOffer;

import java.util.Arrays;

public class Test029 {
    /**
     * 题目：数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字
     */
    //这题不是求众数 而是求出现次数大于数组长度一半的
    //可以用相等+1，不相等就-1的策略 =0就换数 如果满足的话 最后选择的数肯定是出现次数最多的
    //也可以用哈希表做
    public static int moreThanHalfNum(int[] numbers){
        if (numbers==null||numbers.length<1){
            throw new IllegalArgumentException("array length must large than 0!");
        }
        int res=numbers[0];
        int count=1;
        for (int i=1;i<numbers.length;i++){
            if (numbers[i]==res){
                count++;
            }else {
                count--;
            }
            if (count==0){
                //重新选择
                res=numbers[i];
                count=1;
            }
        }
        count = 0;
        for (int number : numbers) {
            if (res == number) {
                count++;
            }
        }

        // 如果出现次数大于数组的一半就返回对应的值
        if (count > numbers.length / 2) {
            return res;
        }
        // 否则输入异常
        else {
            throw new IllegalArgumentException("not exist!");
        }
    }

    public static void main(String[] args) {
        // 存在出现次数超过数组长度一半的数字
        int numbers[] = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(moreThanHalfNum(numbers));

        // 出现次数超过数组长度一半的数字都出现在数组的前半部分
        int numbers2[] = {2, 2, 2, 2, 2, 1, 3, 4, 5};
        System.out.println(moreThanHalfNum(numbers2));

        // 出现次数超过数组长度一半的数字都出现在数组的后半部分
        int numbers3[] = {1, 3, 4, 5, 2, 2, 2, 2, 2};
        System.out.println(moreThanHalfNum(numbers3));

        // 只有一个数
        int numbers4[] = {1};
        System.out.println(moreThanHalfNum(numbers4));

        // 不存在出现次数超过数组长度一半的数字
        int numbers5[] = {1, 2, 3, 2, 4, 2, 5, 2, 3};
        moreThanHalfNum(numbers5);
    }
}
