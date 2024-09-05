package com.example.executorTest;

import java.util.concurrent.Callable;

/**
 * @author Sky
 * create 2020/02/15
 * email sky.li@ixiaoshuidi.com
 **/
public class CallableDemo implements Callable<String> {

    private String name;

    private Integer sleepTimes;

    public CallableDemo(String name, Integer sleepTimes) {
        this.name = name;
        this.sleepTimes = sleepTimes;
    }

    @Override
    public String call() throws Exception {
        // 假设这是一个比较耗时的操作
        Thread.sleep(sleepTimes * 1000);
        return "this is content : hello " + this.name;
    }
}
