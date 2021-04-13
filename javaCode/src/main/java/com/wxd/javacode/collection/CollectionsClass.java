package com.wxd.javacode.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsClass {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("pear");
        list.add("orange");
        // 排序前:
        System.out.println(list);
        Collections.sort(list);
        // 排序后:
        System.out.println(list);

        List<Integer> list1 = new ArrayList<>();
        for (int i=0; i<10; i++) {
            list1.add(i);
        }
        // 洗牌前:
        System.out.println(list1);
        Collections.shuffle(list1);
        // 洗牌后:
        System.out.println(list1);

        List<String> mutable = new ArrayList<>();
        mutable.add("apple");
        mutable.add("pear");
        // 变为不可变集合:
        List<String> immutable = Collections.unmodifiableList(mutable);
        //immutable.add("orange"); // UnsupportedOperationException!
        mutable.add("orange");
        mutable=null;
        System.out.println(immutable);
    }
}
