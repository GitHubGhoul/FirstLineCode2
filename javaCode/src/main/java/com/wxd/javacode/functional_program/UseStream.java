package com.wxd.javacode.functional_program;

import java.math.BigInteger;
import java.util.stream.Stream;

public class UseStream {
    public static void main(String[] args) {
        Stream<BigInteger> naturals = createNaturalStream();
        naturals.map(n -> n.multiply(n)) // 1, 4, 9, 16, 25...
                .limit(100)
                .forEach(System.out::println);

        createNaturalStream()
                .limit(100)
                .forEach(System.out::println);
    }
    //具体实现
    static Stream<BigInteger> createNaturalStream(){
        return null;
    }
}
