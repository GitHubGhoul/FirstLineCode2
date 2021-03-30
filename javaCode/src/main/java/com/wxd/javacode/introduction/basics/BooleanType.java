package com.wxd.javacode.introduction.basics;

public class BooleanType {
    public static void main(String[] args) {
        boolean isGreater = 5 > 3; // true
        int age = 12;
        boolean isZero = age == 0; // false
        boolean isNonZero = !isZero; // true
        boolean isAdult = age >= 18; // false
        boolean isTeenager = age > 6 && age < 18; // true

        int n = -100;
        int x = n >= 0 ? n : -n;
        System.out.println(x);

        int age1 = 7;
        // primary student的定义: 6~12岁
        boolean isPrimaryStudent = age1 >= 6 && age1 <= 12;
        System.out.println(isPrimaryStudent ? "Yes" : "No");
    }

}
