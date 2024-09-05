package com.example.test;



/**
 * @author Sky
 * create 2018/08/01
 * email sky.li@ixiaoshuidi.com
 * saas微信下单请求数据封装
 **/

public class SaasWxPayOrderReqVO {
    //手续费用
    private Double payCharge;
    //入账金额
    private Double enterPayMoney;
    //总金额
    private Double totalMoney;
    //主账号(房东)id
    private String landlordId;
    //房间地址
    private String address;
    //用户微信token
    private String token;
    //支付类型
    private Integer feeType;
    //用户在子商户appid下的唯一标识
    private String openId;
    //pay_orders_id
    private String payOrderId;
    //支付渠道
    private String payChannelName;
    //第三方和水滴的手续费费率
    private Double costCharge;
    //费用承担方（renter:租客承担，landlord:公寓房承担）
    private String payAssume;
    //业务类型 (I:入款 O:出款 B：退款)
    private String bizType;
    //扩展字段
    private String extension;

    public Double getPayCharge() {
        return payCharge;
    }

    public void setPayCharge(Double payCharge) {
        this.payCharge = payCharge;
    }

    public Double getEnterPayMoney() {
        return enterPayMoney;
    }

    public void setEnterPayMoney(Double enterPayMoney) {
        this.enterPayMoney = enterPayMoney;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getLandlordId() {
        return landlordId;
    }

    public void setLandlordId(String landlordId) {
        this.landlordId = landlordId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getFeeType() {
        return feeType;
    }

    public void setFeeType(Integer feeType) {
        this.feeType = feeType;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId;
    }

    public String getPayChannelName() {
        return payChannelName;
    }

    public void setPayChannelName(String payChannelName) {
        this.payChannelName = payChannelName;
    }

    public Double getCostCharge() {
        return costCharge;
    }

    public void setCostCharge(Double costCharge) {
        this.costCharge = costCharge;
    }

    public String getPayAssume() {
        return payAssume;
    }

    public void setPayAssume(String payAssume) {
        this.payAssume = payAssume;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
