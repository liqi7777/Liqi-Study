package com.jz.test.redistest.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author DAICX
 * @version 1.0
 * @Description
 * @date 2021/4/9 15:20
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class RoadPlanningCacheDTO {

    //坐标
    private String coordinate;
    //方向
    private String oisDirection;
    //道路ID
    private String roadId;
    //使用标志
    private String useFlag;
    private Long oisStopTime;

    private Integer oisPointType;
    // 道路名
    private String roadName;

}
