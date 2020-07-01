package SwordOffer;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Leet040 {
    /**
     * 040. 最小的k个数
     *
     * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：arr = [3,2,1], k = 2
     * 输出：[1,2] 或者 [2,1]
     * 示例 2：
     *
     * 输入：arr = [0,1,2,1], k = 1
     * 输出：[0]
     *  
     *
     * 限制：
     *
     * 0 <= k <= arr.length <= 10000
     * 0 <= arr[i] <= 10000
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //最大堆 java中的优先队列
    public int[] getLeastNumbers(int[] arr, int k) {
        Queue<Integer> queue=new PriorityQueue<>((n1,n2)->n2-n1);
        for (int i:arr){
            if (queue.size()<k){
                queue.add(i);
            }else {
                queue.add(i);
                queue.remove();
            }
        }
        int[] res=new int[k];
        for (int i=0;i<k;i++){
            res[i]=queue.poll();
        }
        return res;
    }
    //快排
    public int[] getLeastNumbers2(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k<1 || k>arr.length) {
            return new int[0];
        }

        return  helper(arr, 0, arr.length-1, k-1);
    }

    public int[] helper(int[] arr, int left, int right, int k) {

        int mid = partition(arr, left, right);
        if(mid==k) {
            return Arrays.copyOfRange(arr, 0, mid+1);
        } else if(mid>k) {
            return helper(arr, left, mid-1, k);
        } else {
            return helper(arr, mid+1, right, k);
        }
    }

    // 快排切分
    private int partition(int[] A, int left, int right) {
        int provit = A[left];  //选基准元素(第一个元素)

        while(left<right) {

            while(left<right && A[right]>provit) {   //选第一个元素为基准元素时从尾部开始扫描
                --right;
            }
            A[left] = A[right];

            while(left<right && A[left]<=provit) {
                ++left;
            }
            A[right] = A[left];
        }

        A[left] = provit;  //将基准元素放入正确的位置
        return left;
    }
}
