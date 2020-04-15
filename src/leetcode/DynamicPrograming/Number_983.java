package leetcode.DynamicPrograming;

import java.util.HashSet;
import java.util.Set;

/*
983.最低票价（minummum-cost-for-tickets）
在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行。在接下来的一年里，你要旅行的日子将以一个名为 days 的数组给出。每一项是一个从 1 到 365 的整数。

火车票有三种不同的销售方式：

一张为期一天的通行证售价为 costs[0] 美元；
一张为期七天的通行证售价为 costs[1] 美元；
一张为期三十天的通行证售价为 costs[2] 美元。
通行证允许数天无限制的旅行。 例如，如果我们在第 2 天获得一张为期 7 天的通行证，那么我们可以连着旅行 7 天：第 2 天、第 3 天、第 4 天、第 5 天、第 6 天、第 7 天和第 8 天。

返回你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费。

 

示例 1：

输入：days = [1,4,6,7,8,20], costs = [2,7,15]
输出：11
解释：
例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划：
在第 1 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 1 天生效。
在第 3 天，你花了 costs[1] = $7 买了一张为期 7 天的通行证，它将在第 3, 4, ..., 9 天生效。
在第 20 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 20 天生效。
你总共花了 $11，并完成了你计划的每一天旅行。
示例 2：

输入：days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
输出：17
解释：
例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划：
在第 1 天，你花了 costs[2] = $15 买了一张为期 30 天的通行证，它将在第 1, 2, ..., 30 天生效。
在第 31 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 31 天生效。
你总共花了 $17，并完成了你计划的每一天旅行。
 

提示：

1 <= days.length <= 365
1 <= days[i] <= 365
days 按顺序严格递增
costs.length == 3
1 <= costs[i] <= 1000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-cost-for-tickets
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_983 {
    int[] days;
    int[] costs;
    int min=0;
    int days_end;
    //方法1 递归
    //该方法超时了 通过用例46/66
    public int mincostTickets1(int[] days, int[] costs) {
        this.days=days;
        this.costs=costs;
        this.days_end=days[days.length-1];
        //min=Integer.MAX_VALUE;
        min=Math.max(Math.max(days.length*costs[0],days.length*costs[1]),days.length*costs[2]);
        //初试状态分三种
        dfs(costs[0],days[0],0);
        dfs(costs[1],days[0]+6,0);
        dfs(costs[2],days[0]+29,0);
        return min;
    }
    //cost代表当前已消费数
    //day_max代表当前票够玩到多少天
    //day_current_i 表示当前天数在days中的下标
    void dfs(int cost,int day_max,int day_current_i){
        if (day_current_i>=days.length){
            min=Math.min(cost,min);
            return;
        }
        if (day_max>=days_end) {
            min = Math.min(cost, min);
            return;
        }
        if (day_max>=days[day_current_i]){
            dfs(cost,day_max,day_current_i+1);
        }else {
            //过渡到当前天数的前一天
            if (day_max<days[day_current_i]){
                day_max=days[day_current_i]-1;
            }
            //剪枝
            if (cost+costs[0]<min)
                dfs(cost+costs[0],day_max+1,day_current_i+1);
            if (cost+costs[1]<min)
                dfs(cost+costs[1],day_max+7,day_current_i+1);
            if (cost+costs[2]<min)
                dfs(cost+costs[2],day_max+30,day_current_i+1);
        }

    }
    //方法2 通过上述递归的思想，我们可以逆向思维 转换成下列动态规划转移方程
    //dp[i]表示从第i天到最后一天的旅游计划最小花费
    //  dp(i)=min(dp(i+1)+costs[0],dp(i+7)+costs[1],dp(i+30)+costs[2])
    Integer[] memo;//备忘录,用Interger比较好判断是否有记录， int[]数组有初始值0
    Set<Integer> dayset;
    public int mincostTickets2(int[] days, int[] costs){
        this.costs=costs;
        memo=new Integer[366];
        dayset= new HashSet<>();
        for(int i:days){
            dayset.add(i);
        }
        return dp(1);
    }
    int dp(int i){
        if (i>365)
            return 0;
        if (memo[i]!=null){
            return memo[i];
        }
        int ans;
        if (dayset.contains(i)){
            ans=Math.min(dp(i+1)+costs[0],dp(i+7)+costs[1]);
            ans=Math.min(ans,dp(i+30)+costs[2]);
        }else {
            ans=dp(i+1);
        }
        memo[i]=ans;
        return ans;
    }
    //方法3 上面方法2 如果出行天数比较少时 会出现很多浪费，我们用下标记录天数
    int[] durations=new int[]{1,7,30};
    public int mincostTickets(int[] days,int[] costs){
        this.days=days;
        this.costs=costs;
        memo=new Integer[days.length];
        return dp2(0);
    }
    int dp2(int i) {
        if (i >= days.length)
            return 0;
        if (memo[i] != null)
            return memo[i];

        int ans = Integer.MAX_VALUE;
        int j = i;
        for (int k = 0; k < 3; ++k) {
            while (j < days.length && days[j] < days[i] + durations[k])
                j++;
            ans = Math.min(ans, dp2(j) + costs[k]);
        }

        memo[i] = ans;
        return ans;
    }

}
