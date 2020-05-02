package leetcode;

public class Number_160 {
    /**
     * 160.相交链表（intersection-of-two-linked-lists）
     * 编写一个程序，找到两个单链表相交的起始节点。
     *
     * 如下面的两个链表：
     *     a1   a2
     *              c1  c2  c3
     * b1  b2   b3
     *
     * 在节点 c1 开始相交。
     *
     *  
     *
     * 示例 1：
     *      4 1
     *           8 4 5
     *    5 0 1
     * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
     * 输出：Reference of the node with value = 8
     * 输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
     *  
     *
     * 示例 2：
     * 0 9 1
     *       2 4
     *     3
     * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
     * 输出：Reference of the node with value = 2
     * 输入解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
     *  
     *
     * 示例 3：
     * 2 6 4
     *
     *   1 5
     * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
     * 输出：null
     * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
     * 解释：这两个链表不相交，因此返回 null。
     * 注意：
     *
     *  1. 如果两个链表没有交点，返回 null.
     *  2. 在返回结果后，两个链表仍须保持原有的结构。
     *  3. 可假定整个链表结构中没有循环。
     *  4. 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            this.val=x;
            next=null;
        }
    }
    //先从长的开始遍历长度差 然后一起向后走即可

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int l_long=0,l_short=0;
        ListNode p_long=headA;
        ListNode p_short=headB;
        while (p_long!=null){
            p_long=p_long.next;
            l_long++;
        }
        while (p_short!=null){
            p_short=p_short.next;
            l_short++;
        }
        if (l_long>l_short){
            p_long=headA;
            p_short=headB;
        }else {
            l_long=l_long^l_short;
            l_short=l_long^l_short;
            l_long=l_long^l_short;
            p_long=headB;
            p_short=headA;
        }
        int offset=l_long-l_short;
        while (offset>0){
            p_long=p_long.next;
            offset--;
        }
        while (p_long!=null&&p_short!=null){
            if (p_long==p_short){
                return p_long;
            }else {
                p_long=p_long.next;
                p_short=p_short.next;
            }
        }
        return null;
    }
    //双指针法
    //比较奇妙
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB){
        ListNode ha = headA, hb = headB;
        while (ha != hb) {
            ha = ha != null ? ha.next : headB;
            hb = hb != null ? hb.next : headA;
        }
        return ha;
    }
}
