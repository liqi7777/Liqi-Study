package com.example.jzTest;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @author liqi
 * create  2021-08-05 22:20
 */
@Data
public class StartEndTimePojo {
    private Long startTime;
    private Long endTime;

    private Integer i;

    public StartEndTimePojo() {
    }

    public StartEndTimePojo(Long startTime, Long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        List<Integer> sub = CollUtil.sub(list, 0, 0);
        List<Integer> sub1 = CollUtil.sub(list, 2, list.size());
        System.out.println("aha");
    }
}
