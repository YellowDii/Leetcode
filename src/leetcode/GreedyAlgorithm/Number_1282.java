package leetcode.GreedyAlgorithm;

import java.util.*;

/**
 * 1282.用户分组（group-the-people-given-the-group-size-they-belong-to）
 * 有 n 位用户参加活动，他们的 ID 从 0 到 n - 1，每位用户都 恰好 属于某一用户组。
 * 给你一个长度为 n 的数组 groupSizes，其中包含每位用户所处的用户组的大小，请你返回用户分组情况（存在的用户组以及每个组中用户的 ID）。
 *
 * 你可以任何顺序返回解决方案，ID 的顺序也不受限制。此外，题目给出的数据保证至少存在一种解决方案。
 *
 * 示例 1：
 *
 * 输入：groupSizes = [3,3,3,3,3,1,3]
 * 输出：[[5],[0,1,2],[3,4,6]]
 * 解释：
 * 其他可能的解决方案有 [[2,1,6],[5],[0,4,3]] 和 [[5],[0,6,2],[4,3,1]]。
 * 示例 2：
 *
 * 输入：groupSizes = [2,1,3,3,3,2]
 * 输出：[[1],[0,5],[2,3,4]]
 *  
 *
 * 提示：
 *
 *      1. groupSizes.length == n
 *      2. 1 <= n <= 500
 *      3. 1 <= groupSizes[i] <= n
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/group-the-people-given-the-group-size-they-belong-to
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Number_1282 {
    String group_the_people_given_the_group_size_they_belong_to="1282.用户分组（group-the-people-given-the-group-size-they-belong-to）";
    //方法1 优先队列 size相同时 从小到大依次取出即可
    public List<List<Integer>> groupThePeople1(int[] groupSizes) {
        int len=groupSizes.length;
        List<List<Integer>> res=new ArrayList<>();
        if(len==0){
            return res;
        }
        //利用最小堆 存放int[] 长度为2 第一个数是数组元素 第二个是数组索引
        //lambda 表达式
        PriorityQueue<int[]> minHeap=new PriorityQueue<>(len, Comparator.comparing(o->o[0]));
        for (int i=0;i<len;i++){
            minHeap.add(new int[]{groupSizes[i],i});
        }
        while (!minHeap.isEmpty()){
            //当前size
            int curSize=minHeap.peek()[0];
            List<Integer> list=new ArrayList<>();
            for (int i=0;i<curSize;i++){
                list.add(Objects.requireNonNull(minHeap.poll())[1]);
            }
            //从curSize最小找到最大
            res.add(list);
        }
        return res;
    }
    //方法2 先粗分组 再细分组
    //粗分组 groupsize相同的 分为一组 总共usize  usize=n*gsize
    //细分组 每次从大组中取gsize数量的小组
    public List<List<Integer>> groupThePeople2(int[] groupSizes){
        Map<Integer,List<Integer>> group=new HashMap<>();
        int len=groupSizes.length;
        List<List<Integer>> res=new ArrayList<>();
        if (len==0){
            return res;
        }
        //粗分组 这部分其实可以一句话写成 group.computeIfAbsent(groupSizes[i], x->new ArrayList<>()).add(i);
        /*
        for (int i=0;i<len;i++){
            if (group.get(groupSizes[i])!=null){
                List<Integer> list=group.get(groupSizes[i]);
                list.add(i);
                group.put(groupSizes[i],list);
            }else {
                List<Integer> list=new ArrayList<>();
                list.add(i);
                group.put(groupSizes[i],list);
            }
        }
        */
        for(int i = 0;i < len;i++){
            group.computeIfAbsent(groupSizes[i], x->new ArrayList<>()).add(i); //相当于粗分组
            //实时的细分组 达到数量 就向res添加
            if(group.get(groupSizes[i]).size() == groupSizes[i]){
                res.add(group.get(groupSizes[i]));
                group.remove(groupSizes[i]);
            }
        }
        return res;
    }
}
