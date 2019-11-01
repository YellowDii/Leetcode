package CoreJavaValume1;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.function.IntConsumer;

/**
 * 方法引用
 */
public class Method_reference {
    static class Greeter{
        public void greet(){
            System.out.println("Hello World!");
        }

        public void greet(ActionEvent actionEvent) {
            System.out.println("Hello World!");
        }
    }
    static class TimedGreeter extends Greeter{
        public void greet(){
            Timer timer = new Timer(1000,super::greet);
            timer.start();
        }

    }
    static void repeat(int n,Runnable action){
        for (int i=0;i<n;i++){
            action.run();
        }
    }

    public static void main(String[] args) {
        System.out.println(Double[].class.getName());
        //repeat(10,() -> System.out.println("Hello World!"));

        //动态创建类实例 与e的类型相同 e.getClass().newInstance();
    }
}
