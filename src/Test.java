import sun.reflect.Reflection;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class Test {
    static AtomicInteger atomicInteger=new AtomicInteger();
    public static void main(String[] args) {
        atomicInteger.set(1);
        System.out.println(atomicInteger.incrementAndGet());
        AtomicStampedReference<Integer> money=new AtomicStampedReference<Integer>(19,0);
    }
}
