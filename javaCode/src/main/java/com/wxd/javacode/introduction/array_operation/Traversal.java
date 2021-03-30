package com.wxd.javacode.introduction.array_operation;

import java.util.Arrays;

public class Traversal {
    public static void main(String[] args) {
        int[] ns = { 1, 4, 9, 16, 25 };
        System.out.println(Arrays.toString(ns));
        for (int i=0; i<ns.length; i++) {
            int n = ns[i];
            System.out.println(n);
        }
        for (int n : ns) {
            System.out.println(n);
        }
        exercise();
    }

    private static void exercise(){
        int[] ns = { 1, 4, 9, 16, 25 };
        for (int i = ns.length-1; i >= 0; i--) {
            System.out.println(ns[i]);
        }
    }
}
