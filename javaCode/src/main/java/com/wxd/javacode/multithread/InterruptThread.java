package com.wxd.javacode.multithread;

public class InterruptThread {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new MyThread();
        t.start();
        Thread.sleep(1000);
        t.interrupt();
        t.join();
        System.out.println("end");

        HelloThread1 h = new HelloThread1();
        h.start();
        Thread.sleep(1);
        h.running = false; // 标志位置为false
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            /*int n = 0;
            while (!isInterrupted()) {
                n++;
                System.out.println(n + " hello!");
            }*/
            Thread hello = new HelloThread();
            hello.start();
            try {
                hello.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("interrupted!");
            }
            hello.interrupt();
        }
    }

    static class HelloThread extends Thread {
        @Override
        public void run() {
            int n = 0;
            while (!isInterrupted()) {
                n++;
                System.out.println(n + "hello");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    static class HelloThread1 extends Thread {
        public volatile boolean running = true;
        @Override
        public void run() {
            int n = 0;
            while (running) {
                n ++;
                System.out.println(n + " hello!");
            }
            System.out.println("end!");
        }
    }
}
