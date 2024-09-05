package com.example.preventDuplicateRepeat.redisLockMethod;

import java.lang.annotation.*;

/**
 * @author Sky
 * create 2020/03/18
 * email sky.li@ixiaoshuidi.com
 **/
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheParam {
    /**
     * 字段名称
     *
     * @return String
     */
    String name() default "";
}
