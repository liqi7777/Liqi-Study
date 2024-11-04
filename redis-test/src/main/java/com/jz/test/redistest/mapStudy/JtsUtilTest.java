package com.jz.test.redistest.mapStudy;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.operation.buffer.BufferParameters;
import org.locationtech.jts.operation.distance.DistanceOp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liqi
 * create  2024/7/6 10:16 下午
 */
@Slf4j
public class JtsUtilTest {

    /**
     * 判断点是否在多边形内！
     */
    @Test
    public void test01() {
        Coordinate c = JtsUtil.c("121.64217578123373,31.340339273679334");
        List<Coordinate> coordinateList = JtsUtil.c("121.64154247612032123 31.34069102687375263, 121.64221706788437416 31.34033951545647056, 121.64176475539889566 31.33972566279761196, 121.64109791756315815 31.34007846653627993", ",");
        Polygon fencePloygon = JtsUtil.poloygon(coordinateList.toArray(new Coordinate[coordinateList.size()]));
        Point point = JtsUtil.p(c);
        boolean contains = fencePloygon.contains(point);
        Assert.assertTrue(contains);
    }


    /**
     * 获取点到线段上最近的点
     * <p>
     * 在 JTS（Java Topology Suite）中，LineSegment 和 LineString 都表示线性几何体，但它们有着不同的含义和用途：
     * 1. LineSegment：
     * 定义: LineSegment 表示一个简单的线段，仅由两个点（起点和终点）定义。
     * 用途: 它是一种轻量级的几何对象，通常用于简单的线段计算，比如计算长度、方向、投影等。
     * 特点:
     * 它不是真正的 Geometry 对象，因此不能直接用于 JTS 的拓扑操作（如 intersects()、intersection() 等）。
     * LineSegment 是表示直线段的一种简化工具类，更适合局部几何运算
     * 常见方法：
     * LineSegment.getLength()：返回线段的长度。
     * LineSegment.distance(Point p)：计算线段到点的最小距离。
     * LineSegment.midPoint()：返回线段的中点。
     * LineSegment.intersection(LineSegment ls)：计算两个线段的交点。
     * 示例：
     * Coordinate p1 = new Coordinate(0, 0);
     * Coordinate p2 = new Coordinate(1, 1);
     * LineSegment lineSegment = new LineSegment(p1, p2);
     * <p>
     * double length = lineSegment.getLength();  // 计算线段的长度
     * Coordinate mid = lineSegment.midPoint();  // 获取中点
     * System.out.println("长度: " + length);
     * System.out.println("中点: " + mid);
     * <p>
     * 2. LineString：
     * 定义: LineString 是 JTS 中的一个几何对象，表示由多个连续点（至少两个点）连接组成的折线或多段线。
     * 用途: LineString 是 JTS 的 Geometry 子类，因此可以用于各种拓扑和几何操作，如相交、合并、距离计算等。
     * 特点:
     * LineString 可以由任意数量的点组成，表示一条复杂的折线。
     * 可以和其他 Geometry 对象（如 Polygon、Point）进行拓扑运算（如 intersection()、contains() 等）。
     * 更适合用于复杂几何操作，比如路径分析、道路网处理等。
     * 常见方法：
     * LineString.getLength()：返回折线的长度。
     * LineString.intersects(Geometry g)：判断 LineString 是否与另一个几何对象相交。
     * LineString.getCoordinates()：返回组成该 LineString 的所有点
     * 示例：
     * GeometryFactory geometryFactory = new GeometryFactory();
     * Coordinate[] coordinates = new Coordinate[] {
     * new Coordinate(0, 0),
     * new Coordinate(1, 1),
     * new Coordinate(2, 2)
     * };
     * LineString lineString = geometryFactory.createLineString(coordinates);
     * <p>
     * double length = lineString.getLength();  // 计算折线的长度
     * System.out.println("长度: " + length);
     */
    @Test
    public void test02() {
        //LINESTRING(122.00872559770035 29.778251160951307,122.00883318905616 29.778170638806007)
        LineSegment lineSegment = JtsUtil.getLS("122.00872559770035,29.778251160951307", "122.00883318905616,29.778170638806007");
        Coordinate c = JtsUtil.c("122.0088644933544,29.778238645783823");
        Coordinate coordinate = lineSegment.closestPoint(c);
        System.out.println(coordinate.toString());
    }

