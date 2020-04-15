package leetcode.DynamicPrograming;

import java.util.Arrays;

/*
1024.视频拼接（video-stitching）
你将会获得一系列视频片段，这些片段来自于一项持续时长为 T 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。

视频片段 clips[i] 都用区间进行表示：开始于 clips[i][0] 并于 clips[i][1] 结束。我们甚至可以对这些片段自由地再剪辑，例如片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。

我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）。返回所需片段的最小数目，如果无法完成该任务，则返回 -1 。

 

示例 1：

输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
输出：3
解释：
我们选中 [0,2], [8,10], [1,9] 这三个片段。
然后，按下面的方案重制比赛片段：
将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
现在我们手上有 [0,2] + [2,8] + [8,10]，而这些涵盖了整场比赛 [0, 10]。
示例 2：

输入：clips = [[0,1],[1,2]], T = 5
输出：-1
解释：
我们无法只用 [0,1] 和 [0,2] 覆盖 [0,5] 的整个过程。
示例 3：

输入：clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], T = 9
输出：3
解释：
我们选取片段 [0,4], [4,7] 和 [6,9] 。
示例 4：

输入：clips = [[0,4],[2,8]], T = 5
输出：2
解释：
注意，你可能录制超过比赛结束时间的视频。
 

提示：

    1. 1 <= clips.length <= 100
    2. 0 <= clips[i][0], clips[i][1] <= 100
    3. 0 <= T <= 100

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/video-stitching
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_1024 {
    //可以动态规划dp dp[i]表示从0到时刻i所需要的最小片段
    //然后记录i-1能表示的最大时刻maxRight ，如果maxRight>=i dp[i]=dp[i-1] 否则 查找能表示i的最大时刻 且dp[i]=dp[i-1]+1
    public int videoStiching(int[][] clips,int T){
        int maxRight=-1;
        //因为dp[i]是根据dp[i-1]而来 其实可以只用一个数值就可以存放dp
        int res=0;
        //注意起始遍历位置  为1  不是0
        for (int i=1;i<T;i++){
            boolean found=false;
            if (maxRight>=i){
                found=true;
            }else {
                for (int j=0;j<clips.length;j++){
                    if (i-1>=clips[j][0]&&clips[j][1]>=i){
                        found=true;
                        maxRight=Math.max(maxRight,clips[j][1]);
                    }
                }
                if (found){
                    res++;
                }
                else {
                    res=-1;
                    break;
                }
            }
        }
        return res;
    }
    //排序后的贪心
    public static int videoStitching(int[][] clips, int T) {
        //按左边界递增排序
        Arrays.sort(clips,((o1, o2) -> o1[0]-o2[0]));
        int count=1;
        int end=0;
        int i=0;
        int begin=0;
        while(i<clips.length&&end<T){
            if(clips[i][0]>begin&&clips[i][1]>end){
                if(clips[i][0]>end){ //如果超过当前最右边界，说明不可完成
                    break;
                }
                count++;
                begin=end;
            }
            end=Math.max(end,clips[i][1]);
            i++;
        }
        return end<T?-1:count;
    }

}
