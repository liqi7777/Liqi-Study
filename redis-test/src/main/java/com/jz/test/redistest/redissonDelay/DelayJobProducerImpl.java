package com.jz.test.redistest.redissonDelay;

import org.redisson.api.RDelayedQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author liqi
 * create  2024/11/4 4:05 下午
 * @describe 延时任务提交类
 */
@Component
public class DelayJobProducerImpl implements DelayJobProducer {

    @Resource(name = "rDelayedQueue")
    private RDelayedQueue delayedQueue;

    @Override
    public void submitDelayJob(DeplayJobDTO deplayJobDTO, Long delayTime, TimeUnit timeUnit) {
        delayedQueue.offer(deplayJobDTO, delayTime, timeUnit);
    }
}