    /**
     * 获取线段的中点坐标
     */
    @Test
    public void test02_1() {
        //LINESTRING(122.00872559770035 29.778251160951307,122.00883318905616 29.778170638806007)
        LineSegment lineSegment = JtsUtil.getLS("122.00872559770035,29.778251160951307", "122.00883318905616,29.778170638806007");
        Coordinate coordinate = lineSegment.midPoint();
        log.info("获取中点坐标-1:{}", JSONObject.toJSONString(coordinate));
    }


    /**
     * 获取点在某个方向的指定距离的新坐标点
     */
    @Test
    public void test02_3() {
        Coordinate coordinate = JtsUtil.c("122.00872559770035,29.778251160951307");
        Coordinate relativePos = JtsUtil.getRelativePos(coordinate, 30.0, 90.0);
        log.info("偏移坐标点-1:{}", relativePos.getX() + "," + relativePos.getY());

        Coordinate relativePos1 = GtUtil.getRelativePos(coordinate, 30.0, 90.0);
        log.info("偏移坐标点-2:{}", relativePos1.getX() + "," + relativePos1.getY());
    }


    /**
     * 获取多边形中心点坐标(JTS 的 Centroid 类)
     */
    @Test
    public void test02_2() {
        String wkt = "122.03195707768225 29.799747355968343,122.03198514972944 29.799770861582928,122.03477378218959 29.797306815418782,122.03475024678596 29.797285639027415,122.03195707768225 29.79974735596834";
        List<Coordinate> coordinateList = JtsUtil.c(wkt, ",");
        Coordinate[] coordinates = coordinateList.toArray(new Coordinate[coordinateList.size()]);
        Polygon poloygon = JtsUtil.poloygon(coordinates);
        // 使用 Centroid 类计算质心
        Coordinate centroid = poloygon.getCentroid().getCoordinate();
        log.info("质心坐标-1: " + centroid.x + ", " + centroid.y);

        //如果需要计算多边形的中心点坐标，则使用 JTS 的 Centroid 类 的方法更准确！

        Coordinate center = JtsUtil.getCenter(coordinates);
        log.info("质心坐标-2: " + center.x + ", " + center.y);
    }


    /**
     * 计算当前点在线段上的投影点
     */
    @Test
    public void test03() {
        //LINESTRING(122.00872559770035 29.778251160951307,122.00883318905616 29.778170638806007)
        LineSegment lineSegment = JtsUtil.getLS(JtsUtil.gsc("122.00872559770035,29.778251160951307"), JtsUtil.gsc("122.00883318905616,29.778170638806007"));
        //计算投影因子
        // factor 是一个标量，通常在 0 到 1 之间，表示投影点在 lineSegment 上的位置。如果 factor < 0，则投影点在 lineSegment 的起点之前；如果 factor > 1，则在终点之后；如果 0 ≤ factor ≤ 1，则在线段内部
        double factor = lineSegment.projectionFactor(JtsUtil.gsc("122.00894766833316,29.778177080628005"));
        Coordinate c = lineSegment.pointAlong(factor);
        Coordinate coordinate = TransForm.to84(c);
        System.out.println(coordinate.toString());
    }


    @Test
    public void test04() {
        //122.01035253429386 29.780908625470115,122.01091259892632 29.780487364336615,122.01142516691155 29.78098976982704,122.01083145882055 29.78135232890807
        String wkt = "122.01035253429386 29.780908625470115,122.01091259892632 29.780487364336615,122.01142516691155 29.78098976982704,122.01083145882055 29.78135232890807";
        List<Coordinate> coordinateList = JtsUtil.c(wkt, ",");
        Coordinate[] coordinates = coordinateList.toArray(new Coordinate[coordinateList.size()]);
        LinearRing innerRing = JtsUtil.linearRing(coordinates);
        Polygon polygon = JtsUtil.poloygon(coordinates);
        // 对多边形进行缓冲区操作，生成一个新的几何对象，参数为缓冲区大小、负的四舍五入次数和缓冲区端点类型
        Geometry bufferPolygon = polygon.buffer(JtsUtil.length2Lat(50), -1000, BufferParameters.CAP_FLAT);
        String s = JtsUtil.toWkt(bufferPolygon.getCoordinates());
        System.out.println(s);
    }


