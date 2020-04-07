package SwordOffer;

import java.util.Stack;

public class Test007 {
    /**
     * 用两个栈模拟的队列
     * 用两个核实现一个队列。队列的声明如下，诸实现它的两个函数appendTail和deleteHead，
     * 分别完成在队列尾部插入结点和在队列头部删除结点的功能。
     */
    public static class MyQueue<T>{
        //插入栈 负责插入
        private Stack<T> stack1=new Stack<>();
        //弹出栈 负责弹出数据
        private Stack<T> stack2=new Stack<>();
        public void appendTail(T t){
            stack1.add(t);
        }
        public T deleteHead(){
            //先判断弹出栈是不是空
            //如果为空，将插入栈全部pop到弹出栈中
            if (stack2.isEmpty()){
                while (!stack1.isEmpty()){
                    stack2.push(stack1.pop());
                }
            }
            if (stack2.isEmpty()){
                throw new RuntimeException("no element.");
            }
            //返回弹出栈栈顶，对应队首
            return stack2.pop();
        }
    }
}
