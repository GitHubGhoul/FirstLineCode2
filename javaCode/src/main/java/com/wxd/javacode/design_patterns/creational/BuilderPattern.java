package com.wxd.javacode.design_patterns.creational;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class BuilderPattern {
    public static void main(String[] args) {
        final MyURLBuilder myURLBuilder = new MyURLBuilder();
        final String build = myURLBuilder
                .setScheme(true)
                .setDomain("www.baidu.com")
                .setPath("movie")
                .setPath("get")
                .setQuery(Map.of("page", "1", "size", "10"))
                .build();
        System.out.println(build);
    }

    interface URLBuilder{

        public final static String SSL_SCHEME = "https://";
        public final static String NO_SSL_SCHEME = "http://";

        public URLBuilder builder();
        public URLBuilder setDomain(String domain);
        public URLBuilder setScheme(boolean isSSl);
        public URLBuilder setPath(String path);
        public URLBuilder setQuery(Map<String, String> query);
        public String build();
    }
    static class MyURLBuilder implements URLBuilder{
        private String scheme;
        private String domain;
        private List<String> path;
        private Map<String, String> query;

        public MyURLBuilder() {
            this.scheme = URLBuilder.SSL_SCHEME;
            this.path = new ArrayList<>();
            this.query = new HashMap<>();
        }

        @Override
        public URLBuilder builder() {
            return new MyURLBuilder();
        }
        @Override
        public URLBuilder setDomain(String domain) {
            this.domain = domain;
            return this;
        }

        @Override
        public URLBuilder setScheme(boolean isSSl) {
            this.scheme = isSSl?URLBuilder.SSL_SCHEME:URLBuilder.NO_SSL_SCHEME;
            return this;
        }

        @Override
        public URLBuilder setPath(String path) {
            this.path.add(path);
            return this;
        }

        @Override
        public URLBuilder setQuery(Map<String, String> query) {
            this.query.putAll(query);
            return this;
        }

        @Override
        public String build() {
            final StringJoiner stringQuery = new StringJoiner("&");
            query.forEach((key, value) -> stringQuery.add(key + "=" + value));
            final StringJoiner stringPath = new StringJoiner("/", scheme, "?"+stringQuery.toString());
            stringPath.add(domain);
            path.forEach(stringPath::add);
            return stringPath.toString();
        }
    }
}
