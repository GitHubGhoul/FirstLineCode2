package com.wxd.javacode.collection;

import com.wxd.javacode.object_oriented.basis.Person;
import com.wxd.javacode.object_oriented.basis.Student;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapClass {
    public static void main(String[] args) {
        Map<String, Integer> map = new TreeMap<>();
        map.put("orange", 1);
        map.put("apple", 2);
        map.put("pear", 3);
        for (String key : map.keySet()) {
            System.out.println(key);
        }

        Map<Person, Integer> map1 = new TreeMap<>(new Comparator<Person>() {
            public int compare(Person p1, Person p2) {
                return p1.name.compareTo(p2.name);
            }
        });
        map1.put(new Person("Tom"), 1);
        map1.put(new Person("Bob"), 2);
        map1.put(new Person("Lily"), 3);
        for (Person key : map1.keySet()) {
            System.out.println(key);
        }
        // {Person: Bob}, {Person: Lily}, {Person: Tom}
        System.out.println(map1.get(new Person("Bob"))); // 2

        Map<Student, Integer> map2 = new TreeMap<>(new Comparator<Student>() {
            public int compare(Student p1, Student p2) {
                if (p1.score == p2.score) {
                    return 0;
                }
                return p1.score > p2.score ? -1 : 1;
            }
        });
        map2.put(new Student("Tom", 77), 1);
        map2.put(new Student("Bob", 66), 2);
        map2.put(new Student("Lily", 99), 3);
        for (Student key : map2.keySet()) {
            System.out.println(key);
        }
        System.out.println(map2.get(new Student("Bob", 66))); // null?
    }
}
