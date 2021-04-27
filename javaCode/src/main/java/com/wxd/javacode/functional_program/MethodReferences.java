package com.wxd.javacode.functional_program;

import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MethodReferences {
    public static void main(String[] args) {
        String[] array = new String[]{"Apple", "Orange", "Banana", "Lemon"};
        Arrays.sort(array, MethodReferences::cmp);
        Arrays.sort(array, String::compareTo);
        System.out.println(String.join(", ", array));

        List<String> names = List.of("Bob", "Alice", "Tim");
        List<Person> persons = new ArrayList<>();
        for (String name : names) {
            persons.add(new Person(name));
        }
        List<Person> persons1 = names.stream().map(Person::new).collect(Collectors.toList());
        System.out.println(persons1);
    }

    static int cmp(String s1, String s2) {
        return s1.compareTo(s2);
    }

    static class Person {
        String name;
        public Person(String name) {
            this.name = name;
        }
        public String toString() {
            return "Person:" + this.name;
        }
    }
}
