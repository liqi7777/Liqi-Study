package com.jz.test.redistest.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Builder;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author baomidou
 * @since 2023-04-19
 */
@TableName("IECS_DEVICE_BEHAVIOR_LOG")
@KeySequence(value = "IECS_DEVICE_BEHAVIOR_LOG_SEQ", dbType = DbType.ORACLE)
@SuperBuilder
public class IecsDeviceBehaviorLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 设备行为日志主键
     */
    @TableId(type = IdType.INPUT)
    private Long iecsDeviceBehaviorId;

    /**
     * 设备类型
     */
    private String iecsDeviceType;

    /**
     * 设备编号
     */
    private String iecsDeviceNo;

    /**
     * 行为类型
     */
    private String iecsDeviceBehaviorType;

    /**
     * 内容详情
     */
    private String iecsContentDetail;

    /**
     * 数据来源
     */
    private String iecsSource;

    /**
     * 操作用户
     */
    private String iecsOperateUser;

    /**
     * 记录生成时间
     */
    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime oisInsDt;

    /**
     * 行为发生时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime oisDeviceBehaviorTime;

    public Long getIecsDeviceBehaviorId() {
        return iecsDeviceBehaviorId;
    }

    public void setIecsDeviceBehaviorId(Long iecsDeviceBehaviorId) {
        this.iecsDeviceBehaviorId = iecsDeviceBehaviorId;
    }

    public String getIecsDeviceType() {
        return iecsDeviceType;
    }

    public void setIecsDeviceType(String iecsDeviceType) {
        this.iecsDeviceType = iecsDeviceType;
    }

    public String getIecsDeviceNo() {
        return iecsDeviceNo;
    }

    public void setIecsDeviceNo(String iecsDeviceNo) {
        this.iecsDeviceNo = iecsDeviceNo;
    }

    public String getIecsDeviceBehaviorType() {
        return iecsDeviceBehaviorType;
    }

    public void setIecsDeviceBehaviorType(String iecsDeviceBehaviorType) {
        this.iecsDeviceBehaviorType = iecsDeviceBehaviorType;
    }

    public String getIecsContentDetail() {
        return iecsContentDetail;
    }

    public void setIecsContentDetail(String iecsContentDetail) {
        this.iecsContentDetail = iecsContentDetail;
    }

    public String getIecsSource() {
        return iecsSource;
    }

    public void setIecsSource(String iecsSource) {
        this.iecsSource = iecsSource;
    }

    public String getIecsOperateUser() {
        return iecsOperateUser;
    }

    public void setIecsOperateUser(String iecsOperateUser) {
        this.iecsOperateUser = iecsOperateUser;
    }

    public LocalDateTime getOisInsDt() {
        return oisInsDt;
    }

    public void setOisInsDt(LocalDateTime oisInsDt) {
        this.oisInsDt = oisInsDt;
    }

    public LocalDateTime getOisDeviceBehaviorTime() {
        return oisDeviceBehaviorTime;
    }

    public void setOisDeviceBehaviorTime(LocalDateTime oisDeviceBehaviorTime) {
        this.oisDeviceBehaviorTime = oisDeviceBehaviorTime;
    }

    @Override
    public String toString() {
        return "IecsDeviceBehaviorLog{" +
        "iecsDeviceBehaviorId = " + iecsDeviceBehaviorId +
        ", iecsDeviceType = " + iecsDeviceType +
        ", iecsDeviceNo = " + iecsDeviceNo +
        ", iecsDeviceBehaviorType = " + iecsDeviceBehaviorType +
        ", iecsContentDetail = " + iecsContentDetail +
        ", iecsSource = " + iecsSource +
        ", iecsOperateUser = " + iecsOperateUser +
        ", oisInsDt = " + oisInsDt +
        ", oisDeviceBehaviorTime = " + oisDeviceBehaviorTime +
        "}";
    }
}
