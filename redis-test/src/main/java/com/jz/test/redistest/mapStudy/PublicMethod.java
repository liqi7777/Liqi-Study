package com.jz.test.redistest.mapStudy;


import org.locationtech.spatial4j.io.GeohashUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class PublicMethod {

    public static final double PI = 3.14159265358979324;
    // 赤道半径(单位m)
    public static final double EARTH_RADIUS = 6372795.477598; //6372795.477598  6378137

    public static double[] trainPolyFit = {0.9972739367451966, 4.323852126263266E-4, -1.7522667541578619E-4, 4.966195349399909E-7};

    public static Map<String, Double> getLonLat(String GPSStr) {
        GPSStr = GPSStr.trim();
        String[] GPSTemp = null;
        if (GPSStr.contains(",")) {
            GPSTemp = GPSStr.split(",");
        } else if (GPSStr.contains(" ")) {
            GPSTemp = GPSStr.split(" ");
        }
        Double lon = Double.valueOf(GPSTemp[0].trim());
        Double lat = Double.valueOf(GPSTemp[1].trim());
        Map<String, Double> GPSMap = new HashMap<>();
        GPSMap.put("lon", lon);
        GPSMap.put("lat", lat);
        return GPSMap;
    }

    /**
     * 将经纬度转换为笛卡尔坐标
     *
     * @param lon 经度
     * @param lat 纬度
     * @return 笛卡尔坐标
     */
    public static double[] toCartesian(double lon, double lat) {
        double radLon = Math.toRadians(lon);
        double radLat = Math.toRadians(lat);
        double x = EARTH_RADIUS * Math.cos(radLat) * Math.cos(radLon);
        double y = EARTH_RADIUS * Math.cos(radLat) * Math.sin(radLon);
        double z = EARTH_RADIUS * Math.sin(radLat);
        return new double[]{x, y, z};
    }

    /**
     * 将笛卡尔坐标转换为经纬度
     */
    public static double[] toGeographic(double x, double y, double z) {
        double lon = Math.atan2(y, x);
        double hyp = Math.sqrt(x * x + y * y);
        double lat = Math.atan2(z, hyp);
        return new double[]{Math.toDegrees(lon), Math.toDegrees(lat)};
    }

    /**
     * 转化为弧度(rad)
     */
    private static Double rad(Double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 基于googleMap中的算法得到两经纬度之间的距离,
     * 计算精度与谷歌地图的距离精度差不多，相差范围在0.2米以下
     *
     * @param point1 第一个点的经纬度
     * @param point2 第二个点的经纬度
     * @return 返回的距离，单位m
     */
    public static Double getDistance(String point1, String point2) {
        Map<String, Double> point1Map = getLonLat(point1);
        Map<String, Double> point2Map = getLonLat(point2);
        Double radLat1 = rad(point1Map.get("lat"));
        Double radLat2 = rad(point2Map.get("lat"));
        Double a = radLat1 - radLat2;
        Double b = rad(point1Map.get("lon")) - rad(point2Map.get("lon"));
        Double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        //s = Math.round(s * 10000) / 10000;
        return s;
    }


    //根据当前GPS坐标点，方位角，距离，求解另一个GPS点
    public static String getGPSPoint(String point, Double theta, Double d) {
        Map<String, Double> pointMap = getLonLat(point);
        double lon = Math.toRadians(pointMap.get("lon"));
        double lat = Math.toRadians(pointMap.get("lat"));
        theta = Math.toRadians(theta);
        double lat1 = Math.asin(Math.sin(lat) * Math.cos(d / EARTH_RADIUS) + Math.cos(lat) * Math.sin(d / EARTH_RADIUS) * Math.cos(theta));
        double lon1 = lon + Math.atan2(Math.sin(theta) * Math.sin(d / EARTH_RADIUS) * Math.cos(lat), Math.cos(d / EARTH_RADIUS) - Math.sin(lat) * Math.sin(lat1));
        lat1 = Math.toDegrees(lat1);
        lon1 = Math.toDegrees(lon1);
        return lon1 + "," + lat1;
    }


    /**
     * 计算点到直线的垂足  [122.00593186090681,29.77233327804656, 122.00655057265777,29.772981216521103]  POINT(122.00773583449146 29.77408807630635)
     * 废弃 点与线段过远时偏差较大
     *
     * @param point      点的经纬度
     * @param startPoint 直线第一个点的经纬度
     * @param endPoint   直线第二个点的经纬度
     * @return 垂足点
     */
    @Deprecated
    public static String getGPSPointToLine(String point, String startPoint, String endPoint) {
//        return  JtsUtil.toStr(JtsUtil.getFoot(point,startPoint,endPoint));
        Map<String, Double> pointMap = getLonLat(point);
        Map<String, Double> startPointMap = getLonLat(startPoint);
        Map<String, Double> endPointMap = getLonLat(endPoint);

        double lon1 = startPointMap.get("lon");
        double lat1 = startPointMap.get("lat");
        double lon2 = endPointMap.get("lon");
        double lat2 = endPointMap.get("lat");
        double lon3 = pointMap.get("lon");
        double lat3 = pointMap.get("lat");

        // 转换为笛卡尔坐标
        double[] p1 = toCartesian(lon1, lat1);
        double[] p2 = toCartesian(lon2, lat2);
        double[] p3 = toCartesian(lon3, lat3);

        double[] p1p2 = {p2[0] - p1[0], p2[1] - p1[1], p2[2] - p1[2]};
        double[] p1p3 = {p3[0] - p1[0], p3[1] - p1[1], p3[2] - p1[2]};

        double dotProduct = p1p2[0] * p1p3[0] + p1p2[1] * p1p3[1] + p1p2[2] * p1p3[2];
        double p1p2LengthSquared = p1p2[0] * p1p2[0] + p1p2[1] * p1p2[1] + p1p2[2] * p1p2[2];
        double k = dotProduct / p1p2LengthSquared;

        double[] foot = {p1[0] + k * p1p2[0], p1[1] + k * p1p2[1], p1[2] + k * p1p2[2]};

        // 转换回经纬度
        double[] footLonLat = toGeographic(foot[0], foot[1], foot[2]);

        return footLonLat[0] + "," + footLonLat[1];
    }

    /**
     * 计算两条线段的交点
     *
     * @param startPoint1 第一条线段起点的经纬度
     * @param endPoint1   第一条线段终点的经纬度
     * @param startPoint2 第二条线段起点的经纬度
     * @param endPoint2   第二条线段终点的经纬度
     * @return 相交点
     */
    public static String getCrossPoint(String startPoint1, String endPoint1, String startPoint2, String endPoint2) {
        Map<String, Double> startPoint1Map = getLonLat(startPoint1);
        Map<String, Double> endPoint1Map = getLonLat(endPoint1);
        Map<String, Double> startPoint2Map = getLonLat(startPoint2);
        Map<String, Double> endPoint2Map = getLonLat(endPoint2);

        double lon1 = startPoint1Map.get("lon");
        double lat1 = startPoint1Map.get("lat");
        double lon2 = endPoint1Map.get("lon");
        double lat2 = endPoint1Map.get("lat");
        double lon3 = startPoint2Map.get("lon");
        double lat3 = startPoint2Map.get("lat");
        double lon4 = endPoint2Map.get("lon");
        double lat4 = endPoint2Map.get("lat");

        // 将经纬度转换为笛卡尔坐标
        double[] p1 = toCartesian(lon1, lat1);
        double[] p2 = toCartesian(lon2, lat2);
        double[] p3 = toCartesian(lon3, lat3);
        double[] p4 = toCartesian(lon4, lat4);

        // 计算每条线段所在平面的法向量
        double[] n1 = crossProduct(p1, p2);
        double[] n2 = crossProduct(p3, p4);

        // 计算两条平面法向量的叉积，即为交点所在直线的方向向量
        double[] intersection = crossProduct(n1, n2);
        intersection = normalize(intersection);

        // 转换回经纬度
        double[] intersectionLonLat = toGeographic(intersection[0], intersection[1], intersection[2]);

        return intersectionLonLat[0] + "," + intersectionLonLat[1];
    }

    // 计算两个向量的叉积
    public static double[] crossProduct(double[] v1, double[] v2) {
        return new double[]{
                v1[1] * v2[2] - v1[2] * v2[1],
                v1[2] * v2[0] - v1[0] * v2[2],
                v1[0] * v2[1] - v1[1] * v2[0]
        };
    }

    // 归一化向量
    public static double[] normalize(double[] v) {
        double length = Math.sqrt(v[0] * v[0] + v[1] * v[1] + v[2] * v[2]);
        return new double[]{v[0] / length, v[1] / length, v[2] / length};
    }


    // 计算点到点的向量
    public static double[] vectorBetween(double[] p1, double[] p2) {
        return new double[]{
                p2[0] - p1[0],
                p2[1] - p1[1],
                p2[2] - p1[2]
        };
    }

    // 判断点是否在线段上(笛卡尔坐标)
    public static boolean isPointOnSegment(double[] point, double[] start, double[] end) {
        double[] startToPoint = vectorBetween(start, point);
        double[] startToEnd = vectorBetween(start, end);
        double dotProduct = startToPoint[0] * startToEnd[0] + startToPoint[1] * startToEnd[1] + startToPoint[2] * startToEnd[2];
        double lengthSquared = startToEnd[0] * startToEnd[0] + startToEnd[1] * startToEnd[1] + startToEnd[2] * startToEnd[2];
        return dotProduct >= 0 && dotProduct <= lengthSquared;
    }


    /**
     * 判断点是否在线段上（经纬度）
     *
     * @param point 点的经纬度
     * @param start 线段起点的经纬度
     * @param end   线段终点的经纬度
     * @return 是否在线段上
     */
    public static boolean isPointOnLine(String point, String start, String end) {
        Map<String, Double> PointMap = getLonLat(point);
        Map<String, Double> startPointMap = getLonLat(start);
        Map<String, Double> endPointMap = getLonLat(end);
        double lon1 = PointMap.get("lon");
        double lat1 = PointMap.get("lat");
        double lon2 = startPointMap.get("lon");
        double lat2 = startPointMap.get("lat");
        double lon3 = endPointMap.get("lon");
        double lat3 = endPointMap.get("lat");
        // 转换为笛卡尔坐标
        double[] p1 = toCartesian(lon1, lat1);
        double[] p2 = toCartesian(lon2, lat2);
        double[] p3 = toCartesian(lon3, lat3);
        return isPointOnSegment(p1, p2, p3);
    }


    /**
     * 计算两条线段是否相交
     *
     * @param startPoint      第一条线段起点的经纬度
     * @param endPoint        第一条线段终点的经纬度
     * @param fenceStartPoint 第二条线段起点的经纬度
     * @param fenceEndPoint   第二条线段终点的经纬度
     * @return 是否相交
     */
    public static boolean isLineCross(String startPoint, String endPoint, String fenceStartPoint, String fenceEndPoint) {
        Map<String, Double> startPoint1Map = getLonLat(startPoint);
        Map<String, Double> endPoint1Map = getLonLat(endPoint);
        Map<String, Double> startPoint2Map = getLonLat(fenceStartPoint);
        Map<String, Double> endPoint2Map = getLonLat(fenceEndPoint);

        double lon1 = startPoint1Map.get("lon");
        double lat1 = startPoint1Map.get("lat");
        double lon2 = endPoint1Map.get("lon");
        double lat2 = endPoint1Map.get("lat");
        double lon3 = startPoint2Map.get("lon");
        double lat3 = startPoint2Map.get("lat");
        double lon4 = endPoint2Map.get("lon");
        double lat4 = endPoint2Map.get("lat");

        // 转换为笛卡尔坐标
        double[] p1 = toCartesian(lon1, lat1);
        double[] p2 = toCartesian(lon2, lat2);
        double[] p3 = toCartesian(lon3, lat3);
        double[] p4 = toCartesian(lon4, lat4);

        // 计算每条线段所在平面的法向量
        double[] n1 = crossProduct(p1, p2);
        double[] n2 = crossProduct(p3, p4);

        // 计算两条平面法向量的叉积，即为交点所在直线的方向向量
        double[] intersection = crossProduct(n1, n2);
        intersection = normalize(intersection);

        // 转换回地理坐标
        double[] intersectionLonLat = toGeographic(intersection[0], intersection[1], intersection[2]);

        // 转换回笛卡尔坐标进行判断
        double[] intersectionCartesian = toCartesian(intersectionLonLat[0], intersectionLonLat[1]);

        // 检查交点是否在两条线段上
        boolean onSegment1 = isPointOnSegment(intersectionCartesian, p1, p2);
        boolean onSegment2 = isPointOnSegment(intersectionCartesian, p3, p4);

        return onSegment1 && onSegment2;
    }


    public static boolean between(double a, double b, double target) {
        if (target >= a - 0.01 && target <= b + 0.01 || target <= a + 0.01 && target >= b - 0.01)
            return true;
        else
            return false;
    }

    public static boolean isInPolygon2(String point, List<String> ptsList) {
        int N = ptsList.size();
        int intersectCount = 0;
        double precision = 2e-10;

        // 预先解析输入点的经纬度
        Map<String, Double> pointMap = getLonLat(point);
        double pointLat = pointMap.get("lat");
        double pointLon = pointMap.get("lon");

        // 预先解析第一个顶点
        Map<String, Double> p1Map = getLonLat(ptsList.get(0));
        double p1Lat = p1Map.get("lat");
        double p1Lon = p1Map.get("lon");

        for (int i = 1; i <= N; ++i) {
            Map<String, Double> p2Map = getLonLat(ptsList.get(i % N));
            double p2Lat = p2Map.get("lat");
            double p2Lon = p2Map.get("lon");

            // 判断点是否与多边形的顶点重合
            if (pointLat == p1Lat && pointLon == p1Lon) {
                return true;
            }

            // 过滤掉不可能相交的线段
            if (pointLat < Math.min(p1Lat, p2Lat) || pointLat > Math.max(p1Lat, p2Lat)) {
                p1Lat = p2Lat;
                p1Lon = p2Lon;
                continue;
            }

            // 判断是否有交叉点
            if (pointLat > Math.min(p1Lat, p2Lat) && pointLat < Math.max(p1Lat, p2Lat)) {
                if (pointLon <= Math.max(p1Lon, p2Lon)) {
                    if (p1Lat == p2Lat && pointLon >= Math.min(p1Lon, p2Lon)) {
                        return true;
                    }

                    if (p1Lon == p2Lon) {
                        if (p1Lon == pointLon) {
                            return true;
                        } else {
                            ++intersectCount;
                        }
                    } else {
                        double xinters = (pointLat - p1Lat) * (p2Lon - p1Lon) / (p2Lat - p1Lat) + p1Lon;
                        if (Math.abs(pointLon - xinters) < precision) {
                            return true;
                        }

                        if (pointLon < xinters) {
                            ++intersectCount;
                        }
                    }
                }
            } else {
                if (pointLat == p2Lat && pointLon <= p2Lon) {
                    Map<String, Double> p3Map = getLonLat(ptsList.get((i + 1) % N));
                    double p3Lat = p3Map.get("lat");
                    if (pointLat >= Math.min(p1Lat, p3Lat) && pointLat <= Math.max(p1Lat, p3Lat)) {
                        ++intersectCount;
                    } else {
                        intersectCount += 2;
                    }
                }
            }

            p1Lat = p2Lat;
            p1Lon = p2Lon;
        }

        return intersectCount % 2 != 0;
    }


    /**
     * 判断点是否在多边形内
     *
     * @param point   目标点
     * @param ptsList 多边形的点
     */
    public static boolean isInPolygon(String point, List<String> ptsList) {

        int N = ptsList.size();
        boolean boundOrVertex = true;
        int intersectCount = 0;//交叉点数量
        double precision = 2e-10; //浮点类型计算时候与0比较时候的容差
        String p1, p2;//临近顶点
        Map<String, Double> pointMap = getLonLat(point);

        p1 = ptsList.get(0);
        for (int i = 1; i <= N; ++i) {
            Map<String, Double> p1Map = getLonLat(p1);
            if (pointMap.get("lon").equals(p1Map.get("lon")) && pointMap.get("lat").equals(p1Map.get("lat"))) {
                return boundOrVertex;
            }

            p2 = ptsList.get(i % N);
            Map<String, Double> p2Map = getLonLat(p2);
            if (pointMap.get("lat") < Math.min(p1Map.get("lat"), p2Map.get("lat")) || pointMap.get("lat") > Math.max(p1Map.get("lat"), p2Map.get("lat"))) {
                p1 = p2;
                continue;
            }

            //射线穿过算法
            if (pointMap.get("lat") > Math.min(p1Map.get("lat"), p2Map.get("lat")) && pointMap.get("lat") < Math.max(p1Map.get("lat"), p2Map.get("lat"))) {
                if (pointMap.get("lon") <= Math.max(p1Map.get("lon"), p2Map.get("lon"))) {
                    if (p1Map.get("lat") == p2Map.get("lat") && pointMap.get("lon") >= Math.min(p1Map.get("lon"), p2Map.get("lon"))) {
                        return boundOrVertex;
                    }

                    if (p1Map.get("lon") == p2Map.get("lon")) {
                        if (p1Map.get("lon") == pointMap.get("lon")) {
                            return boundOrVertex;
                        } else {
                            ++intersectCount;
                        }
                    } else {
                        double xinters = (pointMap.get("lat") - p1Map.get("lat")) * (p2Map.get("lon") - p1Map.get("lon")) / (p2Map.get("lat") - p1Map.get("lat")) + p1Map.get("lon");
                        if (Math.abs(pointMap.get("lon") - xinters) < precision) {
                            return boundOrVertex;
                        }

                        if (pointMap.get("lon") < xinters) {
                            ++intersectCount;
                        }
                    }
                }
            } else {
                if (pointMap.get("lat") == p2Map.get("lat") && pointMap.get("lon") <= p2Map.get("lon")) {
                    String p3 = ptsList.get((i + 1) % N);
                    Map<String, Double> p3Map = getLonLat(p3);
                    if (pointMap.get("lat") >= Math.min(p1Map.get("lat"), p3Map.get("lat")) && pointMap.get("lat") <= Math.max(p1Map.get("lat"), p3Map.get("lat"))) {
                        ++intersectCount;
                    } else {
                        intersectCount += 2;
                    }
                }
            }
            p1 = p2;
        }
        if (intersectCount % 2 == 0) {//偶数在多边形外
            return false;
        } else { //奇数在多边形内
            return true;
        }
    }


    public static boolean reverse(double runStraightPos, String vehicleDirection) {
        return Math.abs(Double.valueOf(vehicleDirection) - runStraightPos) > 90.0;
    }

    public static boolean pointInLonRange(String point, String startPoint, String endPoint) {
        startPoint.trim();
        endPoint.trim();
        point.trim();
        Double p;
        Double p1_1;
        Double p2_1;
        if (point.contains(",")) {
            p = Double.valueOf(point.split(",")[0].trim());
        } else {
            p = Double.valueOf(point.split(" ")[0]);
        }
        if (startPoint.contains(",")) {
            p1_1 = Double.valueOf(startPoint.split(",")[0].trim());
        } else {
            p1_1 = Double.valueOf(startPoint.split(" ")[0]);
        }
        if (endPoint.contains(",")) {
            p2_1 = Double.valueOf(endPoint.split(",")[0].trim());
        } else {
            p2_1 = Double.valueOf(endPoint.split(" ")[0]);
        }
        Double p1;
        Double p2;
        if (p1_1 > p2_1) {
            p1 = p2_1;
            p2 = p1_1;
        } else {
            p1 = p1_1;
            p2 = p2_1;
        }
        return p.compareTo(p1) > 0 && p.compareTo(p2) < 0;
//        return p.compareTo(p1) < 0 && p.compareTo(p2) > 0;
    }

    public static int comparePointLon(String startPoint, String endPoint) {
        Double p1;
        Double p2;
        if (startPoint.contains(",")) {
            p1 = Double.valueOf(startPoint.split(",")[0].trim());
        } else {
            p1 = Double.valueOf(startPoint.trim().split(" ")[0]);
        }
        if (endPoint.contains(",")) {
            p2 = Double.valueOf(endPoint.split(",")[0].trim());
        } else {
            p2 = Double.valueOf(endPoint.trim().split(" ")[0]);
        }
        return p1.compareTo(p2);//p2大于p1返回负值
    }

    public static int comparePointLat(String startPoint, String endPoint) {
        Double p1;
        Double p2;
        if (startPoint.contains(",")) {
            p1 = Double.valueOf(startPoint.split(",")[1].trim());
        } else {
            p1 = Double.valueOf(startPoint.trim().split(" ")[1]);
        }
        if (endPoint.contains(",")) {
            p2 = Double.valueOf(endPoint.split(",")[1].trim());
        } else {
            p2 = Double.valueOf(endPoint.trim().split(" ")[1]);
        }
        return p1.compareTo(p2);//p2大于p1返回负值
    }

    //求两点的中点
    public static String midpoint(String point1, String point2) {
        Double point1_1;
        Double point1_2;
        Double point2_1;
        Double point2_2;
        if (point1.contains(",")) {
            point1_1 = Double.valueOf(point1.split(",")[0].trim());
        } else {
            point1_1 = Double.valueOf(point1.trim().split(" ")[0]);
        }
        if (point1.contains(",")) {
            point1_2 = Double.valueOf(point1.split(",")[1].trim());
        } else {
            point1_2 = Double.valueOf(point1.trim().split(" ")[1]);
        }

        if (point2.contains(",")) {
            point2_1 = Double.valueOf(point2.split(",")[0].trim());
        } else {
            point2_1 = Double.valueOf(point2.trim().split(" ")[0]);
        }
        if (point2.contains(",")) {
            point2_2 = Double.valueOf(point2.split(",")[1].trim());
        } else {
            point2_2 = Double.valueOf(point2.trim().split(" ")[1]);
        }

        double point_1 = (point1_1 + point2_1) / 2.0;
        double point_2 = (point1_2 + point2_2) / 2.0;
        return "" + point_1 + "," + point_2;
    }


    //点到直线的距离
    public static double pointToLineDistance(String point, String startPoint, String endPoint) {
        String gpsPointToLine = getGPSPointToLine(point, startPoint, endPoint);
        double result = getDistance(gpsPointToLine, point);
        return result;

    }


    //求解区域最左边和最右边点
    public static Map<String, String> areaLeftAndRightPoint(List<String> area) {
        Map<String, String> result = new HashMap<>();
        List<Double> lonPointList = new ArrayList<>();
        Map<Double, String> pointMessageMap = new HashMap<>();
        for (String s : area) {
            double lonPoint;
            if (s.contains(",")) {
                lonPoint = Double.valueOf(s.split(",")[0].trim());
            } else {
                lonPoint = Double.valueOf(s.trim().split(" ")[0]);
            }
            lonPointList.add(lonPoint);
            pointMessageMap.put(lonPoint, s);
        }
        Collections.sort(lonPointList);
        result.put("left", pointMessageMap.get(lonPointList.get(0)));
        result.put("right", pointMessageMap.get(lonPointList.get(lonPointList.size() - 1)));
        return result;
    }

    /**
     * 传入一个数列x计算平均值
     *
     * @param x
     * @return 平均值
     */
    public static double average(Double[] x) {
        int n = x.length;            //数列元素个数
        double sum = 0;
        for (double i : x) {        //求和
            sum += i;
        }
        return sum / n;
    }

    /**
     * 传入一个数列x计算方差
     * 方差s^2=[（x1-x）^2+（x2-x）^2+......（xn-x）^2]/（n）（x为平均数）
     *
     * @param x 要计算的数列
     * @return 方差
     */
    public static double variance(Double[] x) {
        int n = x.length;            //数列元素个数
        double avg = average(x);    //求平均值
        double var = 0;
        for (double i : x) {
            var += (i - avg) * (i - avg);    //（x1-x）^2+（x2-x）^2+......（xn-x）^2
        }
        return var / n;
    }

    /**
     * 判断是否在同一个方向
     *
     * @param dir1
     * @param dir2
     * @return
     */
    public static boolean samedir(double dir1, double dir2, double offset) {
        double diff = Math.abs(dir2 - dir1);
        double acuteAngle = Math.min(diff, 360 - diff);
        return acuteAngle < offset;
    }

    // Method to convert km/h to m/s using BigDecimal and rounding to the nearest integer
    public static BigDecimal kmhToMs(BigDecimal kmh) {
        BigDecimal divisor = new BigDecimal("3.6");
        return kmh.divide(divisor, 2, RoundingMode.HALF_UP); // rounding to 0 decimal places
    }

    /**
     * 验证纬度是否在合规范围内
     *
     * @param latitude 纬度
     * @return 如果纬度在 -90 到 90 之间，返回 true，否则返回 false
     */
    public static boolean isValidLatitude(double latitude) {
        return latitude >= -90 && latitude <= 90;
    }

    /**
     * 验证经度是否在合规范围内
     *
     * @param longitude 经度
     * @return 如果经度在 -180 到 180 之间，返回 true，否则返回 false
     */
    public static boolean isValidLongitude(double longitude) {
        return longitude >= -180 && longitude <= 180;
    }

    /**
     * 验证经纬度是否在合规范围内
     *
     * @param latitude  纬度
     * @param longitude 经度
     * @return 如果经纬度都在合规范围内，返回 true，否则返回 false
     */
    public static boolean isValidCoordinates(double longitude, double latitude) {
        return isValidLatitude(latitude) && isValidLongitude(longitude);
    }

    /**
     * 判断Double的值是否为0
     *
     * @param value
     * @return
     */
    public static boolean isDoubleZero(double value) {
        return Math.abs(value) < 1e-10;
    }

    /**
     * 经纬度坐标转GeoHash
     *
     * @param position
     * @param precision
     * @return
     */
    public static String lonlatToGeohash(String position, int precision) {
        String[] split = position.trim().split(",");
        return GeohashUtils.encodeLatLon(Double.valueOf(split[0]), Double.valueOf(split[1]), precision);
    }

    /**
     * https://tech.meituan.com/2014/09/05/lucene-distance.html
     * 地理空间距离计算优化
     *
     * @param point1
     * @param point2
     * @return
     */
    public static double distanceSimplifyMore(String point1, String point2) {
        Double lng1 = Double.valueOf(point1.split(",")[0]);
        Double lat1 = Double.valueOf(point1.split(",")[1]);
        Double lng2 = Double.valueOf(point2.split(",")[0]);
        Double lat2 = Double.valueOf(point2.split(",")[1]);
        return distanceSimplifyMore(lat1, lng1, lat2, lng2, trainPolyFit);
    }

    public static double distanceSimplifyMore(double lat1, double lng1, double lat2, double lng2, double[] a) {
        //1) 计算三个参数
        double dx = lng1 - lng2; // 经度差值
        double dy = lat1 - lat2; // 纬度差值
        double b = (lat1 + lat2) / 2.0; // 平均纬度
        //2) 计算东西方向距离和南北方向距离(单位：米)，东西距离采用三阶多项式
        double Lx = (a[3] * b * b * b + a[2] * b * b + a[1] * b + a[0]) * rad(dx) * PublicMethod.EARTH_RADIUS; // 东西距离
        double Ly = PublicMethod.EARTH_RADIUS * rad(dy); // 南北距离
        //3) 用平面的矩形对角距离公式计算总距离
        return Math.sqrt(Lx * Lx + Ly * Ly);
    }


}
