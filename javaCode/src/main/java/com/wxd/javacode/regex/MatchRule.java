package com.wxd.javacode.regex;

import java.util.List;

public class MatchRule {
    public static void main(String[] args) {
        String re1 = "abc";
        System.out.println("abc".matches(re1));
        System.out.println("Abc".matches(re1));
        System.out.println("abcd".matches(re1));

        String re2 = "a\\&c"; // 对应的正则是a\&c
        System.out.println("a&c".matches(re2));
        System.out.println("a-c".matches(re2));
        System.out.println("a&&c".matches(re2));

        String re3 = "java\\d"; // 对应的正则是java\d
        System.out.println("java9".matches(re3));
        System.out.println("java10".matches(re3));
        System.out.println("javac".matches(re3));

        String re4 = "java\\D";
        System.out.println("javax".matches(re4));
        System.out.println("java#".matches(re4));
        System.out.println("java5".matches(re4));

        exercise();
    }
    //请编写一个正则表达式匹配国内的电话号码规则：3~4位区号加7~8位电话，中间用-连接，例如：010-12345678。
    private static void exercise(){
        String re = "\\d{3,4}-\\d{6,8}";
        for (String s : List.of("010-12345678", "020-9999999", "0755-7654321")) {
            if (!s.matches(re)) {
                System.out.println("测试失败: " + s);
                return;
            }
        }
        for (String s : List.of("010 12345678", "A20-9999999", "0755-7654.321")) {
            if (s.matches(re)) {
                System.out.println("测试失败: " + s);
                return;
            }
        }
        System.out.println("测试成功!");
    }
}
