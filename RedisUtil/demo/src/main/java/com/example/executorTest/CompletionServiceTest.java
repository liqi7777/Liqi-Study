package com.example.executorTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Sky
 * create 2020/02/15
 * email sky.li@ixiaoshuidi.com
 **/
public class CompletionServiceTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //同步执行多个任务，一个个提交任务 并且主线程在等待执行结果
//        method1();

        //同步执行多个任务，一次性提交多个任务，并待且主线程同步等这些任务
        method2();


//        method3();


    }

    private static void method3() throws ExecutionException, InterruptedException {
        ExecutorService executeService = Executors.newCachedThreadPool();
        Future<String> helloFuture = executeService.submit(() -> {
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });
        String s = helloFuture.get();
        helloFuture.get();
        System.out.println(s);
        System.out.println("是否等待完成");
    }

    private static void method2() {
        // 方法二
        ExecutorService executeService = Executors.newCachedThreadPool();
        List<CallableDemo> taskList = new ArrayList<CallableDemo>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            taskList.add(new CallableDemo("micro" + i, 10));
        }
        try {
            System.out.println("主线程发起异步任务请求");
            List<Future<String>> resultList = executeService.invokeAll(taskList);
            // 这里会阻塞等待resultList获取到所有异步执行的结果才会执行
            for (Future<String> future : resultList) {
                System.out.println(future.get());
            }
            // 主线程假装很忙执行8秒钟
//            Thread.sleep(8);
            long endTime = System.currentTimeMillis();
            System.out.println("耗时 : " + (endTime - startTime) / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void method1() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletionService<String> completionService = new ExecutorCompletionService(executorService);
        // 十个
        long startTime = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < 10; i++) {
            count++;
            CallableDemo callableDemo = new CallableDemo("micro" + i, 10);
            completionService.submit(callableDemo);
        }
        System.out.println("提交完任务，主线程空闲了, 可以去做一些事情。");
        // 假装做了8秒种其他事情
        try {
            Thread.sleep(8000);
            System.out.println("主线程做完了，等待结果");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            // 做完事情要结果
            for (int i = 0; i < count; i++) {
                Future<String> result = completionService.take();
                System.out.println(result.get());
            }
            long endTime = System.currentTimeMillis();
            System.out.println("耗时 : " + (endTime - startTime) / 1000);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


}
