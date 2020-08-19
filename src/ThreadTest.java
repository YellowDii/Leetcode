import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {

    /**
     * 三个线程 每个只能分别输出1 2 3
     * 使得循环输出123
     */

    public  void solution1() throws InterruptedException {
        Semaphore sm1=new Semaphore(1);
        Semaphore sm2=new Semaphore(1);
        Semaphore sm3=new Semaphore(1);
        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        sm1.acquire();
                        System.out.print(1);
                        sm2.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        sm2.acquire();
                        System.out.print(2);
                        sm3.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread thread3=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        sm3.acquire();
                        System.out.print(3);
                        sm1.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //保证线程1先输出1
        sm2.acquire();
        sm3.acquire();
        thread1.start();
        thread2.start();
        thread3.start();
    }
    private static Lock lock = new ReentrantLock();//通过JDK5中的锁来保证线程的访问的互斥
    private static int state = 0;
    static class First extends Thread {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                if (state % 4 == 0) {
                    System.out.println("1");
                    state++;
                }
                lock.unlock();
            }
        }
    }

    static class Second extends Thread {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                if (state % 4 == 1) {
                    System.out.println("2");
                    state++;
                }
                lock.unlock();
            }
        }
    }

    static class Third extends Thread {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                if (state % 4 == 2) {
                    System.out.println("3");
                    state++;
                }
                lock.unlock();
            }
        }
    }

    static class Forth extends Thread {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                if (state % 4 == 3) {
                    System.out.println("4");
                    state++;
                }
                lock.unlock();
            }
        }
    }

    public static void Solution2() {
        First first = new First();
        Second second = new Second();
        Third third = new Third();
        Forth forth = new Forth();
        first.start();
        second.start();
        third.start();
        forth.start();
    }

    //volatile
    volatile static int state_v = 0;

    static class A extends Thread {
        @Override
        public void run() {
            while (true) {
                if (state_v % 3 == 0) {
                    System.out.print("1");
                    state_v++;
                }
            }
        }
    }

    static class B extends Thread {
        @Override
        public void run() {
            while (true) {
                if (state_v % 3 == 1) {
                    System.out.print("2");
                    state_v++;
                }
            }
        }
    }

    static class C extends Thread {
        @Override
        public void run() {
            while (true) {
                if (state_v % 3 == 2) {
                    System.out.println("3");
                    state_v++;
                }
            }
        }
    }

    public static void Solution3(){
        A a=new A();
        B b=new B();
        C c=new C();

        a.start();
        b.start();
        c.start();
    }

    public static void main(String[] args) {
        Solution3();
    }
}
