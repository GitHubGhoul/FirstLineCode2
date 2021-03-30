package com.wxd.javacode.introduction.basics;

public class DecimalType {
    public static void main(String[] args) {
        double x = 1.0 / 10;
        double y = 1 - 9.0 / 10;
        // 观察x和y是否相等:
        System.out.println(x);
        System.out.println(y);

        // 比较x和y是否相等，先计算其差的绝对值:
        double r = Math.abs(x - y);
        // 再判断绝对值是否足够小:
        if (r < 0.00001) {
            // 可以认为相等
        } else {
            // 不相等
        }

        int n = 5;
        double d = 1.2 + 24.0 / n; // 6.0
        double d0 = 1.2 + 24 / 5; // 5.2
        System.out.println(d);

        double d1 = 0.0 / 0; // NaN
        double d2 = 1.0 / 0; // Infinity
        double d3 = -1.0 / 0; // -Infinity

        int n1 = (int) 12.3; // 12
        int n2 = (int) 12.7; // 12
        int n3 = (int) -12.7; // -12
        int n4 = (int) (12.7 + 0.5); // 13
        int n5 = (int) 1.2e20; // 2147483647

        double dValue = 2.6;
        int nValue = (int) (dValue + 0.5);
        System.out.println(nValue);

        exercise();
    }

    private static void exercise() {
        double a = 1.0;
        double b = 3.0;
        double c = -4.0;
        // 求平方根可用 Math.sqrt():
        // System.out.println(Math.sqrt(2)); ==> 1.414
        // TODO:
        double r1 = (-b + Math.sqrt(b * b - 4 * a * c)) / 2 * a;
        double r2 = (-b - Math.sqrt(b * b - 4 * a * c)) / 2 * a;
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r1 == 1 && r2 == -4 ? "测试通过" : "测试失败");
    }
}
