package com.wxd.javacode.design_patterns.creational;

import java.io.IOException;
import java.nio.file.Paths;

public class AbstractFactoryPattern{
    public static void main(String[] args) throws IOException {
        // 创建AbstractFactory，实际类型是FastFactory:
        AbstractFactory factory = new FastFactory();
        // 生成Html文档:
        HtmlDocument html = factory.createHtml("#Hello\nHello, world!");
        html.save(Paths.get(".", "fast.html"));
        // 生成Word文档:
        WordDocument word = factory.createWord("#Hello\nHello, world!");
        word.save(Paths.get(".", "fast.doc"));
    }
}
