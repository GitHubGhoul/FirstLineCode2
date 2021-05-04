package com.wxd.javacode.design_patterns.creational;

import java.math.BigInteger;

public class NumberFactoryImpl implements NumberFactory{
    @Override
    public Number parse(String s) {
        return new BigInteger(s);
    }
}
