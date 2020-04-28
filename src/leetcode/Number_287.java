package leetcode;

public class Number_287 {
    /**
     * 287. 寻找重复数（find-the-duplicate-number）
     *
     * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
     *
     * 示例 1:
     *
     * 输入: [1,3,4,2,2]
     * 输出: 2
     * 示例 2:
     *
     * 输入: [3,1,3,4,2]
     * 输出: 3
     * 说明：
     *
     *  1.不能更改原数组（假设数组是只读的）。
     *  2.只能使用额外的 O(1) 的空间。
     *  3.时间复杂度小于 O(n^2) 。
     *  4.数组中只有一个重复的数字，但它可能不止重复出现一次。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-the-duplicate-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //忽略前两个条件的话
    //1.可以先排序 然后从左到有找相同就可
    //2.集合 集合中有相同元素直接返回
    //这个很难想到.....  官方解答
    //循环检测 弗洛伊德的乌龟和兔子 即检测单链表是否存在环
    public int findDuplicate(int[] nums) {
        // Find the intersection point of the two runners.
        //兔子的速度是乌龟的两倍 相等时 兔子走了2圈 龟走了1圈
        int tortoise = nums[0];
        int hare = nums[0];
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        //让另一个乌龟从起点开始 和走了1圈的乌龟一起向前走 相当于一个在圈头 一个在圈尾
        //这时候相当于首尾指针 一起移动进行比较即可
        // Find the "entrance" to the cycle.
        int ptr1 = nums[0];
        int ptr2 = tortoise;
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }

        return ptr1;
    }

}
