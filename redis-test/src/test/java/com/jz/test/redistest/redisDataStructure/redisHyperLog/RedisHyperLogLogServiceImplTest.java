package com.jz.test.redistest.redisDataStructure.redisHyperLog;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liqi
 * create  2025/2/25 11:08 上午
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisHyperLogLogServiceImplTest {

    @Autowired
    private RedisHyperLogLogServiceImpl redisHyperLogLogService;

    @Test
    public void testHyperLogLogTest() {
        redisHyperLogLogService.hyperLogLogTest();
    }
}