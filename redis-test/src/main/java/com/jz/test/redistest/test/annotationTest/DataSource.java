package com.jz.test.redistest.test.annotationTest;

import java.lang.annotation.*;

/**
 * 多数据源注解
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@OpInstrAOP
public @interface DataSource {
    String name() default "";
}
