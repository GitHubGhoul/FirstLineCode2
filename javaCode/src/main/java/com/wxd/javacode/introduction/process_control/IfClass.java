package com.wxd.javacode.introduction.process_control;

public class IfClass {
    public static void main(String[] args) {
        int n = 70;
        if (n >= 90) {
            System.out.println("优秀");
        } else if (n >= 60) {
            System.out.println("及格了");
        } else {
            System.out.println("挂科了");
        }
        System.out.println("END");

        double x = 1 - 9.0 / 10;
        if (Math.abs(x - 0.1) < 0.00001) {
            System.out.println("x is 0.1");
        } else {
            System.out.println("x is NOT 0.1");
        }

        String s1 = "hello";
        String s2 = "HELLO".toLowerCase();
        System.out.println(s1);
        System.out.println(s2);
        if (s1.equals(s2)) {
            System.out.println("s1 equals s2");
        } else {
            System.out.println("s1 not equals s2");
        }

        exercise(58.0f, 1.70f);
    }

    private static void exercise(float weight, float height) {
        float bmi = weight / (height * height);
        System.out.println(bmi);
        if (bmi > 32) {
            System.out.println("非常肥胖");
        } else if (bmi > 28) {
            System.out.println("肥胖");
        } else if (bmi > 25) {
            System.out.println("过重");
        } else if (bmi > 18.5) {
            System.out.println("正常");
        } else {
            System.out.println("过轻");
        }
    }
}
