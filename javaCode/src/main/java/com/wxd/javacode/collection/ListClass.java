package com.wxd.javacode.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ListClass {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("apple"); // size=1
        list.add(null); // size=2
        list.add("apple"); // 允许重复添加元素，size=3
        String second = list.get(1); // null
        System.out.println(second);

        List<String> list1 = List.of("apple", "pear", "banana");
        for (int i = 0; i < list1.size(); i++) {
            String s = list1.get(i);
            System.out.println(s);
        }
        for (Iterator<String> it = list1.iterator(); it.hasNext(); ) {
            String s = it.next();
            System.out.println(s);
        }
        for (String s : list1) {
            System.out.println(s);
        }
        List<Integer> list3 = List.of(12, 34, 56);
        Integer[] array = list3.toArray(new Integer[3]);
        Integer[] array1 = list3.toArray(new Integer[list3.size()]);
        Integer[] array2 = list3.toArray(Integer[]::new);
        List<Integer> list4 = List.of(array1);
        for (Integer n : list4) {
            System.out.println(n);
        }
        exercise();
    }

    //给定一组连续的整数，例如：10，11，12，……，20，但其中缺失一个数字，试找出缺失的数字：
    private static void exercise() {
        // 构造从start到end的序列：
        final int start = 10;
        final int end = 20;
        List<Integer> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list.add(i);
        }
        // 洗牌算法shuffle可以随机交换List中的元素位置:
        Collections.shuffle(list);
        // 随机删除List中的一个元素:
        int removed = list.remove((int) (Math.random() * list.size()));
        int found = findMissingNumber(start, end, list);
        System.out.println(list.toString());
        System.out.println("missing number: " + found);
        System.out.println(removed == found ? "测试成功" : "测试失败");
    }

    static int findMissingNumber(int start, int end, List<Integer> list) {
        for (int i = start; i <= end; i++) {
            boolean isFound = false;
            for (Integer in : list) {
                if (i == in) {
                    isFound = true;
                    break;
                } else {
                    continue;
                }
            }
            if (!isFound) {
                return i;
            }
        }
        return 0;
    }
}
