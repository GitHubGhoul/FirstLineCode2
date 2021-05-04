package com.wxd.javacode.design_patterns.behavior;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IteratorPattern {
    public static void main(String[] args) {
        var rarray = new ReverseArrayCollection<String>("apple", "pear", "orange", "banana");
        for (String fruit : rarray) {
            System.out.println(fruit);
        }
    }

    public static class ReverseArrayCollection<T> implements Iterable<T> {
        private T[] array;

        public ReverseArrayCollection(T... objs) {
            this.array = Arrays.copyOfRange(objs, 0, objs.length);
        }

        public Iterator<T> iterator() {
            return new ReverseIterator();
        }

        class ReverseIterator implements Iterator<T> {
            // 索引位置:
            int index;

            public ReverseIterator() {
                // 创建Iterator时,索引在数组末尾:
                this.index = ReverseArrayCollection.this.array.length;
            }

            public boolean hasNext() {
                // 如果索引大于0,那么可以移动到下一个元素(倒序往前移动):
                return index > 0;
            }

            public T next() {
                // 将索引移动到下一个元素并返回(倒序往前移动):
                index--;
                return array[index];
            }
        }
    }
}
