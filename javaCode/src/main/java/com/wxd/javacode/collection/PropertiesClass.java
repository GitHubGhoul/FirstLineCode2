package com.wxd.javacode.collection;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class PropertiesClass {
    public static void main(String[] args) {
        String f = "local.properties";
        Properties props = new Properties();
        try {
            props.load(new java.io.FileInputStream(f));
            String sdkDir = props.getProperty("sdk.dir");
            String ndkDir = props.getProperty("ndk.dir", "0");
            System.out.println(sdkDir);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String settings = "# test" + "\n" + "course=Java" + "\n" + "last_open_date=2019-08-07T12:35:01";
        ByteArrayInputStream input = null;
        try {
            input = new ByteArrayInputStream(settings.getBytes("UTF-8"));
            Properties props1 = new Properties();
            props1.load(input);
            System.out.println("course: " + props1.getProperty("course"));
            System.out.println("last_open_date: " + props1.getProperty("last_open_date"));
            System.out.println("last_open_file: " + props1.getProperty("last_open_file"));
            System.out.println("auto_save: " + props1.getProperty("auto_save", "60"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //写入配置文件
        Properties props2 = new Properties();
        props2.setProperty("url", "http://www.liaoxuefeng.com");
        props2.setProperty("language", "Java");
        try {
            props2.store(new FileOutputStream("C:\\conf\\setting.properties"), "这是写入的properties注释");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
