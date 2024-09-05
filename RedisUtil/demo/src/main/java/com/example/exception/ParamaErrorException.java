package com.example.exception;

/**
 * @author liqi
 * create  2021-07-02 10:58
 */
public class ParamaErrorException extends RuntimeException {

    public ParamaErrorException() {
    }

    public ParamaErrorException(String message) {
        super(message);
    }

    public ParamaErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamaErrorException(Throwable cause) {
        super(cause);
    }

    protected ParamaErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}


