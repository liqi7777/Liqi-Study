package com.jz.test.redistest.mapStudy;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.algorithm.Angle;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.util.AffineTransformation;
import org.locationtech.jts.geom.util.AffineTransformationBuilder;
import org.locationtech.jts.geom.util.NoninvertibleTransformationException;
import org.locationtech.jts.operation.distance.DistanceOp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class JtsUtil {
    /**
     * 地球每纬度的弧长(km)
     */
    public final static double EARTH_ARC = 111319.55;
    public static GeometryFactory gf = new GeometryFactory();

    public static List<Coordinate> c(String ps, String split) {
        List<Coordinate> coordinates = null;
        try {
            String[] c = ps.trim().split(split);
            coordinates = Arrays.stream(c).map(str -> c(str)).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("坐标解析出错!{},split{}", ps, split);
            e.printStackTrace();
        }
        return coordinates;
    }

    public static Coordinate[] list2C(List<String> ps) {
        Coordinate[] coordinates = new Coordinate[ps.size()];
        for (int i = 0; i < ps.size(); i++) {
            coordinates[i] = c(ps.get(i));
        }
        return coordinates;
    }

    public static Coordinate[] list2Gsc(List<String> ps) {
        Coordinate[] coordinates = new Coordinate[ps.size()];
        for (int i = 0; i < ps.size(); i++) {
            coordinates[i] = gsc(ps.get(i));
        }
        return coordinates;
    }

    public static String commaPoint(String p) {
        if (p != null) {
            p.trim().replace(" ", ",");
        }
        return p;
    }

    public static Coordinate gsc(String c) {
        return TransForm.toGs(JtsUtil.c(c));
    }

    public static Coordinate gsc(double x, double y) {
        return TransForm.toGs(JtsUtil.c(x, y));
    }

    public static Coordinate c(String p) {
        try {
            double x, y;
            p = p.trim();
            if (p.contains(",")) {
                String xy[] = p.split(",");
                x = Double.parseDouble(xy[0].trim());
                y = Double.parseDouble(xy[1].trim());
            } else if (p.contains(" ")) {
                String xy[] = p.split(" ");
                x = Double.parseDouble(xy[0].trim());
                y = Double.parseDouble(xy[1].trim());
            } else {
                throw new Exception("数据异常");
            }
            return new Coordinate(x, y, 0);
        } catch (Exception e) {
            log.error("坐标解析出错!{}", p);
            e.printStackTrace();
        }
        return null;
    }

    public static Coordinate[] c(double[][] doubles) {
        try {
            return Arrays.stream(doubles).map(d -> c(d[0], d[1])).toArray(Coordinate[]::new);
        } catch (Exception e) {
            log.error("坐标解析出错!{}", doubles);
            e.printStackTrace();
        }
        return null;
    }

    public static Coordinate c(double x, double y) {
        try {
            return new Coordinate(x, y, 0);
        } catch (Exception e) {
            log.error("坐标解析出错!{}", x + "," + y);
            e.printStackTrace();
        }
        return null;
    }

    public static Point p(double x, double y) {
        try {
            return p(new Coordinate(x, y, 0));
        } catch (Exception e) {
            log.error("坐标解析出错!{}", x + "," + y);
            e.printStackTrace();
        }
        return null;
    }

    public static Point p(String c) {
        try {
            return p(c(c));
        } catch (Exception e) {
            log.error("坐标解析出错!{}", c);
            e.printStackTrace();
        }
        return null;
    }

    public static Point p(Coordinate c) {
        try {
            return gf.createPoint(c);
        } catch (Exception e) {
            log.error("坐标解析出错!{}", c);
            e.printStackTrace();
        }
        return null;
    }

    public static double[] getDouble(String p) {
        try {
            double x, y;
            p = p.trim();
            if (p.contains(",")) {
                String xy[] = p.split(",");
                x = Double.parseDouble(xy[0].trim());
                y = Double.parseDouble(xy[1].trim());
            } else if (p.contains(" ")) {
                String xy[] = p.split(" ");
                x = Double.parseDouble(xy[0].trim());
                y = Double.parseDouble(xy[1].trim());
            } else {
                throw new Exception("数据异常");
            }
            return new double[]{x, y};
        } catch (Exception e) {
            log.error("坐标解析出错!{}", p);
            e.printStackTrace();
        }
//        return new double[]{0, 0};
        return null;
    }

    public static Double[][] getDoubles(Coordinate... c) {
        Double[][] doubles = new Double[c.length][2];
        for (int i = 0; i < c.length; i++) {
            doubles[i] = new Double[]{c[i].x, c[i].y};
        }
        return doubles;
    }

    public static double[][] getdoubles(Coordinate... c) {
        double[][] doubles = new double[c.length][2];
        for (int i = 0; i < c.length; i++) {
            doubles[i] = new double[]{c[i].x, c[i].y};
        }
        return doubles;
    }

    public static String getCenter(List<String> wkt) {
        Coordinate[] cs = new Coordinate[wkt.size()];
        for (int i = 0; i < wkt.size(); i++) {
            cs[i] = c(wkt.get(i));
        }
        return toStr(getCenter(cs));
    }

    public static String getCenter(String... c) {
        Coordinate[] cs = new Coordinate[c.length];
        for (int i = 0; i < c.length; i++) {
            cs[i] = c(c[i]);
        }
        return toStr(getCenter(cs));
    }

    public static Coordinate getCenter(Coordinate... coordinates) {
        try {
            double x = 0, y = 0;
            for (int i = 0; i < coordinates.length; i++) {
                x += coordinates[i].x;
                y += coordinates[i].y;
            }
            return new Coordinate(x / coordinates.length, y / coordinates.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param wkt
     * @return
     */
    public static Coordinate[] parseWkt(String wkt, String split) {
        try {
            Coordinate[] cs = Arrays.stream(wkt.trim().split(split)).map(i -> c(i)).toArray(Coordinate[]::new);
            return cs;
        } catch (Exception e) {
            log.error("坐标解析出错!{}", wkt);
            e.printStackTrace();
        }
        return null;
    }

    public static Coordinate[] parseWktByComma(String wkt) {
        return parseWkt(wkt, ",");
    }

    public static Polygon poloygon(Coordinate[] wkt) {
        try {
            //未闭合直接添加第一个点
            int length = wkt.length;
            Coordinate[] tmp = wkt;
            if (!wkt[0].equals2D(wkt[length - 1])) {
                tmp = ArrayUtil.append(wkt, wkt[0]);
            }
            return gf.createPolygon(tmp);
        } catch (Exception e) {
            log.error("坐标解析出错!{}", wkt);
            e.printStackTrace();
        }
        return null;
    }

    public static LineString lineString(Coordinate[] wkt) {
        try {
            return gf.createLineString(wkt);
        } catch (Exception e) {
            log.error("坐标解析出错!{}", wkt);
            e.printStackTrace();
        }
        return null;
    }

    public static LinearRing linearRing(Coordinate[] wkt) {
        try {
            //未闭合直接添加第一个点
            int length = wkt.length;
            Coordinate[] tmp = wkt;
            if (!wkt[0].equals2D(wkt[length - 1])) {
                tmp = ArrayUtil.append(wkt, wkt[0]);
            }
            return gf.createLinearRing(tmp);
        } catch (Exception e) {
            log.error("坐标解析出错!{}", wkt);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 构建不同坐标系的放射对象 (平移或旋转)
     */
    public static void convert() {
        Coordinate p1 = c(121.643909, 31.338692);
        Coordinate p2 = c(121.643909, 31.338692);
        Coordinate p3 = c(121.646912, 31.337112);
        Coordinate cp1 = c(121.643909, 31.338692);
        Coordinate cp2 = c(121.643909, 31.338692);
        Coordinate cp3 = c(121.646912, 31.337112);
        // 建立仿射变换对象
        AffineTransformation pixel2gs = new AffineTransformationBuilder(p1, p2, p3, cp1, cp2, cp3).getTransformation();
        try {
            AffineTransformation gs2pixel = pixel2gs.getInverse();
        } catch (NoninvertibleTransformationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 已知相对方向和角度计算另一个位置（球面坐标系，经纬度！）
     *
     * @param p
     * @param distance
     * @param degree
     * @return
     */
    public static Coordinate getRelativePos(Coordinate p, double distance, double degree) {
        degree = rad(degree);
        // 将距离转换成经度的计算公式
        double lon = p.x + (distance * Math.sin(degree)) / (EARTH_ARC * Math.cos(rad(p.y)));
        // 将距离转换成纬度的计算公式
        double lat = p.y + (distance * Math.cos(degree)) / (EARTH_ARC);
        return new Coordinate(lon, lat);
    }

    /**
     * 返回se 方向偏转一定方向和距离的位置在沿se方向的相对距离的位置
     *
     * @param s
     * @param e
     * @param shiftDegree 顺时针方向为正
     * @param shiftDis
     * @param relaticeDis
     * @return
     */
    public static Coordinate getRelativePos(Coordinate s, Coordinate e, double shiftDegree, double shiftDis, double relaticeDis) {
        double x, y;
        if (s.equals2D(e)) {
            return s.copy();
        } else {
            //(-180,180)
            //原角度
            double _rad = Math.atan2(e.y - s.y, e.x - s.x);
            double _degree = Math.toDegrees(_rad);
            //矫正角度为0-360
            double degree = (_degree + 360 - shiftDegree + 360) % 360;
            double rad = Math.toRadians(degree);
            // 将距离转换成经度的计算公式
            x = s.x + shiftDis * Math.cos(rad) + relaticeDis * Math.cos(_rad);
            // 将距离转换成纬度的计算公式
            y = s.y + shiftDis * Math.sin(rad) + relaticeDis * Math.sin(_rad);
        }
        return new Coordinate(x, y);
    }

    /**
     * 已知另一位置和相对距离计算另一个位置
     *
     * @param s
     * @param e
     * @param relativeDistance
     * @return
     */
    public static Coordinate getRelativePos(Coordinate s, Coordinate e, double relativeDistance) {
        double c = s.distance(e);
        double _x, _y;
        if (e.getX() - s.getX() == 0) {
            _x = s.getX();
        } else {
            _x = (relativeDistance / c) * (e.getX() - s.getX()) + s.getX();
        }
        if (e.getY() - s.getY() == 0) {
            _y = s.getY();
        } else {
            _y = (relativeDistance / c) * (e.getY() - s.getY()) + s.getY();
        }
        return new Coordinate(_x, _y);
    }

    public static Coordinate getRelativePos(String s, String e, double relativeDistance) {
        return getRelativePos(c(s), c(e), relativeDistance);
    }

//    public static boolean inPloygon(Coordinate c, Coordinate[] wkt) {
//        if (wkt != null) {
//            return inPloygon(new double[]{c.x, c.y}, Arrays.stream(wkt).map(i -> new double[]{i.x, i.y}).collect(Collectors.toList()));
//        }
//        return false;
//    }

    public static boolean inPloygon(String p, List<String> ps) {
        return inPloygon(JtsUtil.c(p), JtsUtil.list2C(ps));
    }

    public static boolean inPloygon(Coordinate p, Coordinate[] ps) {
        return JtsUtil.poloygon(ps).contains(JtsUtil.p(p));
    }

    //内积计算是否在区域内
//    public static boolean inPloygon(double[] c, List<double[]> wkt) {
//        return JtsUtil.poloygon(ps).contains(JtsUtil.p(c));
//        小于0-右侧;等于0-同线;大于0左侧
//        boolean inner = false;
//        double lastflag = 0;
//        for (int i = 0; i < wkt.size(); i++) {
//            int next = i + 1;
//            if (i == wkt.size() - 1) {
//                if (Objects.equals(wkt.get(i)[0], wkt.get(0)[0]) && Objects.equals(wkt.get(i)[1], wkt.get(0)[1])) {
//                    //最后一个点和第一个点相等
//                    break;
//                } else {
//                    next = 0;
//                }
//            }
//            double x1 = wkt.get(next)[0] - wkt.get(i)[0],
//                    y1 = wkt.get(next)[1] - wkt.get(i)[1],
//                    x2 = c[0] - wkt.get(i)[0],
//                    y2 = c[1] - wkt.get(i)[1];
//            double tmpFlag = x1 * y2 - x2 * y1;
//            if (tmpFlag * lastflag >= 0) {
////                方向相同
//                lastflag = tmpFlag;
//                inner = true;
//            } else if (tmpFlag * lastflag < 0) {
//                inner = false;
//                break;
//            }
//        }
//        return inner;
//    }

    /**
     * 向量内积 a*b=|a||b|cosa 一般用来判断向量OA与OB是否同向 大于0同向
     *
     * @param A
     * @param O
     * @param B
     * @return
     */
    public static double innerProduct(Coordinate O, Coordinate A, Coordinate B) {
        double x1 = A.x - O.x, y1 = A.y - O.y, x2 = B.x - O.x, y2 = B.y - O.y;
        return x1 * x2 + y1 * y2;
    }

    /**
     * 向量外积 2|aXb|=|a||b|sina=x1y2-x2y1 一般用来判断向量OA与OB的方位 小于0则OAC为顺时针 即B在OA的右侧
     *
     * @param A
     * @param O
     * @param B
     * @return
     */
    public static double outerProduct(Coordinate O, Coordinate A, Coordinate B) {
        double x1 = A.x - O.x, y1 = A.y - O.y, x2 = B.x - O.x, y2 = B.y - O.y;
        return x1 * y2 - x2 * y1;
    }

    /**
     * 归一化到 -180,180
     *
     * @param degree
     * @return
     */
    public static double degreeNormalizePi(double degree) {
        return degreeNormalize(degree, 180);
    }

    /**
     * 归一化到  0,360
     *
     * @param degree
     * @return
     */
    public static double degreeNormalize2Pi(double degree) {
        return degreeNormalize(degree, 360);
    }
//
//    //左起为第一车道 负数表示左边的车道
//    public static Coordinate[] getLaneEdge(Road r, int index) {
//        try {
//            if (index != 0) {
//                boolean right = index > 0;
//                Coordinate s = right ? r.getLs().p0 : r.getLs().p1;
//                Coordinate e = right ? r.getLs().p1 : r.getLs().p0;
//                double rightDir = degreeNormalizePi(r.getDegree() - 90);
//                double leftDir = degreeNormalizePi(r.getDegree() + 90);
//                double dir = right ? rightDir : leftDir;
////            double leftDir = Math.abs((r.getDegree() + 90) % 360);
////        double rightDir = Math.abs((r.getDir() - 90) % 360);
////        相对中线距离 左为正
//                double lEndpoint = 0, rEndpoint = 0;
////                double totalWidth = (right ? r.getRLaneNum() : r.getLLaneNum() * r.getLaneWidth()) + r.getInterval() / 2d;
//                //返回整条路的范围
//                //与x轴的角度 逆时针方向 0-360
////                lEndpoint = totalWidth / 2d - 0;
////                rEndpoint = totalWidth / 2d - totalWidth;
//                int lane = Math.abs(index);
//                double shift = (r.getInterval() / 2d) + (lane - 1 + 0.5) * r.getLaneWidth();
//                //左起为第一根车道
////                lEndpoint = totalWidth / 2d - (lane - 1) * r.getLaneWidth();
////                rEndpoint = totalWidth / 2d - (lane) * r.getLaneWidth();
//                Coordinate shifts = getRelativePos(s, shift, dir);
//                Coordinate shifte = getRelativePos(e, shift, dir);
////                Coordinate tr = getRelativePos(e, rEndpoint, leftDir);
////                Coordinate tl = getRelativePos(e, lEndpoint, leftDir);
//                return new Coordinate[]{shifts, shifte};
//            }
//        } catch (Exception e) {
//            log.error("数据异常!{}({})", JSON.toJSONString(r), index);
//        }
//        return null;
//    }

//    /**
//     * 获取道路车道范围[leftBottom,rightBottom,rightTop,leftTop] 左起第一个点 顺时针
//     * 默认1车道,车道宽3.72m, 按道路车道宽度和车道计算范围
//     *
//     * @param r    默认道路起始点在正中心
//     * @param lane 0-整条路 1-左起第一根车道
//     * @return
//     */
//    public static Coordinate[] getRoadWkt(MapEdgeGeom r, int lane) {
//        try {
//            Coordinate s = r.getLs().p0;
//            Coordinate e = r.getLs().p1;
//            double leftDir = degreeNormalizePi(r.getDegree() + 90);
////            double leftDir = Math.abs((r.getDegree() + 90) % 360);
////        double rightDir = Math.abs((r.getDir() - 90) % 360);
////        相对中线距离 左为正
//            double lEndpoint = 0, rEndpoint = 0;
//            double totalWidth = r.getLaneIndex() * r.getLaneWidth();
//            if (lane == 0) {
//                //返回整条路的范围
//                //与x轴的角度 逆时针方向 0-360
//                lEndpoint = totalWidth / 2d - 0;
//                rEndpoint = totalWidth / 2d - totalWidth;
//            } else {
//                //左起为第一根车道
//                lEndpoint = totalWidth / 2d - (lane - 1) * r.getLaneWidth();
//                rEndpoint = totalWidth / 2d - (lane) * r.getLaneWidth();
//            }
//            Coordinate bl = getRelativePos(s, lEndpoint, leftDir);
//            Coordinate br = getRelativePos(s, rEndpoint, leftDir);
//            Coordinate tr = getRelativePos(e, rEndpoint, leftDir);
//            Coordinate tl = getRelativePos(e, lEndpoint, leftDir);
//            return new Coordinate[]{bl, br, tr, tl};
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("数据异常!{}({})", r.getId(), r.getCoordinate());
//        }
//        return null;
//    }

    public static double degreeNormalize(double degree, double maxDegree) {
        double cycle = 360;
        double minDegree = cycle - maxDegree;
        while (degree >= maxDegree)
            degree -= cycle;
        while (degree < minDegree)
            degree += cycle;
        return degree;
    }

    /**
     * 判断是否同向 默认阈值为90
     *
     * @param dir1
     * @param dir2
     * @return
     */
    public static boolean sameDir(double dir1, double dir2) {
        return sameDir(dir1, dir2, 90);
    }

    /**
     * 判断是否同向
     *
     * @param dir1
     * @param dir2
     * @return
     */
    public static boolean sameDir(double dir1, double dir2, double angle) {
        double diff = Math.abs(dir2 - dir1);
        double acuteAngle = Math.min(diff, 360 - diff);
        return acuteAngle < angle;
    }

    //获取点集在线段内的投影范围 segement 是否只计算0-1内的范围
    public static double[] projectFactor(LineSegment ls, boolean segment, Coordinate... ps) {
//        LineSegment ls = new LineSegment();
//        ls.setCoordinates(p1, p2);
        double max = Double.MIN_VALUE, min = Double.MAX_VALUE;
        for (int i = 0; i < ps.length; i++) {
            double tmp = ls.projectionFactor(ps[i]);
            if (tmp > max) {
                max = tmp;
            }
            if (tmp < min) {
                min = tmp;
            }
        }

        if (segment) {
            if (max < 0) {
                max = 0;
            } else if (max > 1) {
                max = 1;
            }
            if (min < 0) {
                min = 0;
            } else if (min > 1) {
                min = 1;
            }
        }

        return new double[]{min, max};
    }

    public static String toStr(Coordinate c) {
        return c.x + "," + c.y;
    }

    public static String toStr(LineSegment ls) {
        return toStr(ls.p0) + " " + toStr(ls.p1);
    }

    public static String toStr(Coordinate... c) {
        String str = Arrays.stream(c).map(i -> toStr(i)).collect(Collectors.joining(" "));
        return str;
    }

    public static String toStr(Collection<Coordinate> c) {
        String str = c.stream().map(i -> toStr(i)).collect(Collectors.joining(" "));
        return str;
    }


    /**
     * 逆时针递增
     *
     * @param ps
     * @return
     */
    public static double getShiftDegree(Coordinate[] ps) {
//        double x1 = ps[1].x - ps[0].x, y1 = ps[1].y - ps[0].y, x2 = ps[3].x - ps[2].x, y2 = ps[3].y - ps[2].y;
//        double rad = Angle.angleBetweenOriented(new Coordinate(x2, y2), new Coordinate(0d, 0d), new Coordinate(x1, y1));
//        return Angle.toDegrees(rad);
        //纯数字计算有10度左右误差
        if (ps[0] == null || ps[1] == null || ps[2] == null || ps[3] == null || ps[0].equals2D(ps[1]) || ps[2].equals2D(ps[3])) {
            return 0d;
        }
        //已x轴为0度
        double shift = Math.toDegrees(Angle.angle(ps[2], ps[3]) - Angle.angle(ps[0], ps[1]));
        return shift > 0 ? shift : 360d + shift;
    }

    public static double getDegree(Coordinate s, Coordinate e) {
        return Math.toDegrees(Angle.angle(s, e));
    }


    /**
     * 计算贝塞尔曲线
     *
     * @param controlPoint first 起点  ... 控制点  last 终点
     * @param step         0,1 之间
     * @return
     */
    public static List<Coordinate> bsrqx(Coordinate[] controlPoint, double step) {
        List<Coordinate> bezierPoint = new ArrayList<>();
        // u的步长决定了曲线点的精度
        for (double u = 0; u <= 1; u += step) {
            bezierPoint.add(bsrqxProject(controlPoint, u));
        }
        return bezierPoint;
    }

    public static Coordinate bsrqxProject(Coordinate[] controlPoint, double step) {
        int n = controlPoint.length - 1; //
        int i, r;
        double u = step;
        // u的步长决定了曲线点的精度
//        for (u = 0; u <= 1; u += step) {
        Coordinate[] p = new Coordinate[n + 1];
        for (i = 0; i <= n; i++) {
            p[i] = new Coordinate(controlPoint[i].x, controlPoint[i].y);
        }
        for (r = 1; r <= n; r++) {
            for (i = 0; i <= n - r; i++) {
                p[i].x = p[i].x + u * (p[i + 1].x - p[i].x);
                p[i].y = p[i].y + u * (p[i + 1].y - p[i].y);
            }
        }
//            bezierPoint.add(p[0]);
        return p[0];
    }

    public static Coordinate[] wkt2c(String wkt) {
        List<Coordinate> list = new ArrayList<>();
        if (StrUtil.isNotEmpty(wkt)) {
//            sequence=new CoordinateArraySequence()
            list = Arrays.stream(wkt.split(",")).map(p -> c(p)).filter(coordinate -> coordinate != null).collect(Collectors.toList());
        }
        return list.toArray(new Coordinate[0]);
    }

    public static LineSegment getLS(String s, String e) {
        return getLS(c(s), c(e));
    }

    public static LineSegment getLS(Coordinate s, Coordinate e) {
        return new LineSegment(s, e);
    }

    /**
     * @param p
     * @param distance m
     * @param angle
     * @return
     */
    public static Coordinate getRelativePoint(Coordinate p, double distance, double angle) {
        angle = rad(angle);
        // 将距离转换成经度的计算公式
        double lon = p.x + (distance * Math.sin(angle)) / (EARTH_ARC * Math.cos(rad(p.y)));
        // 将距离转换成纬度的计算公式
        double lat = p.y + (distance * Math.cos(angle)) / (EARTH_ARC);
        return new Coordinate(lon, lat);
    }

    /**
     * 转化为弧度(rad)
     */
    public static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public static Coordinate getFoot(String p, String l1, String l2) {
        Coordinate gsc = getFootPoint(JtsUtil.getLS((JtsUtil.gsc(l1)), JtsUtil.gsc(l2)), JtsUtil.gsc(p));
        return TransForm.to84(gsc);
    }

    public static Coordinate getFootPoint(LineSegment ls, Coordinate c) {
        return ls.pointAlong(ls.projectionFactor(c));
    }

    public static boolean isMiddle(String O, String A, String C) {
        return isMiddle(c(O), c(A), c(C));
    }

    //o 在 A,C 中间
    public static boolean isMiddle(Coordinate O, Coordinate A, Coordinate C) {
        return isMiddle(O.x, O.y, A.x, A.y, C.x, C.y);
    }

    //大于0 则13与12同向 等于0则垂直 小于0 反向
    public static double innerProduct(double x1, double y1, double x2, double y2, double x3, double y3) {
        return (x3 - x1) * (x2 - x1) + (y3 - y1) * (y2 - y1);
    }

    //21,23同向且31,32同向
    public static boolean isMiddle(double x1, double y1, double x2, double y2, double x3, double y3) {
        return ip(x1 - x2, y1 - y2, x3 - x2, y3 - y2) >= 0 && ip(x1 - x3, y1 - y3, x2 - x3, y2 - y3) >= 0;
    }

    //12,13同向并且31 32同向
    public static boolean isLeft(double x1, double y1, double x2, double y2, double x3, double y3) {
        return ip(x1 - x2, y1 - y2, x3 - x2, y3 - y2) <= 0;
    }

    //12,13同向并且21 23同向
    public static boolean isRight(double x1, double y1, double x2, double y2, double x3, double y3) {
        return ip(x1 - x3, y1 - y3, x2 - x3, y2 - y3) <= 0;
    }

    //大于0 则13与12同向 等于0则垂直 小于0 反向
    public static double ip(double x1, double y1, double x2, double y2) {
        return x1 * x2 + y1 * y2;
    }

    public static boolean isLeft(String O, String A, String C) {
        return isLeft(c(O), c(A), c(C));
    }

    //o 在 A,C 中间
    public static boolean isLeft(Coordinate O, Coordinate A, Coordinate C) {
        return isLeft(O.x, O.y, A.x, A.y, C.x, C.y);
    }

    public static boolean isRight(String O, String A, String C) {
        return isRight(c(O), c(A), c(C));
    }

    //o 在 A,C 中间
    public static boolean isRight(Coordinate O, Coordinate A, Coordinate C) {
        return isRight(O.x, O.y, A.x, A.y, C.x, C.y);
    }

    /**
     * 转换长度到纬度量度
     *
     * @param l
     * @return
     */
    public static double length2Lat(double l) {
        return l / EARTH_ARC;
    }

    public static String toWkt(Coordinate... coord) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < coord.length; i++) {
            if (i > 0)
                buf.append(",");
            buf.append(coord[i].x + " " + coord[i].y);
        }
        return buf.toString();
    }

    public static List<String> parseWktToStringList(String wkt) {
        List<String> points = null;
        if (StrUtil.isNotEmpty(wkt)) {
            points = Arrays.stream(wkt.split(",")).map(s -> s.trim().replace(" ", ",")).collect(Collectors.toList());
        }
        return points;
    }

    //点到wkt的最远距离
    public static double pointToWktFarthestDistance(String point, List<String> points) {
        Coordinate[] coordinates = list2Gsc(points);
        Coordinate carCoordinate = JtsUtil.gsc(point);
        double maxDistance = 0.0;
        for (int i = 0; i < coordinates.length - 1; i++) {
            LineSegment lineSegment = JtsUtil.getLS(coordinates[i], coordinates[i + 1]);
            double distance1 = lineSegment.distance(carCoordinate);
            if (distance1 > maxDistance) {
                maxDistance = distance1;
            }
        }
        return maxDistance;
    }


    //点到wkt的最短距离
    public static double pointToWktNearestDistance(String point, List<String> points) {
        Coordinate[] coordinates = list2Gsc(points);
        Coordinate carC = JtsUtil.gsc(point);

        double distance;
        if (coordinates.length > 1) {
            Point carPoint = JtsUtil.p(carC);
            Polygon polygon = JtsUtil.poloygon(coordinates);
            DistanceOp distanceOp = new DistanceOp(polygon, carPoint);
            distance = distanceOp.distance();
        } else {
            distance = coordinates[0].distance(carC);
        }
        return distance;
    }
    /**
     * 转化为弧度(rad)
     */

    /**
     * 算角度
     *
     * @return
     */
    public static double getAngle(double lonA, double latA, double lonB, double latB) {
        double degree;
        double radLatA = rad(latA);
        double radLonA = rad(lonA);
        double radLatB = rad(latB);
        double radLonB = rad(lonB);
        double dLon = radLonB - radLonA;
        double y = Math.sin(dLon) * Math.cos(radLatB);
        double x = Math.cos(radLatA) * Math.sin(radLatB) - Math.sin(radLatA) * Math.cos(radLatB) * Math.cos(dLon);
        degree = Math.atan2(y, x) * 180d / Math.PI;
        degree = (degree + 360) % 360d;
        return degree;
    }

    /**
     * 获取方位角
     *
     * @param p1
     * @param p2
     * @return
     */
    public static double getAngle(Coordinate p1, Coordinate p2) {
        double lonA = p1.x, latA = p1.y, lonB = p2.x, latB = p2.y;
        return getAngle(lonA, latA, lonB, latB);
    }


    public static double step = 1 / 6d;
    public static double crossShift = 8d;

    /**
     * 获取平滑曲线
     *
     * @param in1
     * @param in2
     * @param out1
     * @param out2
     * @return
     */
    public static List<Coordinate> getPinHuaPassPoints(String in1, String in2, String out1, String out2) {
        Coordinate in2_84 = JtsUtil.c(in2);
        Coordinate out1_84 = JtsUtil.c(out1);
        LineSegment inLs = JtsUtil.getLS(TransForm.toGs(JtsUtil.c(in1)), TransForm.toGs(in2_84));
        LineSegment outLs = JtsUtil.getLS(TransForm.toGs(out1_84), TransForm.toGs(JtsUtil.c(out2)));
        //如果交点在10米范围则使用交点平滑
        Coordinate center = JtsUtil.getCenter(inLs.p1, outLs.p0);
        Coordinate inter = inLs.lineIntersection(outLs);
        Coordinate controlP = null;
        List<Coordinate> qx = null;

        if (inter != null && inter.distance(center) < 110 && inLs.projectionFactor(inter) >= 1) {
//            20米内相交
            controlP = TransForm.to84(inter);
//            将起始点移动到靠近交点8米附近避免穿墙
            double factor1 = inLs.projectionFactor(inter) - crossShift / inLs.getLength();
            if (factor1 > 1) {
                Coordinate t1 = inLs.pointAlong(factor1);
                in2_84 = TransForm.to84(t1);
            }
            double factor2 = outLs.projectionFactor(inter) + crossShift / outLs.getLength();
            if (factor2 < 0) {
                Coordinate t2 = outLs.pointAlong(factor2);
                out1_84 = TransForm.to84(t2);
            }
            qx = JtsUtil.bsrqx(new Coordinate[]{in2_84, controlP, out1_84}, step);
        } else {
            //用高斯坐标系计算路口延伸6米后的中心点作为平滑控制点
            Coordinate c1 = inLs.pointAlong((inLs.getLength() + Common.PinHuaParam.shiftDis) / inLs.getLength());
            Coordinate c2 = outLs.pointAlong(-Common.PinHuaParam.shiftDis / outLs.getLength());
            controlP = TransForm.to84(JtsUtil.getCenter(c1, c2));
            qx = JtsUtil.bsrqx(new Coordinate[]{in2_84, controlP, out1_84}, step);
            //去掉首尾
            qx.remove(0);
            qx.remove(qx.size() - 1);
        }

        return qx;
    }

    public static void main(String[] args) {
//  掉头      LINESTRING(122.003260283676 29.771673717046,122.003400499999 29.771569750001) LINESTRING(122.003467199998 29.77165635,122.0033364 29.7717535)
//  左转      LINESTRING(122.003260283676 29.771673717046,122.003400499999 29.771569750001) LINESTRING(122.0036592 29.7717218,122.004179811236 29.77222718942,122.004700422472 29.772732578839,122.005221033707 29.773237968259,122.005741644943 29.773743357678)
        LineSegment ls = JtsUtil.getLS(JtsUtil.gsc("122.00890351256541,29.778879216245556"), JtsUtil.gsc("122.00868451683654,29.778652183508044"));
        double dis = ls.distance(JtsUtil.gsc("122.01382437515598,29.77976946626981"));
        System.out.println(dis);

        double[] ss = new double[]{122.01475082611668, 29.785798731834547, 122.0147238013851, 29.785798683562138, 122.01470468553129, 29.785788196160745, 122.01468875151663, 29.785771487775403, 122.01468077717209, 29.78574899895114, 122.01468395158201, 29.785728468781908, 122.01469588228514, 29.78570287142061, 122.01509148858206, 29.785442427734264, 122.01507363882826, 29.78542184821316, 122.01467116251318, 29.785688793131165, 122.01465574859029, 29.7857213556772, 122.01465292630967, 29.785756101365553, 122.01466231936598, 29.78578180609782, 122.01468579538316, 29.785807318554824, 122.0147050993519, 29.785818567060616, 122.01472029555704, 29.785824008157654, 122.01474372160379, 29.785824891396118, 122.01476984716916, 29.785818498765327};
        Coordinate[] cs = new Coordinate[ss.length / 2];
        for (int i = 0; i < ss.length / 2; i++) {
            cs[i] = JtsUtil.c(ss[i], ss[i + 1]);
        }
        System.out.println(inPloygon(JtsUtil.c("122.01477599,29.78579819"), cs));

        double step = 1 / 15d;
        List<Coordinate> qx = bsrqx(wkt2c("122.003400499999 29.771569750001,122.003467199998 29.77165635,122.0033364 29.7717535"), step);
        log.info("贝塞尔曲线:{}", toStr(qx));
//        List<Edge> ss = JSON.parseArray("[{\"id\":7,\"name\":\"\",\"code\":\"s1\",\"type\":\"\",\"laneWidth\":null,\"laneNum\":null,\"startId\":14,\"endId\":15,\"coordinates\":[{\"x\":1,\"y\":2,\"z\":\"NaN\"},{\"x\":10,\"y\":20,\"z\":\"NaN\"}]},{\"id\":8,\"name\":\"\",\"code\":\"s2\",\"type\":\"\",\"laneWidth\":null,\"laneNum\":null,\"startId\":16,\"endId\":17,\"c1\":\"3,4\",\"c2\":\"30,40\",\"coordinates\":[{\"x\":3,\"y\":4,\"z\":\"NaN\"},{\"x\":30,\"y\":40,\"z\":\"NaN\"}]}]"
//                , Edge.class
//        );

        log.info("" + -90 % 360);

        System.out.println("垂足点" + getFoot("122.00773583449146,29.77408807630635", "122.00593186090681 29.77233327804656", "122.00655057265777 29.772981216521103"));

    }

}
