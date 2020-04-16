package SwordOffer;

public class Test036 {
    /**
     * 题目：
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
     * 输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
     * <p>
     * 输入描述:
     * <p>
     * 题目保证输入的数组中没有相同的数字
     */
    //暴力法
    public int InversePairs(int[] array) {
        if (array == null || array.length < 1) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    count++;
                    count = count % 1000000007;
                }
            }
        }
        return count;
    }

    //归并思路
    public int InversePairs2(int[] array) {
        int len = array.length;
        if (array == null || len <= 0) {
            return 0;
        }
        return mergeSort(array, 0, len - 1);
    }
    //统计array[start:end] 并排序
    private int mergeSort(int[] array, int start, int end) {
        if (start == end)
            return 0;
        int mid = (start + end) / 2;
        int left_count = mergeSort(array, start, mid);
        int right_count = mergeSort(array, mid + 1, end);
        int i = mid, j = end;
        int[] copy = new int[end - start + 1];
        int copy_index = end - start;
        int count = 0;
        while (i >= start && j >= mid + 1) {
            if (array[i] > array[j]) {
                copy[copy_index--] = array[i--];
                count += j - mid;
                if (count > 1000000007) {
                    count %= 1000000007;
                }
            } else {
                copy[copy_index--] = array[j--];
            }
        }
        while (i > start) {
            copy[copy_index--] = array[i--];
        }
        while (j >= mid + 1) {
            copy[copy_index--] = array[j--];
        }
        i = 0;
        while (start <= end) {
            array[start++] = copy[i++];
        }
        return (left_count + right_count + count) % 1000000007;
    }

}
