package leetcode.GreedyAlgorithm;

import java.util.Arrays;

public class Number_45 {
    /**
     * 45.跳跃游戏 II（jump-game-ii）
     *
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     *
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     *
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     *
     * 示例:
     *
     * 输入: [2,3,1,1,4]
     * 输出: 2
     * 解释: 跳到最后一个位置的最小跳跃数是 2。
     *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
     * 说明:
     *
     * 假设你总是可以到达数组的最后一个位置。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/jump-game-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //600ms左右.....  太慢了！
    public int jump(int[] nums) {
        int len=nums.length;
        int[] res=new int[len];
        Arrays.fill(res,Integer.MAX_VALUE);
        res[0]=0;
        for (int i=0;i<len;i++){
            for (int j=1;j<=nums[i];j++){
                if (i+j>len-1){
                    res[len-1]=Math.min(res[len-1],res[i]+1);
                    continue;
                }
                res[i+j]=Math.min(res[i+j],res[i]+1);
            }
        }
        return res[len-1];
    }
    //动态规划  dp[i]应该等于第一个跳到它的点+1
    public int jump2(int[] nums) {
        int len=nums.length;
        int[] dp=new int[len];
        for (int i=1,last=0;i<len;i++){
            //相当于找局部最远
            while (last<len&&nums[last]+last<i){
                last++;
            }
            dp[i]=dp[last]+1;
        }
        return dp[len-1];
    }
    //通过上面思路好理解下面一点

    //通过局部最优解 得到全局最优解
    // 啊 这个好难想到 精妙
    public int jump3(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

}
