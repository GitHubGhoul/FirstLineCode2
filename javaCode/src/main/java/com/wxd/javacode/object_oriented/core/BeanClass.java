package com.wxd.javacode.object_oriented.core;

import com.wxd.javacode.object_oriented.basis.Person;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class BeanClass {
    public static void main(String[] args) {
        BeanInfo info = null;
        try {
            info = Introspector.getBeanInfo(Person.class);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
            System.out.println(pd.getName());
            System.out.println("  " + pd.getReadMethod());
            System.out.println("  " + pd.getWriteMethod());
        }
    }
}
