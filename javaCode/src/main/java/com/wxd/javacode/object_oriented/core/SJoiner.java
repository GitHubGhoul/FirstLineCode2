package com.wxd.javacode.object_oriented.core;

import java.util.StringJoiner;

public class SJoiner {
    public static void main(String[] args) {
        String[] names = {"Bob", "Alice", "Grace"};
        var sb = new StringBuilder();
        sb.append("Hello ");
        for (String name : names) {
            sb.append(name).append(", ");
        }
        // 注意去掉最后的", ":
        sb.delete(sb.length() - 2, sb.length());
        sb.append("!");
        System.out.println(sb.toString());

        var sj = new StringJoiner(", ", "Hello ", "!");
        for (String name : names) {
            sj.add(name);
        }
        System.out.println(sj.toString());

        exercise();
    }

    private static void exercise(){
        String[] fields = { "name", "position", "salary" };
        String table = "employee";
        String select = buildSelectSql(table, fields);
        System.out.println(select);
        System.out.println("SELECT name, position, salary FROM employee".equals(select) ? "测试成功" : "测试失败");
    }

    private static String buildSelectSql(String table,String[] fields){
        var s = new StringJoiner(", ","SELECT "," FROM "+table);
        for (String name : fields) {
            s.add(name);
        }
        return s.toString();
    }
}
