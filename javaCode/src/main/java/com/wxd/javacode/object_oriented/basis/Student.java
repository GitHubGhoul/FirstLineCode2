package com.wxd.javacode.object_oriented.basis;

public class Student extends Person{

    public int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void run(String s){

    }

    public void hello() {
        System.out.println("Student:hello");
    }

}
