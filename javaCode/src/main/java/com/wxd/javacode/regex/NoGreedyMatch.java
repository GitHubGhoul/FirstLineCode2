package com.wxd.javacode.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NoGreedyMatch {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("(\\d+)(0*)");
        Matcher matcher = pattern.matcher("1230000");
        if (matcher.matches()) {
            System.out.println("group1=" + matcher.group(1)); // "1230000"
            System.out.println("group2=" + matcher.group(2)); // ""
        }

        Pattern pattern1 = Pattern.compile("(\\d??)(0*)");
        Matcher matcher1 = pattern1.matcher("1230000");
        if (matcher1.matches()) {
            System.out.println("group1=" + matcher1.group(1)); // "1230000"
            System.out.println("group2=" + matcher1.group(2)); // ""
        }
    }
}
