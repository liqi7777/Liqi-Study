package com.jz.test.redistest.mapStudy;

import org.geotools.referencing.GeodeticCalculator;
import org.locationtech.jts.geom.Coordinate;

import java.awt.geom.Point2D;

/**
 * @author liqi
 * create  2024/10/16 2:12 下午
 */
public class GtUtil {

    /**
     * 已知相对方向和角度计算另一个位置（球面坐标系，经纬度！geotools工具类！）
     *
     * @param p
     * @param distance
     * @param degree
     * @return
     */
    public static Coordinate getRelativePos(Coordinate p, double distance, double degree) {
        // 创建 GeodeticCalculator 对象
        GeodeticCalculator geodeticCalculator = new GeodeticCalculator();
        // 设置起点 (经度, 纬度)
        geodeticCalculator.setStartingGeographicPoint(p.x, p.y);
        // 设置前进的方向和距离 (角度，距离为米)
        geodeticCalculator.setDirection(degree, distance);
        // 计算终点
        Point2D destination = geodeticCalculator.getDestinationGeographicPoint();
        return new Coordinate(destination.getX(), destination.getY());
    }

    /**
     * 获取方位角(geotools工具类！)
     *
     * @param p1
     * @param p2
     * @return
     */
    public static double getAngle(Coordinate p1, Coordinate p2) {
        GeodeticCalculator geodeticCalculator = new GeodeticCalculator();
        // 设置起点 (经度, 纬度)
        geodeticCalculator.setStartingGeographicPoint(p1.x, p1.y);
        geodeticCalculator.setDestinationGeographicPoint(p2.x, p2.y);
        // 获取方位角 (以度为单位)
        double angle = geodeticCalculator.getAzimuth();
        if (angle < 0.0) {
            angle += 360.0;
        }
        return angle;
    }
}
