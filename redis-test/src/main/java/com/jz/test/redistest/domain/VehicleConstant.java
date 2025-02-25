package com.jz.test.redistest.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @version 1.0
 * @Description
 * @Author WANGJ-JZT
 * @date 2021/1/12 11:32
 */
public class VehicleConstant {
    public static final String RTG_WITH_AIV="{RTG_WITH_AIV}";
    public static final Set<String> UNWORK_MODE=new HashSet<>(Arrays.asList("DD","CD"));
    public static final String enableQcDispatch ="enableQcDispatch";
    public static final String ENABLE_ADJUST_BOXDIR ="ENABLE_ADJUST_BOXDIR";
    public static final String ADJUST_BOXDOOR_POS ="ADJUST_BOXDOOR_POS";
    public static final String ENABLE_SEG_NAVIGATE="ENABLE_SEG_NAVIGATE";
    public static final String INSTRUCTION = "{INSTRUCTION}:";
    public static final String QC_DISPATCH = "{QC_DISPATCH}:";
    public static final String ASSIGN_DSCH_DISPATCH ="{ASSIGN_DSCH_DISPATCH}:";
    public static final String LOAD_DSCH_DISPATCH_DATA ="{LOAD_DSCH_DISPATCH_DATA}:";
    public static final String ENABLE_TARGET_TIP = "ENABLE_TARGET_TIP";
    //OTK 跟车箱区逗号分隔
    public static final String OTK_FOLLOW_XQ= "OTK_FOLLOW_XQ";
    public static final String ENABLE_OTK_FOLLOW= "ENABLE_OTK_FOLLOW";
    /**
     * 车辆实时信息，AIV/5G终端，每200ms发送实时数据
     * key:车辆编号(包含AIV/5G终端设备编号）
     * value: 设备位置（经度/纬度），车辆载重状态（0/空车，1/重车），车辆状态（0/故障，1/正常，2/保养，3/其他），
     * 车辆所在区域，行驶方向，车速（m/s），车辆类型（AIV/OTK/ITK），车辆行驶状态（0/停车，1/行车）
     */
    public static final String VEHICLE_REAL_TIME_DATA = "vehicle_real_time_data";
    /**
     * 任务回执前缀
     */
    public static final String TASK_CALLBACK = "TASK_CALLBACK:";
    /**
     * uwb数据
     */
    public static final String UWB_DATA = "{uwb_data}:";

    /**
     * tc宏路径
     */
    public static final String PATH_PLAN = "{PATH_PLAN}:";

    /**
     * tc宏路径(提供给实时计算调用)
     */
    public static final String PATH_PLAN_CALCULATE = "{PATH_PLAN_CALCULATE}:";

    /**
     * 动贝红灯车辆
     */
    public static final String DONG_BEI_RED = "{DONG_BEI_RED}:";
    /**
     * qc车道已分配
     */
    public static final String QC_LANE_DISPATCHED = "{QC_LANE_DISPATCHED}:";
    public static final String PUBLISH_ANQUINF = "{PUBLISH_ANQUINF}:";
    /**
     * UWB空车QC调取
     */
    public static final String UWB_QC_DIAPATCH = "UWB_QC_DIAPATCH:";
    /**
     * 岸桥航次出入引桥路信息
     * key：航次id
     * value：uwb计算的航次结果信息
     */
    public static final String UWB_YQ_INF = "UWB_YQ_INF";
    /**
     * 在港艘次信息
     */
    public static final String UWB_SHIP_INF = "UWB_SHIP_INF";
    /**
     * 在港船舶和桥吊信息
     */
    public static final String UWB_BERTHES_INF = "UWB_BERTHES_INF";
    /**
     * RTG设备实时信息
     * key：设备编号
     * value：设备类型，当前位置
     */
    public static final String RTG_STATE = "RTG_STATE";

    /**
     * 西虹桥定位实时信息
     */
    public static final String XHQ_STATE = "{XHQ_STATE}:";

