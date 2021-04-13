package com.wxd.javacode.object_oriented.basis;

public class PrimaryStudent extends Student{

    private int grade;

    public PrimaryStudent(int grade) {
        this.grade = grade;
    }


    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
