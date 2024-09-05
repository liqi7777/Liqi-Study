package com.jz.test.redistest.exception;

import cn.hutool.core.util.StrUtil;
import org.springframework.util.StreamUtils;

import java.text.MessageFormat;

/**
 * @author liqi
 * create  2021-10-20 13:43
 */
public interface BusinessExceptionAssert extends IResponseEnum, Assert {


    @Override
    default BaseException newException(Object... args) {
        return new BusinessException(this, args);
    }

}
