package HighConcurrentPrograming;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkJoinExample extends RecursiveTask<Integer> {
    private final int threshold = 5000000;
    private int first;
    private int last;

    public ForkJoinExample(int first, int last) {
        this.first = first;
        this.last = last;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        if (last - first <= threshold) {
            // 任务足够小则直接计算
            for (int i = first; i <= last; i++) {
                result += i;
            }
        } else {
            // 拆分成小任务
            int middle = first + (last - first) / 2;
            ForkJoinExample leftTask = new ForkJoinExample(first, middle);
            ForkJoinExample rightTask = new ForkJoinExample(middle + 1, last);
            leftTask.fork();
            rightTask.fork();
            result = leftTask.join() + rightTask.join();
        }
        return result;
    }

    /**
     * thread=5时
     * 比较 i=10000000时 :
     * -2004260032 用时：9678800
     * -2004260032 用时:528119400
     *
     * 比较 i=1000000000时 :
     * -243309312用时：401811800
     * -243309312用时:16749580200
     *
     * thread=5000000 i=1000000000时
     * -243309312用时: 375679400
     * -243309312用时: 170334499
     *
     * thread=50000000 i=1000000000时
     * -243309312用时：388733200
     * -243309312用时: 188395499
     */
    //thread最好是大一点 不然会拆分过多的分支 消耗大量时间
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Long startTime=System.nanoTime();
        int res=0;
        for (int i=0;i<=1000000000;i++){
            res+=i;
        }
        Long endTime=System.nanoTime();

        System.out.println(res +"用时: "+(endTime-startTime));
        startTime=System.nanoTime();
        ForkJoinExample example = new ForkJoinExample(1, 1000000000);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future result = forkJoinPool.submit(example);
        System.out.println(result.get()+"用时: "+(System.nanoTime()-startTime));

        //1.8 以后可以用ComletableFuture

    }
}
