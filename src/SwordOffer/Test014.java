package SwordOffer;

public class Test014 {
    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
     * 使得所有奇数位于数组的前半部分，所有偶数位予数组的后半部分。
     *
     * @param arr
     */
    public static void reorderOddEven(int[] arr){
        //思路： 从左往右找偶数 从右往左找奇数 交换
        if(arr==null||arr.length<=1){
            return;
        }
        int start=0;
        int end=arr.length-1;
        while (start<end){
            while (start<end&arr[start]%2!=0){
                start++;
            }
            while (start<end&arr[end]%2==0){
                end--;
            }
            //交换  用异或交换会出问题 当出现start=end时 异或为0 得加判断start!=end
//            arr[start]=arr[start]^arr[end];
//            arr[end]=arr[start]^arr[end];
//            arr[start]=arr[start]^arr[end];
            //这种交换没问题
            int tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
        }
    }
    /**
     * 输出数组的信息
     *
     * @param arr 待输出数组
     */
    public static void printArray(int[] arr) {
        if (arr != null && arr.length > 0) {
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        reorderOddEven(array);
        printArray(array);
    }
}
