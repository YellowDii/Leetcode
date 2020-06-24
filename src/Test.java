import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test {
    static AtomicInteger atomicInteger=new AtomicInteger();
    private Lock lock=new ReentrantLock();
    private ThreadLocal<Map<String,Object>> beanMap=new ThreadLocal(){
        @Override
        protected Object initialValue() {
            return new HashMap<>();
        }
    };

    public static void main(String[] args) {
//        atomicInteger.set(1);
//        System.out.println(atomicInteger.incrementAndGet());
//        AtomicStampedReference<Integer> money=new AtomicStampedReference<Integer>(19,0);
        Table_name_Number my=new Table_name_Number(new ArrayList<>(),"1");
        List<Item> items=new ArrayList<>();
        items.add(new Item("11",11,22));
        items.add(new Item("3214",2321,42));
        items.add(new Item("214",124,321));
        System.out.println(my.getItems());
        my.getItems().addAll(items);
        System.out.println(my.getItems());

        /**
        HashMap<String, List<String>> hashMap=new HashMap<>();
        List<String> a=new ArrayList<>();
        a.add("11");a.add("32");a.add("3424");
        String s="321sxads";
        hashMap.put(s,a);
        a=new ArrayList<>();
        a.add(s);a.add(s+s);a.add(s+s+"1313");
        hashMap.put(s+s,a);
        for (List<String> value:hashMap.values()){
            System.out.println(value);
        }
        System.out.println("----------------------------------");
        for (String s1:hashMap.get(s+s)){
            hashMap.get(s).add(s1);
        }
        hashMap.remove(s+s);
        for (List<String> value:hashMap.values()){
            System.out.println(value);
        }
         **/
    }
}
