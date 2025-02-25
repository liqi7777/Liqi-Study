package com.jz.test.redistest.domain;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @createDate  2021年10月14日 15:49:52
 */ 
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class IecsOperateInstrTrucksD extends QueryCondition {

    private static final long serialVersionUID = 1L;

    /**
     * 任务1删除标志 1,2,3 为尝试次数 4:放弃重试 5:成功 6,7,8:通过主键回尝试(如果通过主键回收 flag1 和flag2 同时存在) 9:放弃重试 10:成功
     */
    @TableField(value = "TASK_REMOVE_FLAG",updateStrategy = FieldStrategy.IGNORED)
    private Integer taskRemoveFlag;
    /**
     * 任务2删除标志 1,2,3 为尝试次数 4:放弃重试 5:成功 6,7,8:通过主键回尝试(如果通过主键回收 flag1 和flag2 同时存在) 9:放弃重试 10:成功
     */
    @TableField(value = "TASK2_REMOVE_FLAG",updateStrategy = FieldStrategy.IGNORED)
    private Integer task2RemoveFlag;

    /**
     * 车辆任务ID（IECS_OPERATE_INSTR_TRUCKS_D_SEQ）
     */
    @TableId(type = IdType.INPUT)
    private Long oisOpinstridId;

    /**
     * 箱ID
     */
    private Long oisIycCntrid;

    /**
     * 船图箱ID
     */
    private Long oisVpcCntrid;

    /**
     * 操作过程（指令类型）
     */
    private String oisOpprc;

    /**
     * 作业方式，LOAD-装船,DSCH-卸船,02-提箱,16-进箱,FX-翻箱，YX-移箱
     */
    private String oisOpmode;

    /**
     * 优先级
     */
    private Integer oisPriority;

    /**
     * 作业产生时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisGentm;

    /**
     * 作业状态(0-未执行1-待装车2-已装车3-已发车14-驶入锁钮机4-到达锁钮机13-到达锁钮作业位置12-拆装锁钮完成5-离开锁钮机6-到达指定位置7-到达作业位置8-已执行9-待取消10-取消中11-已取消99-异常)
     */
    private String oisOpstatus;

    /**
     * 场箱位
     */
    private String oisYlocation;

    /**
     * 船箱位
     */
    private String oisVlocation;

    /**
     * 要求作业完成时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisAskfinishtime;

    /**
     * 预计到达时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisFfnshtime;

    /**
     * $column.comments
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisRefreshtime;

    /**
     * 作业工艺1-单箱，2-双箱 3- 双吊
     */
    private String oisWkFlow;

    /**
     * 起始位置（经纬度）
     */
    private String oisFromloc;

    /**
     * 当前位置（经纬度）
     */
    private String oisCurloc;

    /**
     * 目标位置（经纬度）
     */
    private String oisToloc;

    /**
     * CWP编号
     */
    private Long oisCwpId;

    /**
     * 更新时间戳
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisTimestamp;

    /**
     * $column.comments
     */
    private String oisAdduser;

    /**
     * $column.comments
     */
    private String oisDeluser;

    /**
     * $column.comments
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisInsertdt;

    /**
     * $column.comments
     */
    private String oisInsertuser;

    /**
     * $column.comments
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisLstupddt;

    /**
     * $column.comments
     */
    private String oisLstupuser;

    /**
     * $column.comments
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisLanetime;

    /**
     * $column.comments
     */
    private Long oisPsplid;

    /**
     * $column.comments
     */
    private Long oisAsplid;

    /**
     * 靠泊ID
     */
    private Long oisVbtId;

    /**
     * 集卡号
     */
    private String oisTrkno;

    /**
     * 要求作业开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisAskstarttime;

    /**
     * 车辆操作模式（0-自动 1-手动 2-远程）
     */
    private String oisWorkingMode;

    /**
     * 任务时间（秒）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisTimeLimit;

    /**
     * 箱号
     */
    private String oisCntrNo;

    /**
     * 锁钮机位置
     */
    private String oisCcsLocation;

    /**
     * 最晚装车时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisLoadLastTime;

    /**
     * 最晚卸车时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisUnloadLastTime;

    /**
     * 任务最晚开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisTaskLastStartTime;

    /**
     * 任务最晚结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisTaskLastEndTime;

    /**
     * 箱区车道号信息
     */
    private String oisCntrAreaLane;

    /**
     * 任务ID(TOS)
     */
    private Long oisOpinstrid;

    /**
     * 序号，1空车，2重车(没有空车重车概念，第一段是1，第二段是2)
     */
    private Long oisOpinstrSeq;

    /**
     * 发送状态
     */
    private String oisSendState;

    /**
     * 开发用（Kris）暂用船头方向，默认1001
     */
    private String oisShipNo;

    /**
     * 作业箱位1-前小箱，2-后小箱,3-
     大箱
     */
    private String oisContainerSpace;

    /**
     * 当前箱重(kg)
     */
    private Long oisContainerWeight;

    /**
     * 尺寸
     */
    private String oisContainerSize;

    /**
     * 箱型
     */
    private String oisContainerType;

    /**
     * 货物类型1-液态，2-固态
     */
    private String oisContainerForm;

    /**
     * 终点nodeId
     */
    private Integer oisEndNodeId;

    /**
     * 道路信息
     */
    private String oisRoadInfo;

    /**
     * 经纬路信息
     */
    private String oisJwRoad;

    /**
     * RTG位置信息
     */
    private String rtgLocationInfo;

    /**
     * 箱子放置状态
     */
    private String cntrPlaceState;

    /**
     * 箱子装卸状态L-装 U-卸
     */
    private String cntrLoadState;

    /**
     * 作业的类型：0-作业任务，1-临时停车作业任务
     */
    private String oisTaskType;

    /**
     * 车辆发车时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisDepartureTime;

    /**
     * 一组必经点坐标，多个点之间用英语输入法的分号隔开.如有压车时间(s)则坐标和时间空格隔开，该字段最多可有5个点
     */
    private String oisPassPoints;

    /**
     * tos作业路id
     */
    private Long oisVcwId;

    /**
     * 一阶段二阶段指令关联id
     */
    private Long oisRelId;

    /**
     * 一车两箱关联字段,默认0
     */
    private Long oisTwoTaskId;

    /**
     * Y:锁钮作业 N:无锁钮作业 L:离开锁钮机 C:锁钮机任务完成
     */
    private String oisLock;

    /**
     * 作业顺序
     */
    private Long taskWorkSerial;

    /**
     * 任务完成时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisCompletionTime;

    /**
     * 岸区车道号信息
     */
    private String oisQcAreaLane;

    /**
     * Y:已通知TOS车辆调度 N:未通知TOS车辆调度
     */
    private String oisTosSflag;

    /**
     * 调度时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisDispatchTime;

    /**
     * TOS车辆调度反馈
     */
    private String oisTosSmemo;

    /**
     * 场箱位分配系统 1：TOS  2:IECS
     */
    private String oisSysType;

    /**
     * 作业箱位1-前小箱，2-后小箱,3-
     大箱
     */
    private String oisContainerSpace2;

    /**
     * 当前箱重(kg)
     */
    private Long oisContainerWeight2;

    /**
     * 尺寸
     */
    private String oisContainerSize2;

    /**
     * 箱型
     */
    private String oisContainerType2;

    /**
     * Y:锁钮作业 N:无锁钮作业
     */
    private String oisLock2;

    /**
     * 场箱位
     */
    private String oisYlocation2;

    /**
     * 船箱位
     */
    private String oisVlocation2;

    /**
     * 箱ID
     */
    private Long oisIycCntrid2;

    /**
     * 船图箱ID
     */
    private Long oisVpcCntrid2;

    /**
     * 任务ID(TOS)
     */
    private Long oisOpinstrid2;

    /**
     * 途径场箱位坐标
     */
    private String oisMLoc;

    /**
     * 正在作业的箱序号
     */
    private Long oisCntrSeq;

    /**
     * 箱数量
     */
    private Long oisCntrCount;

    /**
     * 箱号2
     */
    private String oisCntrNo2;

    /**
     * 任务图片
     */
    private String oisTaskImages;

    /**
     * 是否指定调度
     */
    private String oisSpecSchedule;

    /**
     * 是否二次调度
     */
    private String oisSecSchedule;

    /**
     * 危险品标志
     */
    private String oisDangerFlag;

    /**
     * 锁钮机号
     */
    private String oisLockNo;

    /**
     * RTG当前在作业的箱序号
     */
    private Integer oisRtgcntrNum;

    /**
     * 来源
     */
    private String taskSource;

    /**
     * tos调度箱号1
     */
    private String tosDispatchCntrno;

    /**
     * tos调度箱号2
     */
    private String tosDispatchCntrno2;

    /**
     * tos调度箱号1船图id
     */
    private Long tosDispatchVpcCntrid;

    /**
     * tos调度箱号2船图id
     */
    private Long tosDispatchVpcCntrid2;

    /**
     * 退箱标识Y/N
     */
    private String oisReturnCntrFlag;

    /**
     * 预计到达时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisTaskPreArriveTime;

    /**
     * 预计完成时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisTaskPreCompleteTime;

    public IecsOperateInstrTrucksD() {
    }
    public IecsOperateInstrTrucksD(IecsOperateInstrTrucksPO iecsOperateInstrTrucksPO) {
        BeanUtil.copyProperties(iecsOperateInstrTrucksPO,this);
    }
}