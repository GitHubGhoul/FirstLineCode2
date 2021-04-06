package com.wxd.javacode.general_type;

public class Pair<T,K> {
    private T first;
    private K last;

    public Pair(T first, K last) {
        this.first = first;
        this.last = last;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public K getLast() {
        return last;
    }

    public void setLast(K last) {
        this.last = last;
    }

    // 静态泛型方法应该使用其他类型区分:
    public static <M,N> Pair<M,N> create(M first, N last) {
        return new Pair<M,N>(first, last);
    }
}
