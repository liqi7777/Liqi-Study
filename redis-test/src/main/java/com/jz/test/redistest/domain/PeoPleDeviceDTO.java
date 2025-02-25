package com.jz.test.redistest.domain;

import lombok.Data;

import java.util.List;

/**
 * @author hao.chenghao
 * @description:
 * @date 2021/5/2414:04
 */
@Data
public class PeoPleDeviceDTO {

    private List<PeoPleDeviceInfo> deviceList;

    private String curTime;

}

