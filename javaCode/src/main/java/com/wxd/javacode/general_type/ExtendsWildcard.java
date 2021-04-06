package com.wxd.javacode.general_type;

import java.util.List;

public class ExtendsWildcard {
    public static void main(String[] args) {
        Pair<Integer> p = new Pair<>(123, 456);
        int n = add(p);
        System.out.println(n);
    }

    static int add(Pair<? extends Number> p) {
        Number first = p.getFirst();
        Number last = p.getLast();
        //p.setFirst(new Integer(first.intValue() + 100));
        //p.setLast(new Integer(last.intValue() + 100));
        return first.intValue() + last.intValue();
    }

    static class Pair<T extends Number> {
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
        public void setFirst(T first) {
            this.first = first;
        }
        public void setLast(T last) {
            this.last = last;
        }
    }

    int sumOfList(List<? extends Integer> list) {
        int sum = 0;
        for (int i=0; i<list.size(); i++) {
            Integer n = list.get(i);
            sum = sum + n;
        }
        return sum;
    }
}
