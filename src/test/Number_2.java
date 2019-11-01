package test;

/**
 * 2.两数相加
 *
 */
public class Number_2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //记录进位
        int tmp=0;
        ListNode result=new ListNode(0);
        ListNode p=l1;
        ListNode q=l2;
        ListNode last=result;
        while (p!=null||q!=null){
            int p1=(p!=null)?p.val:0;
            int q1=(q!=null)?q.val:0;
            int sum=tmp+p1+q1;
            tmp=sum/10;
            last.next=new ListNode(sum%10);
            last=last.next;
            if(p!=null)p=p.next;
            if(q!=null)q=q.next;
        }
        if(tmp>0) last.next=new ListNode(tmp);
        return result.next;
    }
    class ListNode{
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
