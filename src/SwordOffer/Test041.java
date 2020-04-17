package SwordOffer;

import java.util.ArrayList;
import java.util.List;

public class Test041 {
    /**
     * 题目1：
     * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得得它们的和正好是s。
     * 如果有多对数字的和等于s，输出任意一对即可。
     */
    //二分查找
    public static List<Integer> findNumbersWithSum(int[] data, int sum){
        if (data==null||data.length<2){
            throw new IllegalArgumentException("Array length must larger than 2");
        }
        List<Integer> list=new ArrayList<>();
        for (int i=0;i<data.length;i++){
            int a=data[i];
            int index=find(data,i+1,sum-a);
            if (index>0){
                list.add(a);
                list.add(data[index]);
                break;
            }
        }
        if (list.size()!=2){
            return null;
        }
        return list;
    }

    private static int find(int[] data,int start, int i) {
        int left=start;
        int right=data.length-1;
        while (left<right){
            int mid=left+(right-left)/2;
            if (i==data[mid]){
                return mid;
            }else if (i>data[mid]){
                left=mid+1;
            }else {
                right=mid;
            }
        }
        return -1;
    }
    //直接遍历 根据当前和与s进行比较来调节指针
    public static List<Integer> findNumbersWithSum2(int[] data, int sum) {
        List<Integer> result = new ArrayList<>(2);

        if (data == null || data.length < 2) {
            return result;
        }

        int ahead = data.length - 1;
        int behind = 0;
        long curSum; // 统计和，取long是防止结果溢出

        while (behind < ahead) {
            curSum = data[behind] + data[ahead];

            if (curSum == sum) {
                result.add(data[behind]);
                result.add(data[ahead]);
                break;
            } else if (curSum < sum) {
                behind++;
            } else {
                ahead--;
            }
        }

        return result;
    }

    /**
     * 题目2：输入一个正数s，打印出所有和为s 的连续正数序列（至少两个数）。
     *
     */
    public static List<List<Integer>> findContinuousSequence(int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (sum < 3) {
            return result;
        }

        int small = 1;
        int big = 2;
        int middle = (1 + sum) / 2;
        int curSum = small + big;

        while (small < middle) {
            if (curSum == sum) {
                List<Integer> list = new ArrayList<>(2);
                for (int i = small; i <= big; i++) {
                    list.add(i);
                }
                result.add(list);
            }

            while (curSum > sum && small < middle) {
                curSum -= small;
                small++;

                if (curSum == sum) {
                    List<Integer> list = new ArrayList<>(2);
                    for (int i = small; i <= big; i++) {
                        list.add(i);
                    }
                    result.add(list);
                }
            }

            big++;
            curSum += big;
        }

        return result;
    }

    public static void main(String[] args) {
        test01();
        System.out.println("---------------");

    }

    private static void test01() {
        int[] data1 = {1, 2, 4, 7, 11, 15};
        System.out.println(findNumbersWithSum(data1, 15));

        int[] data2 = {1, 2, 4, 7, 11, 16};
        System.out.println(findNumbersWithSum(data2, 17));

        int[] data3 = {1, 2, 4, 7, 11, 16};
        System.out.println(findNumbersWithSum(data3, 10));
    }
}
