package leetcode;

/**
  61.旋转链表
  给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 
  示例 1:
 
  输入: 1->2->3->4->5->NULL, k = 2
  输出: 4->5->1->2->3->NULL
  解释:
  向右旋转 1 步: 5->1->2->3->4->NULL
  向右旋转 2 步: 4->5->1->2->3->NULL
  示例 2:
 
  输入: 0->1->2->NULL, k = 4
  输出: 2->0->1->NULL
  解释:
  向右旋转 1 步: 2->0->1->NULL
  向右旋转 2 步: 1->2->0->NULL
  向右旋转 3 步: 0->1->2->NULL
  向右旋转 4 步: 2->0->1->NULL
 
  来源：力扣（LeetCode）
  链接：https://leetcode-cn.com/problems/rotate-list
  著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_61 {
    //相当于把第(链表长度-k%链表长度+1)个以后的分链转移到链表最前面 需要考虑部分特殊情况 时间复杂度为O(n)
    public ListNode rotateRight(ListNode head, int k) {
        int len=0;
        ListNode node=head;
        if (head==null)
            return null;
        while (node!=null){
            len++;
            node=node.next;
        }
        //特殊情况 k=0或者len的倍数时，不需要变换
        if(k%len==0)
            return head;
        //记录切断位置
        int pos=len-k%len+1;
        node=head;
        //pre表示切段位置前面的节点
        ListNode pre=null;
        for (int i=1;i!=pos;i++){
            pre=node;
            node=node.next;
        }
        while (node.next!=null){
            node=node.next;
        }
        node.next=head;
        node=pre.next;
        pre.next=null;
        return node;
    }
    //Definition for singly-linked list.
    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
}
