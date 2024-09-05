package com.example.test;

/**
 * @author Sky
 * create 2019/02/21
 * email sky.li@ixiaoshuidi.com
 **/
public class ConstroctTest02 {
    //静态变量sta1    未赋予初始值
    public static int sta1;
    //静态变量sta1    赋予初始值20
    public static int sta2 = 20;
    private static ConstroctTest02 test = new ConstroctTest02();

    //构造方法中对于静态变量赋值
    private ConstroctTest02() {
        sta1++;
        sta2++;
    }

    public static void main(String[] args) {
        System.out.println(ConstroctTest.sta1);
        System.out.println(ConstroctTest.sta2);
    }
}
