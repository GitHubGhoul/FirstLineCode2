package com.wxd.javacode.multithread;

public class ThreadStatus {
    public static void main(String[] args) throws InterruptedException{
        Thread t = new Thread(() -> {
            System.out.println("hello");
        });
        System.out.println("start");
        t.start();
        t.join();
        System.out.println("end");
    }
}
