package com.wxd.javacode.encrypt_safe;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class CodeAlgorithm {
    public static void main(String[] args) {
        String encoded = URLEncoder.encode("中文!", StandardCharsets.UTF_8);
        System.out.println(encoded);
        String decoded = URLDecoder.decode("%E4%B8%AD%E6%96%87%21",StandardCharsets.UTF_8);
        System.out.println(decoded);
        byte[] input = new byte[]{ (byte) 0xe4, (byte) 0xb8, (byte) 0xad };
        String b64encoded = Base64.getEncoder().encodeToString(input);
        System.out.println(b64encoded);
        byte[] output = Base64.getDecoder().decode("5Lit");
        System.out.println(Arrays.toString(output));
        System.out.println("#####################");

        byte[] input1 = new byte[]{(byte) 0xe4, (byte) 0xb8, (byte) 0xad, 0x21};
        String b64encoded1 = Base64.getEncoder().encodeToString(input1);
        String b64encoded2 = Base64.getEncoder().withoutPadding().encodeToString(input1);
        System.out.println(b64encoded1);
        System.out.println(b64encoded2);
        byte[] output1 = Base64.getDecoder().decode(b64encoded2);
        System.out.println(Arrays.toString(output1));
        System.out.println("#####################");

        byte[] input2 = new byte[] { 0x01, 0x02, 0x7f, 0x00 };
        String b64encoded_2 = Base64.getUrlEncoder().encodeToString(input2);
        System.out.println(b64encoded_2);
        byte[] output2 = Base64.getUrlDecoder().decode(b64encoded_2);
        System.out.println(Arrays.toString(output2));
    }
}
