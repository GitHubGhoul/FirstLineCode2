package com.wxd.javacode.multithread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockClass {
    public static void main(String[] args) throws Exception{
        Counter count = new Counter();
        new Thread() {
            public void run() {
                count.add(1);
            }
        }.start();
        new Thread() {
            public void run() {
                count.add(2);
            }
        }.start();
    }

    static class Counter {
        private final Lock lock = new ReentrantLock();
        private int count;

        public void add(int n){
            lock.lock();
            try {
                count += n;
            } finally {
                lock.unlock();
            }
        }
    }

}
