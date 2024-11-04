package com.jz.test.redistest.redissonDelay;

import org.apache.poi.ss.formula.functions.T;

/**
 * @author liqi
 * create  2024/11/4 4:02 下午
 * @describe 延时任务接口
 */
public interface DelayJob<T> {
    /**
     * 延时任务接口
     *
     * @param deplayJobDTO
     */
    void execute(DeplayJobDTO<T> deplayJobDTO);
}
