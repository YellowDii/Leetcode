

import nju.software.dynamic_proxy.Creature;
import nju.software.dynamic_proxy.MyCglibProxy;
import nju.software.dynamic_proxy.Person;

import java.lang.reflect.Proxy;

public class DynamicProxyDemo {

    public static void main(String[] args) {
        //JDK Proxy
        System.out.println("������JDK Proxy");
        //���в������ǻ��ڽӿڵ�
        //�������������
        Creature lqy = new Person("Galaxy");
        //�����������������
        JDKProxy lqyProxyHandler = new JDKProxy(lqy);
        //���ش������ͱ������������Ϣ�������������ȡ��ǿ��Person
        Creature lqyProxy = (Creature) Proxy.newProxyInstance(Creature.class.getClassLoader(), new Class<?>[]{Creature.class},lqyProxyHandler );
        lqyProxy.findJob();

        //Cglib
        System.out.println("������Cglib");
        //�������ǻ������
        //�������������
        //��������handler
        MyCglibProxy myCglibProxy = new MyCglibProxy();
        myCglibProxy.setObject(lqy);
        Person lqyCglib = (Person) myCglibProxy.newInstance();
        lqyCglib.findJob();
    }
}
