package com.jz.test.redistest.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class VehicleRealDataInfo implements Serializable {
    private static final long serialVersionUID = 3183774948171422601L;
    private Integer pid = 610;
    private String machNo;
    private String machType;
    private Long curTime;
    // 车辆当前任务
    private Long taskId;
    // TOS任务ID
    private Long tosTaskId;
    // 经度
    private Double longitude;
    // 维度
    private Double latitude;
    // 四角坐标
    private String scopeCoordinate;
    // 区域type
    private String areaType;
    // 区域
    private String vehicleArea;
    // 航向角
    private String vehicleHeading;
    // 车辆载重状态（0/空车，1/重车）
    private String vehicleLoadState;
    // 车辆状态（0/故障，1/正常，2/保养，3/其他）
    private String vehicleState;
    // 车辆行驶状态（0/停车，1/行车）
    private String vehicleDrivingState;
    // 目标区域code
    private String targetAreaCode;
    // 目标区域type
    private String targetAreaType;
    // 目标区域距离
    private String targetAreaDistance;
    // 实时速度
    private Double speed;
    // 加速度
    private Double acceleratedSpeed;
    // 角速度
    private Double angularSpeed;

    private List<Object> fenceList;
    private List<Object> obstacleList;
    private List<Object> aroundVehicleList;

    // 当前任务信息
    private VehicleTaskData taskData;
    //交通控制时间戳
    private String trafficCurTime;
    //交通信号数组
    private List<TrafficSignal> trafficList;

    //行驶方向与正北方向夹角，航向角度
    private String courseAngle;
    //周边场箱位置
    private List<Object> aroundYlocation;

    //任务最晚开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime taskLastStartTime;

    //任务最晚完成时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime taskLastEndTime;

    // 路口A区域
    private String trafficAreaCode;

    /**
     * 路径规划
     */
    private String taskRoadPlanning;

    /**
     * 车辆任务类型
     */
    private String taskType;

    /**
     * 当前工作模式，0人工，1智驾，2远控
     */
    private String workingMode;

    /**
     * 错误码
     * 0/正常
     * 1/长时间停车报警；
     * 2/AIV主动功能降级报警
     * 3/异常停车
     */
    private String errorCode;

    //⬇⬇⬇⬇⬇⬇⬇以下字符是班车主题专用⬇⬇⬇⬇⬇⬇⬇

    /**
     * 司南平板设备序列号
     */
    private String deviceNo;

    /**
     * 定位解状态
     * 0：未定位或无效解1：单点定位
     * 2：伪距差分
     * 4：定位RTK固定解
     * 5：定位RTK浮点解
     * 6：组合导航解
     * 7：人工输入固定值8：超宽巷解
     * 9：SBAS解
     * 其它：请忽略
     */
    private String positionIndicator;

    /**
     * 定向解状态
     * 0：未定位或无效解1：单点定位
     * 2：伪距差分
     * 4：定向RTK固定解
     * 5：定向RTK浮点解
     * 6：组合导航解
     * 其它：请忽略
     */
    private String headingIndicator;

    /**
     * 主天线解算卫星数
     */
    private String sVn;

    /**
     * 从天线解算卫星数
     */
    private String solutionSv;

    /**
     * 差分数据延迟
     */
    private String diffAge;

    /**
     * 基准站ID
     */
    private String stationId;

    /**
     * 主天线和从天线的距离（双天线基线长），精确到小数点后3位
     */
    private String baselineLength;

    //⬆⬆⬆⬆⬆⬆⬆以下字符是班车主题专用⬆⬆⬆⬆⬆⬆⬆



}
