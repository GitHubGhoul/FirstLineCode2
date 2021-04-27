package com.wxd.javacode.functional_program;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class CreateStream {
    public static void main(String[] args) throws Exception{
        Stream<String> stream = Stream.of("A", "B", "C", "D");
        // forEach()方法相当于内部循环调用，
        // 可传入符合Consumer接口的void accept(T t)的方法引用：
        stream.forEach(System.out::println);

        Stream<String> stream1 = Arrays.stream(new String[]{"A", "B", "C"});
        Stream<String> stream2 = List.of("X", "Y", "Z").stream();
        stream1.forEach(System.out::println);
        stream2.forEach(System.out::println);

        Stream<Integer> natural = Stream.generate(new NaturalSupplier());
        //注意：无限序列必须先变成有限序列再打印:
        natural.limit(20).forEach(System.out::println);

        try (Stream<String> lines = Files.lines(Paths.get("/Users/wxd/workface/FirstLineCode/javaCode/src/main/res/book.xml"))) {
        }

        Pattern p = Pattern.compile("\\s+");
        Stream<String> s = p.splitAsStream("The quick brown fox jumps over the lazy dog");
        s.forEach(System.out::println);

        // 将int[]数组变为IntStream:
        IntStream is = Arrays.stream(new int[] { 1, 2, 3 });
        // 将Stream<String>转换为LongStream:
        LongStream ls = List.of("1", "2", "3").stream().mapToLong(Long::parseLong);

        Stream<BigInteger> streamF = Stream.generate(new FibonacciQueue());
        streamF.limit(50).forEach(System.out::println);
    }

    static class NaturalSupplier implements Supplier<Integer> {
        int n = 0;

        @Override
        public Integer get() {
            n++;
            return n;
        }
    }

    //编写一个能输出斐波拉契数列（Fibonacci）的LongStream
    static class FibonacciQueue implements Supplier<BigInteger>{

        static Queue<BigInteger> queue = new LinkedList<>();

        static {
            queue.add(new BigInteger("0"));
            queue.add(new BigInteger("1"));
        }

        @Override
        public BigInteger get() {
            BigInteger prev = queue.poll();
            queue.add(prev.add(queue.peek()));
            return queue.peek();
        }
    }
}
