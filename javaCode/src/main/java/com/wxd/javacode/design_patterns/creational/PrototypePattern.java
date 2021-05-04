package com.wxd.javacode.design_patterns.creational;

public class PrototypePattern {
    public static void main(String[] args) {
        Student std1 = new Student();
        std1.setId(123);
        std1.setName("Bob");
        std1.setScore(88);
        // 复制新对象:
        Student std2 = std1.copy();
        System.out.println(std1);
        System.out.println(std2);
        System.out.println(std1 == std2); // false
    }
    public static class Student {
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        private int id;
        private String name;
        private int score;

        public Student copy() {
            Student std = new Student();
            std.id = this.id;
            std.name = this.name;
            std.score = this.score;
            return std;
        }
    }
}
