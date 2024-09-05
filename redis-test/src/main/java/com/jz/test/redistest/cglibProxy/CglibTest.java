package com.jz.test.redistest.cglibProxy;

import org.junit.Test;

/**
 * @author liqi
 * create  2024/9/5 11:22 上午
 */
public class CglibTest {
    @Test
    public void test01() {
        AliSmsService aliSmsService = (AliSmsService) CglibProxyFactory.getProxy(AliSmsService.class);
        aliSmsService.send("Hello World!");
    }
}
