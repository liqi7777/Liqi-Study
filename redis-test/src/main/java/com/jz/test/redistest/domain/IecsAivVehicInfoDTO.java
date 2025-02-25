package com.jz.test.redistest.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * @author daicx
 * @version 1.0
 * @Description 车辆实时信息
 * @date 2020-12-15
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class IecsAivVehicInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    //车辆编号
    private String vehicleNo;
    //车辆位置(经度)
    private Double longitude;
    //车辆位置(纬度)
    private Double latitude;
    //车辆载重状态（0/空车，1/重车）
    private String vehicleLoadState;
    //车辆状态（0/故障，1/正常，2/保养，3/其他）
    private String vehicleState;
    //车辆所在区域
    private String vehicleArea;
    //行驶方向
    private String vehicleDirection;
    //车速（m/s）
    private Double vehicleSpeed;
    //车辆类型（AIV/OTK/ITK）
    private String vehicleType;
    //车辆行驶状态（0/停车，1/行车）
    private String vehicleDrivingState;
    //所在车道
    private String vehicleLane;
    //服务地址
    private String trkServeradd;
    //正北方向夹角
    private String heading;
    //目标区域距离
    private String targetAreaDistance;
    //所在道路
    private String vehicleRoad;
    //车辆四角的坐标信息，以空格分隔不同的角坐标
    private String scopeCoordinate;
    //车道序号
    private Long laneNum;

    //错误码 0-正常,1-长时间停车报警,2-AIV主动功能降级报警
    private String errorCode;

    //uwb任务执行编号
    private String uwbTask;
    // 目标区域code
    private String targetAreaCode;
    // 加速度
    private Double acceleratedSpeed;
    // 角速度
    private Double angularSpeed;
    //最近5个坐标系
    private LinkedList<String> lastlat;

    //车辆当前油量或电量（%）
    private String energyLevel;

    //地图ID
    private String mapId;
    //地图ID类型 0/车道ID 1/路口ID
    private Integer idType;
    private Long curTime;
    //行驶方向与正北方向夹角，航向角
    private String courseAngle;
    //AIV版本  2/2代  3/3代
    private String trkVersion;
    /**
     * 是否有定位 1-是 ,0-否
     */
    private String position;

    /**
     * 外集卡数据来源，APP(app定位上报)，BIGDATA(数据中心)
     */
    private String otkSource;

//----------------------思南字段
    private String deviceNo;
    //	N	String	SN+8位数字	司南平板设备序列号
    private String positionIndicator;
    //	N	String
    // 0：未定位或无效解1：单点定位
//2：伪距差分
//4：定位RTK固定解
//5：定位RTK浮点解
//6：组合导航解
//7：人工输入固定值8：超宽巷解
//9：SBAS解
//    其它：请忽略	定位解状态
    private String headingIndicator;//
    // 	N	String	0：未定位或无效解1：单点定位
//2：伪距差分
//4：定向RTK固定解
//5：定向RTK浮点解
//6：组合导航解
//    其它：请忽略	定向解状态
    private String sVn;//	N	String		主天线解算卫星数
    private String solutionSv;//	N	String		从天线解算卫星数
    private String diffAge;//	N	String		差分数据延迟
    private String stationId;//	N	String		基准站ID
    private String baselineLength;//	N	String		主天线和从天线的距离（双天线基线长），精确到小数点后3位
    //车辆已原地停止的时长 单位毫秒
    private Long stopTime;
    //0:未载箱,1:已载箱
    private String withContainer;
    //0:无箱 1:前小箱位 2:后小箱位 3：大箱位
    private Long containerLocation;
    /**
     * 当前工作模式，0人工，1智驾，2远控
     */
    private String workingMode;
//    0-能接收导航更新；1-不能接收导航更新
    private String refreshNavigation;
    @Transient
    public String getCoordinate() {
        if(longitude!=null&&latitude!=null){
            return new StringBuffer().append(longitude).append(",").append(latitude).toString();
        }
        return null;
    }
}