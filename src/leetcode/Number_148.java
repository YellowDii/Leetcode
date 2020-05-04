package leetcode;

public class Number_148 {
    /**
     * 148.排序链表（sort-list）
     *
     * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
     *
     * 示例 1:
     *
     * 输入: 4->2->1->3
     * 输出: 1->2->3->4
     * 示例 2:
     *
     * 输入: -1->5->3->4->0
     * 输出: -1->0->3->4->5
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sort-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            this.val=x;
        }
    }
    //归并排序 先分割 在合并 不过这个递归 空间复杂度肯定不满足O(1)
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;
        //上面分割好了
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);
        //作为头指针
        ListNode h = new ListNode(0);
        ListNode res = h;
        //开始合并
        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left != null ? left : right;
        return res.next;
    }

    /*----------------------非递归的----------------------------*/


    //根据步长分割
    private ListNode split(ListNode head, int step) {
        if (head == null) return null;

        for (int i = 1; head.next != null && i < step; i++) {
            head = head.next;
        }

        ListNode right = head.next;
        head.next = null;
        return right;
    }
    // 合并两个有序链表
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode sentry = new ListNode(-1);
        ListNode curr = sentry;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }

            curr = curr.next;
        }

        curr.next = l1 != null ? l1 : l2;
        return sentry.next;
    }
    // 获取链表的长度
    private int listNodeLength(ListNode head) {
        int length = 0;
        ListNode curr = head;

        while (curr != null) {
            length++;
            curr = curr.next;
        }

        return length;
    }
    //自底向上的归并
    public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 获取链表长度
        int len = listNodeLength(head);

        // 哨兵节点
        ListNode sentry = new ListNode(-1);
        sentry.next = head;

        // 循环 log n 次
        for (int i = 1; i < len; i <<= 1) {
            ListNode prev = sentry;
            ListNode curr = sentry.next;
            // 循环 n 次
            while (curr != null) {
                ListNode left = curr;
                ListNode right = split(left, i);
                curr = split(right, i);
                prev.next = mergeTwoLists(left, right);

                while (prev.next != null) {
                    prev = prev.next;
                }
            }
        }

        return sentry.next;
    }

}
