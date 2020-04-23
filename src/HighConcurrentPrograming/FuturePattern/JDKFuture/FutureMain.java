package HighConcurrentPrograming.FuturePattern.JDKFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class FutureMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //构造FutureTask
        FutureTask<String> future = new FutureTask<String>(new RealData("a"));
        ExecutorService executor = Executors.newFixedThreadPool(1);
        //执行FutureTask，相当于之前写的client.request("a")发送请求
        //在这里开启线程进行RealData的call()执行
        executor.submit(future);
        System.out.println("请求完毕");
        try {
            //sleep代替其他业务逻辑
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        //如果此时call()方法没有执行完成 则依然会等待
        System.out.println("数据"+future.get());

    }
}
