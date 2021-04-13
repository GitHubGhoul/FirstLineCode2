package com.wxd.javacode.collection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SetClass {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        System.out.println(set.add("abc")); // true
        System.out.println(set.add("xyz")); // true
        System.out.println(set.add("xyz")); // false，添加失败，因为元素已存在
        System.out.println(set.contains("xyz")); // true，元素存在
        System.out.println(set.contains("XYZ")); // false，元素不存在
        System.out.println(set.remove("hello")); // false，删除失败，因为元素不存在
        System.out.println(set.size()); // 2，一共两个元素

        Set<String> set1 = new HashSet<>();
        set1.add("apple");
        set1.add("banana");
        set1.add("pear");
        set1.add("orange");
        for (String s : set1) {
            System.out.println(s);
        }

        Set<String> set2 = new TreeSet<>();
        set2.add("apple");
        set2.add("banana");
        set2.add("pear");
        set2.add("orange");
        for (String s : set2) {
            System.out.println(s);
        }
        System.out.println("######################");
        exercise();
    }

    //在聊天软件中，发送方发送消息时，遇到网络超时后就会自动重发，因此，接收方可能会收到重复的消息，在显示给用户看的时候，需要首先去重。请练习使用Set去除重复的消息：
    private static void exercise() {
        List<Message> received = List.of(
                new Message(1, "Hello!"),
                new Message(2, "发工资了吗？"),
                new Message(2, "发工资了吗？"),
                new Message(3, "去哪吃饭？"),
                new Message(3, "去哪吃饭？"),
                new Message(4, "Bye")
        );
        List<Message> displayMessages = process(received);
        for (Message message : displayMessages) {
            System.out.println(message.text);
        }
    }

    static List<Message> process(List<Message> received) {
        Set<Message> mes = new TreeSet<>(received);
        return new ArrayList<Message>(mes);
    }

    static class Message implements Comparable<Message> {
        public final int sequence;
        public final String text;

        public Message(int sequence, String text) {
            this.sequence = sequence;
            this.text = text;
        }

        @Override
        public int compareTo(Message o) {
            Message m = (Message) o;
            if (this.sequence==m.sequence) {
                return 0;
            }
            return this.sequence>m.sequence? 1: -1;
        }
    }
}
