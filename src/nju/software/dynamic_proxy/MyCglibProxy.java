package nju.software.dynamic_proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyCglibProxy implements MethodInterceptor {
    private Object object;

    public MyCglibProxy(){

    }




    public Object newInstance(){
        //���Ĺ�����
        Enhancer enhancer = new Enhancer();
        //��ȡ����󣬻��ڿ�ܣ�����Ҫ����ʾ��ȡ�������
        enhancer.setSuperclass(object.getClass());
        //���ûص�����
        enhancer.setCallback(this);
        return enhancer.create();
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("forward");
        Object o1 = method.invoke(object,objects);
        System.out.println("res:" + o1.toString());
        System.out.println("backward");
        return null;
    }




    /**
     * ���챻�������ģ��������û�ã��͵���ϰ����ɡ�
     * @param proxyClass
     */
    public void setProxyClass(Class proxyClass){
        try {
            Constructor constructor = proxyClass.getConstructor(null);
            object = constructor.newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
