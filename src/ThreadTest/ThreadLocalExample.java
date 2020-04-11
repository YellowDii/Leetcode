package ThreadTest;

public class ThreadLocalExample {
    public static void main(String[] args) {
        ThreadLocal threadLocal = new ThreadLocal();
        Thread thread1 = new Thread(() -> {
            threadLocal.set(1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadLocal.get());
            threadLocal.remove();
        });
        Thread thread2 = new Thread(() -> {
            threadLocal.set(2);
            //System.out.println(threadLocal.get());
            threadLocal.remove();
        });
        //  其实用的是自己的threadlocal 每个Thread有自己的ThreadLocalMap ThreadLocalMap中保存着各个threaLocal
        thread1.start();
        thread2.start();
    }
}
