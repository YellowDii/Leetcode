package SwordOffer;

public class Test026 {
    /**
     * 复杂链表结点
     */
    public static class ComplexListNode {
        int value;
        ComplexListNode next;
        ComplexListNode sibling;
    }
    /**
     * 实现函复制一个复杂链表。在复杂链表中，每个结点除了有一个next字段指向下一个结点外，
     * 还有一个sibling字段指向链表中的任意结点或者NULL
     *
     * @param head 链表表头结点
     * @return 复制结点的头结点
     */
    public static ComplexListNode clone(ComplexListNode head) {
        // 如果链表为空就直接返回空
        if (head == null) {
            return null;
        }

        // 先复制结点
        cloneNodes(head);
        // 再链接sibling字段
        connectNodes(head);
        // 将整个链表拆分，返回复制链表的头结点
        return reconnectNodes(head);
    }



    /**
     * 复制一个链表，并且将复制后的结点插入到被复制的结点后面，只链接复制结点的next字段
     *
     * @param head 待复制链表的头结点
     */
    private static void cloneNodes(ComplexListNode head) {
        while (head!=null){
            ComplexListNode tmp=new ComplexListNode();
            tmp.value=head.value;
            tmp.next=head.next;
            //并且将复制后的结点插入到被复制的结点后面，只链接复制结点的next字段
            head.next=tmp;
            head=tmp.next;
        }
    }

    /**
     * 设置复制节点的sibing字段
     * @param head
     */
    private static void connectNodes(ComplexListNode head) {
        while (head!=null){
            if(head.sibling!=null){
                //进入判断的head和head.sibling是没复制前的
                //head.next 和 head.sibling.next是复制后的
                head.next.sibling=head.sibling.next;
            }
            //跳到下一个没有复制的
            head=head.next.next;
        }
    }
    /**
     * 刚复制结点和被复制结点拆开，还原被复制的链表，同时生成监制链表
     *
     * @param head 链表的头结点
     * @return 复制链表的头结点
     */
    private static ComplexListNode reconnectNodes(ComplexListNode head) {
        if (head==null){
            return head;
        }
        //用于记录复制链表的头结点
        ComplexListNode newHead=head.next;
        // 用于记录当前处理的复制结点
        ComplexListNode cur=newHead;
        head.next=newHead.next;
        head=head.next;
        while (head!=null){
            cur.next=head.next;
            cur=cur.next;
            head.next=cur.next;
            head=cur.next;
        }
        return newHead;
    }
    /**
     * 判断两个链表是否是同一个链表，不是值相同
     *
     * @param h1 链表头1
     * @param h2 链表头2
     * @return true：两个链表是同一个链表，false：不是
     */
    public static boolean isSame(ComplexListNode h1, ComplexListNode h2) {
        while (h1 != null && h2 != null) {
            if (h1 == h2) {
                h1 = h1.next;
                h2 = h2.next;
            } else {
                return false;
            }
        }

        return h1 == null && h2 == null;
    }
}
