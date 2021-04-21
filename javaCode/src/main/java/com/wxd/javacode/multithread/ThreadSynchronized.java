package com.wxd.javacode.multithread;

public class ThreadSynchronized {
    public static void main(String[] args) throws Exception {
        var add = new AddThread();
        var dec = new DecThread();
        add.start();
        dec.start();
        add.join();
        dec.join();
        System.out.println(Counter.count);

        var ts = new Thread[]{new AddStudentThread(), new DecStudentThread(), new AddTeacherThread(), new DecTeacherThread()};
        for (var t : ts) {
            t.start();
        }
        for (var t : ts) {
            t.join();
        }
        System.out.println(Counter.studentCount);
        System.out.println(Counter.teacherCount);
    }

    static class Counter {
        public static final Object lock = new Object();
        public static final Object lock1 = new Object();
        public static final Object lock2 = new Object();
        public static int count = 0;
        public static int studentCount = 0;
        public static int teacherCount = 0;
    }

    static class AddThread extends Thread {
        public void run() {
            for (int i = 0; i < 10000; i++) {
                synchronized (Counter.lock1) {
                    Counter.count += 1;
                }
            }
        }
    }

    static class DecThread extends Thread {
        public void run() {
            for (int i = 0; i < 10000; i++) {
                synchronized (Counter.lock2) {
                    Counter.count -= 1;
                }
            }
        }
    }

    static class AddStudentThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                synchronized (Counter.lock) {
                    Counter.studentCount += 1;
                }
            }
        }
    }

    static class DecStudentThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                synchronized (Counter.lock) {
                    Counter.studentCount -= 1;
                }
            }
        }
    }

    static class AddTeacherThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                synchronized (Counter.lock) {
                    Counter.teacherCount += 1;
                }
            }
        }
    }

    static class DecTeacherThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                synchronized (Counter.lock) {
                    Counter.teacherCount -= 1;
                }
            }
        }
    }
}
