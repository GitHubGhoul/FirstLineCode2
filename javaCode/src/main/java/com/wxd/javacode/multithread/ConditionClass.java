package com.wxd.javacode.multithread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionClass {
    public static void main(String[] args) throws Exception{
        var q = new TaskQueue();
        var ts = new ArrayList<Thread>();
        for (int i=0; i<5; i++) {
            var t = new Thread() {
                public void run() {
                    // 执行task:
                    while (true) {
                        String s = q.getTask();
                        System.out.println("execute task: " + s);
                    }
                }
            };
            t.start();
            ts.add(t);
        }
        var add = new Thread(() -> {
            for (int i=0; i<10; i++) {
                // 放入task:
                String s = "t-" + Math.random();
                System.out.println("add task: " + s);
                q.addTask(s);
                try { Thread.sleep(100); } catch(InterruptedException e) {}
            }
        });
        add.start();
        add.join();
        Thread.sleep(100);
        for (var t : ts) {
            t.interrupt();
        }
    }

    static class TaskQueue {
        private final Lock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();
        private Queue<String> queue = new LinkedList<>();

        public void addTask(String s) {
            lock.lock();
            try {
                queue.add(s);
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }

        public String getTask() {
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    condition.await();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            return queue.remove();
        }
    }
}
