import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    //保存被代理的对象
    private Object target;
    public CglibProxy(Object target) {
        this.target = target;
    }

    //生成代理对象
    public Object createCgLibProxy(){
        //工具类
        Enhancer enhancer=new Enhancer();
        //设置被代理的对象，也可以理解为设置父类，因为动态代理类是继承了被动态代理类。
        enhancer.setSuperclass(target.getClass());
        //设置回调函数
        enhancer.setCallback(this);
        //创建子类的动态代理类对象
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("01：打开冰箱门~~~~");
        method.invoke(target,objects);
        System.out.println("03：关闭冰箱门~~~~");
        return null;
    }
    
}

