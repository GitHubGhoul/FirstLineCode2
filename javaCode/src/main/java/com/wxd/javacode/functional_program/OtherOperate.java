package com.wxd.javacode.functional_program;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OtherOperate {
    public static void main(String[] args) {
        List<String> sort = List.of("Orange","apple","Banana")
                .stream()
                .sorted(String::compareToIgnoreCase)
                .collect(Collectors.toList());
        System.out.println(sort);

        List<String> dist = List.of("A", "B", "A", "C", "B", "D")
                .stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println(dist);

        List<String> skip = List.of("A","B","C","D","E","F")
                .stream()
                .skip(2)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(skip);

        Stream<String> s1 = List.of("A", "B", "C").stream();
        Stream<String> s2 = List.of("D", "E").stream();
        // 合并:
        Stream<String> concat = Stream.concat(s1, s2);
        String[] result = concat.parallel() // 变成一个可以并行处理的Stream
                .sorted() // 可以进行并行排序
                .toArray(String[]::new);
        System.out.println(Arrays.toString(result));

        Stream<List<Integer>> s = Stream.of(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9));
        Stream<Integer> flat = s.flatMap(list -> list.stream());
        System.out.println(flat.collect(Collectors.toList()));
    }
}
