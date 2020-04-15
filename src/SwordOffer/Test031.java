package SwordOffer;

public class Test031 {
    /**
     * 输入一个整型数组，数组里有正数也有负数。数组中一个或连
     * 续的多个整数组成一个子数组。求所有子数组的和的最大值。要求时间复杂度为O(n)。
     */
    /**
     * 动态规划 dp[i]表示子数组下标为i的最大值
     * 可优化为空间复杂度为O(1)
     */
    public static int findGreatestSumOfSubArray(int[] arr) {
        if (arr == null || arr.length < 1) {
            throw new IllegalArgumentException("Array can't be null");
        }
        int max = Integer.MIN_VALUE;
        int tmp = 0;//表示dp[i]
        /*
        for (int i=0;i<arr.length;i++){
            if (arr[i]>=0&&tmp>=0){
                tmp+=arr[i];
            }else if (arr[i]>=0&&tmp<0){
                tmp=arr[i];
            }else {
                if (tmp>=0){
                    tmp+=arr[i];
                }else {
                    tmp=arr[i];
                }
            }
            max=max>tmp?max:tmp;
        }
        写的太啰嗦*/
        for (int i : arr) {
            if (tmp < 0) {
                tmp = 0;
            }
            if (tmp >= 0) {
                tmp += i;
            }
            if (max < tmp) {
                max = tmp;
            }
        }
        return max;
    }
    public static void main(String[] args) {
        int[] data = {1, -2, 3, 10, -4, 7, 2, -5};
        int[] data2 = {-2, -8, -1, -5, -9};
        int[] data3 = {2, 8, 1, 5, 9};

        System.out.println(findGreatestSumOfSubArray(data));
        System.out.println(findGreatestSumOfSubArray(data2));
        System.out.println(findGreatestSumOfSubArray(data3));
    }
}
