package com.wxd.javacode.multithread;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicClass {
    public static void main(String[] args) {
        IdGenerator idGenerator = new IdGenerator();
        idGenerator.getNextId();
    }
    static class IdGenerator {
        AtomicLong var = new AtomicLong(0);

        public long getNextId() {
            return var.incrementAndGet();
        }
    }
}
