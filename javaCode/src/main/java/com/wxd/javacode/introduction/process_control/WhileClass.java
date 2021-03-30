package com.wxd.javacode.introduction.process_control;

public class WhileClass {
    public static void main(String[] args) {
        int sum = 0; // 累加的和，初始化为0
        int n = 1;
        while (n <= 100) { // 循环条件是n <= 100
            sum = sum + n; // 把n累加到sum中
            n++; // n自身加1
        }
        System.out.println(sum); // 5050
        exercise();
    }

    private static void exercise() {
        int sum = 0;
        int m = 20;
        int n = 100;
        // 使用while计算M+...+N:
        while (m <= n) {
            sum = sum + m;
            m++;
        }
        System.out.println(sum);
    }
}
