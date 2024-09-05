package com.jz.test.redistest.test;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author liqi
 * create  2022/1/18 8:02 下午
 */
@Data
public class Test11 {
    /**
     * client_id
     */
    @ApiModelProperty(value = "client_id",position = 2)
    private String clientId;

    /**
     * 消息
     */
    @ApiModelProperty(value = "消息",position = 3)
    private String queueId;



    /**
     * 数据产生时间
     */
    @ApiModelProperty(value = "数据产生时间",position = 5)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:ms:SS")
    private LocalDateTime time;
}
