package com.wxd.javacode.throwable_handle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CatchException {
    public static void main(String[] args) {
        try {
            //process1();
            //process2();
            //process3();
            // 创建一个FileInputStream对象:
            InputStream input = new FileInputStream("src/readme.txt");
        } catch (IOException e) {
            System.out.println("Bad input");
        } catch (NumberFormatException e) {
            System.out.println("Bad input");
        } catch (Exception e) {
            System.out.println("Unknown error");
        }
    }
}
