package com.wxd.javacode.object_oriented.core;

import java.lang.invoke.StringConcatFactory;

public class SBuilder {
    public static void main(String[] args) {
        var sb = new StringBuilder(1024);
        sb.append("Mr ")
                .append("Bob")
                .append("!")
                .insert(0, "Hello, ");
        System.out.println(sb.toString());

        Adder adder = new Adder();
        adder.add(3)
                .add(5)
                .inc()
                .add(10);
        System.out.println(adder.value());

        exercise();
    }
    static class Adder{
        private int sum = 0;

        public Adder add(int n) {
            sum += n;
            return this;
        }

        public Adder inc() {
            sum ++;
            return this;
        }

        public int value() {
            return sum;
        }
    }

    private static void exercise(){
        String[] fields = { "name", "position", "salary" };
        String table = "employee";
        String insert = buildInsertSql(table, fields);
        System.out.println(insert);
        String s = "INSERT INTO employee (name, position, salary) VALUES (?, ?, ?)";
        System.out.println(s.equals(insert) ? "测试成功" : "测试失败");
    }

    private static String buildInsertSql(String t,String[] f){
        var sb=new StringBuilder(1024);
        sb.append("INSERT INTO ")
                .append(t)
                .append(" (")
                .append(String.join(", ",f))
                .append(") ")
                .append("VALUES (?, ?, ?)");
        return sb.toString();

    }
}
