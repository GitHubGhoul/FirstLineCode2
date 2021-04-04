package com.wxd.javacode.reflection;

public class GetExtendRelations {
    public static void main(String[] args) {
        Class i = Integer.class;
        Class n0 = i.getSuperclass();
        System.out.println(n0);
        Class o = n0.getSuperclass();
        System.out.println(o);
        System.out.println(o.getSuperclass());

        Class s = Integer.class;
        Class s1 = Integer.class.getSuperclass();
        Class[] is = s1.getInterfaces();
        for (Class in : is) {
            System.out.println(in);
        }

        System.out.println(java.io.DataInputStream.class.getSuperclass()); // java.io.FilterInputStream，因为DataInputStream继承自FilterInputStream
        System.out.println(java.io.Closeable.class.getSuperclass()); // null，对接口调用getSuperclass()总是返回null，获取接口的父接口要用getInterfaces()

        Object n = Integer.valueOf(123);
        boolean isDouble = n instanceof Double; // false
        boolean isInteger = n instanceof Integer; // true
        boolean isNumber = n instanceof Number; // true
        boolean isSerializable = n instanceof java.io.Serializable; // true
        // Integer i = ?
        Integer.class.isAssignableFrom(Integer.class); // true，因为Integer可以赋值给Integer
        // Number n = ?
        Number.class.isAssignableFrom(Integer.class); // true，因为Integer可以赋值给Number
        // Object o = ?
        Object.class.isAssignableFrom(Integer.class); // true，因为Integer可以赋值给Object
        // Integer i = ?
        Integer.class.isAssignableFrom(Number.class); // false，因为Number不能赋值给Integer
    }
}
