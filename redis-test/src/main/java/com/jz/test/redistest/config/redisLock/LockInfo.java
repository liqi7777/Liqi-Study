package com.jz.test.redistest.config.redisLock;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Sky
 * create  2020-08-13 10:37
 * email sky.li@ixiaoshuidi.com
 */
@Data
@AllArgsConstructor
public class LockInfo {
    /**
     * 锁名称
     */
    private String lockKey;

    /**
     * 锁值
     */
    private String lockValue;

    /**
     * 过期时间
     */
    private Long expire;

    /**
     * 获取锁超时时间
     */
    private Long acquireTimeout;

    /**
     * 获取锁次数
     */
    private int acquireCount = 0;
}
