package com.wxd.javacode.functional_program;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OutCollect {
    public static void main(String[] args) {
        Stream<Long> s1 = Stream.generate(new NaturalSupplier());
        Stream<Long> s2 = s1.map(n -> n * n);
        Stream<Long> s3 = s2.map(n -> n - 1);
        System.out.println(s3); // java.util.stream.ReferencePipeline$3@3cd1a2f1

        Stream<String> streamList = Stream.of("Apple", "", null, "Pear", "  ", "Orange");
        List<String> list = streamList.filter(s -> s != null && !s.isBlank()).collect(Collectors.toList());
        System.out.println(list);

        List<String> list1 = List.of("Apple", "Banana", "Orange");
        String[] array = list1.stream().toArray(String[]::new);
        System.out.println(Arrays.toString(array));

        Stream<String> streamMap = Stream.of("APPL:Apple", "MSFT:Microsoft");
        Map<String, String> map = streamMap
                .collect(Collectors.toMap(
                        // 把元素s映射为key:
                        s -> s.substring(0, s.indexOf(':')),
                        // 把元素s映射为value:
                        s -> s.substring(s.indexOf(':') + 1)));
        System.out.println(map);

        List<String> listGroups = List.of("Apple", "Banana", "Blackberry", "Coconut", "Avocado", "Cherry", "Apricots");
        Map<String, List<String>> groups = listGroups.stream()
                .collect(Collectors.groupingBy(s -> s.substring(0, 1), Collectors.toList()));
        System.out.println(groups);
    }

    static class NaturalSupplier implements Supplier<Long> {
        long n = 0;
        public Long get() {
            n++;
            return n;
        }
    }
}
