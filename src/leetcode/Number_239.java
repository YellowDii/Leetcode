package leetcode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

public class Number_239 {
    /**
     * 239. 滑动窗口最大值（sliding-window-maximum）
     *
     * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     *
     * 返回滑动窗口中的最大值。
     *
     *  
     *
     * 进阶：
     *
     * 你能在线性时间复杂度内解决此题吗？
     *
     *  
     *
     * 示例:
     *
     * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
     * 输出: [3,3,5,5,6,7]
     * 解释:
     *
     *   滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     *  
     *
     * 提示：
     *
     *  1. 1 <= nums.length <= 10^5
     *  2. -10^4 <= nums[i] <= 10^4
     *  3. 1 <= k <= nums.length
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //暴力法
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];

        int [] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for(int j = i; j < i + k; j++)
                max = Math.max(max, nums[j]);
            output[i] = max;
        }
        return output;
    }
    //这个只要6ms 上面要30+ms 不是垫底了哈哈
    //不过碰到最坏情况 降序数组 时间复杂度为O(n)
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        Window window=new Window(k);
        int[] result=new int[n-k+1];
        for (int i=0;i<n;i++){
            window.add(nums[i]);
            if (i>=k-1){
                result[i-k+1]=window.getMax();
            }
        }
        return result;
    }
    //抽象的窗口
    class Window{
        private int maxSize;
        private int max;
        private LinkedList<Integer> datas;

        Window(int size){
            this.max=Integer.MIN_VALUE;
            this.maxSize=size;
            datas=new LinkedList<>();
        }

        public void add(int node){
            int curSize=datas.size();
            if (curSize<maxSize){
                //当前窗口还没填满
                datas.add(node);
                if(node>max){
                    max=node;
                }
            }else {
                //已经填满
                datas.add(node);
                if (node>max){
                    datas.removeFirst();
                    max=node;
                }else {
                    int delete=datas.removeFirst();
                    //最大项恰好就是头部
                    if (delete==max){
                        updateMax();
                    }
                }
            }
        }
        //更新Max
        private void updateMax() {
            int tmp=datas.getFirst();
            for (Integer i:datas){
                if (i>tmp){
                    tmp=i;
                }
            }
            max=tmp;
        }

        public int getMax(){
            return max;
        }
    }

    /*------------------------------------------------------------------------------------------------------*/

    //官方解答 双向队列
    ArrayDeque<Integer> deq = new ArrayDeque<Integer>();
    int [] nums;

    public void clean_deque(int i, int k) {
        // remove indexes of elements not from sliding window
        if (!deq.isEmpty() && deq.getFirst() == i - k)
            deq.removeFirst();

        // remove from deq indexes of all elements
        // which are smaller than current element nums[i]
        while (!deq.isEmpty() && nums[i] > nums[deq.getLast()])
            deq.removeLast();
    }
    //这个要13ms 不过算法思想还是O(n) 主要还是测试例子的原因
    public int[] maxSlidingWindow3(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;

        // init deque and output
        this.nums = nums;
        int max_idx = 0;
        for (int i = 0; i < k; i++) {
            clean_deque(i, k);
            deq.addLast(i);
            // compute max in nums[:k]
            if (nums[i] > nums[max_idx]) max_idx = i;
        }
        int [] output = new int[n - k + 1];
        output[0] = nums[max_idx];

        // build output
        for (int i  = k; i < n; i++) {
            clean_deque(i, k);
            deq.addLast(i);
            output[i - k + 1] = nums[deq.getFirst()];
        }
        return output;
    }
    //动态规划 两个数组来获取输出
    /**
     * 先把nums 分块 每块长度为k 最后一块长度可能小于k
     * 建立数组 left， 其中 left[j] 是从块的开始到下标 j 最大的元素，方向 左->右。
     * 建立数组 right，其中 right[j] 是从块的结尾到下标 j 最大的元素，方向 右->左。right 数组和 left 除了方向不同以外基本一致。
     * 考虑从下标 i 到下标 j的滑动窗口。
     * 根据定义，right[i] 是左侧块内的最大元素，left[j] 是右侧块内的最大元素。
     * 因此滑动窗口中的最大元素为 max(right[i], left[j])。
     */
    //5ms 挺快的
    public int[] maxSlidingWindow4(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;

        int [] left = new int[n];
        left[0] = nums[0];
        int [] right = new int[n];
        right[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            // from left to right
            if (i % k == 0) left[i] = nums[i];  // block_start
            else left[i] = Math.max(left[i - 1], nums[i]);

            // from right to left
            int j = n - i - 1;
            if ((j + 1) % k == 0) right[j] = nums[j];  // block_end
            else right[j] = Math.max(right[j + 1], nums[j]);
        }

        int [] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++)
            output[i] = Math.max(left[i + k - 1], right[i]);

        return output;
    }

}
