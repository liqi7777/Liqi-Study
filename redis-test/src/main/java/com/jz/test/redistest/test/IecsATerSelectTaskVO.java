package com.jz.test.redistest.test;

import com.jz.iecs.entity.DTO.RoadPlanningDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author liqi
 * create  2021-09-23 11:02
 */
@Getter
@Setter
public class IecsATerSelectTaskVO implements Serializable {

        private static final long serialVersionUID = 1L;

        //pid
        private Long pid;
        //设备编号
        private String machNo;
        //设备类型
        private String machType;
        //回执IP
        private String resultHost;
        //回执端口
        private String resultPort;

        private String taskId;

        private String taskStatus;

        private String operationCode;

        private String workingMode;

        private Long timeLimit;

        private Long containerWeight;

        private String containerNo;

        private String containerSize;

        private String containerType;

        private String ccsLocation;

        private String loadLastTime;

        private String unloadLastTime;

        private String containerForm;

        private String containerWorkType;

        private String containerSpace;

        private String nextPath;

        private Long endNodeId;

        private Long inchingDistance;

        private String taskStart;

        private String taskLastStartTime;

        private String taskEnd;

        private String taskLastEndTime;

        private String cntrAreaLane;

        private String roadInfo;

        private String trafficLights;

        private String jwRoad;

        private String surroundCarInfo;

        private String rtgLocationInfo;

        private String oisYlocation;

        private String oisVlocation;

        private String cntrPlaceState;

        private String cntrLoadState;

        private String mdDistance;

        private List<RoadPlanningDTO> roadPlanning;
        /*private List<terLocation> roadPlanning;*/

        //空重类型(1空车2重车)
        private Long oisOpinstrSeq;
        private Long taskSerial;
        //TOS任务ID
        private Long oisOpinstrid;
        private String tosTaskId;

        //指令状态
        private Long operationStatus;
        //任务类型 0-作业任务
        private String taskType;
        //作业任务类型 0-新下发 1-取消 2-更改
        private Long soaTaskType;
        //时间戳
        private String curTime;
        //tos作业路id
        private Long oisVcwId;
        //一阶段二阶段指令关联id
        private Long oisRelId;
        //一车两箱关联字段
        private Long oisTwoTaskId;
        //作业顺序
        private Long taskWorkSerial;
        //桥吊作业车道号，海侧往陆侧，1-6车道号
        private Long qcWorkLaneNo;
        /**
         * 下一个目的地
         */
        private String targetPosition;
        /**
         * 锁扭作业 （Y/N）
         */
        private String oisLock;
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
        /**
         * 更改内容,更改时有值，多个时则逗号分隔，值为更改的字段名称
         */
        private String updateFieldName;

        /**
         * 指令箱位
         * 1、	单20尺
         * 2、	双20尺
         * 3、	单40尺
         * 4、单45尺
         */
        private Long oisTaskCntrSpace;

        /**
         * 当前作业车辆位置点
         * 1-	前箱位置
         * 2-	后箱位置
         * 3-	大箱位置
         */
        private Long curWorkPosition;
        /**
         * 是否显示全路径
         */
        private Integer enableShowAllPath;
        /**
         * 是否是二次下发的任务
         */
        private Integer enableSecondIssue;

