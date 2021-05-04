package com.wxd.javacode.design_patterns.behavior;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class VisitorPattern {
    public static void main(String[] args) {
        FileStructure fs = new FileStructure(new File("."));
        fs.handle(new JavaFileVisitor());

        try {
            Files.walkFileTree(Paths.get("."), new MyFileVisitor());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public interface Visitor {
        // 访问文件夹:
        void visitDir(File dir);
        // 访问文件:
        void visitFile(File file);
    }
    public static class FileStructure {
        // 根目录:
        private File path;
        public FileStructure(File path) {
            this.path = path;
        }

        public void handle(Visitor visitor) {
            scan(this.path, visitor);
        }

        private void scan(File file, Visitor visitor) {
            if (file.isDirectory()) {
                // 让访问者处理文件夹:
                visitor.visitDir(file);
                for (File sub : file.listFiles()) {
                    // 递归处理子文件夹:
                    scan(sub, visitor);
                }
            } else if (file.isFile()) {
                // 让访问者处理文件:
                visitor.visitFile(file);
            }
        }
    }

    public static class JavaFileVisitor implements Visitor {
        public void visitDir(File dir) {
            System.out.println("Visit dir: " + dir);
        }

        public void visitFile(File file) {
            if (file.getName().endsWith(".java")) {
                System.out.println("Found java file: " + file);
            }
        }
    }

    public static class ClassFileCleanerVisitor implements Visitor {
        public void visitDir(File dir) {
        }

        public void visitFile(File file) {
            if (file.getName().endsWith(".class")) {
                System.out.println("Will clean class file: " + file);
            }
        }
    }

    // 实现一个FileVisitor:
    static class MyFileVisitor extends SimpleFileVisitor<Path> {
        // 处理Directory:
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            System.out.println("pre visit dir: " + dir);
            // 返回CONTINUE表示继续访问:
            return FileVisitResult.CONTINUE;
        }

        // 处理File:
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            System.out.println("visit file: " + file);
            // 返回CONTINUE表示继续访问:
            return FileVisitResult.CONTINUE;
        }
    }
}
