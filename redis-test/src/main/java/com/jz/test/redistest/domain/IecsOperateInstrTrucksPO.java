package com.jz.test.redistest.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @version 1.0 
 * @Description 描述 
 * @author 在此处备注你的姓名简称 
 * @date 2021-01-08 
 */ 
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("IECS_OPERATE_INSTR_TRUCKS")
public class IecsOperateInstrTrucksPO extends QueryCondition {

    private static final long serialVersionUID = 1L;

    /**
     * 车辆任务ID
     */
    @TableId(type = IdType.INPUT)
    private Long oisOpinstridId;
    //箱ID
    private Long oisIycCntrid; 
    //船图箱ID
    private Long oisVpcCntrid; 
    //操作过程（指令类型）
    private String oisOpprc; 
    /**
     * 作业方式 DD: 空车任务
     */
    private String oisOpmode; 
    //优先级
    private Long oisPriority;
    //优先级
    private Long oisPriority2;
    /**
     * 作业产生时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisGentm;
    /**
     * 作业状态(0-未执行1-待装车2-已装车3-已发车14-驶入锁钮机 4-到达锁钮机13-到达锁钮作业位置12-拆装锁钮完成5-离开锁钮机6-到达指定位置7-到达作业位置8-已执行9-待取消10-取消中11-已取消99-异常)
     */
    private String oisOpstatus; 
    //场箱位
    private String oisYlocation; 
    //船箱位
    private String oisVlocation; 
    //要求作业完成时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisAskfinishtime;
    //预计作业时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisFfnshtime;
    //null
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisRefreshtime;
    //作业类型(1  单箱 2  双箱 3  双吊)
    private String oisWkFlow;
    /**
     * 起始位置
     */
    private String oisFromloc; 
    //当前位置
    private String oisCurloc; 
    /**
     * 目标位置
     */
    private String oisToloc;
    //CWP编号
    private Long oisCwpId; 
    //更新时间戳
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisTimestamp;
    //null
    private String oisAdduser; 
    //null
    private String oisDeluser; 
    //null
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisInsertdt;
    //null
    private String oisInsertuser; 
    //null
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisLstupddt;
    //null
    private String oisLstupuser; 
    //null
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisLanetime;
    //null
    private Long oisPsplid; 
    //null
    private Long oisAsplid; 
    //靠泊ID
    private Long oisVbtId; 
    /**
     * 集卡号
     */
    private String oisTrkno;
    //要求作业开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisAskstarttime;
    //车辆操作模式（0-自动 1-手动 2-远程）
    private String oisWorkingMode; 
    //任务时间（秒）
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisTimeLimit;
    //箱号
    private String oisCntrNo; 
    //锁钮机位置
    private String oisCcsLocation; 
    //最晚装车时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisLoadLastTime;
    //最晚卸车时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisUnloadLastTime;
    //任务最晚开始时间
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
    //箱区车道号信息
    private String oisCntrAreaLane;

    //任务ID(TOS)
    private Long oisOpinstrid;

    /**
     * 空重类型(1空车2重车) xk-> 听说已经废弃了原来的意思 改成了 1就是第一段任务 2就是第二段任务
     */
    private Long oisOpinstrSeq;

    /**
     * 发送状态0-未发送1-已发送2-已接收
     */
    private String oisSendState;

    //未完成
    @TableField(exist = false)
    private String oisOpstatusUn;
    /**
     * 不查询空车任务
     */
    @TableField(exist = false)
    private String oisOpmodeNotDd;

    //作业箱位1-前箱，2-后箱,3-单四十 ,4-单四十五,5-双二十尺
    @TableField(exist = false)
    private String containerSpace;

