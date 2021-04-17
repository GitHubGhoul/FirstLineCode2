package com.wxd.javacode.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GroupMatch {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("(\\d{3,4})\\-(\\d{7,8})");
        Matcher m = p.matcher("010-12345678");
        if (m.matches()) {
            String g1 = m.group(1);
            String g2 = m.group(2);
            System.out.println(g1);
            System.out.println(g2);
        } else {
            System.out.println("匹配失败!");
        }

        Pattern pattern = Pattern.compile("(\\d{3,4})\\-(\\d{7,8})");
        pattern.matcher("010-12345678").matches(); // true
        pattern.matcher("021-123456").matches(); // true
        pattern.matcher("022#1234567").matches(); // false
        // 获得Matcher对象:
        Matcher matcher = pattern.matcher("010-12345678");
        if (matcher.matches()) {
            String whole = matcher.group(0); // "010-12345678", 0表示匹配的整个字符串
            String area = matcher.group(1); // "010", 1表示匹配的第1个子串
            String tel = matcher.group(2); // "12345678", 2表示匹配的第2个子串
            System.out.println(whole);
            System.out.println(area);
            System.out.println(tel);
        }

        exercise();
    }

    //利用分组匹配，从字符串"23:01:59"提取时、分、秒。
    private static void exercise(){
        Pattern pattern = Pattern.compile("([0-2][0-9])\\:([0-6][0-9])\\:([0-6][0-9])");
        Matcher matcher = pattern.matcher("23:01:59");
        matcher.matches(); //这一步必须有 否则报错
        String result1 = matcher.group(1);
        String result2 = matcher.group(2);
        String result3 = matcher.group(3);
        System.out.println(result1 + " " + result2 + " " + result3 + " ");
    }
}
