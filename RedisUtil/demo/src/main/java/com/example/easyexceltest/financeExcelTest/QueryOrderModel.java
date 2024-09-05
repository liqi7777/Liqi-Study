package com.example.easyexceltest.financeExcelTest;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author porter
 * create 2018/12/06
 * email porter.wang@ixiaoshuidi.com
 **/
@Data
public class QueryOrderModel {

    //交易时间
    private String createTime;
    //交易流水号
    private String tradeNo;
    //房源名称
    private String houseName;
    //费项类型
    private String feeType;
    //交易人
    private String userName;
    //账单周期
    private String billingCycle;
    //支付方式
    private String payWay;
    //交易状态
    private String tradeStatus;
    //业务类型
    private String bizType;
    //交易金额
    private BigDecimal amount;
    //费项信息
    private List<QueryOrderModel> feeItemType;
    //公寓名称
    private String apartmentName;
    //扩展字段
    private String extension;
    //手续费承担方:renter:租客承担 landlord:房东承担
    private String assumePerson;
    //收单通道
    private String channelDetail;
    //商户编号
    private Integer userId;
    //资金流向
    private String fundFlow;
    //手机号
    private String phone;
    //手续费 通道费
    private BigDecimal serviceCharge;
    //商户名称
    private String merchantName;

    private String payTime;
}
