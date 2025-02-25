package com.jz.test.redistest.domain;

import lombok.Data;

/**
 * @author liqi
 * create  2025/2/25 5:51 下午
 */
@Data
public class ApiData<T> {
    public static ApiData OPERATION_SUCCESS = new ApiData("200", "操作成功");
    public static ApiData OPERATION_FAIL = new ApiData("500", "操作失败");
    public static ApiData SAVE_SUCCESS = new ApiData("200", "保存成功");
    public static ApiData SAVE_FAIL = new ApiData("500", "保存失败");
    public static ApiData EXCEPTION_ERROR = new ApiData("500", "系统异常");
    public static ApiData AUTHENTICATION_FAIL = new ApiData("401", "认证失败");
    private String resultCode;
    private Long resultTimestamp;
    private String resultMsg;
    private T resultData;

    public ApiData(T resultData) {
        this.resultCode = "200";
        this.resultMsg = "操作成功";
        this.resultData = resultData;
    }

    public ApiData(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public ApiData(String resultCode, String resultMsg, T resultData) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.resultData = resultData;
    }

    public Long getResultTimestamp() {
        return System.currentTimeMillis();
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public String getResultMsg() {
        return this.resultMsg;
    }

    public T getResultData() {
        return this.resultData;
    }

    public void setResultCode(final String resultCode) {
        this.resultCode = resultCode;
    }

    public void setResultTimestamp(final Long resultTimestamp) {
        this.resultTimestamp = resultTimestamp;
    }

    public void setResultMsg(final String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public void setResultData(final T resultData) {
        this.resultData = resultData;
    }




    public String toString() {
        return "ApiData(resultCode=" + this.getResultCode() + ", resultTimestamp=" + this.getResultTimestamp() + ", resultMsg=" + this.getResultMsg() + ", resultData=" + this.getResultData() + ")";
    }

    public ApiData() {
    }
}
