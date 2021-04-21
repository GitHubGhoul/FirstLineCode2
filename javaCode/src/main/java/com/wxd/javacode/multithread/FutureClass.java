package com.wxd.javacode.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureClass {
    public static void main(String[] args) throws Exception{
        ExecutorService executor = Executors.newFixedThreadPool(4);
        // 定义任务:
        Callable<String> task = (Callable<String>) new ThreadPool.Task("");
        // 提交任务并获得Future:
        Future<String> future = executor.submit(task);
        // 从Future获取异步执行返回的结果:
        String result = future.get(); // 可能阻塞
        //get()：获取结果（可能会等待）
        //get(long timeout, TimeUnit unit)：获取结果，但只等待指定的时间；
        //cancel(boolean mayInterruptIfRunning)：取消当前任务；
        //isDone()：判断任务是否已完成。
    }
}
