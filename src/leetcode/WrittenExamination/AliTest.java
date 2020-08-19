package leetcode.WrittenExamination;

import java.util.ArrayList;
import java.util.List;

public class AliTest {
    public static Node solve(Node a, Node b){
        if(a==null||b==null){
            return null;
        }
        Node head=null;
        Node pa=a;
        Node pb=b;
        int tmp=0;//保存进位
        while(a!=null&&b!=null){
            int curSum=a.val+b.val+tmp;
            tmp=curSum/10;
            int curVal=curSum%10;
            a=a.next;
            b=b.next;
            head=new Node(curVal,head);
        }
        //如果a还有遍历节点
        if(a!=null){
            while(a!=null){
                int curSum=a.val+tmp;
                tmp=curSum/10;
                int curVal=curSum%10;
                a=a.next;
                head=new Node(curVal,head);
            }
        }
        //如果b还有遍历节点
        if(b!=null){
            while(b!=null){
                int curSum=b.val+tmp;
                tmp=curSum/10;
                int curVal=curSum%10;
                b=b.next;
                head=new Node(curVal,head);
            }
        }
        if(tmp>0){
            return new Node(tmp,head);
        }else{
            return head;
        }
    }

    static class Node{
        int val;
        Node next;
        Node(int val,Node next){
            this.val=val;
            this.next=next;
        }
        Node(int val){
            this.val=val;
            this.next=null;
        }
    }

    public static void main(String[] args) {
        Node node=new Node(2);
        Node head=new Node(5,node);
        head=new Node(9,head);
        head=new Node(4,head);

        Node headB=new Node(2,null);
        headB=new Node(6,headB);
        headB=new Node(4,headB);
        Node ans=solve(head,headB);
        Node headc=new Node(3,null);
        headc=new Node(4,headc);
        headc=new Node(2,headc);

        Node headd=new Node(4,null);
        headd=new Node(6,headd);
        headd=new Node(5,headd);
        Node n=solve(headc,headd);
        while(n!=null){
            System.out.print(n.val+" ");
            n=n.next;
        }
    }
}
