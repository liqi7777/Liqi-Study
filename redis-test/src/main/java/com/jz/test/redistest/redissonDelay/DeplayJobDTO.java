package com.jz.test.redistest.redissonDelay;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liqi
 * create  2024/11/4 4:03 下午
 * @describe 延时任务参数
 */
@Data
@NoArgsConstructor
public class DeplayJobDTO<T> implements Serializable {
    /**
     * 延时任务执行所需参数
     */
    private T param;
    /**
     * 延时任务执行类
     */
    private Class clazz;
}
