package SwordOffer;

public class Test013 {
    /**
     * 链表结点
     */
    public static class ListNode {
        int value;
        ListNode next;
    }
    /**
     * 给定单向链表的头指针和一个结点指针，定义一个函数在0(1)时间删除该结点,
     */
    
    /**
     *
     * @param head 链表表的头
     * @param deleted 待删除的结点
     * @return 删除后的头结点
     */
    public static ListNode deleteNode(ListNode head, ListNode deleted){
        if (head==null||deleted==null){
            return null;
        }
        //如果删除的是头结点
        if (head==deleted){
            return head.next;
        }
        //如果删除的是尾结点
        if (deleted.next==null){
            //寻找待删除节点的前节点
            ListNode cur=head;
            while (cur.next!=deleted){
                cur=cur.next;
            }
            cur.next=null;
        }else {
            //删除的节点在中间
            deleted.value=deleted.next.value;
            deleted.next=deleted.next.next;
        }
        return head;
    }
}
