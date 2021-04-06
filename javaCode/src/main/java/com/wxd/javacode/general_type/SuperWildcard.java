package com.wxd.javacode.general_type;

public class SuperWildcard {
    public static void main(String[] args) {
        Pair<Number> p1 = new Pair<>(12.3, 4.56);
        Pair<Integer> p2 = new Pair<>(123, 456);
        setSame(p1, 100);
        setSame(p2, 200);
        System.out.println(p1.getFirst() + ", " + p1.getLast());

        Pair<Integer> p = new Pair<>(123, 456);
        Pair<?> p3 = p; // 安全地向上转型
        System.out.println(p3.getFirst() + ", " + p3.getLast());
    }

    static void setSame(Pair<? super Integer> p, Integer n) {
        p.setFirst(n);
        p.setLast(n);
    }

    static class Pair<T> {
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
}
