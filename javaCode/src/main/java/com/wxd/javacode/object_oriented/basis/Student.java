package com.wxd.javacode.object_oriented.basis;

public class Student extends Person{

    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String hello(){
        return super.name;
    }

    public void run(String s){

    }

}
