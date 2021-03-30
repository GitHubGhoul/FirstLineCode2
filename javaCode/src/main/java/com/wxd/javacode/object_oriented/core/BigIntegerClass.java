package com.wxd.javacode.object_oriented.core;

import java.math.BigInteger;

public class BigIntegerClass {
    public static void main(String[] args) {
        BigInteger bi = new BigInteger("1234567890");
        System.out.println(bi.pow(5)); // 2867971860299718107233761438093672048294900000

        BigInteger i = new BigInteger("123456789000");
        System.out.println(i.longValue()); // 123456789000
        System.out.println(i.multiply(i).longValueExact()); // java.lang.ArithmeticException: BigInteger out of long range

        BigInteger n = new BigInteger("999999").pow(99);
        float f = n.floatValue();
        System.out.println(f);
    }
}
