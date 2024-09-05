package com.example.timeDiff.method01;

/**
 * @author Sky
 * create  2021-01-15 15:28
 * email sky.li@ixiaoshuidi.com
 */
public class TimeUtils {

    /**
     * @return 当前毫秒数
     */
    public static long nowMs() {
        return System.currentTimeMillis();
    }

    /**
     * 当前毫秒与起始毫秒差
     *
     * @param startMillis 开始纳秒数
     * @return 时间差
     */
    public static long diffMs(long startMillis) {
        return diffMs(startMillis, nowMs());
    }

    public static long diffMs(long start, long end) {
        return end - start;
    }
}