    /**
     * RTG西虹桥定位实时信息
     */
    public static final String RTG_XHQ_STATE = "RTG_XHQ_STATE";

    /**
     * RTG西虹桥定位上报检查RTG任务
     */
    public static final String RTG_XHQ_RTGTASK_CHECK = "{RTG_XHQ_RTGTASK_CHECK}:";

    /**
     * QC设备实时信息，QC
     * key：设备编号
     * value：设备类型，当前位置
     */
    public static final String QC_REAL_TIME_DATA = "qc_real_time_data";

    /**
     * 缓存QC换贝前中心点经纬度坐标的信息
     * key：设备编号
     * value：换贝前中心点经纬度坐标的信息
     */
    public static final String QC_INFO_BEFORE_DO_BEI = "qc_qcCenterCoordinate_info:";

    /**
     * 车辆实时红绿灯信息,缓存有效期1秒
     * key:车辆编号，包含AIV/5G终端设备编号,
     * value: 车辆所在区域,红绿灯信息(0/红灯，1/绿灯),前车序号，当前序号
     */
    public static final String VEHICLE_TRAFFIC_LIGHT = "{vehicle_traffic_light}:";
    public static final String GLOBAL_RED = "{GLOBAL_RED}:";


    /**
     * 车辆当前任务信息
     * 缓存说明：1. 在任务下发时，当前没有正在执行中的任务，则缓存最优先的任务序号的任务
     * 2. 当当前车辆任务执行完成后，则直接缓存下一个任务序号的任务为当前任务
     * key:车辆编号，包含AIV/5G终端设备编号
     * value:  车辆ID，车辆类型，车辆当前任务，任务类型，船名，航次，最晚开始时间，最晚结束时间，当前任务起始位置，当前任务目标位置，下一个任务的起始位置，当前任务路径规划，起始位置预计开始时间
     * 起始位置预计完成时间，目标位置预计开始时间，目标位置预计完成时间，当前任务RTG编号，当前任务桥吊编号，当前任务RTG服务地址，当前任务桥吊服务地址
     */
    public static final String VEHICLE_TASK_DATA = "vehicle_task_data";

    public static final String VEHICLE_TASK_DATA_CNTR_SEQ1_ERR = "vehicle_task_cntr_seq1_err";

    /**
     * 车辆信息缓存
     */
    public static final String VEHICLE_INFO_DATA = "vehicle_info_data";

    /**
     * QC信息缓存
     */
    public static final String QC_INFO_DATA = "qc_info_data";

    /**
     * RTG信息缓存
     */
    public static final String RTG_INFO_DATA = "rtg_info_data";

    /**
     * QC和RTG任务下发
     */
    public static final String QC_AND_RTG_TASK = "qc:and_rtg_task:";

    /**
     * RTG集卡到位请求信息
     */
    public static final String RTG_TRUCK_IN_PLACE = "rtg:truck:in_place:";

    /**
     * QC集卡到位请求信息
     */
    public static final String QC_TRUCK_IN_PLACE = "qc:truck:in_place:";

    /**
     * LK集卡到位请求信息
     */
    public static final String LK_TRUCK_IN_PLACE = "lk:truck:in_place:";

    /**
     * AIV集卡交互
     */
    public static final String AIV_TRUCK_INTERACTION = "aiv:truck:interaction:";

    /**
     * AIV在岸区，触发了物理按钮启停，需要通知UWB，用于岸区交通控制计算
     */
    public static final String AIV_TRUCK_IN_COASTAL_AREA = "aiv_truck_in_coastal_area:";

    /**
     * 车辆电子围栏信息
     */
    public static final String VEHICLE_RAIL_INFO = "vehicle_rail_info";

    /**
     * 人员设备信息
     */
    public static final String PEOPLE_DEVICE_INFO = "people_device_info";
    /**
     * UWB幂等控制
     */
    public static final String UWB_ISSUE = "uwb_issue:";

