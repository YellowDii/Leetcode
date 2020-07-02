package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Number_378 {
    /**
     * 378. 有序数组中第K小的元素（kth-smallest-element-in-a-sorted-matrix）
     *
     * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
     * 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
     *
     *  
     *
     * 示例：
     *
     * matrix = [
     *    [ 1,  5,  9],
     *    [10, 11, 13],
     *    [12, 13, 15]
     * ],
     * k = 8,
     *
     * 返回 13。
     *  
     *
     * 提示：
     * 你可以假设 k 的值永远是有效的，1 ≤ k ≤ n^2 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //相当于从左上角往右下角遍历
    public int kthSmallest(int[][] matrix, int k) {
        if (k==1&&matrix!=null){
            return matrix[0][0];
        }
        //记录每行遍历到的位置
        int[] index=new int[matrix.length];
        Arrays.fill(index,0);
        index[0]=1;
        int res=matrix[0][0];
        for (int i=1;i<k;i++){
            res=Integer.MAX_VALUE;
            int row=-1;
            for (int j=0;j<index.length;j++){
                if (index[j]<matrix[j].length){
                    if (j==0){
                        //第一行的
                        if (matrix[j][index[j]]<=res){
                            res=Math.min(res,matrix[j][index[j]]);
                            row=j;
                        }
                    }else if (index[j-1]!=0){
                        //不是第一行 且前一行存在第j小的数 j<k
                        if (matrix[j][index[j]]<=res){
                            res=Math.min(res,matrix[j][index[j]]);
                            row=j;
                        }
                    }else if (index[j-1]==0){
                        break;
                    }
                }
            }
            index[row]++;
        }
        return res;
    }
    //归并排序
    public int kthSmallest2(int[][] matrix, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{matrix[i][0], i, 0});
        }
        for (int i = 0; i < k - 1; i++) {
            int[] now = pq.poll();
            if (now[2] != n - 1) {
                pq.offer(new int[]{matrix[now[1]][now[2] + 1], now[1], now[2] + 1});
            }
        }
        return pq.poll()[0];
    }
    //二分查找
    public int kthSmallest3(int[][] matrix, int k) {
        int n = matrix.length - 1;
        int l = matrix[0][0];
        int r = matrix[n][n];
        while(l < r){
            int mid = l + (r - l)/2;
            int count = countNotMoreThanMid(matrix,mid,n);
            if(count < k){
                l = mid + 1;
            }else{
                r = mid;
            }
        }
        return l;
    }


    //从左下角往右上角走
    public int countNotMoreThanMid(int[][] matrix,int mid,int n){
        int row = n;
        int column = 0;
        int count = 0;
        while(row >=0&&column <= n){
            if(matrix[row][column] <= mid){
                count = count + row + 1;
                column++;
            }else{
                row--;
            }
        }
        return count;
    }
}