    /**
     * 点距离多边形的最短距离，最长距离
     */
    @Test
    public void test05() {
        //IECS
        //121.64817222312245804,31.33611617530941018 121.64815349767339114,31.33609062600389805 121.64813516386097092,31.33609356670865509 121.6481298652759051,31.33609697120805748 121.64806304790359093,31.33613308749224302 121.64792670097138227,31.33620643688000129 121.64792086777207203,31.33620930719154529 121.64792046203601217,31.33621616564062506 121.64793971733602973,31.33624197869858818 121.64800365884974553,31.33633015944279165 121.64800796682979467,31.33633019428978272 121.6480145321307873,31.33632713422344196 121.64821790350468689,31.33621748540264207 121.64823154380634662,31.33621649863026803 121.64823638739991907,31.3362050744684808 121.64821797073054199,31.33617916051054308
        //122.01035253429386 29.780908625470115,122.01091259892632 29.780487364336615,122.01142516691155 29.78098976982704,122.01083145882055 29.78135232890807,122.0103525342938 29.780908594721993
        //122.01234033732771 29.78338509045721,122.01323233501746 29.782760212636727,122.01406351468276 29.783567742224918,122.01300257803679 29.784015859355208,122.013016093153 29.78389776875193,122.01234033732771 29.78338509045721
        String wkt = "122.01234033732771 29.78338509045721,122.01323233501746 29.782760212636727,122.01406351468276 29.783567742224918,122.01300257803679 29.784015859355208,122.013016093153 29.78389776875193,122.01234033732771 29.78338509045721";
        List<Coordinate> coordinateList = JtsUtil.c(wkt, ",");
        Coordinate[] coordinates = coordinateList.toArray(new Coordinate[coordinateList.size()]);
        Coordinate[] projectedCoordinates = new Coordinate[coordinates.length];
        for (int i = 0; i < coordinates.length; i++) {
            projectedCoordinates[i] = TransForm.toGs(coordinates[i]);
        }
        Polygon polygon = JtsUtil.poloygon(projectedCoordinates);
        String position = "122.01051115870297,29.780507879630033";
        Coordinate carCoordinate = JtsUtil.gsc(position);
        Point carPoint = JtsUtil.p(JtsUtil.gsc(position));
        DistanceOp distanceOp = new DistanceOp(polygon, carPoint);
        double distance = distanceOp.distance();
        System.out.println("最短距离:" + distance);
        Coordinate[] coordinates1 = distanceOp.nearestPoints();
        Coordinate[] coordinates2 = new Coordinate[coordinates1.length];
        for (int i = 0; i < coordinates1.length; i++) {
            coordinates2[i] = TransForm.to84(coordinates1[i]);
        }
        System.out.println(JtsUtil.toWkt(coordinates2));

        double maxDistance = 0.0;
        for (int i = 0; i < projectedCoordinates.length - 1; i++) {
            LineSegment lineSegment = JtsUtil.getLS(projectedCoordinates[i], projectedCoordinates[i + 1]);
            double distance1 = lineSegment.distance(carCoordinate);
            if (distance1 > maxDistance) {
                maxDistance = distance1;
            }
        }
        System.out.println("最长距离:" + maxDistance);

        double distance1 = JtsUtil.pointToWktNearestDistance(position, JtsUtil.parseWktToStringList(wkt));
        System.out.println("最短距离01:" + distance1);

        double distance2 = JtsUtil.pointToWktFarthestDistance(position, JtsUtil.parseWktToStringList(wkt));
        System.out.println("最长距离01:" + distance2);

    }

    /**
     * 点到直线距离
     */
    @Test
    public void test06() {
        double v = PublicMethod.pointToLineDistance("122.01051115870297,29.780507879630033", "122.01234033732771,29.78338509045721", "122.01323233501746,29.782760212636727,");
        System.out.println("最短距离" + v);

        double v2 = PublicMethod.pointToLineDistance("122.01051115870297,29.780507879630033", "122.01323233501746,29.782760212636727,", "122.01406351468276,29.783567742224918");
        System.out.println("最长距离" + v2);

    }

    /**
     * 点到直线的距离
     */
    @Test
    public void test07() {
        double v = PublicMethod.getDistance("122.01107022528139,29.780788654916446", "122.01135075602657,29.78058887387626");
        System.out.println(v);
    }

