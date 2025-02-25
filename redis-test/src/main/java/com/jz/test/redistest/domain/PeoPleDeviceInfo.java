package com.jz.test.redistest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hao.chenghao
 * @description:
 * @date 2021/5/2414:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeoPleDeviceInfo {

    public static final ConcurrentHashMap<String,PeoPleDeviceInfo> MAP_SUBSCRIBED = new ConcurrentHashMap();

    /**
     * 中心点经纬度坐标，x,y
     */
    private String coordinate;
    /**
     * 设备编号
     */
    private String deviceCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeoPleDeviceInfo that = (PeoPleDeviceInfo) o;
        return coordinate.equals(that.coordinate) &&
                deviceCode.equals(that.deviceCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate, deviceCode);
    }

}
