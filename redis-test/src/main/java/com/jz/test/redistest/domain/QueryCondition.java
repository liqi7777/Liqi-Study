//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jz.test.redistest.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class QueryCondition implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableField(exist = false)
    private Integer page;
    @TableField(exist = false)
    private Integer rows;
    @TableField(exist = false)
    private String sortName;
    @TableField(exist = false)
    private String sortOrder;
    @TableField(exist = false)
    private String orderBy;

    public String getOrderBy() {
        return this.getSortName() != null && "" != this.getSortName() ? this.getSortName() + " " + this.getSortOrder() : null;
    }

    public QueryCondition() {
    }

    public Integer getPage() {
        return this.page;
    }

    public Integer getRows() {
        return this.rows;
    }

    public String getSortName() {
        return this.sortName;
    }

    public String getSortOrder() {
        return this.sortOrder;
    }

    public void setPage(final Integer page) {
        this.page = page;
    }

    public void setRows(final Integer rows) {
        this.rows = rows;
    }

    public void setSortName(final String sortName) {
        this.sortName = sortName;
    }

    public void setSortOrder(final String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public void setOrderBy(final String orderBy) {
        this.orderBy = orderBy;
    }



    public String toString() {
        return "QueryCondition(page=" + this.getPage() + ", rows=" + this.getRows() + ", sortName=" + this.getSortName() + ", sortOrder=" + this.getSortOrder() + ", orderBy=" + this.getOrderBy() + ")";
    }

    public void setEndTime2(LocalDateTime of) {
    }
}
