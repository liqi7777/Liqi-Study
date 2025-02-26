package com.jz.test.redistest.redisDataStructure.redisGeo;

import com.alibaba.fastjson.JSONObject;
import com.jz.test.redistest.redisDataStructure.redisBitMap.Result;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liqi
 * create  2025/2/25 1:28 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisGeoServiceImplTest {

    @Autowired
    private RedisGeoServiceImpl redisGeoService;

    @Test
    public void testLoadShopData() {
        redisGeoService.loadShopData();
    }

    @Test
    public void testQueryShopByType() {
        Result result = redisGeoService.queryShopByType(1, 0, 120.149192d, 30.316078d);
        System.out.println(JSONObject.toJSONString(result));
    }


}