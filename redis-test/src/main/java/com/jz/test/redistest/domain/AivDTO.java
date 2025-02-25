package com.jz.test.redistest.domain;

import lombok.Data;

import java.util.List;

@Data
public class AivDTO {
    /**
     *  设备ID
     */
    private String machNo;

    /**
     *  时间戳(curTime)
     */
    private String curTime;

    /**
     *  位置（longitude ）
     */
    private String longitude;

    /**
     *  位置（（latitude）
     */
    private String latitude;

    /**
     *  速度(speed)
     */
    private String speed;

    /**
     *  航向角(vehicleHeading)
     */
    private String vehicleHeading;

    /**
     *  车辆状态(vehicleStatus)
     */
    private String vehicleStatus;

    /**
     * 行驶方向与正北方向夹角，航向角
     */
    private String courseAngle;

    private String vehicleArea;

    // 区域type
    private String areaType;

    private String scopeCoordinate;

    private List<AroundVehicleDTO> aroundVehicleList;

    private String deviceNo;

    private List<TrafficSignal> trafficList;

    //能否更新任务标志位默认为0-能更新任务 （0-能更新任务，1-无法更新任务）
    private String refreshStatus;

    private String jsonString;




    public List<AroundVehicleDTO> getAroundVehicleList() {
        return aroundVehicleList;
    }

    public void setAroundVehicleList(List<AroundVehicleDTO> aroundVehicleList) {
        this.aroundVehicleList = aroundVehicleList;
    }

    public String getCourseAngle() {
        return courseAngle;
    }

    public void setCourseAngle(String courseAngle) {
        this.courseAngle = courseAngle;
    }

    public String getMachNo() {
        return machNo;
    }

    public void setMachNo(String machNo) {
        this.machNo = machNo;
    }

    public String getCurTime() {
        return curTime;
    }

    public void setCurTime(String curTime) {
        this.curTime = curTime;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getVehicleHeading() {
        return vehicleHeading;
    }

    public void setVehicleHeading(String vehicleHeading) {
        this.vehicleHeading = vehicleHeading;
    }

    public String getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(String vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public AivDTO() {
    }

    public AivDTO(String machNo, String curTime, String longitude, String latitude, String speed, String vehicleHeading, String vehicleStatus) {
        this.machNo = machNo;
        this.curTime = curTime;
        this.longitude = longitude;
        this.latitude = latitude;
        this.speed = speed;
        this.vehicleHeading = vehicleHeading;
        this.vehicleStatus = vehicleStatus;
    }
}
