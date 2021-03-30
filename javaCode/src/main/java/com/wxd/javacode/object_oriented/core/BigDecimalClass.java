package com.wxd.javacode.object_oriented.core;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalClass {
    public static void main(String[] args) {
        BigDecimal bd = new BigDecimal("123.4567");
        System.out.println(bd.multiply(bd)); // 15241.55677489

        BigDecimal d1 = new BigDecimal("123.45");
        BigDecimal d2 = new BigDecimal("123.4500");
        BigDecimal d3 = new BigDecimal("1234500");
        System.out.println(d1.scale()); // 2,两位小数
        System.out.println(d2.scale()); // 4
        System.out.println(d3.scale()); // 0

        BigDecimal d4 = new BigDecimal("123.4500");
        BigDecimal d5 = d1.stripTrailingZeros();
        System.out.println(d4.scale()); // 4
        System.out.println(d5.scale()); // 2,因为去掉了00

        BigDecimal d6 = new BigDecimal("1234500");
        BigDecimal d7 = d3.stripTrailingZeros();
        System.out.println(d6.scale()); // 0
        System.out.println(d7.scale()); // -2

        BigDecimal d8 = new BigDecimal("123.456789");
        BigDecimal d9 = d8.setScale(4, RoundingMode.HALF_UP); // 四舍五入，123.4568
        BigDecimal d10 = d8.setScale(4, RoundingMode.DOWN); // 直接截断，123.4567
        System.out.println(d9);
        System.out.println(d10);

        BigDecimal d11 = new BigDecimal("123.456");
        BigDecimal d12 = new BigDecimal("23.456789");
        BigDecimal d13 = d11.divide(d12, 10, RoundingMode.HALF_UP); // 保留10位小数并四舍五入
        BigDecimal d14 = d11.divide(d12); // 报错：ArithmeticException，因为除不尽

        BigDecimal n = new BigDecimal("12.345");
        BigDecimal m = new BigDecimal("0.12");
        BigDecimal[] dr = n.divideAndRemainder(m);
        System.out.println(dr[0]); // 102
        System.out.println(dr[1]); // 0.105

        BigDecimal d15 = new BigDecimal("123.456");
        BigDecimal d16 = new BigDecimal("123.45600");
        System.out.println(d15.equals(d16)); // false,因为scale不同
        System.out.println(d15.equals(d16.stripTrailingZeros())); // true,因为d2去除尾部0后scale变为2
        System.out.println(d15.compareTo(d16)); // 0
    }
}
