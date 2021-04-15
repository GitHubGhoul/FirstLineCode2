package com.wxd.javacode.io;

import java.io.PrintWriter;
import java.io.StringWriter;

public class PrintStreamWriter {
    public static void main(String[] args) {
        System.out.print(12345); // 输出12345
        System.out.print(new Object()); // 输出类似java.lang.Object@3c7a835a
        System.out.println("Hello"); // 输出Hello并换行

        StringWriter buffer = new StringWriter();
        try (PrintWriter pw = new PrintWriter(buffer)) {
            pw.println("Hello");
            pw.println(12345);
            pw.println(true);
        }
        System.out.println(buffer.toString());
    }
}
