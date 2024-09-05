package com.example.jzTest;

import java.lang.annotation.*;

/**
 * @author liqi
 * create  2021-09-16 13:16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public  @interface PrintlnLog {

    Class<?>[] exclude() default {};
}
