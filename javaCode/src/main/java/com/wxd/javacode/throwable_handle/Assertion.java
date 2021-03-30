package com.wxd.javacode.throwable_handle;

public class Assertion {
    public static void main(String[] args) {
        double x = Math.abs(-123.45);
        assert x >= 0;
        System.out.println(x);
    }
    //Java断言的特点是：断言失败时会抛出AssertionError，导致程序结束退出。因此，断言不能用于可恢复的程序错误，只应该用于开发和测试阶段。
    //错误
    void sort(int[] arr) {
        assert arr != null;
    }

    void sort1(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("array cannot be null");
        }
    }
}
