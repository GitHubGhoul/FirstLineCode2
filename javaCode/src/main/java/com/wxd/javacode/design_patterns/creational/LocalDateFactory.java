package com.wxd.javacode.design_patterns.creational;

import java.time.LocalDate;

public class LocalDateFactory {
    public static LocalDate fromInt(int yyyyMMdd){
        return LocalDate.of(yyyyMMdd/10000, yyyyMMdd/100%100, yyyyMMdd%100);
    }
}
