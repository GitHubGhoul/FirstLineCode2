package com.wxd.javacode.reflection;

import com.wxd.javacode.object_oriented.basis.Person;
import com.wxd.javacode.object_oriented.basis.Student;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CallMethod {
    public static void main(String[] args) {
        Class stdClass = Student.class;
        try {
            // 获取public方法getScore:
            System.out.println(stdClass.getMethod("getScore"));
            // 获取继承的public方法getName，无参数:
            System.out.println(stdClass.getMethod("getName"));
            // 获取private方法run，参数为String:
            System.out.println(stdClass.getDeclaredMethod("run", String.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        // String对象:
        String s = "Hello world";
        // 获取String substring(int)方法，参数为int:
        Method m = null;
        try {
            m = String.class.getMethod("substring", int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        // 在s对象上调用该方法并获取结果:
        String r = null;
        try {
            r = (String) m.invoke(s, 6);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // 打印调用结果:
        System.out.println(r);

        // 获取Integer.parseInt(String)方法，参数为String:
        Method m1 = null;
        try {
            m1 = Integer.class.getMethod("parseInt", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        // 调用该静态方法并获取结果:
        Integer n = null;
        try {
            n = (Integer) m1.invoke(null, "12345");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // 打印调用结果:
        System.out.println(n);

        Person p = new Person();
        Method me = null;
        try {
            me = p.getClass().getDeclaredMethod("setName1", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        me.setAccessible(true);
        try {
            me.invoke(p, "Bob");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(p.name);

        // 获取Person的hello方法:
        Method h = null;
        try {
            h = Person.class.getMethod("hello");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        // 对Student实例调用hello方法:
        try {
            h.invoke(new Student());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
