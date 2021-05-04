package com.wxd.javacode.design_patterns.creational;

public class FactoryMethodPattern {
    public static void main(String[] args) {
        NumberFactory factory = NumberFactory.getFactory();
        Number number = factory.parse("123");
    }
}
