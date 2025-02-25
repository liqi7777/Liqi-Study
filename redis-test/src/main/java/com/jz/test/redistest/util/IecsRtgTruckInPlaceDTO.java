package com.jz.test.redistest.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jz.test.redistest.domain.ValidationGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/** 
 * @version 1.0 
 * @Description 集卡到位信息
 * @author wangc
 * @date 2021-2-10
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IecsRtgTruckInPlaceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    //编号
    private Long pid;
    //设备编号
    private String machNo;
    //设备类型
    private String machType;
    //回执IP
    private String resultHost;
    //回执端口
    private String resultPort;
    //任务ID
    @NotNull(groups = ValidationGroup.add.class,message = "任务id不能为空")
    private String taskId;
    //RTG任务ID
    private String rtgTaskId;
    //tos任务ID
    private String tosTaskId;
    //车辆编号
    private String  vehicleNo;
    //经度
    private String  longitude;
    //纬度
    private String  latitude;
    //命令请求时间
    private String sotReqTm;
    //上次请求序号，第一次粗停上次请求序号默认0
    private Long cmdIdx;
    //上次指令执行结果，第一次粗停上次执行结果默认1
    private Long cmdStatus;
    //备用1
    private String spare1;
    //备用2
    private String spare2;

    private String vehicleYlocation;

}
