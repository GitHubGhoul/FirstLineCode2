package com.wxd.javacode.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
    public static void main(String[] args) {
        // 创建一个固定大小的线程池:
        ExecutorService es = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 6; i++) {
            es.submit(new Task("" + i));
        }
        // 关闭线程池:
        es.shutdown();

        int min = 4;
        int max = 10;
        ExecutorService es1 = new ThreadPoolExecutor(min, max,
                60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

        ScheduledExecutorService ses = Executors.newScheduledThreadPool(4);
        // 1秒后执行一次性任务:
        ses.schedule(new Task("one-time"), 1, TimeUnit.SECONDS);
        // 2秒后开始执行定时任务，每3秒执行:
        ses.scheduleAtFixedRate(new Task("fixed-rate"), 2, 3, TimeUnit.SECONDS);
        // 2秒后开始执行定时任务，以3秒为间隔执行:
        ses.scheduleWithFixedDelay(new Task("fixed-delay"), 2, 3, TimeUnit.SECONDS);
    }

    static class Task implements Runnable {
        private final String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("start task " + name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println("end task " + name);
        }
    }

}
