package com.wxd.javacode.object_oriented.basis;

public class Student extends Person {

    private String name;
    public int score;

    public Student() {
    }

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void run(String s) {

    }

    public void hello() {
        System.out.println("Student:hello");
    }

    public String toString() {
        return String.format("{%s: score=%d}", name, score);
    }

}
