package com.example.timeDiff.method01;

import java.util.concurrent.TimeUnit;

/**
 * @author Sky
 * create  2021-01-15 15:23
 * email sky.li@ixiaoshuidi.com
 */
public class TimeDiffTest {

    public static void main(String[] args) throws Exception {

        final long startMs = TimeUtils.nowMs();

        TimeUnit.SECONDS.sleep(5); // 模拟业务代码

        System.out.println("timeCost: " + TimeUtils.diffMs(startMs));
    }

}
