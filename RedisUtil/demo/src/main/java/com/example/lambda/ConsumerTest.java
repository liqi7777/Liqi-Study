package com.example.lambda;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author Sky
 * create 2019/09/09
 * email sky.li@ixiaoshuidi.com
 **/
//代表了 接受一个输入参数并且无返回的操作
public class ConsumerTest {

    public static void modifyTheValue3(int value, Consumer<Integer> consumer) {
        consumer.accept(value);
    }

    public static void modifyTheValue4(int value1, int value2, BiConsumer<Integer, Integer> consumer) {
        consumer.accept(value1, value2);
    }

    public static void main(String[] args) {
        // (x) -> System.out.println(x * 2)接受一个输入参数x
        // 直接输出，并没有返回结果
        // 所以该lambda表达式可以实现Consumer接口
        modifyTheValue3(3, (x) -> System.out.println(x * 2));
        modifyTheValue4(3, 4, (k, v) -> System.out.println(k + "," + v));
    }
}
