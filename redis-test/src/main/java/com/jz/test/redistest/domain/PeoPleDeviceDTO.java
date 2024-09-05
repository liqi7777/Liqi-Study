package com.jz.test.redistest.domain;

import com.jz.iecs.entity.DTO.PeoPleDeviceInfo;
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

