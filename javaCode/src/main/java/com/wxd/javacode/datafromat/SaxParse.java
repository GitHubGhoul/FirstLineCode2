package com.wxd.javacode.datafromat;

import com.sun.tools.javac.Main;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SaxParse {
    public static void main(String[] args) throws Exception{
        InputStream input = new FileInputStream("/Users/wxd/workface/FirstLineCode/javaCode/src/main/res/book.xml");
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser saxParser = spf.newSAXParser();
        saxParser.parse(input, new MyHandler());
    }

    static class MyHandler extends DefaultHandler {
        public void startDocument() throws SAXException {
            print("start document");
        }

        public void endDocument() throws SAXException {
            print("end document");
        }

        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            print("start element:", localName, qName);
        }

        public void endElement(String uri, String localName, String qName) throws SAXException {
            print("end element:", localName, qName);
        }

        public void characters(char[] ch, int start, int length) throws SAXException {
            print("characters:", new String(ch, start, length));
        }

        public void error(SAXParseException e) throws SAXException {
            print("error:", e);
        }

        void print(Object... objs) {
            for (Object obj : objs) {
                System.out.print(obj);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
