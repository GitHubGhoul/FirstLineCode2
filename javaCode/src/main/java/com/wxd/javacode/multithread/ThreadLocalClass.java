package com.wxd.javacode.multithread;

public class ThreadLocalClass {
    public static void main(String[] args) throws Exception{
        log("start main...");
        new Thread(()->{
            log("run task...");
        }).start();
        new Thread(()->{
            log("print...");
        }).start();
        log("end main.");

        try (var ctx = new UserContext("Bob")) {
            // 可任意调用UserContext.currentUser():
            String currentUsr = ctx.currentUser();
        }// 在此自动调用UserContext.close()方法释放ThreadLocal关联对象
    }

    static void log(String s) {
        System.out.println(Thread.currentThread().getName() + ":" + s);
    }

    static class UserContext implements AutoCloseable{

        static final ThreadLocal<String> ctx = new ThreadLocal<>();

        public UserContext(String user){
            ctx.set(user);
        }

        public String currentUser(){
            return ctx.get();
        }

        @Override
        public void close() throws Exception {
ctx.remove();
        }
    }
}
