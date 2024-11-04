package com.jz.test.redistest.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liqi
 * create  2021-08-17 14:29
 */
@Service
//@Scope(value = "prototype")
public class StormServiceImpl implements StormService {

    @Autowired
    // @Qualifier("redisTemplate01")
//    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public RedisTemplate getRedisTemplate() {
        System.out.println("redisTemplate:" + redisTemplate);
        return redisTemplate;
    }
}
