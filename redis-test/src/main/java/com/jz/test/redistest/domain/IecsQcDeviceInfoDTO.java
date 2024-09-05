package com.jz.test.redistest.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jz.iecs.entity.DTO.LaneCoordinate;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/** 
 *车辆实时信息上传
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IecsQcDeviceInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    //设备编号
    private String machNo;
    //设备类型
    private String machType;
    //桥吊中心点经纬度坐标，x,y
    private String qcCenterCoordinate;
    //桥吊周边四个角的坐标信息，以一个空格分隔不同角的坐标,外延20cm
    private String qcScopeCoordinate;
    //最近几次上报的坐标:x y curTime
    private LinkedList<String> lastlat;
    //桥吊当前位置下，各车道作业点位置
    private List<LaneCoordinate> laneCoordinateList;
    //更新时间
    private Long curTime;
    //y4更新时间
    private Long y4CurTime;
    //状态：1-正常 2-换倍中 3-换倍完成
    private Long qcStatus;
    //桥吊移动的航向角
    private Double head;
    //桥吊移动的速度m/s
    private Double speed;
}