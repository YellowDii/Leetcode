package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Number_406 {
    /**
     * 406. 根据身高重建队列(queue-reconstruction-by-height)
     *
     * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
     *
     * 注意：
     * 总人数少于1100人。
     *
     * 示例
     *
     * 输入:
     * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
     *
     * 输出:
     * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 算法：
     * 排序：
     * 按高度降序排列。
     * 在同一高度的人中，按 k 值的升序排列。
     * 逐个地把它们放在输出队列中，索引等于它们的 k 值。
     * 返回输出队列
     * @param people
     * @return
     */
    //官方解答有图片 会更容易懂
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                //注意这个排序 h降序+k增序
                return o1[0]==o2[0]?o1[1]-o2[1]:o2[0]-o1[0];
            }
        });
        List<int[]> list=new LinkedList<>();
        for (int[] i:people){
            list.add(i[1],i);
        }
        return list.toArray(new int[people.length][2]);
    }
}
