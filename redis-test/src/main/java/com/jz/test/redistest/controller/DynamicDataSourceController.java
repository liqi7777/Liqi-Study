package com.jz.test.redistest.controller;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.jz.test.redistest.domain.ConfigInfo;
import com.jz.test.redistest.domain.PositionTransDTO;
import com.jz.test.redistest.domain.SysConfig;
import com.jz.test.redistest.mapper.ConfigInfoMapper;
import com.jz.test.redistest.mapper.SysConfigMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author liqi
 * create  2022/6/27 12:16 下午
 */
@Slf4j
@RestController
@RequestMapping("/dynamicDataSource")
public class DynamicDataSourceController {

    @Autowired
    private SysConfigMapper sysConfigMapper;

    @Autowired
    private ConfigInfoMapper configInfoMapper;

    @Autowired
    @Qualifier("redisTemplate01")
    private RedisTemplate<String, Object> redisTemplate01;


    @GetMapping("/master")
    public SysConfig testMaster() {
        return sysConfigMapper.selectByPrimaryKey(1);
    }

    @GetMapping("/slave")
    public ConfigInfo testSlave() {
        return configInfoMapper.selectByPrimaryKey(1L);
    }


    @GetMapping("/test01")
    public String test01() {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("haha");
                scheduledExecutorService.shutdown();
            }
        }, 30, 1, TimeUnit.SECONDS);
        System.out.println("ahhahehhe");
        return "OK";
    }


    @GetMapping("/test02")
    public String test02() {
//        Object o = redisTemplate01.opsForList().rightPop("IECS_SIMPLE_CALCULATE:TRANS_QUEUE:H386");
//        if (Objects.nonNull(o)) {
//            String o1 = (String) o;
//            PositionTransDTO positionTransDTO = JSONObject.parseObject(o1, PositionTransDTO.class);
//            log.info("positionTransDTO:{}",JSONObject.toJSONString(positionTransDTO));
//        }

        Set<String> set = redisTemplate01.keys("IECS_SIMPLE_CALCULATE:TRANS_QUEUE:*");
        if (CollUtil.isNotEmpty(set)) {
            List<PositionTransDTO> positionTransDTOS = new ArrayList<>();
            for (String s : set) {
                Object o = redisTemplate01.opsForList().rightPop(s);
                if (Objects.nonNull(o)) {
                    String o1 = (String) o;
                    PositionTransDTO positionTransDTO = JSONObject.parseObject(o1, PositionTransDTO.class);
                    positionTransDTOS.add(positionTransDTO);
                }
            }
        }
        return "OK";
    }
}