    /**
     * QC心跳缓存
     */
    public static final String QC_HEARTBEAT = "qc_heartbeat";
    /**
     * RTG心跳缓存
     */
    public static final String RTG_HEARTBEAT = "rtg_heartbeat";
    /**
     * AIV心跳缓存
     */
    public static final String AIV_HEARTBEAT = "aiv_heartbeat";

    /**
     * LK心跳缓存
     */
    public static final String LK_HEARTBEAT = "lk_heartbeat";
    /**
     * 车辆实时定位
     */
    public static final String ITK_REAL_TIME_COORDINATES = "vehicle_real_time_coordinates";
    /**
     * 作业工艺缓存
     */
    public static final String OPERATION_PROCESS = "operation_process:";
    /**
     * 作业工艺回馈缓存
     */
    public static final String OPERATION_PROCESS_RESULT = "operation_process_result:";

    public static final String VESSELS_IN_PORT_RESULT = "vessels_in_port_result";

    /**
     * 路口 未下发 任务缓存
     */
    public static final String CROSSROADS_NOT_WORK = "CROSSROADS_NOT_WORK";

    /**
     * 锁钮异常
     */
    public static final String LK_TASK_ABNORMAL = "lk_task_abnormal";
    public static final String QC_DONG_BEI = "{QC_DONG_BEI}:";
    public static final String UWB_RECEIPT_FLAG = "UWB_RECEIPT_FLAG:";
    //qc主动推送
    public static final String QC_MANUAL_FINISH = "qc_manual_finish";
    public static final Object UWB_YFC = "{UWB_YFC}:";

    //锁钮精停缓存
    public static final String LK_ACCURATE_ARRIVAL = "LK_ACCURATE_ARRIVAL";

    //锁钮精停缓存
    public static final String LK_TASK = "LK_TASK";

    //桥吊开工/完工缓存
    public static final String QC_ACOMMEN_OR_COMPLETION = "QC_ACOMMEN_OR_COMPLETION";

    //內集卡设备没有心跳
    public static final String APP_DEVICE_NO_HEART = "app_device_no_heart:";

    /**
     * 内集卡设备心跳保活    这里只是单纯记录内集卡上报的数据，实际没有去判断是否有心跳 车辆表维护了该数据
     */
    public static final String APP_DEVICE_HEART = "{app_device_heart}:";

    /**
     * 外集卡上报信息
     */
    public static final String OTK_DEVICE_HEART = "otk_device_heart:";

    /**
     * 外集卡集卡交互
     */
    public static final String OTK_INTERACTION = "OTK:INTERACTION:";

    /**
     * 向外集卡发送消息
     */
    public static final String SEND_MESSAGE_BY_OTK = "send:message_by_otk:";

    //通知内集卡消息（桥吊卸船特种箱）
    public static final String SEND_TRUCK_MESSAGE = "SEND_TRUCK_MESSAGE:";

    //桥吊卸船特种箱时有资质的车
    public static final String QC_TRUCK = "QC_TRUCK:";

    //桥吊到位缓存index
    public static final String QC_INDEX = "QC_INDEX:";

    //岸边拆锁缓存
    public static final String SHORE_UNLOCKING = "SHORE_UNLOCKING:";
    //临时停车拦截
    public static final String TEMPORARY_PARKING_CACHE = "TEMPORARY_PARKING_CACHE:";

    //感知rtg異常消息 （废弃）
    public static final String RTG_RESULT_REPORTING = "RTG_RESULT_REPORTING";

    //有心跳超时未完成任务(分钟)
    public static final String INHEART_NOCOMPLETE_TIMEOUT = "INHEART_NOCOMPLETE_TIMEOUT";

    //车辆有心跳无调度报警时间间隔(分钟)
    public static final String INHEART_NODISPATCH = "INHEART_NODISPATCH";

    //大车运行信号
    public static final String QC_SOP_MGRUNNING = "QC_SOP_MGRUNNING";

    //执行装船任务的车辆在空车状态下停在桥吊下面
    public static final String VEHICLE_STOP_QC_LOAD = "vehicle:stop_qc_load:";

    //车辆已发车后速度大于0时
    public static final String VEHICLE_START_ONE = "{vehicle:start_one}:";

