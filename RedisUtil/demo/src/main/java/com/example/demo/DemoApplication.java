package com.example.demo;

import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.fastjson.JSONObject;
import com.example.util.RedisUtil;
import com.github.xjs.ezprofiler.annotation.EnableProfiler;
import com.jz.digital.dto.AivDTO;
import com.jz.digital.service.impl.DigitalTwinServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.SpringVersion;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @readme 本项目的所有接口采用json 协议，为运营平台提供后台接口。
 */
@SpringBootApplication
@ComponentScan("com.example")
@EnableRetry
//开启基于注解的缓存
@EnableCaching
//启动类加@EnableEncrypt注解，开启加解密自动配置  monkey-api-encrypt
//@EnableEncrypt
@EnableAsync
@EnableScheduling
@Slf4j
@EnableProfiler
public class DemoApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
//		applicationContext.publishEvent(new MyEvent("测试事件"));
        Object ymlListMapConfig = applicationContext.getBean("quartzConfig");
        System.out.println("haha");
        System.out.println("spring版本号：" + SpringVersion.getVersion());
        System.out.println(Runtime.
                getRuntime().availableProcessors());
    }

    @Autowired
    private RedisUtil redisUtil;


//    @Scheduled(fixedRateString = "200")
//    @Async
    public void test() throws Exception {
//        DigitalTwinServiceImpl digitalTwinServiceImpl = new DigitalTwinServiceImpl();
//        List<AivDTO> card = digitalTwinServiceImpl.getCard();
//        if(card.size()==0){
//            log.error("数据为空");
//        }
//        card=card.stream().filter(aivDTO -> aivDTO.getMachNo().equals("H415")).collect(Collectors.toList());
//        System.out.println(JSONObject.toJSONString(card));

    }


}
