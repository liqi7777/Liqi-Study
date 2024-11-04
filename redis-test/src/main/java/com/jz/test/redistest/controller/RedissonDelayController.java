package com.jz.test.redistest.controller;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.jz.test.redistest.domain.ConfigInfo;
import com.jz.test.redistest.domain.PositionTransDTO;
import com.jz.test.redistest.domain.SysConfig;
import com.jz.test.redistest.mapper.ConfigInfoMapper;
import com.jz.test.redistest.mapper.SysConfigMapper;
import com.jz.test.redistest.redissonDelay.DelayJobProducer;
import com.jz.test.redistest.redissonDelay.DeplayJobDTO;
import com.jz.test.redistest.redissonDelay.ParamDTO;
import com.jz.test.redistest.redissonDelay.TestDelayJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/delayJob")
public class RedissonDelayController {
    @Autowired
    private DelayJobProducer delayJobProducer;

    @GetMapping("/producer")
    public String test01() {
        ParamDTO paramDTO = new ParamDTO();
        paramDTO.setName("lindj");
        paramDTO.setAge(66);

        DeplayJobDTO<ParamDTO> deplayJobDTO = new DeplayJobDTO<>();
        deplayJobDTO.setParam(paramDTO);
        deplayJobDTO.setClazz(TestDelayJob.class);

        delayJobProducer.submitDelayJob(deplayJobDTO, 30L, TimeUnit.SECONDS);
        log.info("延时任务提交完成");
        return "success";
    }

}
