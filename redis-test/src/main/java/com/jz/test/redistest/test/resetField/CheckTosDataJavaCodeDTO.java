package com.jz.test.redistest.test.resetField;

import lombok.Data;

/**
 * @author liqi
 * create  2021/11/29 5:11 下午
 */
@Data
public class CheckTosDataJavaCodeDTO {
    private Integer n_taskcount;


    public static void main(String[] args) {
        String areaBayRowData = "123212";
        String s = areaBayRowData.substring(0, 2) + areaBayRowData.substring(2, 4) + areaBayRowData.substring(4, 5);
        System.out.println(s);
    }
}
