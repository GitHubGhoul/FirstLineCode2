package com.wxd.javacode.object_oriented.basis;

import com.wxd.javacode.annotation.Range;
import com.wxd.javacode.annotation.Report;

public class Person implements Comparable<Person>{

    @Range(min=1, max=20)
    public String name;
    private int age;

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Person.count = count;
    }

    public static int count;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void setName1(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String setName(String name1,String name2){
        return name1+name2;
    }

    final public void run(){
        System.out.println("Person.run");
    }

    public void hello() {
        System.out.println("Person:hello");
    }

    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name);
    }
}
