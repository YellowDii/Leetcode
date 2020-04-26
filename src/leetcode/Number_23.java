package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Number_23 {
    /**
     * 23. 合并K个排序链表(merge-k-sorted-list)
     *
     * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
     *
     * 示例:
     *
     * 输入:
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * 输出: 1->1->2->3->4->4->5->6
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public  class ListNode{
        int val;
        ListNode next;
        ListNode(int value){
            this.val=value;
        }
    }
    //暴力法...
    public  ListNode mergeKLists(ListNode[] lists) {
        int len=lists.length;
        if (lists==null||len<1){
            return null;
        }
        ListNode[] pointer=new ListNode[len];
        for (int i=0;i<len;i++){
            pointer[i]=lists[i];
        }
        ListNode head=new ListNode(0);
        ListNode cur=head;
        boolean finish=false;
        while (!finish){
            ListNode next=minListNode(pointer);
            if (next!=null){
                cur.next=next;
                cur=cur.next;
            }else {
                finish=true;
            }
        }
        return head.next;
    }

    private  ListNode minListNode(ListNode[] pointer) {
        ListNode node=pointer[0];
        for (int i=1;i<pointer.length;i++){
            if (node!=null&&pointer[i]!=null){
                node=node.val>pointer[i].val?pointer[i]:node;
            }else if (pointer[i]!=null){
                node=pointer[i];
            }
        }
        //如果当前指向确实有最小点 则指针后移
        if (node!=null){
            for (int i=0;i<pointer.length;i++){
                if (pointer[i]==node){
                    pointer[i]=pointer[i].next;
                }
            }
        }
        return node;
    }
    //可以先转换为数组 然后用Arrays.sort
    public ListNode mergeKLists2(ListNode[] lists) {
        int num = 0;//记录一共有多少个元素，以便确定创建数组的大小
        for (ListNode node : lists) {
            while (node != null) {
                num++;
                node = node.next;
            }
        }//O(nk)的复杂度，k为链表个数，n为假设每个链表长度都为n

        int[] list = new int[num];
        int i = 0;
        //装入数组 O(nk)
        for (ListNode node : lists) {
            while (node != null) {
                list[i] = node.val;
                node = node.next;
                i++;
            }
        }
        //排序 O(nklog(nk))
        Arrays.sort(list);
        System.out.println(Arrays.toString(list));
        if (list.length==0)return null;
        ListNode ans = new ListNode(list[0]);
        ListNode head = ans;
        //装回链表O(nk)
        for (int k = 1; k < num; ++k) {
            ans.next = new ListNode(list[k]);
            ans = ans.next;
        }
        return head;
    }
    //二分法合并
    ListNode merge(ListNode[] lists,int low,int high){
        if(low == high){
            return lists[low];
        }
        int mid=(low+high)/2;
        ListNode left=merge(lists,low,mid);
        ListNode right=merge(lists,mid+1,high);
        if(left == null){
            return right;
        }
        if(right == null){
            return left;
        }
        ListNode head=null;//头结点
        ListNode cur=null;//当前指针

        while(left!=null && right!=null){
            if(left.val > right.val){
                if(head==null){
                    head=right;
                    cur=right;
                }
                else{
                    cur.next=right;
                    cur=cur.next;
                }
                right=right.next;
            }
            else{
                if(head==null){
                    head=left;
                    cur=left;
                }
                else{
                    cur.next=left;
                    cur=cur.next;
                }
                left=left.next;
            }
        }

        if(left == null){
            cur.next=right;
        }
        if(right == null){
            cur.next=left;
        }
        return head;
    }
    public ListNode mergeKLists3(ListNode[] lists) {
        int len=lists.length;
        if(len <=0){
            return null;
        }
        return merge(lists,0,len-1);
    }

}
