package SwordOffer;

public class Test016 {
    public static class ListNode {
        int value;
        ListNode next;
    }

    /**
     *  定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点。
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head){
        ListNode root=new ListNode();
        root.next=null;
        ListNode next;//指向下个要插入的节点
        while (head!=null){
            //头插法
            next=head.next;
            head.next=root.next;
            root.next=head;

            head=next;
        }
        return root.next;
    }

}
