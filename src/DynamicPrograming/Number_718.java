package DynamicPrograming;

import java.util.Arrays;

/*
718.最长重复子数组（maximum-length-of-repeated-subarray）
给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。

示例 1:

输入:
A: [1,2,3,2,1]
B: [3,2,1,4,7]
输出: 3
解释:
长度最长的公共子数组是 [3, 2, 1]。
说明:

1.  1 <= len(A), len(B) <= 1000
2.  0 <= A[i], B[i] < 100

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_718 {
    String maximum_length_of_repeated_subarray="718.最长重复子数组（maximum-length-of-repeated-subarray）\n";
    //像两个尺子一样的比较 不停滑动
    /*
             1 2 3 2 1        1 2 3 2 1            1 2 3 2 1
     3 2 1 4 7          3 2 1 4 7              3 2 1 4 7
     类似这样


     */
    public int findLength(int[] A, int[] B) {
        int[] min;
        int[] max;
        if (A.length>=B.length){
            min=B;max=A;
        }else {
            min=A;max=B;
        }
        int[] dp=new int[min.length+1];
        int ans=0;
        for (int i=1;i<min.length;i++){
            int index=0;
            for (int j=min.length-i;j<min.length;j++){
                if (min[j]==max[index++]){
                    dp[j]=dp[j-1]+1;
                }else {
                    dp[j]=0;
                }
                ans=Math.max(ans,dp[j]);
            }
        }
        Arrays.fill(dp,0);
        for (int i=0;i<max.length;i++){
            for (int offset=0;offset<min.length&&i+offset<max.length;offset++){
                if (min[offset]==max[i+offset]){
                    dp[offset+1]=dp[offset]+1;
                }else {
                    dp[offset+1]=0;
                }
                ans=Math.max(ans,dp[offset+1]);
            }
        }
        return ans;
    }
    //跟上面的思路一样 但是要快一些 精妙些
    public int findLength2(int[] A, int[] B) {
        int max = 0;
        int[] dp = new int[B.length + 1];
        for (int i = 0; i < A.length; i++) {
            for (int j = B.length - 1; j >= 0; j--) {
                if (A[i] == B[j]) {
                    dp[j + 1] = dp[j] + 1;
                    max = Math.max(max, dp[j + 1]);
                } else {
                    dp[j + 1] = 0;
                }
            }
        }
        return max;
    }

}
