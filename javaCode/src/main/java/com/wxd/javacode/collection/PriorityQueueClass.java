package com.wxd.javacode.collection;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueClass {
    public static void main(String[] args) {
        Queue<String> q = new PriorityQueue<>();
        // 添加3个元素到队列:
        q.offer("apple");
        q.offer("pear");
        q.offer("banana");
        System.out.println(q.poll()); // apple
        System.out.println(q.poll()); // banana
        System.out.println(q.poll()); // pear
        System.out.println(q.poll()); // null,因为队列为空

        Queue<User> q1 = new PriorityQueue<>(new UserComparator());
        // 添加3个元素到队列:
        q1.offer(new User("Bob", "A1"));
        q1.offer(new User("Alice", "A2"));
        q1.offer(new User("Boss", "V1"));
        System.out.println(q1.poll()); // Boss/V1
        System.out.println(q1.poll()); // Bob/A1
        System.out.println(q1.poll()); // Alice/A2
        System.out.println(q1.poll()); // null,因为队列为空
    }

    static class UserComparator implements Comparator<User> {
        public int compare(User u1, User u2) {
            if (u1.number.charAt(0) == u2.number.charAt(0)) {
                // 如果两人的号都是A开头或者都是V开头,比较号的大小:
                return u1.number.compareTo(u2.number);
            }
            if (u1.number.charAt(0) == 'V') {
                // u1的号码是V开头,优先级高:
                return -1;
            } else {
                return 1;
            }
        }
    }

    static class User{
        public final String name;
        public final String number;

        public User(String name, String number) {
            this.name = name;
            this.number = number;
        }

        public String toString() {
            return name + "/" + number;
        }
    }

}
