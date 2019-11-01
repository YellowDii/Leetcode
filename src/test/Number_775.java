package test;

/**
 * 775.全局倒置与局部倒置
 * 数组 A 是 [0, 1, ..., N - 1] 的一种排列，N 是数组 A 的长度。全局倒置指的是 i,j 满足 0 <= i < j < N 并且 A[i] > A[j] ，局部倒置指的是 i 满足 0 <= i < N 并且 A[i] > A[i+1] 。
 *
 * 当数组 A 中全局倒置的数量等于局部倒置的数量时，返回 true 。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: A = [1,0,2]
 * 输出: true
 * 解释: 有 1 个全局倒置，和 1 个局部倒置。
 * 示例 2:
 *
 * 输入: A = [1,2,0]
 * 输出: false
 * 解释: 有 2 个全局倒置，和 1 个局部倒置。
 * 注意:
 *
 * A 是 [0, 1, ..., A.length - 1] 的一种排列
 * A 的长度在 [1, 5000]之间
 * 这个问题的时间限制已经减少了。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/global-and-local-inversions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_775 {
    //根据题意，局部倒置最多是N-1个，所以全局倒置大于N-1时可直接返回false
    //先用暴力法,该方法在遇到第190个测试用例时超时，时间复杂度O（n*n）
    public boolean isIdealPermutation1(int[] A) {
        int local = 0,global=0;
        for (int i=0;i<A.length-1;i++){
            if (A[i]>A[i+1])
                local++;
            for (int j=i+1;j<A.length;j++){
                if (A[i]>A[j])
                    global++;
                if (global>A.length-1)
                    return false;
            }
        }
        return local==global?true:false;
    }
    //因为存在局部偏差，则等级于存在全局偏差（i,i+1），所以要使全局偏差等于局部偏差，那要保证每一对全局偏差的i，j相差不大于1
    //如何保证 下面有两种思路，
    //第一种：题目说了N个数，从0到N-1，本应该顺序为0，1，2。。。N-1  那么A中如果有abs(A[i]-i)>1则全局偏差必然又相差大于1的
    //例如[2,1,0]，其中2应在最后，但是它占据了0的位置，那么它总存在比它小的数与它有全局偏差，不是0就是1，
    // 但是它离本应该存在的位置过远，即全局偏差距离过远
    public boolean isIdealPermutation2(int[] A) {
        for (int i=0;i<A.length;i++){
            if (A[i]-i>1||A[i]-i<-1)
                return false;
        }
        return true;
    }
    //第二种是确保前i项中最大的数为第i-1项或者是它自己
    //即变相确保i，j差不大于1
    public boolean isIdealPermutation3(int[] A) {
        int max=0;
        int maxIndex=0;
        for (int i=1;i<A.length;i++){
            if (maxIndex!=i-1&&max>A[i])
                return false;
            if (max<A[i-1]){
                max=A[i-1];
                maxIndex=i-1;
            }
        }
        return true;
    }
}
