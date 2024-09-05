package com.jz.test.redistest.asyncTool;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUnit;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liqi
 * create  2023/11/6 2:28 下午
 */
@Slf4j
public class Test01 {

    private ExecutorService completableFutureThreadPool = Executors.newFixedThreadPool(30, new ThreadFactoryBuilder().setNameFormat("completableFutureThreadPool-%d").build());

    @Data
    class Test01Iner {
        private LocalDateTime one;
    }

    @Data
    class Test02Iner {
        private Long one;
    }


    @Test
    public void test01() {
        Test01Iner iner = new Test01Iner();
        Test02Iner iner1  = new Test02Iner();
        iner1.setOne(System.currentTimeMillis());
        BeanUtil.copyProperties(iner1,iner);
    }


    @Test
    public void test02() {
        String phone ="138";
        String s1 = null;
        String s2 = null;
        String s = phone + "," + s1 + "," + s2;
        System.out.println(phone + "," + s1 + "," + s2);
        String[] split = s.split(",");
        System.out.println(split);


        System.out.println(DateUnit.MINUTE.getMillis());


        String s5 = "http://192.168.1.200:8173/process";
        System.out.println(s5.contains("192.168"));

        String s6="{\"pid\":410,\"machNo\":\"H857\",\"machType\":\"AIV\",\"resultHost\":\"192.168.1.24\",\"resultPort\":\"8080\",\"msgType\":\"1\"}";
        System.out.println(s6.contains("\"pid\":410"));
    }

    @Test
    public void test03() throws InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return null;
        },completableFutureThreadPool);
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return null;
        },completableFutureThreadPool);
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return null;
        },completableFutureThreadPool);
        Thread.sleep(3000);
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return null;
        },completableFutureThreadPool);
    }

}
