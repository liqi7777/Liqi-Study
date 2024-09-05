package com.example.timeDiff.method03;

import com.alibaba.fastjson.JSON;
import com.example.timeDiff.method02.TraceWatch;

import java.util.concurrent.TimeUnit;

/**
 * @author Sky
 * create  2021-01-15 17:37
 * email sky.li@ixiaoshuidi.com
 */
public class TraceHolderTest {
    public static void main(String[] args) {

        TraceWatch traceWatch = new TraceWatch();

        TraceHolder.run(traceWatch, "function1", i -> {
            try {
                TimeUnit.SECONDS.sleep(1); // 模拟业务代码
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        String result = TraceHolder.run(traceWatch, "function2", () -> {
            try {
                TimeUnit.SECONDS.sleep(1); // 模拟业务代码
                return "YES";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "NO";
            }
        });

        TraceHolder.run(traceWatch, "function1", i -> {
            try {
                TimeUnit.SECONDS.sleep(1); // 模拟业务代码
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(JSON.toJSONString(traceWatch.getTaskMap()));
    }

}

