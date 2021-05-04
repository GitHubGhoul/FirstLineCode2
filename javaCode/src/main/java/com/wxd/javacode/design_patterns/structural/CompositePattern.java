package com.wxd.javacode.design_patterns.structural;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class CompositePattern {
    public static void main(String[] args) {
        Node root = new ElementNode("school");
        root.add(new ElementNode("classA")
                .add(new TextNode("Tom"))
                .add(new TextNode("Alice")));
        root.add(new ElementNode("classB")
                .add(new TextNode("Bob"))
                .add(new TextNode("Grace"))
                .add(new CommentNode("comment...")));
        System.out.println(root.toXml());
    }
    public interface Node {
        // 添加一个节点为子节点:
        Node add(Node node);
        // 获取子节点:
        List<Node> children();
        // 输出为XML:
        String toXml();
    }
    public static class ElementNode implements Node{

        private String name;
        private List<Node> list = new ArrayList<>();

        public ElementNode(String name) {
            this.name = name;
        }

        @Override
        public Node add(Node node) {
            list.add(node);
            return this;
        }

        @Override
        public List<Node> children() {
            return list;
        }

        @Override
        public String toXml() {
            String start = "<" + name + ">\n";
            String end = "</" + name + ">\n";
            StringJoiner sj = new StringJoiner("", start, end);
            list.forEach(node -> {
                sj.add(node.toXml() + "\n");
            });
            return sj.toString();
        }
    }

    public static class TextNode implements Node {
        private String text;

        public TextNode(String text) {
            this.text = text;
        }

        public Node add(Node node) {
            throw new UnsupportedOperationException();
        }

        public List<Node> children() {
            return List.of();
        }

        public String toXml() {
            return text;
        }
    }

    public static class CommentNode implements Node {
        private String text;

        public CommentNode(String text) {
            this.text = text;
        }

        public Node add(Node node) {
            throw new UnsupportedOperationException();
        }

        public List<Node> children() {
            return List.of();
        }

        public String toXml() {
            return "<!-- " + text + " -->";
        }
    }

}
