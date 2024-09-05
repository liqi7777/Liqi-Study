package com.example.ScheduledAsyncTest;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Sky
 * create 2020/06/19
 * email sky.li@ixiaoshuidi.com
 **/

//@Component
//@Configuration
//@EnableScheduling
public class ScheduledAsyncTest {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//    @Scheduled(fixedDelay = 1000)
//    public void executeUpdateYqTask() {
//        System.out.println(Thread.currentThread().getName() + " >>> task one " + format.format(new Date()));
//    }
//
//    @Scheduled(fixedDelay = 1000)
//    public void executeRepaymentTask() throws InterruptedException {
//        System.out.println(Thread.currentThread().getName() + " >>> task two " + format.format(new Date()));
//        Thread.sleep(5000);
//    }


    @Scheduled(cron = "0 45 13 * * ?")
    public void executeUpdateYqTask01() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " >>> task one " + format.format(new Date()));
        Thread.sleep(300000);
        System.out.println("task one 执行完成");
    }


    @Scheduled(cron = "0 46 13 * * ?")
    public void executeUpdateYqTask02() {
        System.out.println(Thread.currentThread().getName() + " >>> task two " + format.format(new Date()));
    }
}
