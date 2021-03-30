package com.wxd.javacode.throwable_handle;

import java.io.File;
import java.util.Optional;

public class NullPointerException {

    public static void main(String[] args) {
        Person p = new Person();
        System.out.println(p.address.city.toLowerCase());
    }

    private static Optional<String> readFromFile(String file) {
        if (!new File(file).exists()) {
            return Optional.empty();
        }
        return Optional.of(file);
    }

    static class Person {
        String[] name = new String[2];
        Address address = new Address();
    }

    static class Address {
        String city;
        String street;
        String zipcode;
    }

}
