package com.jz.test.redistest.controller;

import com.jz.test.redistest.service.OrderedInsertService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author liqi
 * create  2024/12/30 9:27 上午
 */
@RestController
@RequestMapping("/api/insert")
@Api("多线程顺序处理业务，并按照顺序写入！")
public class OrderedInsertController {
    private final OrderedInsertService orderedInsertService;

    public OrderedInsertController(OrderedInsertService orderedInsertService) {
        this.orderedInsertService = orderedInsertService;
    }

    @ApiOperation("多线程顺序处理业务，并按照顺序写入！")
    @PostMapping("/insertData")
    public String insertData() {
        List<String> dataList = Arrays.asList("data1", "data2", "data3", "data4", "data5", "data6");
        try {
            orderedInsertService.orderedInsert(dataList);
            return "Data inserted successfully!";
        } catch (InterruptedException e) {
            return "Failed to insert data!";
        }
    }
}
