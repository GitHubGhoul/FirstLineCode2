package com.wxd.javacode.multithread;

import java.util.concurrent.locks.StampedLock;

public class StampedLockClass {
    public static void main(String[] args) {
        Point point = new Point();
        new Thread() {
            public void run() {
                point.move(1,2);
            }
        }.start();
        new Thread() {
            public void run() {
                point.distanceFromOrigin();
            }
        }.start();
    }

    static class Point {
        private final StampedLock stampedLock = new StampedLock();
        private double x;
        private double y;

        public void move(double deltaX, double deltaY) {
            long stamp = stampedLock.writeLock();
            try {
                x += deltaX;
                y += deltaY;
            } finally {
                stampedLock.unlockWrite(stamp);
            }
        }

        public double distanceFromOrigin() {
            long stamp = stampedLock.tryOptimisticRead(); // 获得一个乐观读锁
            // 注意下面两行代码不是原子操作
            // 假设x,y = (100,200)
            double currentX = x;
            // 此处已读取到x=100，但x,y可能被写线程修改为(300,400)
            double currentY = y;
            // 此处已读取到y，如果没有写入，读取是正确的(100,200)
            // 如果有写入，读取是错误的(100,400)
            if (!stampedLock.validate(stamp)) { // 检查乐观读锁后是否有其他写锁发生
                stamp = stampedLock.readLock(); // 获取一个悲观读锁
                try {
                    currentX = x;
                    currentY = y;
                } finally {
                    stampedLock.unlockRead(stamp); // 释放悲观读锁
                }
            }
            return Math.sqrt(currentX * currentX + currentY * currentY);
        }
    }
}
