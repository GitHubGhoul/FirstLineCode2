package com.wxd.javacode.design_patterns.behavior;

import java.util.HashMap;
import java.util.Map;

public class TemplateMethodPattern {
    public static void main(String[] args) {
        AbstractSetting setting1 = new LocalSetting();
        System.out.println("test = " + setting1.getSetting("test"));
        System.out.println("test = " + setting1.getSetting("test"));
    }

    public static abstract class AbstractSetting {

        public final String getSetting(String key) {
            String value = lookupCache(key);
            if (value == null) {
                value = readFromDatabase(key);
                System.out.println("[DEBUG] load from db: " + key + " = " + value);
                putIntoCache(key, value);
            } else {
                System.out.println("[DEBUG] load from cache: " + key + " = " + value);
            }
            return value;
        }

        protected abstract String lookupCache(String key);

        protected abstract void putIntoCache(String key, String value);

        private String readFromDatabase(String key) {
            return Integer.toHexString(0x7fffffff & key.hashCode());
        }
    }

    public static class LocalSetting extends AbstractSetting {

        Map<String, String> cache = new HashMap<>();

        @Override
        protected String lookupCache(String key) {
            return cache.get(key);
        }

        @Override
        protected void putIntoCache(String key, String value) {
            cache.put(key, value);
        }

    }

}