    //开发用（Kris） 字段废弃，需要删除
    private String oisShipNo;
    //作业箱位1-前小箱，2-后小箱,3-大箱
    private String oisContainerSpace;
    //当前箱重(kg)
    private Long oisContainerWeight;
    //尺寸
    private String oisContainerSize;
    //箱型
    private String oisContainerType;
    //货物类型1-液态，2-固态
    private String oisContainerForm;
    //终点nodeId
    private Long oisEndNodeId;
    //道路信息
    private String oisRoadInfo;
    //经纬路信息
    private String oisJwRoad;
    //RTG位置信息
    private String rtgLocationInfo;
    //箱子放置状态
    private String cntrPlaceState;
    //箱子装卸状态L-装 U-卸
    private String cntrLoadState;
    // 作业的类型：0-作业任务，1-临时停车作业任务
    private String oisTaskType;
    //车辆发车时间
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisDepartureTime;
    //一组必经点坐标，多个点之间用英语输入法的分号隔开.一个点坐标字符长度不超过40，则该字段最多可有5个点
    private String oisPassPoints;
    //tos作业路id
    private Long oisVcwId;
    /**
     * 一阶段二阶段指令关联id
     */
    private Long oisRelId;
    //一车两箱关联字段
    private Long oisTwoTaskId;
    //Y:锁钮作业 N:无锁钮作业
    private String oisLock;
    //作业顺序
    private Long taskWorkSerial;
    //任务完成时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisCompletionTime;
    //岸区车道号信息
    private String oisQcAreaLane;
    //Y:已通知TOS车辆调度 N:未通知TOS车辆调度
    private String oisTosSflag;
    /**
     * 车辆调度时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisDispatchTime;

    /**
     * 车辆调度反馈
     */
    private String oisTosSmemo;
    //场箱位分配系统 1：TOS  2:IECS
    private String oisSysType;
    //作业箱位1-前小箱，2-后小箱,3-大箱
    private String oisContainerSpace2;
    //当前箱重(kg)
    private Long oisContainerWeight2;
    //尺寸
    private String oisContainerSize2;
    //箱型
    private String oisContainerType2;
    //Y:锁钮作业 N:无锁钮作业
    private String oisLock2;
    //场箱位
    private String oisYlocation2;
    //船箱位
    private String oisVlocation2;
    //箱ID
    private Long oisIycCntrid2;
    //船图箱ID
    private Long oisVpcCntrid2;
    //任务ID(TOS)
    private Long oisOpinstrid2;
    //途径场箱位坐标
    private String oisMLoc;
    //正在作业的箱序号
    private Long oisCntrSeq;
    //箱数量
    private Long oisCntrCount;
    //箱号2
    private String oisCntrNo2;
    //任务图片
    private String oisTaskImages;
    /**
     * 是否指定调度
     */
    private String oisSpecSchedule;
    //是否二次调度
    private String oisSecSchedule;
    /**
     * 危险品标志
     */
    private String oisDangerFlag;
    //锁钮机号
    private String oisLockNo;
    //RTG当前在作业的箱序号
    private Long oisRtgcntrNum;

    /**
     * 来源
     */
    private String taskSource;
    /**
     * tos调度箱号1
     */
    private String tosDispatchCntrno;
    /**
     * tos调度箱号1
     */
    private String tosDispatchCntrno2;
    /**
     * tos调度箱号1船图id
     */
    private Long tosDispatchVpcCntrid;
    /**
     * tos调度箱号1船图id
     */
    private Long tosDispatchVpcCntrid2;
    /**
     * 退箱标识Y/N
     */
    private String oisReturnCntrFlag;
    /**
     * 取消类型1-取消后重置状态2-取消后完成指令3-取消后删除
     */
    private String oisCancelType;

    /**
     * 箱1 1-朝向车头,2-朝向车尾
     */
    private Integer oisBoxDirection;
    /**
     * 箱2 1-朝向车头,2-朝向车尾
     */
    private Integer oisBoxDirection2;
    /**
     * 1-	不需要吊箱门
     * 2-	需要吊箱门
     * 3-	吊箱门已完成
     * 4 -  AIV主动申请掉头
     */

    private Integer adjustBoxDirection;
    /**
     * tos预留集卡申请标记 Y 成功 N 失败
     */
    private String oisTosReserveFlag;

    /**
     * 预计到达时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisTaskPreArriveTime;

    /**
     * 预计到达时间第一箱
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisTaskPreArriveTime1;

    /**
     * 预计到达时间第二箱
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisTaskPreArriveTime2;

    /**
     * 预计完成时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisTaskPreCompleteTime;

    /**
     * 艘次ID
     */
    private Long vocOcrrid;

    /**
     * 航次ID
     */
    private Long shipNo;


    //停车点：TMP_CY_P -临时查验点
    private String oisPlanType;

    /**
     * 节点日志标记
     */
    private Integer oisNodeLogFlag;

    /**
     * 目标位置
     */
    private String targetPosition;

    /**
     * 车辆临时停车的原因
     */
    private String oisTempInfo;

    //杂项任务类型  0-加气/充电  1-
    private String oisMiscType;

    /**
     * 回执阶段
     */
    private String oisReceiptStage;

    //是否是查验任务 Y-是
    private String oisCheckTask;

    //是否已二次卸船调度 Y/N SECOND_DSCH_DISPATCH
    private String secondDschDispatch;
    //内外集卡临停信息    {}|{}|{}   临停贝|跟车车号|跟车距离
    //外集卡箱区外停车信息 {}|{}|{}|{}|{}      编码|名称|坐标|时间|原因
    private String parkInf;
    /**
     * RTG调度交换车后变更标志Y/N
     */
    private String oisChangeFlag;

    /**
     * 导航变道标志Y/N
     */
    private String navigateChangeLane;

    /**
     * 车辆可驶离时间（当车辆指令变更为2时更新）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oisVehicleLeaveTime;

    /**
     * 是否被人工修改过指令Y/N
     */
    private String oisArtificialFlag;
   
    /**
     * 是否自动化 重进重出 Y,N OIS_REINOUT
     */
    private String oisReinout;
}