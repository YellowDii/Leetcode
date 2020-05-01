package leetcode;

public class Number_21 {
    /**
     * 21.合并两个有序链表(merge-two-sorted-lists)
     *
     * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
     *
     * 示例：
     *
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){this.val=x;}
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head=new ListNode(0);
        ListNode cur=head;
        while (l1!=null&&l2!=null){
            if (l1.val<l2.val){
                cur.next=l1;
                l1=l1.next;
                cur=cur.next;
            }else {
                cur.next=l2;
                l2=l2.next;
                cur=cur.next;
            }
        }
        if (l1!=null){
            cur.next=l1;
        }
        if (l2!=null){
            cur.next=l2;
        }
        return head.next;
    }
}
