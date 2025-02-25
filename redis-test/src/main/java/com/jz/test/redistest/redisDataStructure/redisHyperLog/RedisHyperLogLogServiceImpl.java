package com.jz.test.redistest.redisDataStructure.redisHyperLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author liqi
 * create  2025/2/25 10:44 上午
 */
@Service
public class RedisHyperLogLogServiceImpl {

    @Autowired
    private RedisTemplate redisTemplate;


    void hyperLogLogTest() {
        String[] values = new String[1000];
        int j = 0;
        for (int i = 0; i < 1000000; i++) {
            j = i % 1000;
            values[j] = "user_" + i;
            if (j == 999) {
                // 发送到Redis
                redisTemplate.opsForHyperLogLog().add("hl2", values);
            }
        }
        // 统计数量
        Long count = redisTemplate.opsForHyperLogLog().size("hl2");
        System.out.println("count = " + count);
    }
}
