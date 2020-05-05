package leetcode;

public class Number_141 {
    /**
     * 141.环形链表（linked-list-cycle）
     *
     * 给定一个链表，判断链表中是否有环。
     *
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
     *
     *
     * 示例 1：
     *  3 -> 2 -> 0 -> -4
     *       |__________|
     *
     * 输入：head = [3,2,0,-4], pos = 1
     * 输出：true
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     *
     *
     * 示例 2：
     *
     * 输入：head = [1,2], pos = 0
     * 输出：true
     * 解释：链表中有一个环，其尾部连接到第一个节点。
     *
     *
     * 示例 3：
     *
     * 输入：head = [1], pos = -1
     * 输出：false
     * 解释：链表中没有环。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/linked-list-cycle
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val=x;
        }
    }
    //快慢指针
    public boolean hasCycle(ListNode head) {
        ListNode slower=head;
        ListNode faster=head;
        while (slower!=null&&faster!=null&&faster.next!=null){
            slower=slower.next;
            faster=faster.next.next;
            if (slower==faster){
                return true;
            }
        }
        return false;
    }
}
