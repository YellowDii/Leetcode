package nju.software.dynamic_proxy;

import java.util.Random;

public class Person implements Creature {
    private String name;

    public Person(String name){
        this.name = name;
    }
    //cglib的enhancer的create方法要求被代理类必须有一个无参构造
    public Person(){

    }

    public Boolean findJob(){
        System.out.println(name + "找工作，冲！");
        int a = new Random().nextInt(2);
        if(a == 0){
            System.out.println("失敗了。");
            return false;
        }else if(a == 1){
            System.out.println("耶！成功了！");
            return true;
        }else {
            System.out.println("你對random.nextInt函数认识错误！");
            return false;
        }
    }
}
