package leetcode;

import java.util.Arrays;
import java.util.Stack;

public class Number_739 {
    /**
     * 739. 每日温度 (daily temperatures)
     * 根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
     *
     * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
     *
     * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/daily-temperatures
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //从后向前遍历 暴力法
    public int[] dailyTemperatures(int[] T) {
        if (T==null||T.length<1){
            return null;
        }
        int[] result=new int[T.length];
        result[T.length-1]=0;
        for (int i=T.length-2;i>=0;i--){
            if (T[i]<T[i+1]){
                result[i]=1;
            }else {
                for (int j=i+1;j<T.length;j++){
                    result[i]++;
                    if (T[i]>=T[j]){
                        //如果后面的数比T[i]大 而且对应天数为0 则置0 跳出循环
                        if (result[j]==0){
                            result[i]=0;
                            break;
                        }
                    }else {
                        break;
                    }
                }
            }
        }
        return result;
    }
    //气温范围在[30,100] 可以优化 用索引做
    /**
     * 题目要求我们找出下一次温度比当天高距离的天数。因为温度只能在 [30，100] 之内，如果现在的温度是 T[i]=50，我们只需要找到下一个出现的 51，52，…，100，然后取最快出现的那个位置。
     *
     * 算法：
     *
     * 我们按逆序遍历列表，对于每个 T[i]，我们要知道 (T[i],100] 温度所出现的位置，为此我们用一个 next 数组记录该数据，若当前位置出现 T[i]=100，则我们将该索引记录在 next[100]。
     * warmer_index 记录比当前温度高的索引位置，它等于 next[T[i]+1], next[T[i]+2], ..., next[100] 的最小值。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/daily-temperatures/solution/mei-ri-wen-du-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int[] dailyTemperatures2(int[] T) {
        int[] ans = new int[T.length];
        int[] next = new int[101];
        Arrays.fill(next, Integer.MAX_VALUE);
        for (int i = T.length - 1; i >= 0; --i) {
            int warmer_index = Integer.MAX_VALUE;
            for (int t = T[i] + 1; t <= 100; ++t) {
                if (next[t] < warmer_index)
                    warmer_index = next[t];
            }
            if (warmer_index < Integer.MAX_VALUE)
                ans[i] = warmer_index - i;
            next[T[i]] = i;
        }
        return ans;
    }

    //栈
    //跟前面的思路差不多 但这个使用栈优化了
    //后两种方法都很快 测试时前面的要快点
    public int[] dailyTemperatures3(int[] T) {
        int[] ans = new int[T.length];
        Stack<Integer> stack = new Stack();
        for (int i = T.length - 1; i >= 0; --i) {
            while (!stack.isEmpty() && T[i] >= T[stack.peek()]) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return ans;
    }



}
