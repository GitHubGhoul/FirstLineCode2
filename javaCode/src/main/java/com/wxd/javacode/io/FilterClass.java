package com.wxd.javacode.io;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FilterClass {
    public static void main(String[] args) throws IOException{
        byte[] data = "hello, world!".getBytes("UTF-8");
        File file = new File("hello.txt");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(data);
        try (CountInputStream input = new CountInputStream(file)) {
            int n;
            while ((n = input.read()) != -1) {
                System.out.println((char)n);
            }
            System.out.println("Total read " + input.getBytesRead() + " bytes");
        }
    }

    static class CountInputStream extends FileInputStream{

        private int count = 0;
        private InputStream in;

        public CountInputStream(File file) throws FileNotFoundException {
            super(file);
            in = new FileInputStream(file); // 创建字节输入流
        }

        public int getBytesRead(){
            return this.count;
        }

        public int read()throws IOException{
            int n = in.read();
            if (n != -1) {
                this.count ++;
            }
            return n;
        }

        public int read(byte[] b, int off, int len) throws IOException {
            int n = in.read(b, off, len);
            if (n != -1) {
                this.count += n;
            }
            return n;
        }
    }
}
