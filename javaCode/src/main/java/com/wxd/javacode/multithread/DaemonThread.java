package com.wxd.javacode.multithread;

public class DaemonThread {
    public static void main(String[] args) throws Exception{
        Thread t = new MyThread();
        t.setDaemon(true);
        t.start();
        System.out.println("end");
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            int n = 0;
            while (!isInterrupted()) {
                n++;
                System.out.println(n + "hello!");
            }
        }
    }
}
