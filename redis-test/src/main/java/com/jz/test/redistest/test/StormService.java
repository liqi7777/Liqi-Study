package com.jz.test.redistest.test;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author liqi
 * create  2021-08-17 14:29
 */
public interface StormService {

     RedisTemplate getRedisTemplate();
}
