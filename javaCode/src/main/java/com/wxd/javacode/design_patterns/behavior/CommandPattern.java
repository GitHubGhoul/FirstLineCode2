package com.wxd.javacode.design_patterns.behavior;

import com.sun.activation.viewers.TextEditor;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class CommandPattern {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        editor.add("Command pattern in text editor.\n");
        // 执行一个CopyCommand:
        Command copy = new CopyCommand(editor);
        copy.execute();
        editor.add("----\n");
        // 执行一个PasteCommand:
        Command paste = new PasteCommand(editor);
        paste.execute();
        System.out.println(editor.getState());
    }

    public static class TextEditor {
        private StringBuilder buffer = new StringBuilder();

        public void copy() {
            Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable text = new StringSelection(buffer.toString());
            clip.setContents(text, null);
        }

        public void paste() {
            Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable clipTf = sysClip.getContents(null);
            if (clipTf != null) {
                if (clipTf.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    try {
                        String text = (String) clipTf.getTransferData(DataFlavor.stringFlavor);
                        add(text);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void add(String s) {
            buffer.append(s);
        }

        public void delete() {
            if (buffer.length() > 0) {
                buffer.deleteCharAt(buffer.length() - 1);
            }
        }

        public String getState() {
            return buffer.toString();
        }

        public void print() {
            System.out.println(this.buffer.toString());
        }
    }

    public interface Command {
        void execute();
    }

    public static class CopyCommand implements Command {
        // 持有执行者对象:
        private TextEditor receiver;

        public CopyCommand(TextEditor receiver) {
            this.receiver = receiver;
        }

        public void execute() {
            receiver.copy();
        }
    }

    public static class PasteCommand implements Command {
        private TextEditor receiver;

        public PasteCommand(TextEditor receiver) {
            this.receiver = receiver;
        }

        public void execute() {
            receiver.paste();
        }
    }
}
