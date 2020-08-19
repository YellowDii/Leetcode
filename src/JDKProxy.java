import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JDKProxy implements InvocationHandler {
    private Object person;

    public JDKProxy(Object o){
        person = o;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("��������ǰ��Ϣ����");
        Object res = method.invoke(person,args);
        System.out.println("�������º���Ϣ����");
        if(res instanceof Boolean){
            if(((Boolean) res).booleanValue()){
                System.out.println("�����ˡ�");
            }else {
                System.out.println("���");
            }
        }

        return null;
    }
}
