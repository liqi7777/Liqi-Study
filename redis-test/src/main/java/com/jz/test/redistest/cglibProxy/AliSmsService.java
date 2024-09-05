package com.jz.test.redistest.cglibProxy;

/**
 * @author liqi
 * create  2024/9/5 11:20 上午
 */
public class AliSmsService {
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}
