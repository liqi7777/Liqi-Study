package com.example.redis;

import cn.hutool.core.thread.ThreadUtil;
import com.example.test.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @author Sky
 * create 2019/06/17
 * email sky.li@ixiaoshuidi.com
 **/
@RestController
@RequestMapping("redis")
public class RedisPublishController {
    private int m = 0;

    @Autowired
    @Qualifier("stringRedisTemplate")
    private StringRedisTemplate template;

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping(value = "publish", method = {RequestMethod.POST, RequestMethod.GET})
    public String publish() {
        int index = m;
        for (int i = m; i < index + 10; i++) {
            template.convertAndSend("mytopic", "这是我发第" + i + "条的消息啊");
        }
        return "结束";
    }

    @RequestMapping(value = "testRedisQueueListener", method = {RequestMethod.POST, RequestMethod.GET})
    public String testRedisQueueListener() {
        int index = 0;
        for (int i = 0; i < 10; i++) {
            template.opsForList().leftPush("consumeQueue_test", String.valueOf(i));
        }
        return "结束";
    }


    @GetMapping("setifabsent")
    public String setifabsent() throws Exception {
//        ExecutorService executorService = ThreadUtil.newExecutor(10);
//        List<Callable<Boolean>> taskList = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            taskList.add(() -> template.opsForValue().setIfAbsent("liqi", "haha"));
//        }
//        List<Future<Boolean>> futures = executorService.invokeAll(taskList);
//        for (Future<Boolean> future : futures) {
//            System.out.println(future.get());
//        }
//        Person liqi = new Person(1, "liqi");
//
//        System.out.println(System.currentTimeMillis());
//        System.out.println(redisTemplate.opsForValue().setIfAbsent("liqi-1",liqi));

//        UwbAndRtgDTO uwbAndRtgDTO=new UwbAndRtgDTO(true,false);
//        redisTemplate.opsForValue().set("liqi-2",uwbAndRtgDTO);

        UwbAndRtgDTO uwbAndRtgDTO = (UwbAndRtgDTO) redisTemplate.opsForValue().get("liqi-2");
        if (uwbAndRtgDTO.getUwb() && !uwbAndRtgDTO.getRtg()) {
            uwbAndRtgDTO.setRtg(true);
            UwbAndRtgDTO oldUwbAndRtg = (UwbAndRtgDTO) redisTemplate.opsForValue().getAndSet("liqi-2", uwbAndRtgDTO);
            System.out.println(oldUwbAndRtg);
        }
        return "ok";
    }

}