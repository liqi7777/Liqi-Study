package com.example.redisListnerQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Sky
 * create 2019/06/24
 * email sky.li@ixiaoshuidi.com
 **/
@Component
public class RedisQueueListener implements CommandLineRunner {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void consumeQueue() {

    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("xiangmuqidong");
//        while (true) {
//            String consumeQueue_test = stringRedisTemplate.opsForList().rightPop("consumeQueue_test", 0, TimeUnit.SECONDS);
//            System.out.println("redis事件队列监听，结果：" + consumeQueue_test);
//        }
    }
}
