package leetcode;

public class Number_1248 {
    /**
     * 1248. 统计「优美子数组」（count-number-of-nice-subarrays）
     *
     * 给你一个整数数组 nums 和一个整数 k。
     * 
     * 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
     * 
     * 请返回这个数组中「优美子数组」的数目。
     * 
     *  
     * 
     * 示例 1：
     * 
     * 输入：nums = [1,1,2,1,1], k = 3
     * 输出：2
     * 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
     * 示例 2：
     * 
     * 输入：nums = [2,4,6], k = 1
     * 输出：0
     * 解释：数列中不包含任何奇数，所以不存在优美子数组。
     * 示例 3：
     * 
     * 输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
     * 输出：16
     *  
     * 
     * 提示：
     * 
     * 1. 1 <= nums.length <= 50000
     * 2. 1 <= nums[i] <= 10^5
     * 3. 1 <= k <= nums.length
     * 
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/count-number-of-nice-subarrays
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //直接遍历 统计前后情况
    //滑动窗口的思想 自己写的有点啰嗦 也不易读...
    public static int numberOfSubarrays(int[] nums, int k) {
        int count = 0;
        int start = 0, end = 0;
        int len = nums.length;
        int cur=0;//当前nums[star:end]的奇数个数
        while (start < len && end < len ) {
            int afters=0,befores=0;
            while (cur < k && start < len && end < len ) {
                if ((nums[end++] & 1) == 1) {
                    cur++;
                    if (cur==k){
                        afters++;
                        int end2=end;
                        while (end2<len&&(nums[end2]&1)==0){
                            afters++;//记录下个奇数前的偶数个数 （后面的）
                            end2++;
                        }
                        break;
                    }
                }
            }
            while (start < len  && start < end){
                if ((nums[start++]&1)==0){
                    ++befores;//记录下个奇数前的偶数个数 （前面的）
                }else {
                    ++befores;
                    cur--;
                    break;
                }
            }
            count+=befores*afters;
        }
        return count;
    }
    //下面这个写的更清晰些
    public int numberOfSubarrays2(int[] nums, int k) {
        int left = 0, right = 0, oddCnt = 0, res = 0;
        while (right < nums.length) {
            // 右指针先走，每遇到一个奇数则 oddCnt++。
            if ((nums[right++] & 1) == 1) {
                oddCnt++;
            }

            //  若当前滑动窗口 [left, right) 中有 k 个奇数了，进入此分支统计当前滑动窗口中的优美子数组个数。
            if (oddCnt == k) {
                // 先将滑动窗口的右边界向右拓展，直到遇到下一个奇数（或出界）
                // rightEvenCnt 即为第 k 个奇数右边的偶数的个数
                int tmp = right;
                while (right < nums.length && (nums[right] & 1) == 0) {
                    right++;
                }
                int rightEvenCnt = right - tmp;
                // leftEvenCnt 即为第 1 个奇数左边的偶数的个数
                int leftEvenCnt = 0;
                while ((nums[left] & 1) == 0) {
                    leftEvenCnt++;
                    left++;
                }
                // 第 1 个奇数左边的 leftEvenCnt 个偶数都可以作为优美子数组的起点
                // (因为第1个奇数左边可以1个偶数都不取，所以起点的选择有 leftEvenCnt + 1 种）
                // 第 k 个奇数右边的 rightEvenCnt 个偶数都可以作为优美子数组的终点
                // (因为第k个奇数右边可以1个偶数都不取，所以终点的选择有 rightEvenCnt + 1 种）
                // 所以该滑动窗口中，优美子数组左右起点的选择组合数为 (leftEvenCnt + 1) * (rightEvenCnt + 1)
                res += (leftEvenCnt + 1) * (rightEvenCnt + 1);

                // 此时 left 指向的是第 1 个奇数，因为该区间已经统计完了，因此 left 右移一位，oddCnt--
                left++;
                oddCnt--;
            }

        }

        return res;
    }

    public int numberOfSubarrays3(int[] nums, int k) {
        if(nums == null || nums.length == 0) return -0;
        int ans = 0, sum = 0;
        int[] map = new int[nums.length + 1];//从头到尾遍历 记录奇数为i的子数组个数
        map[0] = 1;
        for(int i : nums) {
            sum += i&1;
            map[sum]++;
            if(sum >= k) ans += map[sum - k];
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] nums=new int[]{1,1,2,1,1};
        System.out.println(numberOfSubarrays(nums,3));
    }
}
