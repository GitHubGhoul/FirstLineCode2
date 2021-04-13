package com.wxd.javacode.collection;

import com.wxd.javacode.object_oriented.basis.Person;

import java.util.List;

public class EditEquals {
    public static void main(String[] args) {
        List<String> list = List.of("A", "B", "C");
        System.out.println(list.contains("C")); // true
        System.out.println(list.contains("X")); // false
        System.out.println(list.indexOf("C")); // 2
        System.out.println(list.indexOf("X")); // -1

        List<Person> personList = List.of(
                new Person("Xiao Ming"),
                new Person("Xiao Hong"),
                new Person("Bob")
        );
        System.out.println(personList.contains(new Person("Bob"))); // false

        exercise();
    }
    //给Person类增加equals方法，使得调用indexOf()方法返回正常：
    private static void exercise(){
        List<Person> list = List.of(
                new Person("Xiao", "Ming", 18),
                new Person("Xiao", "Hong", 25),
                new Person("Bob", "Smith", 20)
        );
        boolean exist = list.contains(new Person("Bob", "Smith", 20));
        System.out.println(exist ? "测试成功!" : "测试失败!");
    }
}
