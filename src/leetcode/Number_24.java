package leetcode;


public class Number_24 {
    /**
     * 24. �������������еĽڵ�(swap-nodes-in-pair)
     *
     * ����һ���������������������ڵĽڵ㣬�����ؽ����������
     *
     * �㲻��ֻ�ǵ����ĸı�ڵ��ڲ���ֵ��������Ҫʵ�ʵĽ��нڵ㽻����
     *
     * ?
     *
     * ʾ��:
     *
     * ���� 1->2->3->4, ��Ӧ�÷��� 2->1->4->3.
     *
     * ��Դ�����ۣ�LeetCode��
     * ���ӣ�https://leetcode-cn.com/problems/swap-nodes-in-pairs
     * ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
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
