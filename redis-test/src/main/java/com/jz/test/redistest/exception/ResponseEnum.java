package com.jz.test.redistest.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author liqi
 * create  2021-10-20 13:53
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum implements BusinessExceptionAssert {
    /**
     * Bad licence type
     */
    BAD_LICENCE_TYPE(7001, "Bad licence type.");

    private final int code;

    private final String message;

}
