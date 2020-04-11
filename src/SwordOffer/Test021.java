package SwordOffer;

import java.util.Stack;

public class Test021 {
    /**
     * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的min函数。
     * 在该栈中，调用pop、push 及min的时间复杂度都是0(1)
     */
    public static class StackWithMin<T extends Comparable<T>>{
        // 数据栈，用于存放插入的数据
        private Stack<T> dataStack;
        // 最小数位置栈，存放数据栈中最小的数的位置
        // 注意 是位置（假设位置处在第i个 值为k 它表示在dataStack[0]~[i]所有数中 最小数为dataStack[k]）
        private Stack<Integer> minStack;
        public StackWithMin(){
            this.dataStack=new Stack<>();
            this.minStack=new Stack<>();
        }
        public T pop(){
            if (dataStack.isEmpty()){
                throw new RuntimeException("Stack is empty!");
            }
            // 如果有数据，最小数位置栈和数据栈必定是有相同的元素个数，
            // 两个栈同时出栈
            minStack.pop();
            return dataStack.pop();
        }
        public void push(T t){
            if (t==null){
                throw new RuntimeException("can't push NULL!");
            }
            if (dataStack.isEmpty()){
                dataStack.push(t);
                minStack.push(0);
            }else {
                T e=dataStack.get(minStack.peek());
                dataStack.push(t);
                if (t.compareTo(e)<0){
                    minStack.push(dataStack.size()-1);
                }else {
                    minStack.push(minStack.peek());
                }
            }
        }
        public T min(){
            if (minStack.isEmpty()){
                throw new RuntimeException("Stack is empty!");
            }
            return dataStack.get(minStack.peek());
        }
    }

    public static void main(String[] args) {
        StackWithMin<Integer> stack = new StackWithMin<>();
        stack.push(3);
        System.out.println(stack.min() == 3);
        stack.push(4);
        System.out.println(stack.min() == 3);
        stack.push(2);
        System.out.println(stack.min() == 2);
        stack.push(3);
        System.out.println(stack.min() == 2);
        stack.pop();
        System.out.println(stack.min() == 2);
        stack.pop();
        System.out.println(stack.min() == 3);
        stack.push(0);
        System.out.println(stack.min() == 0);
    }
}
