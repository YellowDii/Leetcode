package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Number_56 {
    /**
     * 56.合并区间 （merge intervals）
     * 给出一个区间的集合，请合并所有重叠的区间。
     *
     * 示例 1:
     *
     * 输入: [[1,3],[2,6],[8,10],[15,18]]
     * 输出: [[1,6],[8,10],[15,18]]
     * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * 示例 2:
     *
     * 输入: [[1,4],[4,5]]
     * 输出: [[1,5]]
     * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-intervals
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int[][] merge(int[][] intervals) {
        int[][] res=new int[intervals.length][2];
        if (intervals==null||intervals.length<1){
            return res;
        }
        if (intervals.length==1){
            return intervals;
        }

        int[][] copy=Arrays.copyOf(intervals,intervals.length);
        Comparator<int[]> comparator=new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0]!=o2[0]){
                    return o1[0]-o2[0];
                }else {
                    return o1[1]-o2[1];
                }
            }
        };
        Arrays.sort(copy,comparator);
        List<Integer[]> list=new ArrayList<>();
        int a=copy[0][0];
        int b=copy[0][1];
        for (int i=1;i<copy.length;i++){
            int m=copy[i][0];
            int n=copy[i][1];
            if (m>b){
                list.add(new Integer[]{a,b});
                a=m;
                b=n;
            }else {
                b=b>n?b:n;
            }
        }
        //处理末尾
        list.add(new Integer[]{a,b});
        for (int i=0;i<list.size();i++){
            res[i][0]=list.get(i)[0];
            res[i][1]=list.get(i)[1];
        }
        return Arrays.copyOf(res,list.size());
    }
    //上面代码太啰嗦 不美观
    public  int[][] merge2(int[][] intervals){
        // 先按照区间起始位置排序
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        // 遍历区间
        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for (int[] interval: intervals) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);
    }
}
