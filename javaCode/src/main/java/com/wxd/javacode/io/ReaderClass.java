package com.wxd.javacode.io;

import java.io.CharArrayReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

public class ReaderClass {
    public static void main(String[] args) throws IOException{
        try (Reader reader = new CharArrayReader("Hello".toCharArray())) {
        }
        try (Reader reader = new StringReader("Hello")) {
        }
        try (Reader reader = new InputStreamReader(new FileInputStream("src/readme.txt"), "UTF-8")) {
            // TODO:
        }
    }

    public void readFile() throws IOException {
        // 创建一个FileReader对象:
        try(Reader reader = new FileReader("src/readme.txt", StandardCharsets.UTF_8)){// 字符编码是???
            for (;;) {
                int n = reader.read(); // 反复调用read()方法，直到返回-1
                if (n == -1) {
                    break;
                }
                System.out.println((char)n); // 打印char
            }
            reader.close(); // 关闭流
        }
    }

    public void readFile1() throws IOException {
        try (Reader reader = new FileReader("src/readme.txt", StandardCharsets.UTF_8)) {
            char[] buffer = new char[1000];
            int n;
            while ((n = reader.read(buffer)) != -1) {
                System.out.println("read " + n + " chars.");
            }
        }
    }
}
