package nju.software.dynamic_proxy;

import java.util.Random;

public class Person implements Creature {
    private String name;

    public Person(String name){
        this.name = name;
    }
    //cglib��enhancer��create����Ҫ�󱻴����������һ���޲ι���
    public Person(){

    }

    public Boolean findJob(){
        System.out.println(name + "�ҹ������壡");
        int a = new Random().nextInt(2);
        if(a == 0){
            System.out.println("ʧ���ˡ�");
            return false;
        }else if(a == 1){
            System.out.println("Ү���ɹ��ˣ�");
            return true;
        }else {
            System.out.println("�㌦random.nextInt������ʶ����");
            return false;
        }
    }
}
