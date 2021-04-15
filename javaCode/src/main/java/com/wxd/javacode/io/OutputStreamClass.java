package com.wxd.javacode.io;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class OutputStreamClass {
    public static void main(String[] args) throws IOException{
        byte[] data;
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            output.write("Hello ".getBytes("UTF-8"));
            output.write("world!".getBytes("UTF-8"));
            data = output.toByteArray();
        }
        System.out.println(new String(data, "UTF-8"));
    }

    public static void writeFile() throws IOException {
        try (OutputStream output = new FileOutputStream("out/readme.txt")) {
            output.write("Hello".getBytes("UTF-8")); // Hello
        } // 编译器在此自动为我们写入finally并调用close()
    }

    public static void writeFile1() throws IOException {
        // 读取input.txt，写入output.txt:
        try (InputStream input = new FileInputStream("input.txt");
             OutputStream output = new FileOutputStream("output.txt"))
        {
            input.transferTo(output); // transferTo的作用是?
        }
    }
}
