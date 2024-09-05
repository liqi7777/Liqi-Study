package com.example.easyexceltest.financeExcelTest;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author porter
 * create 2018/12/06
 * email porter.wang@ixiaoshuidi.com
 **/
@Data
public class QueryConditionModel implements Serializable {
    private static final long serialVersionUID = 1409585292972743405L;

    //用户id
    private Integer userId;
    //开始时间
    private String startTime;
    //结束时间
    private String endTime;
    //支付方式
    private String payWay;
    //业务类型
    private String bizType;
    //房源/房间名称   交易人信息
    private String data;
    //交易状态
    private String tradeStatus;
    //费用类型 1交易额 2 机构手续费 3平台手续费4结算手续费
    private Integer feeType;

    private Integer fee;
    //跳转页数
    private Integer page;
    //每页数据长度
    private Integer pageSize;
    //交易类型
    private String settlementType;

    private String instCode;
    //资金流向
    private String fundFlow;
    //用户验证
    private String token;
    //交易类型
    private String transactionType;
    //用户类型
    private int userType;
    //商户名称/提现流水号
    private String keyword;
    //用户id集合
    private List<Long> userIds;
}
