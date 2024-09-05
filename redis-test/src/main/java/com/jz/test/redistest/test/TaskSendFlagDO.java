package com.jz.test.redistest.test;

import lombok.Data;

import java.io.Serializable;

/** 
 * @version 1.0 
 * @Description  任务下发标志
 * @author daicx
 * @date 2020-12-15
 */
@Data
public class TaskSendFlagDO implements Serializable {

    private static final long serialVersionUID = 1L;

    //桥吊
    private boolean qcFlag;
    //RTG
    private boolean rtgFlag;
    //车辆
    private boolean vehicleFlag;

    public TaskSendFlagDO(){

    }

    public TaskSendFlagDO(boolean qcFlag, boolean rtgFlag, boolean vehicleFlag){
        this.qcFlag = qcFlag;
        this.rtgFlag = rtgFlag;
        this.vehicleFlag = vehicleFlag;
    }

}