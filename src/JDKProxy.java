import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JDKProxy implements InvocationHandler {
    private Object person;

    public JDKProxy(Object o){
        person = o;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("这里是事前消息处理");
        Object res = method.invoke(person,args);
        System.out.println("这里是事后消息处理");
        if(res instanceof Boolean){
            if(((Boolean) res).booleanValue()){
                System.out.println("人上人。");
            }else {
                System.out.println("废物。");
            }
        }

        return null;
    }
}
