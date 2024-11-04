package com.jz.test.redistest.redissonDelay;

import java.util.concurrent.TimeUnit;

/**
 * @author liqi
 * create  2024/11/4 4:04 下午
 * @describe 延时任务提交接口
 */
public interface DelayJobProducer {
    /**
     * 提交延时任务
     *
     * @param deplayJobDTO
     * @param delayTime
     * @param timeUnit
     */
    void submitDelayJob(DeplayJobDTO deplayJobDTO, Long delayTime, TimeUnit timeUnit);
}
