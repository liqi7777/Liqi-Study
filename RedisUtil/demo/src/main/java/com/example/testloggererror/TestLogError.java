package com.example.testloggererror;

import cn.hutool.core.exceptions.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author Sky
 * create 2019/12/27
 * email sky.li@ixiaoshuidi.com
 **/
@Slf4j
public class TestLogError {

    public static void main(String[] args) {
        float f = 0.5f;
        System.out.println(f);
        int i = 5;
        try {
            // 模拟空指针异常
            Integer nullInt = Integer.valueOf(null);
            int[] array = {1, 2, 3, 4, 5};
            int outBoundInt = array[5];
        } catch (Exception e) {

            // 使用字符串拼接
            log.error("使用 + 号连接直接输出 e : " + e);
            log.error("使用 + 号连接直接输出 e.getMessage() : " + e.getMessage());
            log.error("使用 + 号连接直接输出 e.toString() : " + e.toString());
            // 使用逗号分隔，调用两个参数的error方法
            log.error("使用 , 号 使第二个参数作为Throwable : ", e);
            // 尝试使用分隔符,第二个参数为Throwable,会发现分隔符没有起作用，第二个参数的不同据，调用不同的重载方法
            log.error("第二个参数为Throwable，使用分隔符打印 {} : {} ", 1, e);
            // 尝试使用分隔符，第二个参数为Object,会发现分隔符起作用了，根据第二个参数的不同类型，调用不同的重载方法
            log.error("第二个参数为Object，使用分隔符打印 {} ", e);

            log.error("ExceptionUtil:{}", ExceptionUtil.stacktraceToString(e));
        }

        AtomicInteger SEQ = new AtomicInteger(1000);
        int andSet = SEQ.getAndSet(1000);
        System.out.println(andSet);
        int andIncrement = SEQ.getAndIncrement();
        System.out.println(andIncrement);
    }

}