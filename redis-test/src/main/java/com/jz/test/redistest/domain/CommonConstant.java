package com.jz.test.redistest.domain;

/**
 * @author hao.chenghao
 * @description:
 * @date 2021/4/2915:51
 */
public interface CommonConstant {
    /**
     * 系统参数 qc顺序
     */
    String QC_ORDER = "QC_ORDER";
    String SEG_NAVIGATE="{SEG_NAVIGATE}:";
    String RTG_CHANGE_BOX = "{RTG_CHANGE_BOX}:";
    String RTG_DONGBEI_STATE = "{RTG_DONGBEI_STATE}:";

    String QC_ADJOIN_NUM = "QC_ADJOIN_NUM";
    String AREA_SUGGEST="{AREA_SUGGEST}:";
    String RTG_GJCHECKS_FLAG = "{RTG_GJCHECKS_FLAG}:";
    String RTG_GAMEPAD_CONTROL_FLAG = "{RTG_GAMEPAD_CONTROL_FLAG}:";
    /**
     * 数据字典 redis缓存key
     */
    String SYS_PARAMETER = "SYS_PARAMETER";
    /**
     * 周边车辆key
     */
    String SOUVENIR_VEHICLE = "souvenirVehicle";
    /**
     * 目标区域距离key
     */
    String TARGET_AREA_DISTANCE = "targetAreaDistance";

    /**
     * 周边电子围栏范围距离
     */
    String ELECTRIC_FENCE_DISTANCE = "electricFenceDistance";

    String USE_ITK = "USE_ITK";

    /**
     * uwb岸桥被动规划
     */
    String UWB_QC_PASSIVEPLAN = "UWB_QC_PASSIVEPLAN";
    /**
     * uwb岸桥障碍物规划
     */
    String UWB_QC_BARRIERPLAN = "UWB_QC_BARRIERPLAN";
    /**
     * uwb箱区被动规划
     */
    String UWB_XQ_PASSIVEPLAN = "UWB_XQ_PASSIVEPLAN";
    /**
     * uwb箱区障碍物规划
     */
    String UWB_XQ_BARRIERPLAN = "UWB_XQ_BARRIERPLAN";
    /**
     * PLC数据
     */
    String PLC_DATA = "PLC_DATA:";
    /**
     * 车辆进出道口坐标
     */
    String OTK_IN_GATE = "OTK_IN_GATE";
    String OTK_OUT_GATE = "OTK_OUT_GATE";

    /**
     * 岸区目标区域距离key
     */
    String ANQU_TARGET_AREA_DISTANCE = "ANQU_TARGET_AREA_DISTANCE";

    String ITK_ONLY_UNAUTO = "ITK_ONLY_UNAUTO";

    String CANCEL_MACHINES = "CANCEL_MACHINES";

    /**
     * 聚时PLC数据
     */
    String JS_LK_PLC_DATA = "JS_LK_PLC_DATA";

    /**
     * 设备通信方式
     */
    String DEVICE_COMM_MODE = "DEVICE_COMM_MODE";

    /**
     * 箱号感知向TOS申请场箱位
     */
    String BOX_PERCEPTION_APPLAY_YLOCATION = "{BOX_PERCEPTION_APPLAY_YLOCATION}:";

    /**
     * 过街完成关闭,打开TOS平板任务接收,Y：打开, N:关闭
     */
    String RTG_GJ_C_TOS_ONOFF = "RTG_GJ_C_TOS_ONOFF";

    /**
     * 感知型RTG启动标记
     */
    String AWARE_RTG_MOVE_BAY = "IECS:AWARE_RTG_MOVE_BAY:";
    /**
     * 转场影响到的路口
     */
    String RTG_CHANGE_BOX_CROSS = "IECS:RTG_CHANGE_BOX_CROSS:";

    /**
     * 机械设备工号
     */
    String EVENT_MACHINE_DRIVER = "IECS:EVENT_MACHINE_DRIVER:{}";

    /**
     * AIV申请解除黑名单
     */
    String AIV_APPLY_RELEASE_BLACKLIST = "IECS:AIV_APPLY_RELEASE_BLACKLIST:";

    /**
     * AIV导航变道信息
     */
    String AIV_CHANGE_LANE = "IECS:AIV_CHANGE_LANE:";

    /**
     * 车辆上报区域
     */
    String TASK_AREA = "TASK_AREA:";

    /**
     *  app版本号
     */
    String APP_VERSION = "APP_VERSION:";

    /**
     * 记录用户有无操作远控的rps
     */
    String YK_RPS = "{YK_RPS}:";

    /**
     * RTG转场通知的车辆
     */
    String RTG_CHANGE_BOX_VEHICLE = "{rtg_change_box_vehicle}:";

    /**
     * 内集卡作业任务
     */
    String ITK_TASK = "{itk:task}:";

    /**
     * RTG已完成的作业任务
     */
    String RTG_WORK_TASK = "{rtg:work_task}:";

    /**
     * RTG箱号不匹配
     */
    String RTG_TASK_97 = "{rtg:task_97}:";

    /**
     * RTG可驶离
     */
    String RTG_MAY_LEAVE_DATA = "{rtg:may_leave_data}:";

}
