package leetcode;

public class Number_25 {
    /**
     * 25.K个一组翻转链表（reverse-nodes-in-k-group）
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     *
     * k 是一个正整数，它的值小于或等于链表的长度。
     *
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     *
     *  
     *
     * 示例：
     *
     * 给你这个链表：1->2->3->4->5
     *
     * 当 k = 2 时，应当返回: 2->1->4->3->5
     *
     * 当 k = 3 时，应当返回: 3->2->1->4->5
     *
     *  
     *
     * 说明：
     *
     * 你的算法只能使用常数的额外空间。
     * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            this.val=x;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0), prev = dummy, curr = head, next;
        dummy.next = head;
        int length = 0;
        while(head != null) {
            length++;
            head = head.next;
        }
        head = dummy.next;
        for(int i = 0; i < length / k; i++) {
            for(int j = 0; j < k - 1; j++) {
                next = curr.next;
                curr.next = next.next;
                next.next = prev.next;
                prev.next = next;
            }
            prev = curr;
            curr = prev.next;
        }
        return dummy.next;
    }
    //递归
    public ListNode reverseKGroup2(ListNode head, int k) {
        if (k==1||head==null)
            return head;
        return helper(head,k-1);
    }

    public ListNode helper(ListNode head,int k){
        if (head==null)
            return null;
        ListNode start=head,end=start;
        int index;
        for (index=0;index<k;++index){//让end移动到第k个节点上
            if (end==null)
                break;
            end=end.next;
        }
        if (index!=k||end==null)//这种情况下，说明不够k个，直接返回head，即不翻转
            return head;
        ListNode node=end==null?null:end.next;
        end.next=null;//这里先将k个节点与后面的连接断开，方便翻转链表
        reverse(start);//翻转这k个节点
        start.next=helper(node,k);//让前k个节点的头节点的next指向后k个节点的尾节点
        return end;
    }

    /**
     * 翻转链表
     * @param head
     */
    public ListNode reverse(ListNode head){
        ListNode pre=head,next=pre.next;
        while (next!=null){
            ListNode tmp=next.next;
            next.next=pre;
            pre=next;
            next=tmp;
        }
        return head;
    }
    //非递归
    public ListNode reverseKGroup3(ListNode head, int k) {
        if (k==1||head==null)
            return head;
        return iterateHelper(head,k-1);
    }

    public ListNode iterateHelper(ListNode head,int k){
        ListNode start=head,res=head;
        ListNode pre=null;
        while (true){
            int index;
            ListNode end=start;
            for (index=0;index<k;++index){
                if (end==null)
                    break;
                end=end.next;
            }
            if (index!=k||end==null)
                break;
            ListNode node=end==null?null:end.next;
            end.next=null;
            reverse(start);
            if (pre!=null){
                pre.next=end;
            }else {
                res=end;
            }
            pre=start;
            start=node;
        }
        if (pre!=null)
            pre.next=start;
        return res;
    }

}
