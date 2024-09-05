package com.example.easyexceltest.financeExcelTest;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author porter
 * create 2018/12/18
 * email porter.wang@ixiaoshuidi.com
 **/
@Data
public class QueryOrderModelVO {
    //数据内容
    private List<QueryOrderModel> queryOrderModels;
    //数据总数
    private Integer count;
    //当前页数
    private Integer page;
    //总页数
    private Integer pages;
    //收费通道
    private List<Map<String,String>> payChannels;
    //总金额
    private BigDecimal countAmount;
}
