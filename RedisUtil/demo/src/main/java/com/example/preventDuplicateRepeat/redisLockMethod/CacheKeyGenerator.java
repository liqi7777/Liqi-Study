package com.example.preventDuplicateRepeat.redisLockMethod;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author Sky
 * create 2020/03/18
 * email sky.li@ixiaoshuidi.com
 **/
public interface CacheKeyGenerator {
    /**
     * 获取AOP参数,生成指定缓存Key
     *
     * @param pjp PJP
     * @return 缓存KEY
     */
    String getLockKey(ProceedingJoinPoint pjp);
}
