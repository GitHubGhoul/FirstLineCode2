package com.wxd.javacode.functional_program;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class UseFilter {
    public static void main(String[] args) {
        IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .filter(n -> n % 2 != 0)
                .forEach(System.out::println);

        Stream.generate(new LocalDateSupplier())
                .limit(31)
                .filter(ldt -> ldt.getDayOfWeek() == DayOfWeek.SATURDAY || ldt.getDayOfWeek() == DayOfWeek.SUNDAY)
                .forEach(System.out::println);

        List<Person> persons = List.of(new Person("小明", 88), new Person("小黑", 62), new Person("小白", 45),
                new Person("小黄", 78), new Person("小红", 99), new Person("小林", 58));
        // 请使用filter过滤出及格的同学，然后打印名字:
        persons.stream()
                .filter(obj -> obj.score > 59)
                .forEach(obj -> System.out.println(obj.name));
    }

    static class LocalDateSupplier implements Supplier<LocalDate> {

        LocalDate start = LocalDate.of(2020, 1, 1);
        int n = -1;

        @Override
        public LocalDate get() {
            n++;
            return start.plusDays(n);
        }
    }

    static class Person {
        String name;
        int score;

        Person(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }
}
