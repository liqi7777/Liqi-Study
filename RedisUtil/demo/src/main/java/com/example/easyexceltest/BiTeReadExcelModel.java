package com.example.easyexceltest;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * @author Sky
 * create 2019/07/24
 * email sky.li@ixiaoshuidi.com
 **/

public class BiTeReadExcelModel extends BaseRowModel {

    @ExcelProperty(index = 0)
    private String deviceThirdNo;
    @ExcelProperty(index = 1)
    private String address;
    @ExcelProperty(index = 2)
    private String deviceName;

    public String getDeviceThirdNo() {
        return deviceThirdNo;
    }

    public void setDeviceThirdNo(String deviceThirdNo) {
        this.deviceThirdNo = deviceThirdNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
