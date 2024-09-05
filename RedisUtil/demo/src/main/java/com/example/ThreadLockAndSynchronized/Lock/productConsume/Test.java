package com.example.ThreadLockAndSynchronized.Lock.productConsume;

/**
 * @author Sky
 * create 2020/04/16
 * email sky.li@ixiaoshuidi.com
 **/
public class Test {
    public static void main(String[] args) {

        @SuppressWarnings("unchecked")
        SuppliersAndConsumerPlus<String> consumer = new SuppliersAndConsumerPlus<>(
                String::getBytes,
                () -> "a",
                () -> "b",
                () -> "c",
                () -> "d");

        consumer.start();

    }
}