    /**
     * 查验业务流程
     */
    public static final String CHECK_AREA_INFO = "check_area_info";

    /**
     * 外集卡UWB导航信息
     */
    public static final String OTK_UWB_MESSAGE = "{otk_uwb_message}:";

    /**
     * 车辆实时信息请求
     */
    public static final String VEHICLE_REAL_TIME_REPORT = "{vehicle:real_time_report}:";

    /**
     * TIK,AIV的车号（提供给实时计算使用）
     */
    public static final String VEHICLE_INFO_DATA_ITKAIV = "vehicle_info_data_ITKAIV";

    /**
     * 班车的车号（提供给实时计算使用）
     */
    public static final String VEHICLE_INFO_DATA_BC = "vehicle_info_data_BC";

    /**
     * QC基础设备号信息(提供给实时计算使用)
     */
    public static final String QC_INFO_DATA_CAL = "qc_info_data_cal";

    /**
     * RTG基础设备号信息(提供给实时计算使用)
     */
    public static final String RTG_INFO_DATA_CAL = "rtg_info_data_cal";

    /**
     * 闯红灯标记缓存
     */
    public static final String TC_TIP_BREAKREDLIGHT = "tc:tip:breakRedLight:";

    /**
     * 车辆预计到达时间
     */
    public static final String VEHICLE_PRE_ARRIVE_TIME = "{vehicle:pre_arrive_time}:";

    /**
     * RTG_RPS心跳缓存
     */
    public static final String RTG_RPS_HEARTBEAT = "rtg_rps_heartbeat";

    /**
     * 正在作业的RTG列表
     */
    public static final String IN_PROGRESS_RTG = "IECS:RTG:IN_PROGRESS";

    /**
     * RTG平均作业耗时
     */
    public static final String AVG_COST_TIME_RTG = "IECS:RTG:AVG_COST_TIME";

    /**
     * RTG编号为空
     */
    public static final String RTG_TASK_NO_NULL = "rtg:task:no_null:";

    /**
     * RTG指令丢失
     */
    public static final String RTG_LOCATION_LOSE = "rtg:location:lose:";

    /**
     * AIV在引桥临时停车处理
     */
    public static final String AIV_YQ_TEMP_STOP = "{aiv:yq:temp_stop}:";

    /**
     * 车辆临时停车原因
     */
    public static final String VEHICLE_TEMP_STOP_INFO = "{vehicle:temp_stop_info}:";

    /**
     * 车辆进入箱区详情
     */
    public static final String VEHICLE_IN_AREA_INFO = "{vehicle:in_area_info}:";

    /**
     * 车辆进入岸区详情
     */
    public static final String VEHICLE_IN_AQ_INFO = "{vehicle:in_aq_info}:";

    /**
     * 船舶作业效率
     */
    public static final String VESSEL_WORK_EFFICIENCY = "{vessel:work:efficiency}:";

    public static final String WORKING_CROSS = "IECS:WORKING_CROSS:";

    /**
     * 卸船进箱区二次分配标记
     */
    public static final String SCHSECOND_ALLOCATE_YLOGIC_INBOX = "{SCHSECOND_ALLOCATE_YLOGIC_INBOX}:";

    /**
     * 车辆任务重发
     */
    public static final String VEHICLE_TASKSEND_RETRY = "{VEHICLE_TASKSEND_RETRY}:";

    /**
     * RTG确认标记
     */
    public static final String RTG_TOS_CONFIRM = "{RTG_TOS_CONFIRM}:";

    /**
     * aiv电量redis缓存(格式：AIV_ENERGY_CACHE:T:H237 --> 20 )
     */
    public static final String AIV_ENERGY_CACHE = "AIV_ENERGY_CACHE:T:";

    /**
     * 延迟删除车辆指令
     */
    public static final String VEHICLE_ONE_DEL = "{VEHICLE:ONE_DEL}:";

