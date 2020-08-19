package leetcode;


public class Number_24 {
    /**
     * 24. 两两交换链表中的节点(swap-nodes-in-pair)
     *
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     *
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     * ?
     *
     * 示例:
     *
     * 给定 1->2->3->4, 你应该返回 2->1->4->3.
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static class ListNode {
        int value;
        ListNode next;
        ListNode(int x){
            this.value=x;
        }
    }
    public ListNode swapPairs(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }
        ListNode res=head.next;
        ListNode tmp=head,pre=null;
        while (tmp!=null){
            if (tmp.next!=null){
                ListNode end=tmp.next.next;
                if (pre==null){
                    res=swap(tmp);
                }else {
                    pre.next=swap(tmp);
                }
                pre=tmp;
                tmp=end;
            }else {
                break;
            }
        }
        return res;
    }

    private ListNode swap(ListNode tmp) {
        ListNode head=tmp.next;
        tmp.next=head.next;
        head.next=tmp;
        return head;
    }
}
