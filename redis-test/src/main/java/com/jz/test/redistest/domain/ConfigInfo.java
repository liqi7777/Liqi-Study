package com.jz.test.redistest.domain;

import java.time.LocalDateTime;

/**
 * @author  liqi
 * create  2022/6/27 12:13 下午
 */
/**
    * config_info
    */
public class ConfigInfo {
    /**
    * id
    */
    private Long id;

    /**
    * data_id
    */
    private String dataId;

    private String groupId;

    /**
    * content
    */
    private String content;

    /**
    * md5
    */
    private String md5;

    /**
    * 创建时间
    */
    private LocalDateTime gmtCreate;

    /**
    * 修改时间
    */
    private LocalDateTime gmtModified;

    /**
    * source user
    */
    private String srcUser;

    /**
    * source ip
    */
    private String srcIp;

    private String appName;

    /**
    * 租户字段
    */
    private String tenantId;

    private String cDesc;

    private String cUse;

    private String effect;

    private String type;

    private String cSchema;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getSrcUser() {
        return srcUser;
    }

    public void setSrcUser(String srcUser) {
        this.srcUser = srcUser;
    }

    public String getSrcIp() {
        return srcIp;
    }

    public void setSrcIp(String srcIp) {
        this.srcIp = srcIp;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getcDesc() {
        return cDesc;
    }

    public void setcDesc(String cDesc) {
        this.cDesc = cDesc;
    }

    public String getcUse() {
        return cUse;
    }

    public void setcUse(String cUse) {
        this.cUse = cUse;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getcSchema() {
        return cSchema;
    }

    public void setcSchema(String cSchema) {
        this.cSchema = cSchema;
    }
}