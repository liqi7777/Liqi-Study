package com.jz.test.redistest.exception;

/**
 * @author liqi
 * create  2021-10-19 17:37
 */
public class LiqiException extends RuntimeException {
    private static final long serialVersionUID = 5672001508215377481L;

    public LiqiException() {
        super();
    }

    public LiqiException(String message) {
        super(message);
    }

    public LiqiException(String message, Throwable cause) {
        super(message, cause);
    }

    public LiqiException(Throwable cause) {
        super(cause);
    }

    protected LiqiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
