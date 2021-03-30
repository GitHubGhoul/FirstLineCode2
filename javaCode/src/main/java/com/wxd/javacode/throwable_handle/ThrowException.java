package com.wxd.javacode.throwable_handle;

public class ThrowException {
    public static void main(String[] args) {
        try {
            process1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void process1() {

        process2();
    }

    static void process2() {
        Integer.parseInt(null); // 会抛出NumberFormatException
    }

    private static void exception() throws Exception{
        Exception origin = null;
        try {
            System.out.println(Integer.parseInt("abc"));
        } catch (Exception e) {
            origin = e;
            throw e;
        } finally {
            Exception e = new IllegalArgumentException();
            if (origin != null) {
                e.addSuppressed(origin);
            }
            throw e;
        }
    }
}
