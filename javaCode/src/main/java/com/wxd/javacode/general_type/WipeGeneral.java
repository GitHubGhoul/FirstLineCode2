package com.wxd.javacode.general_type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class WipeGeneral {
    public static void main(String[] args) {
        Pair<String> p1 = new Pair<>("Hello", "world");
        Pair<Integer> p2 = new Pair<>(123, 456);
        Class c1 = p1.getClass();
        Class c2 = p2.getClass();
        System.out.println(c1==c2); // true
        System.out.println(c1==Pair.class); // true

        Pair<String> pair = new Pair<>(String.class);

        Class<IntPair> clazz = IntPair.class;
        Type t = clazz.getGenericSuperclass();
        if (t instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) t;
            Type[] types = pt.getActualTypeArguments(); // 可能有多个泛型类型
            Type firstType = types[0]; // 取第一个泛型类型
            Class<?> typeClass = (Class<?>) firstType;
            System.out.println(typeClass); // Integer
        }
    }

    public static class Pair<T> {
        private T first;
        private T last;
        public Pair(T first, T last) {
            this.first = first;
            this.last = last;
        }
        public T getFirst() {
            return first;
        }
        public T getLast() {
            return last;
        }

        public Pair(Class<T> clazz) {
            try {
                first = clazz.newInstance();
                last = clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    static class IntPair extends Pair<Integer> {
        public IntPair(Integer first, Integer last) {
            super(first, last);
        }
    }

}
