package SwordOffer;

public class Test012 {
    /**
     * 输入数字n，按顺序打印出从1到最大的n位十进制数。比如输入3，则打印出1、2、3 一直到最大的3位数即999。
     */

    /**
     * 输入数组的元素，从左到右，从第一个非0值到开始输出到最后的元素。
     *
     * @param arr 要输出的数组
     */
    public static void printArray(int[] arr) {
        // 找第一个非0的元素
        int index = 0;
        while (index < arr.length && arr[index] == 0) {
            index++;
        }

        // 从第一个非0值到开始输出到最后的元素。
        for (int i = index; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        // 条件成立说明数组中有非零元素，所以需要换行
        if (index < arr.length) {
            System.out.println();
        }
    }
    public static void printOneToNthDigits(int n) {
        // 输入值必须大于0
        if (n < 1) {
            throw new RuntimeException("The input number must larger than 0");
        }

        // 创建一个长度为n的数组
        int[] arr = new int[n];
        // 为数组元素赋初始值
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }

        // 求结果，如果最高位没有进位就一直进行处理
        while (addOne(arr) == 0) {
            printArray(arr);
        }
    }

    /**
     * 每次+1
     * @param arr
     * @return 返回0表示没到最大值 返回1表示再+1会超过最大n位数
     */
    private static int addOne(int[] arr) {
        int carry=1;
        int index=arr.length;
        //进位处理
        do{
            index--;
            arr[index]+=carry;
            carry=arr[index]/10;
            arr[index]%=10;
        }while (carry!=0&&index>0);

        if (carry>0&&index==0){
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        printOneToNthDigits(5);
    }
}
