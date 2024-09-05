package com.jz.test.redistest.iecsTsdb;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author liqi
 * create  2021/11/18 3:50 下午
 */
@Data
public class TsdbTestAreaVehicleReqDTO {

    @ApiModelProperty("区域编号")
    @NotBlank(message = "区域号不能为空")
    private String areaNo;

    private Long startTime;

    private Long endTime;


}
