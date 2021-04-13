package com.wxd.javacode.collection;

import com.wxd.javacode.object_oriented.basis.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapClass {
    public static void main(String[] args) {
        Student s = new Student("Xiao Ming", 99);
        Map<String, Student> map = new HashMap<>();
        map.put("Xiao Ming", s); // 将"Xiao Ming"和Student实例映射并关联
        Student target = map.get("Xiao Ming"); // 通过key查找并返回映射的Student实例
        System.out.println(target == s); // true，同一个实例
        System.out.println(target.score); // 99
        Student another = map.get("Bob"); // 通过另一个key查找
        System.out.println(another); // 未找到返回null

        Map<String, Integer> map1 = new HashMap<>();
        map1.put("apple", 123);
        map1.put("pear", 456);
        System.out.println(map1.get("apple")); // 123
        map1.put("apple", 789); // 再次放入apple作为key，但value变为789
        System.out.println(map1.get("apple")); // 789
        for (String key : map1.keySet()) {
            Integer value = map1.get(key);
            System.out.println(key + " = " + value);
        }
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " = " + value);
        }

        exercise();
    }

    //请编写一个根据name查找score的程序，并利用Map充当缓存，以提高查找效率：
    private static void exercise() {
        List<Student> list = List.of(
                new Student("Bob", 78),
                new Student("Alice", 85),
                new Student("Brush", 66),
                new Student("Newton", 99));
        var holder = new Students(list);
        System.out.println(holder.getScore("Bob") == 78 ? "测试成功!" : "测试失败!");
        System.out.println(holder.getScore("Alice") == 85 ? "测试成功!" : "测试失败!");
        System.out.println(holder.getScore("Tom") == -1 ? "测试成功!" : "测试失败!");
    }

    static class Students {
        List<Student> list;
        Map<String, Integer> cache;

        Students(List<Student> list) {
            this.list = list;
            cache = new HashMap<>();
        }

        /**
         * 根据name查找score，找到返回score，未找到返回-1
         */
        int getScore(String name) {
            // 先在Map中查找:
            Integer score = this.cache.get(name);
            if (score == null) {
                // TODO:
                score = findInList(name);
                if (score != null) {
                    // 存在，缓存到MAP，下次查找则直接查询MAP
                    this.cache.put(name, score);
                } else {
                    System.out.println("学生名：%s, 系统中不存在!".formatted(name));
                }
            }
            return score == null ? -1 : score.intValue();
        }

        Integer findInList(String name) {
            for (var ss : this.list) {
                if (ss.getName().equals(name)) {
                    return ss.score;
                }
            }
            return null;
        }
    }
}
