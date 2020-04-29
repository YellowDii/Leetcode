package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Number_1095 {
    /**
     * 1095.山脉数组中查找目标值（find-in-mountain-array）
     *
     * （这是一个 交互式问题 ）
     *
     * 给你一个 山脉数组 mountainArr，请你返回能够使得 mountainArr.get(index) 等于 target 最小 的下标 index 值。
     *
     * 如果不存在这样的下标 index，就请返回 -1。
     *
     *  
     *
     * 何为山脉数组？如果数组 A 是一个山脉数组的话，那它满足如下条件：
     *
     * 首先，A.length >= 3
     *
     * 其次，在 0 < i < A.length - 1 条件下，存在 i 使得：
     *
     * A[0] < A[1] < ... A[i-1] < A[i]
     * A[i] > A[i+1] > ... > A[A.length - 1]
     *  
     *
     * 你将 不能直接访问该山脉数组，必须通过 MountainArray 接口来获取数据：
     *
     * MountainArray.get(k) - 会返回数组中索引为k 的元素（下标从 0 开始）
     * MountainArray.length() - 会返回该数组的长度
     *  
     *
     * 注意：
     *
     * 对 MountainArray.get 发起超过 100 次调用的提交将被视为错误答案。此外，任何试图规避判题系统的解决方案都将会导致比赛资格被取消。
     *
     * 为了帮助大家更好地理解交互式问题，我们准备了一个样例 “答案”：https://leetcode-cn.com/playground/RKhe3ave，请注意这 不是一个正确答案。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：array = [1,2,3,4,5,3,1], target = 3
     * 输出：2
     * 解释：3 在数组中出现了两次，下标分别为 2 和 5，我们返回最小的下标 2。
     * 示例 2：
     *
     * 输入：array = [0,1,2,4,2,1], target = 3
     * 输出：-1
     * 解释：3 在数组中没有出现，返回 -1。
     *  
     *
     * 提示：
     *
     * 3 <= mountain_arr.length() <= 10000
     * 0 <= target <= 10^9
     * 0 <= mountain_arr.get(index) <= 10^9
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-in-mountain-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    interface MountainArray{
        public int get(int index);
        public int length();
    }
    //题目要求get方法小于100次 长度小于10000 首先想到二分查找 但是二分查找得有序 山峰分为升序和降序 先找山峰
    //对于一个范围 [i, j]，我们可以先找到范围 [i, j] 中间连续的两个点 mid 与 mid + 1。
    // 如果 mountainArr.get(mid + 1) > mountainArr.get(mid)，
    // 那么可以知道峰值在范围 [mid + 1, j] 内；如果 mountainArr.get(mid + 1) < mountainArr.get(mid)，
    // 那么可以知道峰值在范围 [i, mid] 内。
    // 通过这样的方法，我们可以在 O(\log n)O(logn) 的时间内找到峰值所处的下标。

    public int findInMountainArray(int target, MountainArray mountainArr) {
        //先找山峰
        int left=0,right=mountainArr.length()-1;
        while (left+1<right){
            int mid=left+(right-left)/2;
            int midVal=mountainArr.get(mid);

            if (mountainArr.get(mid-1)<midVal){
                left=mid;
            }else {
                right=mid;
            }
        }
        int peekIndex=mountainArr.get(left)>mountainArr.get(right)?left:right;
        int result=binarySearch(mountainArr,0,peekIndex,target,true);
        if (result!=-1){
            return result;
        }else {
            return binarySearch(mountainArr,peekIndex+1,mountainArr.length()-1,target,false);
        }
    }

    private int binarySearch(MountainArray mountainArr,int start,int end,int target,boolean asc){
        while (start<=end){
            int mid=start+(end-start)/2;
            int midVal=mountainArr.get(mid);

            if (midVal==target){
                return mid;
            }
            if (midVal<target){
                start=asc?mid+1:start;
                end=asc?end:mid-1;
            }else {
                start=asc?start:mid+1;
                end=asc?mid-1:end;
            }
        }
        return -1;
    }
}
