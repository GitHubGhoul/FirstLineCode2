package com.wxd.javacode.io;

import java.io.CharArrayWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class WriterClass {
    public static void main(String[] args) throws IOException {
        try (Writer writer = new FileWriter("readme.txt", StandardCharsets.UTF_8)) {
            writer.write('H'); // 写入单个字符
            writer.write("Hello".toCharArray()); // 写入char[]
            writer.write("Hello"); // 写入String
        }
        try (CharArrayWriter writer = new CharArrayWriter()) {
            writer.write(65);
            writer.write(66);
            writer.write(67);
            char[] data = writer.toCharArray(); // { 'A', 'B', 'C' }
        }
        try (Writer writer = new OutputStreamWriter(new FileOutputStream("readme.txt"), "UTF-8")) {
            // TODO:
        }
    }
}
