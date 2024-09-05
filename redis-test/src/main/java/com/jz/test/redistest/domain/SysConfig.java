package com.jz.test.redistest.domain;

import java.time.LocalDateTime;

/**
 * @author  liqi
 * create  2022/6/27 12:07 下午
 */

/**
    * 参数配置表
    */
public class SysConfig {
    /**
    * 参数主键
    */
    private Integer configId;

    /**
    * 参数名称
    */
    private String configName;

    /**
    * 参数键名
    */
    private String configKey;

    /**
    * 参数键值
    */
    private String configValue;

    /**
    * 系统内置（Y是 N否）
    */
    private String configType;

    /**
    * 创建者
    */
    private String createBy;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 更新者
    */
    private String updateBy;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;

    /**
    * 备注
    */
    private String remark;

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}