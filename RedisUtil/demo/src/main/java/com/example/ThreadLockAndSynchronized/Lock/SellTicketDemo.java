package com.example.ThreadLockAndSynchronized.Lock;

/**
 * @author Sky
 * create 2020/04/16
 * email sky.li@ixiaoshuidi.com
 **/
public class SellTicketDemo {

    public static void main(String[] args) {

        // 创建资源对象
        SellTicket st = new SellTicket();

        // 创建窗口
        Thread thread1 = new Thread(st, "窗口一");
        Thread thread2 = new Thread(st, "窗口二");
        Thread thread3 = new Thread(st, "窗口三");

        // 开启线程
        thread1.start();
        thread2.start();
        thread3.start();
    }
}