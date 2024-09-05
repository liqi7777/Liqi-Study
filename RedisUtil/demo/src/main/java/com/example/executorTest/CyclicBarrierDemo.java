package com.example.executorTest;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 链接地址：https://www.cnblogs.com/twoheads/p/9555867.html
 *
 * @author Sky
 * create  2020-11-10 13:43
 * email sky.li@ixiaoshuidi.com
 */
public class CyclicBarrierDemo implements Runnable {
    /**
     * 创建拦截线程数4，线程到达barrier后执行 barrierAction
     */
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(4, this);

    private Executor executor = Executors.newFixedThreadPool(4);

    private ConcurrentMap<String, Integer> count = Maps.<String, Integer>newConcurrentMap();

    @Override
    public void run() {
        int result = 0;
        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            result += entry.getValue();
        }
        count.put(Thread.currentThread().getName() + "_result", result);
        System.out.println(JSONObject.toJSONString(count));
    }

    public void calculate() {
        for (int i = 0; i < 4; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    count.put(Thread.currentThread().getName(), 10);
                    try {
                        System.out.println(Thread.currentThread().getName() + " is waiting!");
                        cyclicBarrier.await();
                        System.out.println(Thread.currentThread().getName() + " is working!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void main(String[] args) {
        new CyclicBarrierDemo().calculate();
    }
}
