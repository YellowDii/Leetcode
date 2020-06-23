import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class QuickSort {
    public static void solution(int[] array) {
        int len = array.length;
        if (len == 0) {
            return;
        }
        int left = 0, right = len - 1;
        quickSort(array, left, right);
    }

    public static void quickSort(int[] array, int left, int right) {
        if (left < right) {
            int partitionIndex = myPartiton(array, left, right);
            quickSort(array, left, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, right);
        }
    }
    //自己写个分区方法  两个指针 下面这个要方便点
    public static int myPartiton(int[] array, int left, int right){
        int pivort=array[left];
        int index_left=left;
        int index_right=right;
        while (index_left<index_right){
            while (index_left<index_right&&array[index_right]>pivort){
                index_right--;
            }
            array[index_left]=array[index_right];
            while (index_left<index_right&&array[index_left]<=pivort){
                index_left++;
            }
            array[index_right]=array[index_left];
        }
        array[index_left]=pivort;
        return index_left;
    }

    // 第一种分区方法
    public static  int partition(int[] array, int left, int right) {
        // 选取左边第一个数作为中轴数
        int pivot = left;
        // 从中轴数后面一个位置开始比较
        int index = pivot + 1;
        // 保证指针左边的数都是小于中轴数的
        for (int i = left + 1; i <= right; i++) {
            // 如果小于中轴数，将当前数的位置和中轴指针交换
            if (array[i]< array[pivot]) {
                swap(array, i, index);
                // 指针继续后移
                ++index;
            }
        }
        // 将中轴数的位置和指针左边一个位置交换，也就是把中轴数换到中间
        swap(array, pivot, index - 1);
        // 最后返回这个中轴数的位置
        return index - 1;
    }
    /**
     * 第二种分区方式 这种方式更快
     * @param array
     * @param left
     * @param right
     * @param <T>
     * @return
     */
    public static <T extends Comparable<? super T>> int partition2
    (T[] array, int left, int right) {
        // 随机选定一个中轴数
        swap(array, left, (int) (Math.random() * (right - left) + left));
        T pivot = array[left];
        int i = left, j = right + 1;
        while (i < j) {
            while (i < j && array[--j].compareTo(pivot) > 0);
            while (i < j && array[++i].compareTo(pivot) < 0);
            if (i < j) {
                swap(array, i, j);
            }
        }
        swap(array, left, j);
        return j;
    }

    public static <T extends Comparable<? super T>> void swap(T[] array, int a, int b) {
        T tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    public static void swap(int[] array,int a,int b){
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    public static void main(String[] args) {
        int[] array=new int[200];
        for (int i=0;i<array.length;i++){
            array[i]=new Random().nextInt(1000);
        }
        solution(array);
        for (int i: array){
            System.out.print(i+" ");
        }
    }
}
