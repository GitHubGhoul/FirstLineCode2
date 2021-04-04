package com.wxd.javacode.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CallConstructMethod {
    public static void main(String[] args) {
        // 获取构造方法Integer(int):
        Constructor cons1 = null;
        try {
            cons1 = Integer.class.getConstructor(int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        // 调用构造方法:
        Integer n1 = null;
        try {
            n1 = (Integer) cons1.newInstance(123);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(n1);

        // 获取构造方法Integer(String)
        Constructor cons2 = null;
        try {
            cons2 = Integer.class.getConstructor(String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Integer n2 = null;
        try {
            n2 = (Integer) cons2.newInstance("456");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(n2);
    }
}
