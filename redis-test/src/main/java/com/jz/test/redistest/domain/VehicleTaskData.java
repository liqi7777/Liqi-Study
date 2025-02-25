package com.jz.test.redistest.domain;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author DAICX
 * @version 1.0
 * @Description 车辆当前任务信息
 * @date 2021/1/19 11:36
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class VehicleTaskData {

    //车辆编号
    private String vehicleNo;
    //车辆类型
    private String vehicleType;
    //车辆当前任务
    private Long taskId;
    //TOS任务ID
    private Long tosTaskId;
    //TOS任务ID
    private Long tosTaskId2;
    //任务类型
    private String taskType;
    // 作业的类型：0-作业任务，1-临时停车作业任务
    private String oisTaskType;
    private String oisOpprc;
    //船名
    private String vesselName;
    //航次
    private String voyage;
    //最晚开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime lastStartTime;
    //最晚结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime lastEndTime;
    //当前任务起始位置
    private String taskStartLn;
    //当前任务目标位置
    private String taskTargetLn;
    //下一个任务的起始位置
    private String nextTaskStartLn;
    //当前任务路径规划
    private List<RoadPlanningCacheDTO> taskRoadPlanning;
    //起始位置预计开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime startLnExpectTime;
    //起始位置预计完成时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime stLnEtCompleteTime;
    //目标位置预计开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime targetLnExpectTime;
    //目标位置预计完成时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime targetLnEtCompleteTime;
    //当前任务RTG编号
    private String rtgNo;
    //当前任务桥吊编号
    private String qcNo;
    //当前任务RTG服务地址
    private String rtgServiceAddress;
    //当前任务桥吊服务地址
    private String qcServiceAddress;

    //目标区域编号
    private String targetAreaCode;
    //目标区域类型
    private String targetAreaType;
    //一阶段二阶段指令关联id
    private Long oisRelId;
    //任务状态
    private String taskStatus;
    //一车两箱关联字段
    private Long oisTwoTaskId;
    //空重类型(1空车2重车)
    private Long oisOpinstrSeq;
    /**
     * 锁扭作业 （Y/N）
     */
    private String oisLock;
    //锁钮机号  锁钮机号/临时锁钮位置  TMPL_{坐标默认车辆位置}_{延船头方向偏移距离}
    private String oisLockNo;
    /**
     * 场箱位
     */
    private String vLocation;

    private String yLocation;
    /**
     * 场箱位
     */
    private String vLocation2;

    private String yLocation2;
    /**
     * 设备号
     */
    private String machNo;

    //正在作业的箱序号
    private Long oisCntrSeq;
    //箱数量
    private Long oisCntrCount;
    /**
     * 是否指定调度
     */
    private String oisSpecSchedule;
    //tos作业路id
    private Long oisVcwId;
    //岸区车道号信息
    private String oisQcAreaLane;

    private int pathType;

    /**
     * 是否进入箱区
     */
    private Boolean enterBlockAreaFlag;

    /**
     * 箱区代号
     */
    private String enterBlockAreaCode;

    /**
     * 与箱区最小贝位的距离
     */
    private Double distanceFromSmallestBay;

    /**
     * 箱区贝位
     */
    private String enterBlockBayNo;

    /**
     * 箱区道路
     */
    private String enterBlockAreaRoad;

    /**
     * 当前时间
     */
    private Long curTime;
    /**
     * 艘次ID
     */
    private Long vocOcrrid;
    //船头面海方向 R/L
    private String shipDir;
    //航次id
    private String shipNo;
    //分段引导类型
    private String planType;
    private String uwbType;
    /**
     * 场箱位对应的箱区编码(一车两箱则是两个,逗号分隔,例子:XQ3J3X,XQ4C4D)
     * 仅仅在箱区的时候维护。
     */
    private String ylocationRegionCode;

    /**
     * 运输状态
     * 1：空车 2：大箱 3：双小箱 4：前小箱 5：后小箱
     */
    private String transportStatus;

    /**
     * 箱1作业箱位 1-前小箱，2-后小箱,3-大箱
     */
    private String oisContainerSpace;

    /**
     * 箱2作业箱位 1-前小箱，2-后小箱,3-大箱
     */
    private String oisContainerSpace2;
    private String secondDschDispatch;
    private String oisCntrNo;
    private String oisCntrNo2;


    @Transient
    public String getCurrentYlocation() {
        return Objects.equals(oisCntrSeq, 2l) ? yLocation2 : yLocation;
    }

    @Transient
    public String getCurrentVlocation() {
        return Objects.equals(oisCntrSeq, 2l) ? vLocation2 : vLocation;
    }

    /**
     * 获取车辆任务当前目标场箱位
     *
     * @return
     */
    @Transient
    public String getTargetYlocation() {
        String targetYlocation = null;
        if (Objects.equals(2l, this.getOisOpinstrSeq()) && Objects.equals("YY", this.getOisOpprc())) {
            targetYlocation = this.getCurrentVlocation();
        } else {
            targetYlocation = this.getCurrentYlocation();
        }
        return targetYlocation;
    }

    @Transient
    public Long getCurrentTosid() {
        return Objects.equals(oisCntrSeq, 2l) ? tosTaskId2 : tosTaskId;
    }

    @Transient
    public String getTargetBox() {
        List<String> boxList = getBoxList();
        if (Objects.equals(oisCntrSeq, 1l)) {
            return boxList.get(0);
        } else {
            return boxList.get(1);
        }
    }

    @Transient
    public String getWorkBox(List<String> reachBoxList) {
        List<String> taskBoxList = getBoxList();
        String realTargetBox = null;
        List<String> intersetBox = (List<String>) CollUtil.intersection(taskBoxList, reachBoxList);
        if (intersetBox.size() == 0) {
            //无法判断其目标
            realTargetBox = null;
        } else if (intersetBox.size() == 1) {
            realTargetBox = intersetBox.get(0);
        } else if (intersetBox.size() == 2) {
            realTargetBox = getTargetBox();
        }
        return realTargetBox;
    }

    @Transient
    public List<String> getBoxList() {
//        String box1 = null, box2 = null;
//        if ("61".equals(getTaskType()) && Long.valueOf(2).equals(oisOpinstrSeq)) {
//            box1 = StrUtil.sub(getVLocation(), 0, 2);
//            box2 = StrUtil.sub(getVLocation2(), 0, 2);
//        } else {
//            box1 = StrUtil.sub(getYLocation(), 0, 2);
//            box2 = StrUtil.sub(getYLocation2(), 0, 2);
//        }
//        return Arrays.asList(box1, box2);
        return getYList(ylocation -> StrUtil.sub(ylocation, 0, 2));
    }

    @Transient
    public boolean sameBox() {
        List<String> boxs = getBoxList();
        if (boxs.get(1) != null && Objects.equals(boxs.get(0), boxs.get(1))) {
            return true;
        }
        return false;
    }

    /**
     * 获取箱信息
     *
     * @param function 自定义转换器
     * @return
     */
    @Transient
    public List<String> getYList(Function<String, String> function) {
        String s1 = null, s2 = null;
        if ("YY".equals(getOisOpprc()) && Long.valueOf(2).equals(oisOpinstrSeq)) {
            s1 = function.apply(getVLocation());
            s2 = function.apply(getVLocation2());
        } else {
            s1 = function.apply(getYLocation());
            s2 = function.apply(getYLocation2());
        }
        return Arrays.asList(s1, s2);
    }

    @Transient
    public boolean isToY5() {
        String target = getCurrentYlocation();
        if (target != null && (target.contains("明") || target.contains("W5")) && target.length() == 5) {
            return true;
        }
        return false;
    }
}
