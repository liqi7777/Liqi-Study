package com.jz.test.redistest.test;

/**
 * @author liqi
 * create  2024/1/10 11:16 上午
 */
import java.time.Instant;

public class GetCurrentTimestamp {
    public static void main(String[] args) {
        // 获取当前时间戳
        Instant currentTimestamp = Instant.now();

        // 输出当前时间戳
        System.out.println("当前时间戳：" + currentTimestamp);

        // 获取时间戳的毫秒数
        long milliseconds = currentTimestamp.toEpochMilli();
        System.out.println("当前时间戳（毫秒）：" + milliseconds);

        // 获取时间戳的秒数和纳秒数
        long seconds = currentTimestamp.getEpochSecond();
        int nano = currentTimestamp.getNano();
        System.out.println("秒数：" + seconds);
        System.out.println("纳秒数：" + nano);
    }
}
