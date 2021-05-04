package com.wxd.javacode.design_patterns.structural;

import java.util.HashMap;
import java.util.Map;

public class FlyweightPattern {
    public static void main(String[] args) {
        Integer n1 = Integer.valueOf(100);
        Integer n2 = Integer.valueOf(100);
        System.out.println(n1 == n2); // true
    }

    public static class Student {
        // 持有缓存:
        private static final Map<String, Student> cache = new HashMap<>();

        // 静态工厂方法:
        public static Student create(int id, String name) {
            String key = id + "\n" + name;
            // 先查找缓存:
            Student std = cache.get(key);
            if (std == null) {
                // 未找到,创建新对象:
                System.out.println(String.format("create new Student(%s, %s)", id, name));
                std = new Student(id, name);
                // 放入缓存:
                cache.put(key, std);
            } else {
                // 缓存中存在:
                System.out.println(String.format("return cached Student(%s, %s)", std.id, std.name));
            }
            return std;
        }

        private final int id;
        private final String name;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
