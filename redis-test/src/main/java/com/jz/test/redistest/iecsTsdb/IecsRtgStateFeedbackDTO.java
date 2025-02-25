package com.jz.test.redistest.iecsTsdb;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @version 1.0
 * @Description RTG(轮胎吊)   集卡交互
 * @author wagc
 * @date 2020-12-15
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IecsRtgStateFeedbackDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    //编号
    private Long pid;
    //设备编号
    private String machNo;
    //设备类型
    private String machType;
    //RTG编号
    private String  sosRtgId;
    //RTG作业模式
    //1-全自动/自动模式
    //2-远程半自动/辅助模式
    //3-远程人工/远程手动
    //4-本地司机/司机室模式
    private String  sosWorkMode;
    //RTG作业状态
    private String  sosWorkStatus;
    //RTG位置（箱区倍位）
    private String sosposition;
    //RTG在箱区里的最后一次箱区倍位
    private String boxSosPosition;
    //更新时间 废弃  过渡期保留一周
    @Deprecated
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime sotUpdateTm;
    //更新时间 毫秒时间戳 新的
    private Long curTime;
    /**
     * RTG中心点经纬度坐标，x,y
     */
    private String rtgCenterCoordinate;
    /**
     * RTG周边四个角的坐标信息，以一个空格分隔不同角的坐标,外延20cm
     */
    private String rtgScopeCoordinate;
    /**
     * 是否参与RTG调度  Y/是, 否
     */
    private String rtgDispatch;
    /**
     * YMC_MECHANICAL_TYPE	0	RTG机械设备类型，0，非自动，1自动，2感知        1(自动化rtg)可在相邻自动化箱区移动;0,2(非自动和感知RTG)可在自动与非自动箱区移动
     */
    private  String autoType;
    /**
     * YMC_BLOCK_DISPATCH		是否支持跨block(Y是,否)
     */
    private  String blockDispatch;

    /**
     * 1/联机模式
     * 2/单机模式
     */
    private String sosOnlineStatus;

    //最近几次上报的坐标:x y curTime dis
    private List<Object[]> lastlat;
    //    1 正常 2-移动中 3-移动完成
    private String moveState;

    private String locationLose;

    //大车移动状态0停止1运动
    private String mgRunning;
}