package SwordOffer;

public class Test015 {
    /**
     * 输入一个键表，输出该链表中倒数第k 个结点．为了符合大多数人的习惯，
     * 本题从1开始计数，即链表的尾结点是倒数第1个结点．例如一个链表有6个结点，
     * 从头结点开始它们的值依次是1、2、3、4、5 6。这个链表的倒数第3个结点是值为4的结点．
     *
     */
    public static class ListNode {
        int value;
        ListNode next;
    }
    //这题思想就是保持距离差
    // 倒数第k个到最后一个距离为k-1 那先遍历到正数第k个，然后与头结点一直往后走，走到尾 指向头结点的临时指针就指向倒数第k个了
    public static ListNode findKthToTail(ListNode head, int k){
       if (head==null||k<=0){
           return null;
       }
       //先找到正数第k个
        ListNode end=head;
        for (int i=1;i<k;i++){
            if (end.next!=null){
                end=end.next;
            }else {
                return null;
            }
        }
        //一起向后走
        ListNode res=head;
        while (end.next!=null){
            res=res.next;
            end=end.next;
        }
        return res;
    }
    public static void main(String[] args) {
        ListNode head = new ListNode();
        head.value = 1;

        head.next = new ListNode();
        head.next.value = 2;

        head.next.next = new ListNode();
        head.next.next.value = 3;

        head.next.next.next = new ListNode();
        head.next.next.next.value = 4;

        head.next.next.next.next = new ListNode();
        head.next.next.next.next.value = 5;

        head.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.value = 6;

        head.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.value = 7;

        head.next.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.next.value = 8;

        head.next.next.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.next.next.value = 9;

        System.out.println(findKthToTail(head, 1).value); // 倒数第一个
        System.out.println(findKthToTail(head, 5).value); // 中间的一个
        System.out.println(findKthToTail(head, 9).value); // 倒数最后一个就是顺数第一个

        System.out.println(findKthToTail(head, 10));
    }
}
