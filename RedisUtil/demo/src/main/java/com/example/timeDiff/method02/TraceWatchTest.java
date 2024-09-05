package com.example.timeDiff.method02;

import com.alibaba.fastjson.JSON;

import java.util.concurrent.TimeUnit;

/**
 * @author Sky
 * create  2021-01-15 16:30
 * email sky.li@ixiaoshuidi.com
 */
public class TraceWatchTest {

    public static void main(String[] args) throws Exception {
        TraceWatch traceWatch = new TraceWatch();

        traceWatch.start("function1");
        TimeUnit.SECONDS.sleep(1); // 模拟业务代码
        traceWatch.stop();

        traceWatch.start("function2");
        TimeUnit.SECONDS.sleep(1); // 模拟业务代码
        traceWatch.stop();

        traceWatch.record("function1", 1); // 直接记录耗时

        System.out.println(JSON.toJSONString(traceWatch.getTaskMap()));
    }

}