    /**
     * 驳船AIV装车完成后校验作业工艺失败临时停车
     */
    public static final String AIV_YQ_TEMP_STOP_BAR = "{aiv:yq:temp_stop_bar}:";

    public static final String UWB_RECEIPT_FAIL = "UWB_RECEIPT_FAIL:";


    /**
     * 状态   1-开始人工控制  2-解除人工控制
     */
    public static final String AIV_TRK_LABOUR_CONTROLS = "aiv_trk_labour_controls:";


    /**
     * rtgRps取消精停
     */
    public static final String RTG_RPS_CANCEL_FINE_STOP = "RTG_RPS_CANCEL_FINE_STOP";

    public static final String AIV_TASK_CALLBACK = "{IECS_AIV_TASK}:";

    public static final String AIV_TASK_INCHING_MOTION = "IECS:AIV:TASK:INCHING:MOTION:";


    /**
     * SOS信息
     */
    public static final String SOS_INFORMATION = "SOS_INFORMATION";

    /**
     * AIV变道提醒
     */
    public static final String AIV_LANE_CHANGE_REMINDER = "AIV_LANE_CHANGE_REMINDER:";

    /**
     * QC作业车道信息
     */
    public static final String QC_WORK_LANE_DATA = "QC_WORK_LANE_DATA";

    /**
     * 集卡作业路
     */
    public static final String ROUTE_TRUCK_ID_NO = "ROUTE_TRUCK_ID_NO";

    /**
     * UWB计算的最大车数
     */
    public static final String UWB_QUAY_VEHICLE_NUMBER = "{uwb:quay_vehicle_number}:";


    /**
     * 下发任务来源内集卡心跳
     */
    public static final String ITK_HEART_SEND_TASK = "ITK_HEART_SEND_TASK:";

    public static final String CACHE_OTK_TOS_TASK = "IECS:OTK:TOS:TASK:";

    public static final String CACHE_OTK_TOS_TASK_RETRY = "IECS:OTK:TOS:TASK:RETRY";

    /**
     * AIV车辆任务任务回执
     */
    public static final String CACHE_AIV_TASK_RECEIPT = "IECS:AIV:TASK:RECEIPT:";

    /**
     * 每个工班加油次数 （hashkey 车号 数量）
     */
    public static final String CACHE_TRUCK_ADD_OIL_COUNT = "TRUCK:ADD:OIL:COUNT";


    /**
     * 车辆等待
     */
    public static final String VEHICLE_WAIT_RTG = "{VEHICLE_WAIT_RTG}:";

    /**
     * 车辆等待RTG
     */
    public static final String VEHICLE_WAIT_RTG_SHOW = "{VEHICLE_WAIT_RTG_SHOW}:";

    /**
     * 车辆箱区等待
     */
    public static final String VEHICLE_AREA_WAIT_SHOW = "{VEHICLE_AREA_WAIT_SHOW}";

    /**
     * 车辆岸区等待
     */
    public static final String VEHICLE_AQ_WAIT_SHOW = "{VEHICLE_AQ_WAIT_SHOW}";

    public static final String OTK_TASK_TIMEOUT_WARN = "OTK:TASK:TIMEOUT:WARN:";

    public static final String IECS_VEHICLE_BOX_IN = "{IECS_VEHICLE_BOX_IN}:";

    /**
     * 目标设备状态下发
     */
    public static final String AIV_TARGET_DEVICE = "aiv:target_device:";

    /**
     * AIV空箱箱区已到位
     */
    public static final String VEHICLE_AIV_EMPTY_INPLACE = "{vehicle_aiv_emptyBox_inPlace}:";

    /**
     * AIV切反向的时候保存箱位置 ｜ 车辆指令未完成页面(前后箱交换)-保存箱位置，例如:{disposeaivinversionlogic_cntrpos}:箱号，有效期两小时
     */
    public static final String DISPOSEAIVINVERSIONLOGIC_CNTRPOS = "{disposeaivinversionlogic_cntrpos}:";

    //车辆停车等待原因
    public static final String VEHICLE_STOP_INFO = "{vehicle:stop_info}:";

}
