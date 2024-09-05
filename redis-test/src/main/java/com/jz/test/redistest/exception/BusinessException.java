package com.jz.test.redistest.exception;

/**
 * @author liqi
 * create  2021-10-20 13:17
 */
public class BusinessException extends BaseException {

    private static final long serialVersionUID = 1L;


    public BusinessException(IResponseEnum responseEnum, Object[] args) {
        super(responseEnum, args);
    }

}
