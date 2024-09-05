package com.jz.test.redistest.exception;

import cn.hutool.core.util.StrUtil;

/**
 * @author liqi
 * create  2021-10-19 19:06
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 3344696990654126911L;

    private final int code;

    private final String message;

    public BaseException(IResponseEnum iResponseEnum) {
        super(iResponseEnum.getMessage());
        this.code = iResponseEnum.getCode();
        this.message = iResponseEnum.getMessage();
    }

    public BaseException(IResponseEnum iResponseEnum, Object... args) {
        this(iResponseEnum.getCode(), StrUtil.format(iResponseEnum.getMessage(), args));
    }

    public BaseException(IResponseEnum iResponseEnum, Throwable e, Object... args) {
        this(iResponseEnum.getCode(), StrUtil.format(iResponseEnum.getMessage(), args), e);
    }

    public BaseException(IResponseEnum iResponseEnum, Throwable e) {
        super(iResponseEnum.getMessage(), e);
        this.code = iResponseEnum.getCode();
        this.message = iResponseEnum.getMessage();
    }


    public BaseException(int code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public BaseException(int code, String message, Throwable e) {
        super(message, e);
        this.message = message;
        this.code = code;
    }

}
