package com.wxd.javacode.datetime;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class InstantClass {
    public static void main(String[] args) {
        Instant now = Instant.now();
        System.out.println(now.getEpochSecond()); // 秒
        System.out.println(now.toEpochMilli()); // 毫秒

        Instant ins = Instant.ofEpochSecond(1568568760);
        ZonedDateTime zdt = ins.atZone(ZoneId.systemDefault());
        System.out.println(zdt); // 2019-09-16T01:32:40+08:00[Asia/Shanghai]
    }
}
