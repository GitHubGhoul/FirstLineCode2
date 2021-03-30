package com.wxd.javacode.introduction.basics;

public class ArrayType {
    public static void main(String[] args) {
        // 5位同学的成绩:
        int[] ns = new int[5];
        System.out.println(ns.length); // 5
        System.out.println(ns[5]); // 索引n不能超出范围

        // 5位同学的成绩:
        int[] ns1 = new int[] { 68, 79, 91, 85, 62 };
        System.out.println(ns1.length); // 编译器自动推算数组大小为5

        // 5位同学的成绩:
        int[] ns2;
        ns2 = new int[] { 68, 79, 91, 85, 62 };
        System.out.println(ns2.length); // 5
        ns2 = new int[] { 1, 2, 3 };
        System.out.println(ns2.length); // 3
    }

    private static void exercise(){
        String[] names = {"ABC", "XYZ", "zoo"};
        String s = names[1];
        names[1] = "cat";
        System.out.println(s); // s是"XYZ"还是"cat"?
    }
}
