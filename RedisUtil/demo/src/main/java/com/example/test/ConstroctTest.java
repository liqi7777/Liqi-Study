package com.example.test;

import java.math.BigDecimal;

/**
 * @author Sky
 * create 2019/02/21
 * email sky.li@ixiaoshuidi.com
 **/
public class ConstroctTest {
    private static ConstroctTest test = new ConstroctTest();
    //静态变量sta1    未赋予初始值
    public static int sta1;
    //静态变量sta1    赋予初始值20
    public static int sta2 = 20;
    //构造方法中对于静态变量赋值
    private ConstroctTest() {
        sta1 ++ ;
        sta2 ++ ;
    }
    public static void main(String[] args) {
//        System.out.println(ConstroctTest.sta1);
//        System.out.println(ConstroctTest.sta2);
//        Integer i=1;
//        String a="1";
//        System.out.println(a.equals(String.valueOf(i)));
//        System.out.println(i.toString().equals(a));
//        System.out.println(new BigDecimal("").compareTo(new BigDecimal("5")));
        System.out.println(new BigDecimal("-5.2").compareTo(new BigDecimal("5")));
    }
}
