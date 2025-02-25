package com.jz.test.redistest.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrafficSignal {
    /**
     * 交通信号 0/红灯，1/绿灯 2/黄灯
     */
    private Long trafficSignal;
    //交通控制路口中心点坐标
    private String trafficCenterCoordinate;
    //交通控制 动态区域坐标 x,y x1,y1
    private String trafficRegionCoordinate;

    //当前车辆序号
    private Long currentNum;
    /**
     * 优先级
     */
    private Long priority;
    /**
     * 分组序号
     */
    private Integer groupIndex;
    /**
     * 剩余时间
     */
    private Integer restTime;
    /**
     * 当前路口的任务最晚开始时间
     */
    private long lastStartTime;
    /**
     *     1-直 2-左 4-右 32 调头
     */
    private Integer turnMode;

    /**
     * 信号灯来源 10：tc 11：物理信号灯  12:RTG转场 13:iecs 20：uwb
     */
    private Integer source;
//    //前车序号
//    private Long preNum;
//前车序号
private Long fVehicleNo;
    //前车车号
    private String fVehicleNoStr;
    //前车经纬度
    private String fVehicleCoordinate;
    /**
     * 红灯原因
     */
    private  String msg;

    /**
     * 0/普通灯 （基于地图停车线停车）
     * 1/虚拟路口控制 （基于冲突区域停车）
     * 2/立即停车 （当前位置最大减速度停车）
     */
    private int trafficType;

    /**
     * 0/正常控制
     * 1/被自动加入黑名单
     * 2/被人工设置加入黑名单
     */
    private String trafficReason = "0";
}