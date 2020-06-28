package SwordOffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Test005 {
    /**
     * 题目：
     * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
     */
    class ListNode{
        int val;
        ListNode next;
        ListNode(int value){
            this.val=value;
        }
    }

    //单向栈
    public static ArrayList<ListNode> printListInverselyUsingIteration(ListNode root){
        ArrayList<ListNode> res=new ArrayList<>();
        if (root==null) {
            return res;
        }
        Stack<ListNode> stack=new Stack<>();
        while (root!=null){
            stack.push(root);
            root=root.next;
        }
        while (!stack.isEmpty()){
            res.add(stack.pop());
        }
        return res;
    }
}
