package leetcode;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Mian02_01 {
    /**
     * 面试题 02.01. 移除重复节点(remove-duplicate-node-lcci)
     *
     * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
     *
     * 示例1:
     *
     *  输入：[1, 2, 3, 3, 2, 1]
     *  输出：[1, 2, 3]
     * 示例2:
     *
     *  输入：[1, 1, 1, 1, 2]
     *  输出：[1, 2]
     * 提示：
     *
     * 链表长度在[0, 20000]范围内。
     * 链表元素在[0, 20000]范围内。
     * 进阶：
     *
     * 如果不得使用临时缓冲区，该怎么解决？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-duplicate-node-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val=x;
        }
    }

    //可以用set
    public ListNode removeDuplicateNodes(ListNode head) {
        Set<Integer> set=new HashSet<>();
        ListNode tmp=new ListNode(-1);
        tmp.next=head;
        while (tmp.next!=null){
            if (!set.contains(tmp.next.val)){
                set.add(tmp.next.val);
                tmp=tmp.next;
            }else {
                if (tmp.next.next==null){
                    tmp.next=null;
                }else {
                    tmp.next=tmp.next.next;
                }
            }
        }
        return head;
    }
    //这个时间复杂度上升了 但是没用缓冲区 即类似哈希表啊等等
    public ListNode removeDuplicateNodes2(ListNode head) {
        ListNode ob = head;
        while (ob != null) {
            ListNode oc = ob;
            while (oc.next != null) {
                if (oc.next.val == ob.val) {
                    oc.next = oc.next.next;
                } else {
                    oc = oc.next;
                }
            }
            ob = ob.next;
        }
        return head;
    }

}
