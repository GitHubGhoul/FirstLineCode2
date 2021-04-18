package com.wxd.javacode.encrypt_safe;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.Security;

public class HashAlgorithm {
    public static void main(String[] args) throws Exception{
        // 创建一个MessageDigest实例:
        MessageDigest md = MessageDigest.getInstance("MD5");
        // 反复调用update输入数据:
        md.update("Hello".getBytes("UTF-8"));
        md.update("World".getBytes("UTF-8"));
        byte[] result = md.digest(); // 16 bytes: 68e109f0f40ca72a15e05cc22786f8e6
        System.out.println(new BigInteger(1, result).toString(16));

        // 创建一个MessageDigest实例:
        MessageDigest md1 = MessageDigest.getInstance("SHA-1");
        // 反复调用update输入数据:
        md1.update("Hello".getBytes("UTF-8"));
        md1.update("World".getBytes("UTF-8"));
        byte[] result1 = md1.digest(); // 20 bytes: db8ac1c259eb89d4a131b253bacfca5f319d54f2
        System.out.println(new BigInteger(1, result1).toString(16));

        // 注册BouncyCastle:
        Security.addProvider(new BouncyCastleProvider());
        // 按名称正常调用:
        MessageDigest md2 = MessageDigest.getInstance("RipeMD160");
        md2.update("HelloWorld".getBytes("UTF-8"));
        byte[] result2 = md2.digest();
        System.out.println(new BigInteger(1, result2).toString(16));
    }
}
