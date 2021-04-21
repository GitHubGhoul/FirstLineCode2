package com.wxd.javacode.multithread;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockClass {
    public static void main(String[] args) {
        Counter count = new Counter();
        new Thread() {
            public void run() {
                count.inc(1);
            }
        }.start();
        new Thread() {
            public void run() {
                count.get();
            }
        }.start();
    }
    static class Counter{
        private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
        private final Lock rlock = rwLock.readLock();
        private final Lock wlock = rwLock.writeLock();
        private int[] counts = new int[10];

        public void inc(int index) {
            wlock.lock(); // 加写锁
            try {
                counts[index] += 1;
            } finally {
                wlock.unlock(); // 释放写锁
            }
        }

        public int[] get() {
            rlock.lock(); // 加读锁
            try {
                return Arrays.copyOf(counts, counts.length);
            } finally {
                rlock.unlock(); // 释放读锁
            }
        }
    }

}
