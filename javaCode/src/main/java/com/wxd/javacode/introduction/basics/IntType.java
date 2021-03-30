package com.wxd.javacode.introduction.basics;

public class IntType {

    public static void main(String[] args) {
        int i = (100 + 200) * (99 - 88); // 3300
        int n = 7 * (5 + (i - 9)); // 23072
        System.out.println(i);
        System.out.println(n);

        int x = 12345 / 67; // 184
        int y = 12345 % 67; // 12345÷67的余数是17

        int x1 = 2147483640;
        int y1 = 15;
        int sum = x1 + y1;
        System.out.println(sum); // -2147483641

        int n2 = 3300;
        n2++; // 3301, 相当于 n = n + 1;
        n2--; // 3300, 相当于 n = n - 1;
        int y2 = 100 + (++n2); // 不要这么写
        System.out.println(y2);

        exercise();
    }

    private void shift() {
        int n = 7;       // 00000000 00000000 00000000 00000111 = 7
        int a = n << 1;  // 00000000 00000000 00000000 00001110 = 14
        int b = n << 2;  // 00000000 00000000 00000000 00011100 = 28
        int c = n << 28; // 01110000 00000000 00000000 00000000 = 1879048192
        int d = n << 29; // 11100000 00000000 00000000 00000000 = -536870912

        int n1 = 7;       // 00000000 00000000 00000000 00000111 = 7
        int a1 = n1 >> 1;  // 00000000 00000000 00000000 00000011 = 3
        int b1 = n1 >> 2;  // 00000000 00000000 00000000 00000001 = 1
        int c1 = n1 >> 3;  // 00000000 00000000 00000000 00000000 = 0

        int n2 = -536870912;
        int a2 = n2 >> 1;  // 11110000 00000000 00000000 00000000 = -268435456
        int b2 = n2 >> 2;  // 11111000 00000000 00000000 00000000 = -134217728
        int c2 = n2 >> 28; // 11111111 11111111 11111111 11111110 = -2
        int d2 = n2 >> 29; // 11111111 11111111 11111111 11111111 = -1

        int n3 = -536870912;
        int a3 = n3 >>> 1;  // 01110000 00000000 00000000 00000000 = 1879048192
        int b3 = n3 >>> 2;  // 00111000 00000000 00000000 00000000 = 939524096
        int c3 = n3 >>> 29; // 00000000 00000000 00000000 00000111 = 7
        int d3 = n3 >>> 31; // 00000000 00000000 00000000 00000001 = 1
    }

    private void place() {
        int n1 = 0 & 0; // 0
        int n2 = 0 & 1; // 0
        int n3 = 1 & 0; // 0
        int n4 = 1 & 1; // 1

        int n5 = 0 | 0; // 0
        int n6 = 0 | 1; // 1
        int n7 = 1 | 0; // 1
        int n8 = 1 | 1; // 1

        int n9 = ~0; // 1
        int n10 = ~1; // 0

        int n11 = 0 ^ 0; // 0
        int n12 = 0 ^ 1; // 1
        int n13 = 1 ^ 0; // 1
        int n14 = 1 ^ 1; // 0

        int i = 167776589; // 00001010 00000000 00010001 01001101
        int n = 167776512; // 00001010 00000000 00010001 00000000
        System.out.println(i & n); // 167776512
    }

    private void trans() {
        short s = 1234;
        int i = 123456;
        int x = s + i; // s自动转型为int
        //short y = s + i; // 编译错误!

        int i0 = 12345;
        short s0 = (short) i0; // 12345

        int i1 = 1234567;
        short s1 = (short) i1; // -10617
        System.out.println(s1);
        int i2 = 12345678;
        short s2 = (short) i2; // 24910
        System.out.println(s2);
    }

    private static void exercise() {
        int n = 100;
        int sum = (1 + n) * n / 2;
        System.out.println(sum);
        System.out.println(sum == 5050 ? "测试通过" : "测试失败");
    }
}
