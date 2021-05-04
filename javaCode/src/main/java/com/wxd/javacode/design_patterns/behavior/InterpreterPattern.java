package com.wxd.javacode.design_patterns.behavior;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InterpreterPattern {
    public static void main(String[] args) {
        log("[{}] start {} at {}...", LocalTime.now().withNano(0), "engine", LocalDate.now());
    }

    static void log(String format, Object... args) {
        int len = format.length();
        int argIndex = 0;
        StringBuilder sb = new StringBuilder(len + 20);
        Pattern pattern = Pattern.compile("\\{\\}");
        Matcher m = pattern.matcher(format);
        while (m.find()) {
            m.appendReplacement(sb, args[argIndex++].toString());
        }
        m.appendTail(sb);
        System.out.println(sb.toString());
    }
}
