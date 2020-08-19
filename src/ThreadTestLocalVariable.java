import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ThreadTestLocalVariable {
    public static void main(String[] args) {
        Map<Integer,Integer> map=new HashMap();
        int[] j = {0};
        for (int i=0;i<10;i++){
             new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        map.put(j[0],1);
                        j[0]++;
                    }
                }
            }).start();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println(map.size());
                }
            }
        }).start();
    }
}
