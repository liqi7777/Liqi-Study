package com.jz.test.redistest.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liqi
 * create  2024/12/30 9:29 上午
 */
@Service
public class OrderedInsertService {

    // 模拟数据库插入方法
    private void insertIntoDatabase(String data) {
        System.out.println("Inserting: " + data);
        // 实际的数据库插入逻辑在这里实现
    }

    public void orderedInsert(List<String> dataList) throws InterruptedException {
        int threadCount = 4; // 使用的线程数
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        // 使用有序的 ConcurrentSkipListMap 存储插入结果
        ConcurrentSkipListMap<Integer, String> orderedResults = new ConcurrentSkipListMap<>();
        CountDownLatch latch = new CountDownLatch(dataList.size()); // 计数器，确保所有任务完成

        for (int i = 0; i < dataList.size(); i++) {
            final int index = i;
            final String data = dataList.get(i);

            executor.submit(() -> {
                try {
                    // 模拟耗时的插入操作
                    insertIntoDatabase(data);
                    // 将结果放入有序 Map 中
                    orderedResults.put(index, data);
                } finally {
                    latch.countDown(); // 任务完成，计数器减1
                }
            });
        }

        latch.await(); // 等待所有任务完成
        executor.shutdown();

        // 确保按照顺序插入数据库
        for (Map.Entry<Integer, String> entry : orderedResults.entrySet()) {
            insertIntoDatabase(entry.getValue()); // 按照 Map 的顺序最终插入
        }
    }
}
