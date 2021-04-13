package com.wxd.javacode.collection;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

public class StackClass {
    public static void main(String[] args) {
        String hex = toHex(12500);
        if (hex.equalsIgnoreCase("30D4")) {
            System.out.println("测试通过");
        } else {
            System.out.println("测试失败");
        }

        String exp = "1 + 2 * (9 - 5)";
        SuffixExpression se = compile(exp);
        int result = se.execute();
        System.out.println(exp + " = " + result + " " + (result == 1 + 2 * (9 - 5) ? "✓" : "✗"));

        String exp1 = "x + 2 * (y - 5)";
        SuffixExpression se1 = compile1(exp1);
        Map<String, Integer> env1 = Map.of("x", 1, "y", 9);
        int result1 = se1.execute(env1);
        System.out.println(exp1 + " = " + result1 + " " + (result1 == 1 + 2 * (9 - 5) ? "✓" : "✗"));
    }

    //利用Stack把一个给定的整数转换为十六进制：
    private static String toHex(int n) {
        Deque<String> stack = new LinkedList<>();

        int jinzhi = 16;
        int shang = n / jinzhi;
        int yushu = n % jinzhi;
        stack.push("%x".formatted(yushu));
        while (shang != 0) {
            yushu = shang % jinzhi;
            stack.push("%x".formatted(yushu));
            shang = shang / jinzhi;
        }
        StringBuilder sb = new StringBuilder();
        while (stack.peek() != null) {
            sb.append(stack.pop());
        }

        System.out.println(sb.toString());
        return sb.toString();
    }

    //利用Stack把字符串中缀表达式编译为后缀表达式，然后再利用栈执行后缀表达式获得计算结果：
    static SuffixExpression compile(String exp) {
        Map<Character, Integer> property = Map.of('(', 0, '*', 1, '/', 1, '+', 2, '-', 2);
        exp = exp.replace(" ", "");
        Deque<Character> operate = new LinkedList<>();
        String number = "";
        for (int i = 0; i < exp.length(); i++) {
            if (Character.isDigit(exp.charAt(i))) {
                number += exp.charAt(i);
            } else {
                if (operate.isEmpty()) {
                    operate.push(exp.charAt(i));
                } else {
                    switch (exp.charAt(i)) {
                        case '(':
                            operate.push(exp.charAt(i));
                            break;
                        case ')':
                            while (!operate.isEmpty()){
                                if (operate.peek().equals('(')) {
                                    operate.pop();
                                    break;
                                } else {
                                    number += operate.pop();
                                }
                            }
                            break;
                        default:
                            while (!operate.isEmpty()) {
                                if (operate.peek().equals('(')){
                                    operate.push(exp.charAt(i));
                                    break;
                                }
                                if (property.get(exp.charAt(i)) <= property.get(operate.peek())) {
                                    operate.push(exp.charAt(i));
                                    break;
                                } else {
                                    number += operate.pop();
                                }
                            }
                    }
                }
            }
        }
        while (!operate.isEmpty()){
            number += operate.pop();
        }
        return new SuffixExpression(number);
    }

    static SuffixExpression compile1(String exp) {
        // TODO:
        String isOperate = "+-*/()";
        Map<Character, Integer> property = Map.of('(', 0, '*', 1, '/', 1, '+', 2, '-', 2);
        exp = exp.replace(" ", "");
        Deque<Character> operate = new LinkedList<>();
        String number = "";
        for (int i = 0; i < exp.length(); i++) {
            if (!isOperate.contains(exp.charAt(i) + "")) {
                number += exp.charAt(i);
            } else {
                if (operate.isEmpty()) {
                    operate.push(exp.charAt(i));
                } else {
                    switch (exp.charAt(i)) {
                        case '(':
                            operate.push(exp.charAt(i));
                            break;
                        case ')':
                            while (!operate.isEmpty()) {
                                if (operate.peek().equals('(')) {
                                    operate.pop();
                                    break;
                                } else {
                                    number += operate.pop();
                                }
                            }
                            break;
                        default:
                            while (!operate.isEmpty()) {
                                if (operate.peek().equals('(')) {
                                    operate.push(exp.charAt(i));
                                    break;
                                }else {
                                    if (property.get(exp.charAt(i)) <= property.get(operate.peek())) {
                                        operate.push(exp.charAt(i));
                                        break;
                                    } else {
                                        number += operate.pop();
                                    }
                                }
                            }
                    }
                }
            }
        }
        while (!operate.isEmpty()) {
            number += operate.pop();
        }
        return new SuffixExpression(number);
    }

    static class SuffixExpression {
        String st = "";
        Deque<Integer> result = new LinkedList<>();
        public SuffixExpression(String st){
            this.st = st;
        }
        int execute() {
            // TODO:
            for(int i = 0; i<st.length(); i++){
                if (Character.isDigit(st.charAt(i))){
                    result.push(Integer.parseInt(st.charAt(i) + ""));
                }else{
                    Integer a0 = Integer.parseInt(result.pop() + "");
                    Integer a1 = Integer.parseInt(result.pop() + "");
                    switch (st.charAt(i)){
                        case '+' :
                            result.push(a1 + a0);
                            break;
                        case '-' :
                            result.push(a1 - a0);
                            break;
                        case '*' :
                            result.push(a1 * a0);
                            break;
                        case '/' :
                            result.push(a1 / a0);
                            break;
                    }
                }
            }
            int re = result.pop().intValue();
            return re;
        }

        //此方法value值>9会有问题
        int execute(Map<String, Integer> env) {
            // TODO:
            for (Map.Entry<String, Integer> entry : env.entrySet()){
                if (st.contains(entry.getKey())){
                    st = st.replace(entry.getKey() + "", entry.getValue() + "");
                }
            }
            for (int i = 0; i < st.length(); i++) {
                if (Character.isDigit(st.charAt(i))) {
                    result.push(Integer.parseInt(st.charAt(i) + ""));
                } else {
                    Integer a0 = Integer.parseInt(result.pop() + "");
                    Integer a1 = Integer.parseInt(result.pop() + "");
                    switch (st.charAt(i)) {
                        case '+':
                            result.push(a1 + a0);
                            break;
                        case '-':
                            result.push(a1 - a0);
                            break;
                        case '*':
                            result.push(a1 * a0);
                            break;
                        case '/':
                            result.push(a1 / a0);
                            break;
                    }
                }
            }
            int re = result.pop().intValue();
            return re;
        }
    }

}
