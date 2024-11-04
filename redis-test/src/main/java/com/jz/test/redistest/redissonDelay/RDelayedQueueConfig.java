package com.jz.test.redistest.redissonDelay;

import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liqi
 * create  2024/11/4 4:09 下午
 */
@Configuration
public class RDelayedQueueConfig {

    @Bean("rDelayedQueue")
    public RDelayedQueue rDelayedQueue(RedissonClient redissonClient) {
        RBlockingQueue blockingQueue = redissonClient.getBlockingQueue(DelayJobTimmer.deleyQueueName);
        return redissonClient.getDelayedQueue(blockingQueue);
    }
}
