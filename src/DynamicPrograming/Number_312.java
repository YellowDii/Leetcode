package DynamicPrograming;

import java.util.HashMap;
import java.util.Map;

/*
312.戳气球  ( burst-balloons )
有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。

现在要求你戳破所有的气球。每当你戳破一个气球 i 时，你可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。

求所能获得硬币的最大数量。

说明:

你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
示例:

输入: [3,1,5,8]
输出: 167
解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
     coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/burst-balloons
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

/*
解法二 回溯法
再分析解法一的过程。

例如：[3,1,5,8]

先选5爆掉，剩下[3,1,8]。得金币40.

这里，为什么要将剩下的3,1,8再拼新序列？

因为，[3,1,5,8] 中5被爆掉，1和8如果不相邻，在爆掉1和8时，就无法正确计算金币。

同样，[3，1，8]中爆掉1后，3和8必须相邻。

这样，在爆掉一个球后，剩下的左和右两个部分必须相关联，才能得出正确解。

如此，每递归一层只能少一个球，复杂度为O(n!)。

如果，能在爆一个球时，将序列分成相独立的两部分，并得出正确的解。就能大幅降低时间复杂度。

再看， [3,1,5,8] 中，如果5没有被爆掉，那么[3,1]和[8]就独立了，它们都只与5有关了。

到这里，改进的思路，就很清晰了。

选择任一个球B，但是不爆，等其分割的左右部分爆完后，最后爆球B。

例如 [3,1,5,8]

选5，先爆[3,1]，也用同样的方式.

再爆[8]，

最后爆5.

与解法一的区别，任选一个球，解法一最先爆，解法二最后爆。

实际上，这样的思路转变，并不容易想到。有时就这么尴尬，自己想不出，一看题解秒懂，总会有一层窗户纸隔着。

这里产生一个问题，最后爆的金币怎么计算？

其实，要爆的区间边界是清楚的。计算金币也就很容易。

还有些区间会重复计算，用记忆化的方式去重;

思路：

A序列;
[i,j]区间;
d[i][j]:记忆化去重;
dfs(A,i,j){
    if([i,j]已经算过) 返回d[i][j];
    if(i > j) 返回0;
    for(k in [i,j]){
        r = A[i-1] * A[k] * A[j+1] + dfs(A,i,k-1) + dfs(A,k+1,j);
        取r的最大值;
    }
}

作者：jason-2
链接：https://leetcode-cn.com/problems/burst-balloons/solution/san-chong-jie-fa-by-jason-2-6/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
/*
解法三 动态规划
在解法二的基础上再进一步。

大区间能分解出独立的小区间（小区间是连续的，），并先爆小区间。

我们可以先算小区间，并组合出大区间。

例如：[3,1,5,8]

第一步，算长度为1的区间。

先算3,1,5,8。

第2步，算长度为2的区间。

再算3,1 ; 1,5 ; 5,8。

对于3,1。3最后爆，1已经算出；同样1最后爆，3已经算出。

对于1,5。1最后爆，5已经算出；同样5最后爆，1已经算出。

对于5,8。5最后爆，8已经算出；同样8最后爆，5已经算出。

第3步，算长度为3的区间。

再算3,1,5 ; 1,5,8。

对于3,1,5。3最后爆，1,5已经算出；1最后爆，3和5已经算出。 5最后爆，3,1已经算出。

对于1,5,8。1最后爆，5,8已经算出；5最后爆，1和8已经算出。 8最后爆，1,5已经算出。

第4步，算3，1，5，8.

3最后爆，1，5，8已经算出。1最后爆,3和5，8已经算出。5最后爆，3，1和8已经算出。8最后爆，3，1，5已经算出。

完整的思路：

A序列;
[i,j]区间;
d[i][j]:记忆化去重;
c：区间长度;
for(c in [1,N]){
    for(i in [1,N-c+1]){
        j = i+c-1;
         for(k in [i,j]){
             d[i][j] = max(A[i-1]*A[k]*A[j+1] + d[i][k-1]+d[k+1][j]);
         }
    }
}

作者：jason-2
链接：https://leetcode-cn.com/problems/burst-balloons/solution/san-chong-jie-fa-by-jason-2-6/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Number_312 {
    //要先戳除边界外最小的泡泡,而且有0先戳0
    // 测试发现算法思路错误 ，并不是这个戳泡顺序
    public int maxCoins(int[] nums) {
        int len = nums.length;
        if (len==1){
            return nums[0];
        }
        if (len==2){
            if (nums[0]<nums[1]){
                return nums[0]*nums[1]+nums[1];
            }else {
                return nums[1]*nums[0]+nums[0];
            }
        }
        //存放已经戳破的泡泡位置
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        int min=0,maxcoins=0;
        int start=1,end=len-2;//记录边界位置
        //确定左边界
        for (int i=0;i<len-1;i++){
            if (nums[i]==0){
                map.put(i,1);
                start++;
            }else
                break;
        }
        //确定右边界
        for (int i=len-1;i>=0;i--){
            if (nums[i]==0){
                map.put(i,1);
                end--;
            }else
                break;
        }
        while (map.size()<len){
            if (map.size()==len-2){
                int i_left=0,i_right=len-1;
                while (map.get(i_left)!=null) i_left++;
                while (map.get(i_right)!=null) i_right--;
                if (nums[i_left]<nums[i_right]){
                    maxcoins+=nums[i_left]*nums[i_right]+nums[i_right];
                    map.put(i_left,1);
                    map.put(i_right,1);
                }else{
                    maxcoins+=nums[i_right]*nums[i_left]+nums[i_left];
                    map.put(i_left,1);
                    map.put(i_right,1);
                }
                break;
            }
            //戳中间的泡泡
            for (int i=start;i<=end;i++){
                if (nums[i]==0){
                    map.put(i,1);
                    continue;
                }
                if (map.get(i)!=null){
                    continue;
                }else {
                    int i_min=start,min_value=Integer.MAX_VALUE;
                    //确定剩余泡泡的最小泡泡位置
                    for (int j=i_min;j<=end;j++){
                        if (min_value>nums[j]&&map.get(j)==null){
                            min_value=nums[j];
                            i_min=j;
                        }
                    }
                    //计算戳破该泡得到的硬币
                    int left=i_min-1,right=i_min+1;
                    while (map.get(left)!=null&&left>=start) left--;
                    while (map.get(right)!=null&&right<=end) right++;
                    maxcoins+=nums[left]*nums[i_min]*nums[right];
                    map.put(i_min,1);
                }
            }
        }
        return maxcoins;
    }
    //如果顺序遍历，先遍历第一个泡泡，则时间复杂度为O(n！)
    //思路需要转换一下，不要讨论谁先爆，讨论谁最后爆，时间复杂度会大大降低
    //按照上面解法3的思路进行动态规划
    //dp[i][j]表示nums[i]~nums[j]的连续区间硬币的最大值
    public int maxCoins2(int[] nums){
        int n=nums.length;
        int[][] dp=new int[n+2][n+2];
        dp[0][0]=1;dp[n+1][n+1]=1;
        //nums2是nums加上隐藏的边界 相当于nums[-1],nums[n]
        int[] nums2=new int[n+2];
        nums2[0]=1;nums2[n+1]=1;
        for (int i=1;i<n+1;i++){
            nums2[i]=nums[i-1];
        }
        //长度为1的小区间
        for (int i=1;i<=n;i++){
            dp[i][i]=nums2[i-1]*nums2[i]*nums2[i+1];
        }
        //从步长为1开始计算各个小区间
        for (int c=1;c<n;c++){
            //小区间的左边界
            for (int i=1;i+c<n+1;i++){
                for (int k=i;k<=i+c;k++){
                    dp[i][i+c]=Math.max(dp[i][i+c],dp[i][k-1]+dp[k+1][i+c]+nums2[i-1]*nums2[i+c+1]*nums2[k]);
                }
            }
        }
        return dp[1][n];
    }
}
