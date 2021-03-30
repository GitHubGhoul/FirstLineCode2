package com.wxd.javacode.introduction.basics;

public class DataType {
    public static void main(String[] args) {
        int i = 2147483647;
        int i2 = -2147483648;
        int i3 = 2_000_000_000; // 加下划线更容易识别
        int i4 = 0xff0000; // 十六进制表示的16711680
        int i5 = 0b1000000000; // 二进制表示的512
        long l = 9000000000000000000L; // long型的结尾需要加L

        float f1 = 3.14f;
        float f2 = 3.14e38f; // 科学计数法表示的3.14x10^38
        double d = 1.79e308;
        double d2 = -1.79e308;
        double d3 = 4.9e-324; // 科学计数法表示的4.9x10^-324

        boolean b1 = true;
        boolean b2 = false;
        boolean isGreater = 5 > 3; // 计算结果为true
        int age = 12;
        boolean isAdult = age >= 18; // 计算结果为false

        char a = 'A';
        char zh = '中';
        System.out.println(a);
        System.out.println(zh);

        String s = "hello";

        final double PI = 3.14; // PI是一个常量
        double r = 5.0;
        double area = PI * r * r;
        //PI = 300; // compile error!

        var sb = new StringBuilder();
    }
}