        /**
         * 昨夜工艺
         * 1、单箱吊
         * 2、双箱吊
         */
        private Long oisWkFlow;
        /**
         * 航次id
         */
        private String shipNo;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IecsATerSelectTaskVO that = (IecsATerSelectTaskVO) o;
        return Objects.equals(pid, that.pid) &&
                Objects.equals(machNo, that.machNo) &&
                Objects.equals(machType, that.machType) &&
                Objects.equals(resultHost, that.resultHost) &&
                Objects.equals(resultPort, that.resultPort) &&
                Objects.equals(taskId, that.taskId) &&
                Objects.equals(taskStatus, that.taskStatus) &&
                Objects.equals(operationCode, that.operationCode) &&
                Objects.equals(workingMode, that.workingMode) &&
                Objects.equals(timeLimit, that.timeLimit) &&
                Objects.equals(containerWeight, that.containerWeight) &&
                Objects.equals(containerNo, that.containerNo) &&
                Objects.equals(containerSize, that.containerSize) &&
                Objects.equals(containerType, that.containerType) &&
                Objects.equals(ccsLocation, that.ccsLocation) &&
                Objects.equals(loadLastTime, that.loadLastTime) &&
                Objects.equals(unloadLastTime, that.unloadLastTime) &&
                Objects.equals(containerForm, that.containerForm) &&
                Objects.equals(containerWorkType, that.containerWorkType) &&
                Objects.equals(containerSpace, that.containerSpace) &&
                Objects.equals(nextPath, that.nextPath) &&
                Objects.equals(endNodeId, that.endNodeId) &&
                Objects.equals(inchingDistance, that.inchingDistance) &&
                Objects.equals(taskStart, that.taskStart) &&
                Objects.equals(taskLastStartTime, that.taskLastStartTime) &&
                Objects.equals(taskEnd, that.taskEnd) &&
                Objects.equals(taskLastEndTime, that.taskLastEndTime) &&
                Objects.equals(cntrAreaLane, that.cntrAreaLane) &&
                Objects.equals(roadInfo, that.roadInfo) &&
                Objects.equals(trafficLights, that.trafficLights) &&
                Objects.equals(jwRoad, that.jwRoad) &&
                Objects.equals(surroundCarInfo, that.surroundCarInfo) &&
                Objects.equals(rtgLocationInfo, that.rtgLocationInfo) &&
                Objects.equals(oisYlocation, that.oisYlocation) &&
                Objects.equals(oisVlocation, that.oisVlocation) &&
                Objects.equals(cntrPlaceState, that.cntrPlaceState) &&
                Objects.equals(cntrLoadState, that.cntrLoadState) &&
                Objects.equals(mdDistance, that.mdDistance) &&
                Objects.equals(roadPlanning, that.roadPlanning) &&
                Objects.equals(oisOpinstrSeq, that.oisOpinstrSeq) &&
                Objects.equals(taskSerial, that.taskSerial) &&
                Objects.equals(oisOpinstrid, that.oisOpinstrid) &&
                Objects.equals(tosTaskId, that.tosTaskId) &&
                Objects.equals(operationStatus, that.operationStatus) &&
                Objects.equals(taskType, that.taskType) &&
                Objects.equals(soaTaskType, that.soaTaskType) &&
                Objects.equals(curTime, that.curTime) &&
                Objects.equals(oisVcwId, that.oisVcwId) &&
                Objects.equals(oisRelId, that.oisRelId) &&
                Objects.equals(oisTwoTaskId, that.oisTwoTaskId) &&
                Objects.equals(taskWorkSerial, that.taskWorkSerial) &&
                Objects.equals(qcWorkLaneNo, that.qcWorkLaneNo) &&
                Objects.equals(targetPosition, that.targetPosition) &&
                Objects.equals(oisLock, that.oisLock) &&
                Objects.equals(oisContainerSpace2, that.oisContainerSpace2) &&
                Objects.equals(oisContainerWeight2, that.oisContainerWeight2) &&
                Objects.equals(oisContainerSize2, that.oisContainerSize2) &&
                Objects.equals(oisContainerType2, that.oisContainerType2) &&
                Objects.equals(oisLock2, that.oisLock2) &&
                Objects.equals(oisYlocation2, that.oisYlocation2) &&
                Objects.equals(oisVlocation2, that.oisVlocation2) &&
                Objects.equals(oisIycCntrid2, that.oisIycCntrid2) &&
                Objects.equals(oisVpcCntrid2, that.oisVpcCntrid2) &&
                Objects.equals(oisOpinstrid2, that.oisOpinstrid2) &&
                Objects.equals(oisMLoc, that.oisMLoc) &&
                Objects.equals(oisCntrSeq, that.oisCntrSeq) &&
                Objects.equals(oisCntrCount, that.oisCntrCount) &&
                Objects.equals(oisCntrNo2, that.oisCntrNo2) &&
                Objects.equals(updateFieldName, that.updateFieldName) &&
                Objects.equals(oisTaskCntrSpace, that.oisTaskCntrSpace) &&
                Objects.equals(curWorkPosition, that.curWorkPosition) &&
                Objects.equals(enableShowAllPath, that.enableShowAllPath) &&
                Objects.equals(enableSecondIssue, that.enableSecondIssue) &&
                Objects.equals(oisWkFlow, that.oisWkFlow) &&
                Objects.equals(shipNo, that.shipNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, machNo, machType, resultHost, resultPort, taskId, taskStatus, operationCode, workingMode, timeLimit, containerWeight, containerNo, containerSize, containerType, ccsLocation, loadLastTime, unloadLastTime, containerForm, containerWorkType, containerSpace, nextPath, endNodeId, inchingDistance, taskStart, taskLastStartTime, taskEnd, taskLastEndTime, cntrAreaLane, roadInfo, trafficLights, jwRoad, surroundCarInfo, rtgLocationInfo, oisYlocation, oisVlocation, cntrPlaceState, cntrLoadState, mdDistance, roadPlanning, oisOpinstrSeq, taskSerial, oisOpinstrid, tosTaskId, operationStatus, taskType, soaTaskType, curTime, oisVcwId, oisRelId, oisTwoTaskId, taskWorkSerial, qcWorkLaneNo, targetPosition, oisLock, oisContainerSpace2, oisContainerWeight2, oisContainerSize2, oisContainerType2, oisLock2, oisYlocation2, oisVlocation2, oisIycCntrid2, oisVpcCntrid2, oisOpinstrid2, oisMLoc, oisCntrSeq, oisCntrCount, oisCntrNo2, updateFieldName, oisTaskCntrSpace, curWorkPosition, enableShowAllPath, enableSecondIssue, oisWkFlow, shipNo);
    }
}
