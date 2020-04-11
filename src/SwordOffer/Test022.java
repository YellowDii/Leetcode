package SwordOffer;

import java.util.Stack;

public class Test022 {
    /**
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断二个序列是否为该栈的弹出顺序。
     * 假设压入栈的所有数字均不相等。例如序列1 、2、3 、4、5 是某栈压栈序列，
     * 序列4、5、3、2、1是该压栈序列对应的一个弹出序列，
     * 但4、3、5、1、2就不可能是该压棋序列的弹出序列。
     */
    public static boolean isPopOrder(int[] push, int[] pop){
        boolean isOk=false;
        if (push!=null&&pop!=null&&push.length>0&&pop.length==push.length){
            Stack<Integer> stack=new Stack<>();
            int pushnext=0;
            int popnext=0;
            while (popnext<pop.length){
                while (stack.isEmpty()||stack.peek()!=pop[popnext]){
                    if (pushnext>=push.length){
                        break;
                    }
                    stack.push(push[pushnext]);
                    pushnext++;
                }
                //这时候出循环有两种情况
                //1.栈顶就是要出栈的元素
                //2.一直push到最后 栈顶都不是出栈元素
                if (stack.peek()!=pop[popnext]){
                    break;//这是第二种情况
                }
                //第一种情况
                stack.pop();
                popnext++;
            }
            if (stack.isEmpty()){
                isOk=true;
            }
        }

        return isOk;
    }
    public static void main(String[] args) {
        int[] push = {1, 2, 3, 4, 5};
        int[] pop1 = {4, 5, 3, 2, 1};
        int[] pop2 = {3, 5, 4, 2, 1};
        int[] pop3 = {4, 3, 5, 1, 2};
        int[] pop4 = {3, 5, 4, 1, 2};

        System.out.println("true: " + isPopOrder(push, pop1));
        System.out.println("true: " + isPopOrder(push, pop2));
        System.out.println("false: " + isPopOrder(push, pop3));
        System.out.println("false: " + isPopOrder(push, pop4));
    }
}
