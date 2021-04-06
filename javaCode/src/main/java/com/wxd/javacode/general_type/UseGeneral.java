package com.wxd.javacode.general_type;

import com.wxd.javacode.object_oriented.basis.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UseGeneral {
    public static void main(String[] args) {
        // 编译器警告:
        List list = new ArrayList();
        list.add("Hello");
        list.add("World");
        String first = (String) list.get(0);
        String second = (String) list.get(1);

        String[] ss = new String[] { "Orange", "Apple", "Pear" };
        Arrays.sort(ss);
        System.out.println(Arrays.toString(ss));

        Person[] ps = new Person[] {
                new Person("Bob", 61),
                new Person("Alice", 88),
                new Person("Lily", 75),
        };
        Arrays.sort(ps);
        System.out.println(Arrays.toString(ps));

    }
}
