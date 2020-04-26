package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Number_347 {
    /**
     * 347. 前 K 个高频元素(top-k-frequent-elements)
     *
     * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: nums = [1,1,1,2,2,3], k = 2
     * 输出: [1,2]
     * 示例 2:
     *
     * 输入: nums = [1], k = 1
     * 输出: [1]
     *  
     *
     * 提示：
     *
     * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
     * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
     * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
     * 你可以按任意顺序返回答案。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //堆排序 最小堆 借助HashMap
    //java中的PriorityQueue
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for (int i:nums){
            if (map.containsKey(i)){
                map.put(i,map.get(i)+1);
            }else {
                map.put(i,1);
            }
        }
        PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->(map.get(a)-map.get(b)));
        for (Integer key:map.keySet()){
            if (pq.size()<k){
                pq.add(key);
            }else if (map.get(pq.peek())<map.get(key)){
                pq.remove();
                pq.add(key);
            }
        }
        // 取出最小堆中的元素
        int[] res=new int[k];
        for (int i=0;i<k;i++){
            if (!pq.isEmpty()){
                res[i]=pq.remove();
            }
        }
        return res;
    }
    //桶排序 前面与堆排序一致
    public int[] topKFrequent2(int[] nums, int k) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for (int i:nums){
            if (map.containsKey(i)){
                map.put(i,map.get(i)+1);
            }else {
                map.put(i,1);
            }
        }
        //桶排序
        //将频率作为数组下标，对于出现频率不同的数字集合，存入对应的数组下标
        List<Integer>[] bucket=new List[nums.length+1];
        for (Integer key:map.keySet()){
            int i=map.get(key);
            if (bucket[i]==null){
                bucket[i]=new ArrayList<>();
            }
            bucket[i].add(key);
        }
        int[] res=new int[k];
        int res_size=0;//记录res中的个数
        for (int i=bucket.length-1;i>=0&&res_size<k;i--){
            if (bucket[i]!=null){
                for (Integer b:bucket[i]){
                    res[res_size++]=b;
                }
            }
        }
        return res;
    }
}
