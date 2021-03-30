package com.wxd.javacode.throwable_handle;

public class CustomizeException extends RuntimeException{

    public CustomizeException() {
        super();
    }

    public CustomizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomizeException(String message) {
        super(message);
    }

    public CustomizeException(Throwable cause) {
        super(cause);
    }
}
