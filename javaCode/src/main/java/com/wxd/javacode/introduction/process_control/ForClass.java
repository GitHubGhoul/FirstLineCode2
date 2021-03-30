package com.wxd.javacode.introduction.process_control;

public class ForClass {
    public static void main(String[] args) {
        int sum = 0;
        for (int i=1; i<=100; i++) {
            sum = sum + i;
        }
        System.out.println(sum);

        int[] ns = { 1, 4, 9, 16, 25 };
        int sum1 = 0;
        for (int i=0; i<ns.length; i++) {
            System.out.println("i = " + i + ", ns[i] = " + ns[i]);
            sum1 = sum + ns[i];
        }
        System.out.println("sum = " + sum1);

        int[] ns1 = { 1, 4, 9, 16, 25 };
        for (int n : ns1) {
            System.out.println(n);
        }

        exercise();
    }

    private static void exercise(){
        int[] ns = { 1, 4, 9, 16, 25 };
        for (int i=0; i<ns.length; i++) {
            System.out.println(ns[i]);
        }
    }
}
