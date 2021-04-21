package com.wxd.javacode.multithread;

public class DeadLock {
    public static void main(String[] args) {

    }

    static class Counter {

        public static final Object lockA = new Object();
        public static final Object lockB = new Object();
        public static int value = 0;
        public static int another = 0;

        public void add(int m) {
            synchronized(Counter.lockA) { // 获得lockA的锁
                this.value += m;
                synchronized(Counter.lockB) { // 获得lockB的锁
                    this.another += m;
                } // 释放lockB的锁
            } // 释放lockA的锁
        }

        public void dec(int m) {
            synchronized(lockB) { // 获得lockB的锁
                this.another -= m;
                synchronized(lockA) { // 获得lockA的锁
                    this.value -= m;
                } // 释放lockA的锁
            } // 释放lockB的锁
        }
    }
}
