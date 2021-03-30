package com.wxd.javacode.introduction.array_operation;

import java.util.Arrays;

public class Multidimensional {
    public static void main(String[] args) {
        int[][] ns = {
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 }
        };
        int[] arr0 = ns[0];
        System.out.println(arr0.length); // 4
        System.out.println(Arrays.deepToString(ns));
        exercise();
    }

    private static void exercise(){
        // 用二维数组表示的学生成绩:
        int[][] scores = {
                { 82, 90, 91 },
                { 68, 72, 64 },
                { 95, 91, 89 },
                { 67, 52, 60 },
                { 79, 81, 85 },
        };
        double total = 0;
        int num = 0;
        // TODO:
        for (int[] i:scores) {
            for (int j:i) {
                total = total+j;
                num++;
            }
        }
        double average = total/num;
        System.out.println(average);
        if (Math.abs(average - 77.733333) < 0.000001) {
            System.out.println("测试成功");
        } else {
            System.out.println("测试失败");
        }
    }
}
