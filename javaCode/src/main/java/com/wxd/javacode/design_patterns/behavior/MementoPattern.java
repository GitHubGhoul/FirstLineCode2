package com.wxd.javacode.design_patterns.behavior;

public class MementoPattern {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        editor.add("Hello");
        editor.add(',');
        editor.delete();
        editor.add(' ');
        editor.add("world");
        // 保存状态:
        String state = editor.getState();
        // 继续编辑:
        editor.add("!!!");
        editor.delete();
        editor.print();
        // 恢复状态:
        editor.setState(state);
        editor.print();

    }

    public static class TextEditor {
        private StringBuilder buffer = new StringBuilder();

        public void add(char ch) {
            buffer.append(ch);
        }

        public void add(String s) {
            buffer.append(s);
        }

        public void delete() {
            if (buffer.length() > 0) {
                buffer.deleteCharAt(buffer.length() - 1);
            }
        }

        // 获取状态:
        public String getState() {
            return buffer.toString();
        }

        // 恢复状态:
        public void setState(String state) {
            this.buffer.delete(0, this.buffer.length());
            this.buffer.append(state);
        }

        public void print() {
            System.out.println(this.buffer.toString());
        }

    }
}
