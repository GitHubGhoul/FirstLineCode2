package com.wxd.javacode.design_patterns.creational;

public interface NumberFactory {

    Number parse(String s);

    static NumberFactory getFactory(){
        return impl;
    }

    static NumberFactory impl = new NumberFactoryImpl();
}
