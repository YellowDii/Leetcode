package SwordOffer;

public class Test037 {
    /**
     * 题目：
     * 输入两个链表，找出它们的第一个公共结点。
     */
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    //长的先走 走过两者长度差后 一起走
    public static ListNode findFirstCommonNode(ListNode head1,ListNode head2){
        int length1 = getListLength(head1);
        int length2 = getListLength(head2);

        int diff = length1 - length2;
        ListNode longListHead = head1;
        ListNode shortListHead = head2;

        if (diff < 0) {
            longListHead = head2;
            shortListHead = head1;
            diff = length2 - length1;
        }

        for (int i = 0; i < diff; i++) {
            longListHead = longListHead.next;
        }

        while (longListHead != null && shortListHead != null && longListHead != shortListHead) {
            longListHead = longListHead.next;
            shortListHead = shortListHead.next;
        }

        // 返回第一个相同的公共结点，如果没有返回null
        return longListHead;
    }

    private static int getListLength(ListNode head) {
        int len=0;
        if (head!=null){
            len++;
            head=head.next;
        }
        return len;
    }
}
