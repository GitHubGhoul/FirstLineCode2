package com.wxd.javacode.regex;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchAndReplace {
    public static void main(String[] args) {
        String s = "the quick brown fox jumps over the lazy dog.";
        Pattern p = Pattern.compile("\\wo\\w");
        Matcher m = p.matcher(s);
        while (m.find()) {
            String sub = s.substring(m.start(), m.end());
            System.out.println(sub);
        }

        String s1 = "The     quick\t\t brown   fox  jumps   over the  lazy dog.";
        String r1 = s1.replaceAll("\\s+", " ");
        System.out.println(r1); // "The quick brown fox jumps over the lazy dog."

        String s2 = "the quick brown fox jumps over the lazy dog.";
        String r2 = s2.replaceAll("\\s([a-z]{4})\\s", " <b>$1</b> ");
        System.out.println(r2);

        Map<String, String> map = new HashMap<>();
        map.put("name", "Bob");
        map.put("lang", "Java");
        exercise(map);
    }

    //一个简单的模板引擎，利用正则表达式实现这个功能。
    private static void exercise(Map<String, String> data) {
        String s = "Hello, ${name}! You are learning ${lang}!";
        Pattern p = Pattern.compile("\\$\\{(\\w+)\\}");
        Matcher m = p.matcher(s);
        // TODO:
        StringBuilder sb = new StringBuilder();
        while (m.find()) {
            m.appendReplacement(sb, data.get(m.group(1)).toString());
        }
        m.appendTail(sb);
        System.out.println(sb.toString());
    }
}
