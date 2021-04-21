package com.wxd.javacode.multithread;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentClass {
    public static void main(String[] args) {

        List<String> list = new CopyOnWriteArrayList<>();
        // 在不同的线程读写:
        list.add("A");
        list.add("B");
        list.get(0);

        Map<String, String> map = new ConcurrentHashMap<>();
        // 在不同的线程读写:
        map.put("A", "1");
        map.put("B", "2");
        map.get("A");
    }
}
