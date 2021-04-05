package com.wxd.javacode.annotation;

import com.wxd.javacode.object_oriented.basis.Person;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class CallAnnotation {
    public static void main(String[] args) {
        // 判断@Report是否存在于Person类:
        Person.class.isAnnotationPresent(Report.class);

        // 获取Person定义的@Report注解:
        Report report = Person.class.getAnnotation(Report.class);
        if(report!=null){
            int type = report.type();
            String level = report.level();
        }

        try {
            check(new Person("111111111111111111111",0));
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    static void check(Person person) throws IllegalArgumentException, ReflectiveOperationException {
        // 遍历所有Field:
        for (Field field : person.getClass().getFields()) {
            // 获取Field定义的@Range:
            Range range = field.getAnnotation(Range.class);
            // 如果@Range存在:
            if (range != null) {
                // 获取Field的值:
                Object value = field.get(person);
                // 如果值是String:
                if (value instanceof String) {
                    String s = (String) value;
                    // 判断值是否满足@Range的min/max:
                    if (s.length() < range.min() || s.length() > range.max()) {
                        throw new IllegalArgumentException("Invalid field");
                    }
                }
            }
        }
    }
}
