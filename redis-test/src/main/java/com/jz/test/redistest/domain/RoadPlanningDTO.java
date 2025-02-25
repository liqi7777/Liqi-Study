package com.jz.test.redistest.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
public class RoadPlanningDTO {

    //坐标
    private String coordinate;
    //方向
    private Long oisDirection;
    //道路ID
    private String roadId;
    private String roadName;
    private Integer oisPointType;  // 点类型(OIS_POINT_TYPE 0-默认1-起点2-终点3-路口中心点4-必经点5-锁钮点6-引桥路的点 7-途径装卸箱点8-临时停车点9-岸区点)

}
