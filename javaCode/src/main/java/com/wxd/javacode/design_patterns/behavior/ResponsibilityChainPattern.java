package com.wxd.javacode.design_patterns.behavior;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ResponsibilityChainPattern {
    public static void main(String[] args) {
        // 构造责任链:
        HandlerChain chain = new HandlerChain();
        chain.addHandler(new ManagerHandler());
        chain.addHandler(new DirectorHandler());
        chain.addHandler(new CEOHandler());
        // 处理请求:
        chain.process(new Request("Bob", new BigDecimal("123.45")));
        chain.process(new Request("Alice", new BigDecimal("1234.56")));
        chain.process(new Request("Bill", new BigDecimal("12345.67")));
        chain.process(new Request("John", new BigDecimal("123456.78")));
    }

    public static class Request {
        private String name;
        private BigDecimal amount;

        public Request(String name, BigDecimal amount) {
            this.name = name;
            this.amount = amount;
        }

        public String getName() {
            return name;
        }

        public BigDecimal getAmount() {
            return amount;
        }
    }

    public interface Handler {
        // 返回Boolean.TRUE = 成功
        // 返回Boolean.FALSE = 拒绝
        // 返回null = 交下一个处理
        Boolean process(Request request);
    }

    public static class ManagerHandler implements Handler {
        public Boolean process(Request request) {
            // 如果超过1000元，处理不了，交下一个处理:
            if (request.getAmount().compareTo(BigDecimal.valueOf(1000)) > 0) {
                return null;
            }
            // 对Bob有偏见:
            return !request.getName().equalsIgnoreCase("bob");
        }
    }

    public static class DirectorHandler implements Handler {

        @Override
        public Boolean process(Request request) {
            if (request.getAmount().compareTo(BigDecimal.valueOf(10000)) > 0) {
                return null;
            }
            return !request.getName().equalsIgnoreCase("tom");
        }
    }

    public static class CEOHandler implements Handler {

        @Override
        public Boolean process(Request request) {
            if (request.getAmount().compareTo(BigDecimal.valueOf(50000)) > 0) {
                return Boolean.FALSE;
            }
            return !request.getName().equalsIgnoreCase("alice");
        }
    }

    public static class HandlerChain {
        // 持有所有Handler:
        private List<Handler> handlers = new ArrayList<>();

        public void addHandler(Handler handler) {
            this.handlers.add(handler);
        }

        public boolean process(Request request) {
            // 依次调用每个Handler:
            for (Handler handler : handlers) {
                Boolean r = handler.process(request);
                if (r != null) {
                    // 如果返回TRUE或FALSE，处理结束:
                    System.out.println(request + " " + (r ? "Approved by " : "Denied by ") + handler.getClass().getSimpleName());
                    return r;
                }
            }
            throw new RuntimeException("Could not handle request: " + request);
        }
    }
}
