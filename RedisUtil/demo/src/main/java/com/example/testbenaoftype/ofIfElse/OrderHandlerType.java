package com.example.testbenaoftype.ofIfElse;

import java.lang.annotation.*;

/**
 * @author liqi
 * create  2021-09-01 15:53
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface OrderHandlerType {
    int value() default 0;
}