    /**
     * 点到wkt的最远距离
     */
    @Test
    public void test08() {
        String wkt = "122.01234033732771 29.78338509045721,122.01323233501746 29.782760212636727,122.01406351468276 29.783567742224918,122.01300257803679 29.784015859355208,122.013016093153 29.78389776875193,122.01234033732771 29.78338509045721";
        String position = "122.01303407458917,29.783178509982022";
        double distance1 = JtsUtil.pointToWktFarthestDistance(position, JtsUtil.parseWktToStringList(wkt));
        System.out.println(distance1);
    }


    /**
     * 点到wkt的最短距离
     */
    @Test
    public void test09() {
        // String position = "122.00412077408805,29.774389741087617";
        // ArrayList<String> strings = Lists.newArrayList("122.00259120486743,29.774019471251975"
        //         , "122.00361358767394,29.77327615601653"
        //         , "122.004406304777,29.77401918538574"
        //         , "122.00337791066849,29.774803290361476",
        //         "122.00337791066845,29.77480326153061", "122.00337791066845,29.77480326153061", "122.00259120486743,29.774019471251975");
        String position = "122.01393312672883,29.7829581732694";

        ArrayList<String> strings = Lists.newArrayList(
                "122.01405459627267,29.782562954363065",
                "122.01429286988338,29.7828123024754",
                "122.0134601654106,29.783417797018192",
                "122.01322189224587,29.78316844739597");
        double distance1 = JtsUtil.pointToWktNearestDistance(position, strings);
        // double distance2 = JtsUtil.pointToWktFarthestDistance(position, strings);
        System.out.println(distance1);
        // System.out.println(distance2);
    }


    @Test
    public void test11() {
        BigDecimal bigDecimal = new BigDecimal("0.0");
        System.out.println(bigDecimal.compareTo(BigDecimal.valueOf(0)));
    }

    @Test
    public void test12() {
        // boolean doubleZero = PublicMethod.isDoubleZero(0.0);
        // System.out.println(doubleZero);
        System.out.println(LocalDateTimeUtil.toEpochMilli(LocalDateTime.now()));
    }

    /**
     * 获取方向角度
     */
    @Test
    public void test13() {
        String c1 = "122.00904061210488,29.778033685356867";
        String c2 = "122.00895083823701,29.778054448107177";
        double angle = JtsUtil.getAngle(JtsUtil.c(c1), JtsUtil.c(c2));
        log.info("获取方向角度-1:{}", angle);

        double angle1 = GtUtil.getAngle(JtsUtil.c(c1), JtsUtil.c(c2));
        log.info("获取方向角度-2:{}", angle1);
    }


    /**
     * 获取两个线段的交点
     */
    @Test
    public void test13_0() {
        String in1 = "122.003260283676 29.771673717046";
        String in2 = "122.003400499999 29.771569750001";

        String out1 = "122.003467199998 29.77165635";
        String out2 = "122.0033364 29.7717535";

        LineSegment inLs = JtsUtil.getLS(TransForm.toGs(JtsUtil.c(in1)), TransForm.toGs(JtsUtil.c(in2)));
        LineSegment outLs = JtsUtil.getLS(TransForm.toGs(JtsUtil.c(out1)), TransForm.toGs(JtsUtil.c(out2)));


        Coordinate coordinate = inLs.lineIntersection(outLs);
        Coordinate coordinate1 = TransForm.to84(coordinate);
        log.info("相交的坐标点:{}", JtsUtil.toStr(coordinate1));
    }

    /**
     * 平滑曲线
     */
    @Test
    public void test14() {
        List<Coordinate> pass1 = JtsUtil.getPinHuaPassPoints("122.003260283676 29.771673717046", "122.003400499999 29.771569750001", "122.003467199998 29.77165635", "122.0033364 29.7717535");
        List<Coordinate> pass2 = JtsUtil.getPinHuaPassPoints("122.003260283676 29.771673717046", "122.003400499999 29.771569750001", "122.0036592 29.7717218", "122.005741644943 29.773743357678");
        String s1 = StrUtil.format("{}:{}:{}", "122.003260283676 29.771673717046,122.003400499999 29.771569750001", pass1, "122.003467199998 29.77165635,122.0033364 29.7717535");
        String s2 = StrUtil.format("{}:{}:{}", "122.003260283676 29.771673717046,122.003400499999 29.771569750001", pass2, "122.0036592 29.7717218,122.005741644943 29.773743357678");
        System.out.println("掉头:" + s1);
        System.out.println("左转:" + s2);
    }

}