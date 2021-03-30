package com.wxd.javacode.object_oriented.basis;

public class Income {

    protected double income;

    public Income(double income) {
        this.income = income;
    }

    public double getTax() {
        return income * 0.1;
    }


}
