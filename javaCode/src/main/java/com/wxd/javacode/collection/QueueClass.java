package com.wxd.javacode.collection;

import java.util.LinkedList;
import java.util.Queue;

public class QueueClass {
    public static void main(String[] args) {
        Queue<String> q = new LinkedList<>();
        // 添加3个元素到队列:
        q.offer("apple");
        q.offer("pear");
        q.offer("banana");
        // 从队列取出元素:
        System.out.println(q.poll()); // apple
        System.out.println(q.poll()); // pear
        System.out.println(q.poll()); // banana
        System.out.println(q.poll()); // null,因为队列是空的

        Queue<String> q1 = new LinkedList<>();
        // 添加3个元素到队列:
        q1.offer("apple");
        q1.offer("pear");
        q1.offer("banana");
        // 队首永远都是apple，因为peek()不会删除它:
        System.out.println(q1.peek()); // apple
        System.out.println(q1.peek()); // apple
        System.out.println(q1.peek()); // apple
    }
}
