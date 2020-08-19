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
        //核心工具类
        Enhancer enhancer = new Enhancer();
        //获取类对象，基于框架，不需要再显示获取类加载器
        enhancer.setSuperclass(object.getClass());
        //设置回调函数
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
     * 创造被代理类的模板对象，这个没用，就当复习反射吧。
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
