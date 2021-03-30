package com.wxd.javacode.introduction.process_control;

public class DoWhileClass {
    public static void main(String[] args) {
        int sum = 0;
        int n = 1;
        do {
            sum = sum + n;
            n++;
        } while (n <= 100);
        System.out.println(sum);
        exercise();
    }

    private static void exercise() {
        int sum = 0;
        int m = 20;
        int n = 100;
        // 使用do while计算M+...+N:
        do {
            sum = sum + m;
            m++;
        } while (m <= n);
        System.out.println(sum);
    }
}
