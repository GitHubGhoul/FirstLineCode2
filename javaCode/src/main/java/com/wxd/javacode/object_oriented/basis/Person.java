package com.wxd.javacode.object_oriented.basis;

public class Person {

    protected String name;
    protected int age;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
}
