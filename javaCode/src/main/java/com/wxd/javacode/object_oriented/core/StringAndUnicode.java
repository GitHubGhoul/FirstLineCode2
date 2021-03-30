package com.wxd.javacode.object_oriented.core;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class StringAndUnicode {
    public static void main(String[] args) {
        String s = "Hello";
        System.out.println(s);
        s = s.toUpperCase();
        System.out.println(s);

        // 是否包含子串:
        "Hello".contains("ll"); // true
        "Hello".indexOf("l"); // 2
        "Hello".lastIndexOf("l"); // 3
        "Hello".startsWith("He"); // true
        "Hello".endsWith("lo"); // true

        "Hello".substring(2); // "llo"
        "Hello".substring(2, 4); //"ll"

        "  \tHello\r\n ".trim(); // "Hello"
        "\u3000Hello\u3000".strip(); // "Hello"
        " Hello ".stripLeading(); // "Hello "
        " Hello ".stripTrailing(); // " Hello"

        "".isEmpty(); // true，因为字符串长度为0
        "  ".isEmpty(); // false，因为字符串长度不为0
        "  \n".isBlank(); // true，因为只包含空白字符
        " Hello ".isBlank(); // false，因为包含非空白字符

        String s1 = "hello";
        s1.replace('l', 'w'); // "hewwo"，所有字符'l'被替换为'w'
        s1.replace("ll", "~~"); // "he~~o"，所有子串"ll"被替换为"~~"

        String s2 = "A,,B;C ,D";
        s2.replaceAll("[\\,\\;\\s]+", ","); // "A,B,C,D"

        String s3 = "A,B,C,D";
        String[] ss = s3.split("\\,"); // {"A", "B", "C", "D"}
    }

    private static void format() {
        String s = "Hi %s, your score is %d!";
        System.out.println(s.formatted("Alice", 80));
        System.out.println(String.format("Hi %s, your score is %.2f!", "Bob", 59.5));
    }

    private static void trans() {
        String.valueOf(123); // "123"
        String.valueOf(45.67); // "45.67"
        String.valueOf(true); // "true"
        String.valueOf(new Object()); // 类似java.lang.Object@636be97c

        int n1 = Integer.parseInt("123"); // 123
        int n2 = Integer.parseInt("ff", 16); // 按十六进制转换，255

        boolean b1 = Boolean.parseBoolean("true"); // true
        boolean b2 = Boolean.parseBoolean("FALSE"); // false

        Integer.getInteger("java.version"); // 版本号，11

        char[] cs = "Hello".toCharArray(); // String -> char[]
        String s = new String(cs); // char[] -> String

    }

    private static void unicode() throws UnsupportedEncodingException {
        byte[] b1 = "Hello".getBytes(); // 按系统默认编码转换，不推荐
        byte[] b2 = "Hello".getBytes("UTF-8"); // 按UTF-8编码转换
        byte[] b3 = "Hello".getBytes("GBK"); // 按GBK编码转换
        byte[] b4 = "Hello".getBytes(StandardCharsets.UTF_8); // 按UTF-8编码转换
    }
}
