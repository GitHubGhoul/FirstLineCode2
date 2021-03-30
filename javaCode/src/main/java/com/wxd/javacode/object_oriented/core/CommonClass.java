package com.wxd.javacode.object_oriented.core;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

public class CommonClass {
    public static void main(String[] args) {
        double x = Math.random(); // x的范围是[0,1)
        double min = 10;
        double max = 50;
        double y = x * (max - min) + min; // y的范围是[10,50)
        long n = (long) y; // n的范围是[10,50)的整数
        System.out.println(y);
        System.out.println(n);

        Random r = new Random(12345);
        for (int i = 0; i < 10; i++) {
            System.out.println(r.nextInt(100));
        }

        SecureRandom sr0 = new SecureRandom();
        System.out.println(sr0.nextInt(100));

        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstanceStrong(); // 获取高强度安全随机数生成器
        } catch (NoSuchAlgorithmException e) {
            sr = new SecureRandom(); // 获取普通的安全随机数生成器
        }
        byte[] buffer = new byte[16];
        sr.nextBytes(buffer); // 用安全随机数填充buffer
        System.out.println(Arrays.toString(buffer));
    }

    private static void math() {
        Math.abs(-100); // 100
        Math.abs(-7.8); // 7.8

        Math.max(100, 99); // 100
        Math.min(1.2, 2.3); // 1.2

        Math.pow(2, 10); // 2的10次方=1024

        Math.sqrt(2); // 1.414...

        Math.exp(2); // 7.389...

        Math.log(4); // 1.386...

        Math.log10(100); // 2

        Math.sin(3.14); // 0.00159...
        Math.cos(3.14); // -0.9999...
        Math.tan(3.14); // -0.0015...
        Math.asin(1.0); // 1.57079...
        Math.acos(1.0); // 0.0

        double pi = Math.PI; // 3.14159...
        double e = Math.E; // 2.7182818...
        Math.sin(Math.PI / 6); // sin(π/6) = 0.5

        System.out.println(Math.random());
    }

    private static void random() {
        Random r = new Random();
        r.nextInt(); // 2071575453,每次都不一样
        r.nextInt(10); // 5,生成一个[0,10)之间的int
        r.nextLong(); // 8811649292570369305,每次都不一样
        r.nextFloat(); // 0.54335...生成一个[0,1)之间的float
        r.nextDouble(); // 0.3716...生成一个[0,1)之间的double
    }
}