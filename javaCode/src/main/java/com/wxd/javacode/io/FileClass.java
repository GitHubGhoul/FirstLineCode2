package com.wxd.javacode.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileClass {
    public static void main(String[] args) {
        File f = new File("..");
        try {
            System.out.println(f.getPath());
            System.out.println(f.getAbsolutePath());
            System.out.println(f.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        File f1 = new File("/Users");
        File f2 = new File("/Users/wxd/workface/FirstLineCode/build.gradle");
        File f3 = new File("/Users/wxd/workface");
        System.out.println(f1.isFile());
        System.out.println(f1.isDirectory());
        System.out.println(f2.isFile());
        System.out.println(f2.isDirectory());
        System.out.println(f3.isFile());
        System.out.println(f3.isDirectory());

        Path p1 = Paths.get(".", "project", "study"); // 构造一个Path对象
        System.out.println(p1);
        Path p2 = p1.toAbsolutePath(); // 转换为绝对路径
        System.out.println(p2);
        Path p3 = p2.normalize(); // 转换为规范路径
        System.out.println(p3);
        File fp = p3.toFile(); // 转换为File对象
        System.out.println(fp);
        for (Path p : Paths.get("..").toAbsolutePath()) { // 可以直接遍历Path
            System.out.println("  " + p);
        }

        File file = new File("/Users");
        File[] fs1 = file.listFiles(); // 列出所有文件和子目录
        printFiles(fs1);
        File[] fs2 = file.listFiles(new FilenameFilter() { // 仅列出.exe文件
            public boolean accept(File dir, String name) {
                return name.endsWith(".dmg"); // 返回true表示接受该文件
            }
        });
        printFiles(fs2);
    }

    static void printFiles(File[] files) {
        System.out.println("==========");
        if (files != null) {
            for (File f : files) {
                System.out.println(f);
            }
        }
        System.out.println("==========");

        exercise();
    }
    //利用File对象列出指定目录下的所有子目录和文件，并按层次打印。
    private static void exercise(){
        File file = new File(".");
        printFile(file, 0);
    }

    static void printFile(File file, int level){
        if (file.isDirectory()){
            System.out.println(getIndent(level, ' ') + file.getName() + "/");
            for (File f:file.listFiles()){
                printFile(f, level + 1);
            }
        } else if (file.isFile()){
            System.out.println(getIndent(level, ' ') + file.getName());
        }
    }

    static String getIndent(int level, char ch){
        char[] a  = new char[level];
        Arrays.fill(a, ch);
        return new String(a);
    }
}
