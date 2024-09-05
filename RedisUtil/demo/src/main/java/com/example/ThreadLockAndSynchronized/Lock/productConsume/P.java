package com.example.ThreadLockAndSynchronized.Lock.productConsume;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Sky
 * create 2020/04/16
 * email sky.li@ixiaoshuidi.com
 **/
public class P {
    private static final SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss-SSS");

    public static void out(Object obj) {
        System.out.println(format.format(new Date()) + " " + obj);
    }

    public static void outnl(Object obj) {
        System.out.printf(format.format(new Date()) + " " + obj);
    }
}
