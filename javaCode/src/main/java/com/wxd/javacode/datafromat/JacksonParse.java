package com.wxd.javacode.datafromat;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.sun.tools.javac.Main;

import java.io.FileInputStream;
import java.io.InputStream;

public class JacksonParse {
    public static void main(String[] args) throws Exception{
        InputStream input = new FileInputStream("/Users/wxd/workface/FirstLineCode/javaCode/src/main/res/book.xml");
        JacksonXmlModule module = new JacksonXmlModule();
        XmlMapper mapper = new XmlMapper(module);
        Book book = mapper.readValue(input, Book.class);
        System.out.println(book.id);
        System.out.println(book.name);
        System.out.println(book.author);
        System.out.println(book.isbn);
        System.out.println(book.tags);
        System.out.println(book.pubDate);
    }
}
