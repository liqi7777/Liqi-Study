package com.example.executorTest;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Sky
 * create  2020-12-15 09:06
 * email sky.li@ixiaoshuidi.com
 */
public class TimerDemo {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            //最大重试次数
            int MAX_RETRY = 5;

            @Override
            public void run() {
                MAX_RETRY--;
                if (MAX_RETRY == 0) {
                    timer.cancel();
                } else {
                    System.out.println("timer" + MAX_RETRY);
                }
            }
        }, 2000, 5000);


        System.out.println("final");

    }
}
