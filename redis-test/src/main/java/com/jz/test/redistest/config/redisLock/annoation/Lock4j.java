package com.jz.test.redistest.config.redisLock.annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Sky
 * create  2020-08-13 10:10
 * email sky.li@ixiaoshuidi.com
 */
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Lock4j {
    /**
     * KEY 默认包名+方法名
     */
    String[] keys() default "";

    /**
     * 过期时间 单位：毫秒
     * <pre>
     *     过期时间一定是要长于业务的执行时间.
     * </pre>
     */
    long expire() default 60000;

    /**
     * 获取锁超时时间 单位：毫秒
     * <pre>
     *     结合业务,建议该时间不宜设置过长,特别在并发高的情况下.
     * </pre>
     */
    long timeout() default 100;

    /**
     * 获取锁失败返回信息
     *
     * @return
     */
    String message() default "请勿重复操作";
}
