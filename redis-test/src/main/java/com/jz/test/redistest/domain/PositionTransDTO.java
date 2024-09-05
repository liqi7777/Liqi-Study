package com.jz.test.redistest.domain;

import com.jz.iecs.entity.DTO.IecsAivVehicInfoDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liqi
 * create  2023/10/9 10:31 上午
 */
@Data
public class PositionTransDTO implements Serializable {
    private static final long serialVersionUID = 5666501953748769656L;
    private VehicleRealDataInfo vehicleRealDataInfo;
    private IecsAivVehicInfoDTO iecsAivVehicInfoDTO;
}
