package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 992. K 个不同整数的子数组
 * 给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定独立的子数组为好子数组。
 * <p>
 * （例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）
 * <p>
 * 返回 A 中好子数组的数目。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输出：A = [1,2,1,2,3], K = 2
 * 输入：7
 * 解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
 * 示例 2：
 * <p>
 * 输入：A = [1,2,1,3,4], K = 3
 * 输出：3
 * 解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 20000
 * 1 <= A[i] <= A.length
 * 1 <= K <= A.length
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarrays-with-k-different-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_992 {
    //1.暴力法 超出时间限制
    public int subarraysWithKDistinct1(int[] A, int K) {
        int count1, count2 = 0;
        int len = A.length;
        for (int i = 0; i < len - K + 1; i++) {
            count1 = K;
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i; j < len; j++) {
                if (count1 == K && K != 1) {
                    count1--;
                    map.put(A[j], 0);
                } else if (count1 == 1 && map.get(A[j]) == null) {
                    count1--;
                    count2++;
                    map.put(A[j], 0);
                } else if (count1 > 0 && map.get(A[j]) == null) {
                    count1--;
                    map.put(A[j], 0);
                } else if (count1 > 0 && map.get(A[j]) != null) {
                    continue;
                } else if (count1 == 0 && map.get(A[j]) == null) {
                    break;
                } else if (count1 == 0 && map.get(A[j]) != null) {
                    count2++;
                }
            }
        }

        return count2;
    }
    //方法2 参考官方解答 滑动窗口
    //left2-left1 可以举个例子：A=[1 1 1 2],K=2
    //确保一个窗口的不同数字为K，一个窗口为K-1,且俩窗口右端点相同，只需要左端点相减就能求出以该右端点满足条件的子数组个数
    //相当于3-0=3，(i,j)表示A[i]至A[j],左右皆闭合 则子数组为(0,3)(1,3)(2,3)
    public int subarraysWithKDistinct2(int[] A, int K){
        int ans=0,left1=0,left2=0;
        Window window1=new Window();
        Window window2=new Window();
        for (int right=0;right<A.length;right++){
            window1.add(A[right]);
            window2.add(A[right]);

            while (window1.different>K)
                window1.remove(A[left1++]);
            while (window2.different>=K)
                window2.remove(A[left2++]);

            ans+=left2-left1;
        }
        return ans;
    }
    class Window{
        //key表示数，value表示该数在窗口中的数量
        Map<Integer,Integer> map;
        //表示窗口中不同数字的个数
        int different;

        Window(){
            this.map=new HashMap<>();
            different=0;
        }
        //添加，如果首次添加则diff加1
        void add(int x){
            map.put(x,map.getOrDefault(x,0)+1);
            if (map.get(x)==1){
                different++;
            }
        }
        //移除
        void remove(int x){
            map.put(x,map.get(x)-1);
            if (map.get(x)==0){
                map.remove(x);
                different--;
            }
        }
        int getDifferent(){
            return this.different;
        }
    }
}
