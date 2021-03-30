package com.wxd.javacode.throwable_handle;

import com.sun.tools.javac.Main;

import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

public class Logging {
    public static void main(String[] args) {
        Logger logger = Logger.getGlobal();
        logger.info("start process...");
        logger.warning("memory is running out...");
        logger.fine("ignored.");
        logger.severe("process will be terminated...");

        Logger logger1 = Logger.getLogger(Main.class.getName());
        logger1.info("Start process...");
        try {
            "".getBytes("invalidCharsetName");
        } catch (UnsupportedEncodingException e) {
            // TODO: 使用logger.severe()打印异常
            logger1.severe("Process exception.");
        }
        logger1.info("Process end.");
    }
}
