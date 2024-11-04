package com.jz.test.redistest.redissonDelay;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liqi
 * create  2024/11/4 4:06 下午
 * @describe 延时任务执行类
 */
@Slf4j
@Component
public class DelayJobTimmer {
    public static final String deleyQueueName = "redissonDelay_Test";
    @Autowired
    private RedissonClient client;
    @Autowired
    private ApplicationContext context;

    // todo 可以替换成Spring管理的动态线程池！
    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    @PostConstruct
    public void startJobTimer() {
        RBlockingQueue<DeplayJobDTO> blockingQueue =
                client.getBlockingQueue(deleyQueueName);
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        // 从队列中获取任务
                        DeplayJobDTO jobDTO = blockingQueue.take();
                        executorService.execute(new DelayJobTask(context, jobDTO));
                    } catch (Exception e) {
                        log.error("延时任务执行失败{}", e);
                        //todo 可以保存数据库
                    }
                }
            }
        }.start();
    }

    class DelayJobTask implements Runnable {
        private ApplicationContext context;
        private DeplayJobDTO deplayJobDTO;

        public DelayJobTask(ApplicationContext context, DeplayJobDTO deplayJobDTO) {
            this.context = context;
            this.deplayJobDTO = deplayJobDTO;
        }

        @Override
        public void run() {
            DelayJob delayJob = (DelayJob) context.getBean(deplayJobDTO.getClazz());
            delayJob.execute(deplayJobDTO);
        }
    }
}
