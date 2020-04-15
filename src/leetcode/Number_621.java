package leetcode;

import java.util.Arrays;

public class Number_621 {
    /**
     * 621. 任务调度器 (task scheduler)
     * 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。
     * 任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
     *
     * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
     *
     * 你需要计算完成所有任务所需要的最短时间。
     *
     *  
     *
     * 示例 ：
     *
     * 输入：tasks = ["A","A","A","B","B","B"], n = 2
     * 输出：8
     * 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
     *  
     *
     * 提示：
     *
     *  1.任务的总个数为 [1, 10000]。
     *  2.n 的取值范围为 [0, 100]。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/task-scheduler
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 应当尽早安排出现次数较多的任务。我们假设 A 为出现次数最多的任务，
     * 假设其出现了 p 次，考虑到冷却时间，那么执行完所有任务的时间至少为 (p - 1) * (n + 1) + 1。我们把这个过程形象化地用图 1 表现出，
     * 可以发现，CPU 产生了 (p - 1) * n 个空闲时间，只有 p 个时间是在工作的。
     *
     */
    public int leastInterval(char[] tasks, int n){
        if (tasks==null||tasks.length<1){
            return -1;
        }
        int[] count=new int[26];
        for (char c:tasks){
            count[c-'A']++;
        }
        Arrays.sort(count);
        int max=count[count.length-1]-1;//至少要 max_val*(n+1)+1 时间
        int idol_slots=max*n;//表示空闲时间
        for (int i=count.length-2;i>=0;i--){
            idol_slots-=Math.min(count[i],max);
        }
        return idol_slots>0?idol_slots+tasks.length:tasks.length;
    }
}
