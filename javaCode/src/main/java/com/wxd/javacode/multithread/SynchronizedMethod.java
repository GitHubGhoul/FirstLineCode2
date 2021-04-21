package com.wxd.javacode.multithread;

public class SynchronizedMethod {
    public static void main(String[] args) {
        var c1 = new Counter();
        var c2 = new Counter();

        // 对c1进行操作的线程:
        new Thread(() -> {
            c1.add(1);
        }).start();
        new Thread(() -> {
            c1.dec(1);
        }).start();

        // 对c2进行操作的线程:
        new Thread(() -> {
            c2.add(1);
        }).start();
        new Thread(() -> {
            c2.dec(1);
        }).start();
    }
    static class Counter {
        private int count = 0;

        public void add(int n) {
            synchronized(this) {
                count += n;
            }
        }

        public void dec(int n) {
            synchronized(this) {
                count -= n;
            }
        }

        public int get() {
            return count;
        }
    }
}
