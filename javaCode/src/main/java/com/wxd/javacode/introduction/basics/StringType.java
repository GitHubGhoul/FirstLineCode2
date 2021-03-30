package com.wxd.javacode.introduction.basics;

public class StringType {
    public static void main(String[] args) {
        char c1 = 'A';
        char c2 = '中';

        int n1 = 'A'; // 字母“A”的Unicodde编码是65
        int n2 = '中'; // 汉字“中”的Unicode编码是20013

        // 注意是十六进制:
        char c3 = '\u0041'; // 'A'，因为十六进制0041 = 十进制65
        char c4 = '\u4e2d'; // '中'，因为十六进制4e2d = 十进制20013

        String s = ""; // 空字符串，包含0个字符
        String s1 = "A"; // 包含一个字符
        String s2 = "ABC"; // 包含3个字符
        String s3 = "中文 ABC"; // 包含6个字符，其中有一个空格

        String s4 = "abc\"xyz"; // 包含7个字符: a, b, c, ", x, y, z
        String s5 = "abc\\xyz"; // 包含7个字符: a, b, c, \, x, y, z
        String s6 = "ABC\n\u4e2d\u6587"; // 包含6个字符: A, B, C, 换行符, 中, 文

        /*String s7 = """
                   SELECT * FROM
                     users
                   WHERE id > 100
                   ORDER BY name DESC
                   """;*/ //java13开始
        System.out.println(s);

        String s0 = "hello";
        String t = s0;
        s0 = "world";
        System.out.println(t); // t是"hello"还是"world"?
        exercise();
    }

    private static void exercise(){
        int a = 72;
        int b = 105;
        int c = 65281;
        // FIXME:
        String s = String.valueOf((char)a) + String.valueOf((char) b) + String.valueOf((char) c);
        System.out.println(s);
    }
}
