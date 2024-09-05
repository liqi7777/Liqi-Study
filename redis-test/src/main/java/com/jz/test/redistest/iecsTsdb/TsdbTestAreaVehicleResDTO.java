package com.jz.test.redistest.iecsTsdb;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liqi
 * create  2021/11/18 3:51 下午
 */
@Data
public class TsdbTestAreaVehicleResDTO {

    @ApiModelProperty("数据点数量")
    private Long dataPointNum;

    private Long consumeTime;
}
