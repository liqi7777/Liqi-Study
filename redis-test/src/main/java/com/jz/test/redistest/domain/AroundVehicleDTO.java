package com.jz.test.redistest.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class AroundVehicleDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String vehicleNo;
	private String vehicleHeading;
	private String vehiclePosition;
	private String vehicleType;
	private Double speed;
	private Double acceleratedSpeed;
	private Double angularSpeed;
	private Long distance;
	private String scopeCoordinate;
	private String roadId;
	private Long laneNum;
	/**
	 * 定位原始时间
	 * 戳,RTG/QC/ITK/OTK/AIV/PP 有值
	 */
	private String coordinateTime;
}