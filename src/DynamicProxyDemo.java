

import nju.software.dynamic_proxy.Creature;
import nju.software.dynamic_proxy.MyCglibProxy;
import nju.software.dynamic_proxy.Person;

import java.lang.reflect.Proxy;

public class DynamicProxyDemo {

    public static void main(String[] args) {
        //JDK Proxy
        System.out.println("这里是JDK Proxy");
        //所有操作都是基于接口的
        //创建被代理对象
        Creature lqy = new Person("Galaxy");
        //创建被代理对象处理器
        JDKProxy lqyProxyHandler = new JDKProxy(lqy);
        //加载处理器和被代理对象类信息，类加载器，获取加强版Person
        Creature lqyProxy = (Creature) Proxy.newProxyInstance(Creature.class.getClassLoader(), new Class<?>[]{Creature.class},lqyProxyHandler );
        lqyProxy.findJob();

        //Cglib
        System.out.println("这里是Cglib");
        //操作都是基于类的
        //创建被代理对象
        //创建代理handler
        MyCglibProxy myCglibProxy = new MyCglibProxy();
        myCglibProxy.setObject(lqy);
        Person lqyCglib = (Person) myCglibProxy.newInstance();
        lqyCglib.findJob();
    }
}
