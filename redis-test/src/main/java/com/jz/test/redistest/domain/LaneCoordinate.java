package com.jz.test.redistest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author DAICX
 * @version 1.0
 * @Description
 * @date 2021/4/7 9:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaneCoordinate implements Serializable {

    //车道ID
    private String laneId;

    //作业点经纬度坐标，x,y
    private String coordinate;

}
