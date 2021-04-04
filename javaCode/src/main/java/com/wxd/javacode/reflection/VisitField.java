package com.wxd.javacode.reflection;

import com.wxd.javacode.object_oriented.basis.Person;
import com.wxd.javacode.object_oriented.basis.Student;

import java.lang.reflect.Field;

public class VisitField {
    public static void main(String[] args) {
        Class stdClass = Student.class;
        try {
            // 获取public字段"score":
            System.out.println(stdClass.getField("score"));
            // 获取继承的public字段"name":
            System.out.println(stdClass.getField("name"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        Object p = new Person("Xiao Ming",19);
        Class c = p.getClass();
        Field f = null;
        try {
            f = c.getDeclaredField("age");
            f.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        Object value = null;
        try {
            value = f.get(p);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(value); // "19"

        Person p1 = new Person("Xiao Ming",20);
        System.out.println(p1.getAge()); // "Xiao Ming"
        Class c1 = p.getClass();
        Field f1 = null;
        try {
            f1 = c1.getDeclaredField("age");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        f1.setAccessible(true);
        System.out.println(f1.getModifiers()); // "2"
        try {
            f1.set(p1, 21);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(p1.getAge()); // "21"
    }
}
