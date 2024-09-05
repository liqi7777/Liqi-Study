package com.jz.test.redistest.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.headingdata.trans.JavaTrans;
import com.headingdata.trans.pojos.Feature;
import com.headingdata.trans.pojos.LngLatPoint;
import com.headingdata.trans.pojos.QueryRoadType;
import com.headingdata.trans.pojos.ResultBean;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author DAICX @version 1.0
 * @Description
 * @date 2021/4/10 17:03
 */
@Component
@Slf4j
public class ZhtMapUtils {
	public static List<Feature> features;
	public static double SHIP_LEFT_HEAD;
	public static double SHIP_RIGHT_HEAD;
	/**
	 * 地球每纬度的弧长(km)
	 */
	public final static double EARTH_ARC = 111319.55;
	/**
	 * 地球赤道半径
	 **/
	private static final double R = 6378137d;

	static {
		// 2.4初始化
		JavaTrans.initData();
		features = JavaTrans.lanePolygonFeatureList;
		if (CollectionUtil.isNotEmpty(features)) {
			features.forEach(feature -> {
				Feature head = JavaTrans.rvHeadingList.stream()
						.filter(feature1 -> feature1.getRVID().equals(feature.getRVID())).findFirst().orElse(null);
//            Feature lvid = JavaTrans.rvidToLaneIds.stream().filter(feature1 -> feature1.getRVID().equals(feature.getRVID()) && feature1.getLaneNum().equals(feature.getLaneNum())).findFirst().orElse(null);
				// 设置航向角
				if (head != null) {
					feature.setHeading(head.heading);
				}
			});
			SHIP_LEFT_HEAD = 302.21121988059826d;
			SHIP_RIGHT_HEAD = (SHIP_LEFT_HEAD + 180) % 360d;
		}
	}

	/**
	 * 2.1 加密方法调用
	 *
	 * @param lng
	 * @param lat
	 * @return
	 */
	public static LngLatPoint CoordEncrypt(double lng, double lat) {
		return JavaTrans.CoordEncrypt(lng, lat, 50);
	}

	/**
	 * 2.2 计算两点间距离
	 *
	 * @param longitude1
	 * @param latitude1
	 * @param longitude2
	 * @param latitude2
	 * @return
	 */
	public static double calPointLen(double longitude1, double latitude1, double longitude2, double latitude2) {
		// 测量两点间距离
		return JavaTrans.calPointLen(longitude1, latitude1, longitude2, latitude2);
	}

	/**
	 * 2.3 判断点是否在多边形内
	 *
	 * @param point
	 * @param APoints
	 * @return
	 */
	public static boolean isPointInPolygon(LngLatPoint point, List<LngLatPoint> APoints) {
		return JavaTrans.isPointInPolygon(point, APoints);
	}

//    /**
//     * 2.5	获取道路
//     * @param lng
//     * @param lat
//     * @return
//     */
//    public static ResultBean getRoad(double lng, double lat){
//        return JavaTrans.getRoad(new LngLatPoint(lng, lat, 0));
//    }

	/**
	 * 2.5 获取道路
	 *
	 * @param lng
	 * @param lat
	 * @return
	 */
	public static ResultBean getRoad(double lng, double lat) {
		return JavaTrans.getRoad(QueryRoadType.TYPE_DEFAULT, new LngLatPoint(lng, lat, 0), null, 0);
	}

	public static ResultBean getRoad(double lng, double lat, double heading) {
		return JavaTrans.getRoad(QueryRoadType.TYPE_HEADING, new LngLatPoint(lng, lat, 0), null, heading);
	}

	public static ResultBean getRoad(double lng, double lat, double lastLng, double lastLat) {
		return JavaTrans.getRoad(QueryRoadType.TYPE_LAST_POINT, new LngLatPoint(lng, lat, 0),
				new LngLatPoint(lastLng, lastLat, 0), 0);
	}

	/**
	 * 2.6 获取路口
	 *
	 * @param lng
	 * @param lat
	 * @return
	 */
	public static ResultBean getRoadJunction(double lng, double lat) {
		return JavaTrans.getRoadJunction(new LngLatPoint(lng, lat, 0));
	}

	/**
	 * 2.7 获取路口距离
	 *
	 * @param lng
	 * @param lat
	 * @param lng2
	 * @param lat2
	 * @return
	 */
	public static ResultBean getRoadJunctionDis(double lng, double lat, double lng2, double lat2) {
		return JavaTrans.getRoadJunctionDis(new LngLatPoint(lng, lat, 0), 0);
	}

	/**
	 * 2.8 获取车道方向引导列表
	 *
	 * @param lng
	 * @param lat
	 * @return
	 */
	public static ResultBean getLaneDir(double lng, double lat) {
		return JavaTrans.getLaneDir(new LngLatPoint(lng, lat, 0), 0);
	}

	/**
	 * 2.9 获取车道前方障碍物
	 *
	 * @param lng
	 * @param lat
	 * @return
	 */
	public static ResultBean getLaneObstacleList(double lng, double lat) {
		return JavaTrans.getLaneObstacleList(new LngLatPoint(lng, lat, 0));
	}

	public static void main(String[] args) {
		JavaTrans.initData();
		// 2.1 加密方法调用
//        LngLatPoint str11 = JavaTrans.CoordEncrypt(121.648615,31.333243, 50);
//        System.out.println(str11);
//        //2.2	计算两点间距离
//        double s = JavaTrans.calPointLen(121.6449825, 31.3378781, 121.6450610, 31.3380871);
//        System.out.println(s);
//        //2.3	判断点是否在多边形内
//        System.out.println(System.currentTimeMillis());
//        LngLatPoint pointtest1 = new LngLatPoint(121.64396727561633, 31.338361620779875, 0);
//        //多边形内点
//        LngLatPoint pointtest2 = new LngLatPoint(114.3448 , 30.4928, 0);
//        121.642406,31.339521 121.645063,31.338087 121.644790,31.337725 121.642142,31.339162
//        List<LngLatPoint> doubles = new ArrayList<>();
//        LngLatPoint point1 = new LngLatPoint(121.642406,31.339521, 0);
//        doubles.add(point1);
//        LngLatPoint point2 = new LngLatPoint(121.645063, 31.338087, 0);
//        doubles.add(point2);
//        LngLatPoint point3 = new LngLatPoint(121.644790, 31.337725, 0);
//        doubles.add(point3);
//        LngLatPoint point4 = new LngLatPoint(121.642142, 31.339162, 0);
//        doubles.add(point4);
//        boolean isIN = JavaTrans.isPointInPolygon(pointtest1, doubles);
//        boolean isOut = JavaTrans.isPointInPolygon(pointtest2, doubles);
//        System.out.println("isIN = " + isIN);
//        System.out.println(System.currentTimeMillis());
//        System.out.println("isOut = " + isOut);
		// 2.4初始化
		/*
		 * System.out.println(System.currentTimeMillis()); boolean
		 * flag=JavaTrans.initData(); //2.5 获取道路
		 */
		ResultBean bean = getRoad(121.64519271932548, 31.34264530810765);
		System.out.println("bean = " + bean.getRVID());
		System.out.println(JSON.toJSONString(bean));

		// 2.6 获取路口
//        ResultBean bean1 = JavaTrans.getRoadJunction(new LngLatPoint(121.64471, 31.33470, 0));
//        System.out.println("bean1 = " + bean1.getJID());
//        //2.7	获取路口距离
//        ResultBean bean2 = JavaTrans.getRoadJunctionDis(new LngLatPoint(121.64471, 31.33470, 0), "721138899999457296");
//        System.out.println("bean2 = " + bean2.getJDis());
//        //2.8	获取车道方向引导列表
//        ResultBean bean4 = JavaTrans.getLaneDir(new LngLatPoint(121.64471, 31.33470, 0), "721138899999457296");
//        System.out.println("bean4 = " + bean4.getLDir().size());
//        //2.9	获取车道前方障碍物
//        ResultBean bean3 = JavaTrans.getLaneObstacleList(new LngLatPoint(121.64471, 31.33470, 0), "721138899999457857", "2");
//        System.out.println("bean3 = " + bean3.getLaneObstacleList().size());
//        String zb = "121.64612315,31.34162692999952 121.64640283000003,31.34149632999952 121.64624177000002,31.341268379999516 121.64600784999999,31.34141879999952";
//        System.out.println(getNewCoordinates(zb));
	}

	private static String getNewCoordinates(String coordinates) {
		String newCoordinates = "";
		String[] arr = coordinates.split("\\ ");
		if (arr.length == 4) {
			double aj = Double.valueOf(arr[0].split(",")[0]), aw = Double.valueOf(arr[0].split(",")[1]),
					bj = Double.valueOf(arr[1].split(",")[0]), bw = Double.valueOf(arr[1].split(",")[1]),
					cj = Double.valueOf(arr[2].split(",")[0]), cw = Double.valueOf(arr[2].split(",")[1]),
					dj = Double.valueOf(arr[3].split(",")[0]), dw = Double.valueOf(arr[3].split(",")[1]);
			// a在c的的角度
			double a = getAngle1(cj, cw, aj, aw);
			String[] aa = calLocationByDistanceAndLocationAndDirection(a, aj, aw, 10);
			// b在d的的角度
			double b = getAngle1(dj, dw, bj, bw);
			String[] bb = calLocationByDistanceAndLocationAndDirection(b, bj, bw, 10);
			// c在a的的角度
			double c = getAngle1(aj, aw, cj, cw);
			String[] cc = calLocationByDistanceAndLocationAndDirection(c, cj, cw, 10);
			// b在d的的角度
			double d = getAngle1(bj, bw, dj, dw);
			String[] dd = calLocationByDistanceAndLocationAndDirection(d, dj, dw, 10);
			newCoordinates = aa[0] + "," + aa[1] + " " + bb[0] + "," + bb[1] + " " + cc[0] + "," + cc[1] + " " + dd[0]
					+ "," + dd[1];
			/*
			 * System.out.println(aa[0]+","+aa[1]); System.out.println(bb[0]+","+bb[1]);
			 * System.out.println(cc[0]+","+cc[1]); System.out.println(dd[0]+","+dd[1]);
			 */
		}
		return newCoordinates;
	}

	/**
	 * @param lat_a 纬度1
	 * @param lng_a 经度1
	 * @param lat_b 纬度2
	 * @param lng_b 经度2
	 * @return
	 */
	public static double getAngle1(double lng_a, double lat_a, double lng_b, double lat_b) {

		double y = Math.sin(lng_b - lng_a) * Math.cos(lat_b);
		double x = Math.cos(lat_a) * Math.sin(lat_b) - Math.sin(lat_a) * Math.cos(lat_b) * Math.cos(lng_b - lng_a);
		double brng = Math.atan2(y, x);

		brng = Math.toDegrees(brng);
		if (brng < 0) {
			brng = brng + 360;
		}
		return brng;
	}

	/**
	 * 180°
	 **/
	private static final DecimalFormat df = new DecimalFormat("0.00000000000000");

	/**
	 * 根据一点的坐标与距离，以及方向，计算另外一点的位置
	 *
	 * @param angle     角度，从正北顺时针方向开始计算
	 * @param startLong 起始点经度
	 * @param startLat  起始点纬度
	 * @param distance  距离，单位m
	 * @return
	 */
	public static String[] calLocationByDistanceAndLocationAndDirection(double angle, double startLong, double startLat,
			double distance) {
		String[] result = new String[2];
		// 将距离转换成经度的计算公式
		double s = distance / R;
		// 转换为radian，否则结果会不正确
		angle = Math.toRadians(angle);
		startLong = Math.toRadians(startLong);
		startLat = Math.toRadians(startLat);
		double lat = Math.asin(Math.sin(startLat) * Math.cos(s) + Math.cos(startLat) * Math.sin(s) * Math.cos(angle));
		double lon = startLong + Math.atan2(Math.sin(angle) * Math.sin(s) * Math.cos(startLat),
				Math.cos(s) - Math.sin(startLat) * Math.sin(lat));
		// 转为正常的10进制经纬度
		lon = Math.toDegrees(lon);
		lat = Math.toDegrees(lat);
		result[0] = df.format(lon);
		result[1] = df.format(lat);
		return result;
	}

	public static Map.Entry<Integer, Double> getNearestPoint(List<Point> points, String point) {
		List<Map.Entry<Integer, Double>> list = point2Lines(new Point(point), points, false);
		if (CollectionUtil.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 返回点到闭合区域的距离内部点为0
	 *
	 * @param point
	 * @return
	 */
	public static double point2Polygon(Point point, List<Point> points) {
		List<Map.Entry<Integer, Double>> list = point2Lines(point, points, true);
		if (CollectionUtil.isNotEmpty(list)) {
			return list.get(0).getValue();
		}
		return Integer.MAX_VALUE;
	}

	public static double pointToLine(String p0, String p1, String p2) {
		return pointToLine(getPoint(p0), getPoint(p1), getPoint(p2));
	}

	public static Point getPoint(String p) {
		return new Point(Double.parseDouble(p.split(" ")[0]), Double.parseDouble(p.split(" ")[1]));
	}

	// 求点(x0,y0)到线段（x1,y1）,(x2,y2)的最短距离点到由两点组成的线段
	public static double pointToLine(Point p0, Point p1, Point p2) {
		double space = 0;
		double a, b, c;
		a = getDistance(p1, p2);// 线段的长度
		b = getDistance(p1, p0);// (x1,y1)到点的距离
		c = getDistance(p2, p0);// (x2,y2)到点的距离
		if (c <= 0.000001 || b <= 0.000001) {
			space = 0;
			return space;
		}
		if (a <= 0.000001) {
			space = b;
			return space;
		}
		if (c * c >= a * a + b * b) {
			space = b;
			return space;
		}
		if (b * b >= a * a + c * c) {
			space = c;
			return space;
		}
		double p = (a + b + c) / 2;// 半周长
		double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));// 海伦公式求面积
		space = 2 * s / a;// 返回点到线的距离（利用三角形面积公式求高）
		return space;
	}

	/**
	 * 通过经纬度获取距离(单位：米)
	 *
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
		double s = JavaTrans.calPointLen(lng1, lat1, lng2, lat2);
		return s;
	}

	public static double getDistance(Point p1, Point p2) {
		return getDistance(p1.getY(), p1.getX(), p2.getY(), p2.getX());
	}

	public static double getDistance(String p1, String p2) {
		return getDistance(new Point(p1), new Point(p2));
	}

	// 垂足点
	public static Point getFootPoint(Point point, Point pnt1, Point pnt2) {
		double A = pnt2.y - pnt1.y; // y2-y1
		double B = pnt1.x - pnt2.x; // x1-x2;
		double C = pnt2.x * pnt1.y - pnt1.x * pnt2.y; // x2*y1-x1*y2
		if (A * A + B * B < 1e-13) {
			return pnt1; // pnt1与pnt2重叠
		} else if (Math.abs(A * point.x + B * point.y + C) < 1e-13) {
			return point; // point在直线上(pnt1_pnt2)
		} else {
			double x = (B * B * point.x - A * B * point.y - A * C) / (A * A + B * B);
			double y = (-A * B * point.x + A * A * point.y - B * C) / (A * A + B * B);
			return new Point(x, y);
		}
	}

	// 点到直线的垂直距离
	public static double getDistanceByPointToLine(String point, String pnt1, String pnt2) {
		return getDistanceByPointToLine(new Point(point), new Point(pnt1), new Point(pnt2));
	}

	public static double getDistanceByPointToLine(Point point, Point pnt1, Point pnt2) {
//        double dis = 0;
//        if (pnt1.x == pnt2.x) {
//            if (pnt1.y == pnt2.y) {
//                double dx = point.x - pnt1.x;
//                double dy = point.y - pnt1.y;
//                dis = Math.sqrt(dx * dx + dy * dy);
//            } else
//                dis = Math.abs(point.x - pnt1.x);
//        } else {
//            double lineK = (pnt2.y - pnt1.y) / (pnt2.x - pnt1.x);
//            double lineC = (pnt2.x * pnt1.y - pnt1.x * pnt2.y) / (pnt2.x - pnt1.x);
//            dis = Math.abs(lineK * point.x - point.y + lineC) / (Math.sqrt(lineK * lineK + 1));
//        }

		double dis = 0;
		double a, b, c;
		a = getDistance(pnt1, pnt2);// 线段的长度
		b = getDistance(pnt1, point);// (x1,y1)到点的距离
		c = getDistance(pnt2, point);// (x2,y2)到点的距离
		double p = (a + b + c) / 2;// 半周长
		double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));// 海伦公式求面积
		dis = 2 * s / a;// 返回点到线的距离（利用三角形面积公式求高）

		return dis;
	}

	public static Point getRelativePoint(Point s, Point e, double relativeDistance) {
		double c = getDistance(s, e);
		double _x = (relativeDistance / c) * (e.getX() - s.getX()) + s.getX();
		double _y = (relativeDistance / c) * (e.getY() - s.getY()) + s.getY();
		return new Point(_x, _y);
	}

	public static String getRelativePoint(String s, String e, double relativeDistance) {
		Point p1 = new Point(Double.parseDouble(s.split(",")[0]), Double.parseDouble(s.split(",")[1]));
		Point p2 = new Point(Double.parseDouble(e.split(",")[0]), Double.parseDouble(e.split(",")[1]));
		Point p3 = getRelativePoint(p1, p2, relativeDistance);
		return p3.getX() + "," + p3.getY();
	}

	@Data
	public static class Point {
		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public Point(String point) {
			String[] s = point.trim().split(" |,");
			this.x = Double.parseDouble(s[0]);
			this.y = Double.parseDouble(s[1]);
		}

		public Point() {
		}

		public String toString() {
			return getCoordinate();
		}

		public String getCoordinate() {
			return StrUtil.format("{},{}", x, y);
		}

		public double x;
		public double y;
	}

	/**
	 * 2.3 返回点离连续线段的最短距离,闭合时内部点为0
	 *
	 * @param point
	 * @return
	 */
	public static List<Map.Entry<Integer, Double>> point2Lines(Point point, List<Point> points, boolean close) {
		try {
			if (points != null) {
				Map<Integer, Double> map = new HashMap<>();
				if (points.size() == 1) {
					map.put(0, getDistance(point, points.get(0)));
				} else {
					boolean computeflag = true;
					if (close) {
						List<LngLatPoint> lngLatPoints = points.stream().map(point1 -> new LngLatPoint(point1.x, point1.y, 0d)).collect(Collectors.toList());
						if (isPointInPolygon(new LngLatPoint(point.x, point.y, 0d), lngLatPoints)) {
							map.put(0, 0d);
							//内部点无需遍历
							computeflag = false;
						}
					}
					if (computeflag) {
						for (int i = 1; i < points.size(); i++) {
							Point sp = points.get(i - 1);
							Point ep = points.get(i);
							double dis = pointToLine(point, sp, ep);
							map.put(i - 1, dis);
						}
						if (close) {
							double dis = pointToLine(point, points.get(points.size() - 1), points.get(0));
							map.put(points.size() - 1, dis);
						}
					}
				}
				List<Map.Entry<Integer, Double>> list = new ArrayList(map.entrySet());
				Collections.sort(list, (o1, o2) -> (o1.getValue().compareTo(o2.getValue())));
				return list;
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	public static List<Map.Entry<Integer, Double>> getRoadDistance(ZhtMapUtils.Point point) {
		Map<Integer, Double> map = new HashMap<>();
		for (int i = 0; i < features.size(); i++) {
			Feature lf = features.get(i);
			List<LngLatPoint> lngLatPoints = lf.getPointsList();
			List<Point> points = lngLatPoints.stream().map(o -> new ZhtMapUtils.Point(o.lng, o.lat))
					.collect(Collectors.toList());
			double d = ZhtMapUtils.point2Polygon(point, points);
			map.put(i, d);
		}
		List<Map.Entry<Integer, Double>> list = new ArrayList(map.entrySet());
		Collections.sort(list, (o1, o2) -> (o1.getValue().compareTo(o2.getValue())));
		return list;
	}

	public static Feature getNearestRoad(ZhtMapUtils.Point point, ZhtMapUtils.Point point2, Double head) {
		return getNearestRoad(point, point2, head, 90d);
	}

	public static Feature getNearestRoad(ZhtMapUtils.Point point, ZhtMapUtils.Point point2, Double head,
			Double headthreshold) {
		headthreshold = headthreshold == null ? 45 : headthreshold;
		List<Map.Entry<Integer, Double>> list = getRoadDistance(point);
		// 考虑最大可能的车道数
		int threshold = 5;
		int i = 0;
		if (point2 != null) {
			head = JavaTrans.getAzimuth(new LngLatPoint(point.x, point.y, 0d), new LngLatPoint(point2.x, point2.y, 0d));
		}
		if (head != null) {
			// 有方向角参考取最近路前N匹配度小于45的路
			for (; i < list.size(); i++) {
				int feature_index = list.get(i).getKey();
				double _head = features.get(feature_index).getHeading();
				if (Math.abs(head - _head) <= headthreshold) {
					break;
				}
				// 大于阈值没有匹配到方向角取第一条数据
				if (i >= threshold) {
					i = 0;
					break;
				}
			}
		}
		Feature feature = features.get(list.get(i).getKey());
//        Feature feature = BeanUtil.copyProperties(source, Feature.class);
//        distance = list.get(i).getValue();
//        feature.setDis(String.valueOf(distance));
		return feature;
	}

	public static void print(String item, double test, double stand) {
		log.info(String.format("%s:测试值%.2f,参考值%.2f,误差:%.2f", item, test, stand, (test - stand) / stand));
	}

	/**
	 * 半正失计算
	 *
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double getdis(Point p1, Point p2) {
		double radLat1 = JavaTrans.rad(p1.y);
		double radLat2 = JavaTrans.rad(p2.y);
		double a = radLat1 - radLat2;
		double b = JavaTrans.rad(p1.x) - JavaTrans.rad(p2.x);
		double c = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
//        c = c * 6378.137;// 6378.137赤道半径
		return c * R;
	}

	/**
	 * @param p
	 * @param distance m
	 * @param angle
	 * @return
	 */
	public static Point getRelativePoint(Point p, double distance, double angle) {
		angle = rad(angle);
		// 将距离转换成经度的计算公式
		double lon = p.x + (distance * Math.sin(angle)) / (EARTH_ARC * Math.cos(rad(p.y)));
		// 将距离转换成纬度的计算公式
		double lat = p.y + (distance * Math.cos(angle)) / (EARTH_ARC);
		return new Point(lon, lat);
	}

	/**
	 * 转化为弧度(rad)
	 */
	public static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 获取方位角
	 *
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double getAngle(Point p1, Point p2) {
		double var2 = p2.y;
		var2 = (90.0D - var2) * Math.PI / 180.0D;
		double var4 = p1.y;
		double p20001 = var2;
		var4 = (90.0D - var4) * Math.PI / 180.0D;
		double var6 = (p2.x - p1.x) * Math.PI / 180.0D;
		double p20002 = Math.cos(var2) * Math.cos(var4) + Math.sin(var2) * Math.sin(var4) * Math.cos(var6);
		var2 = p20002 * p20002;
		var2 = Math.sqrt(1.0D - var2);
		var2 = Math.asin(Math.sin(p20001) * Math.sin(var6) / var2) * 180.0D / Math.PI;
		var4 = 0.0D;
		if (p2.x > p1.x && p2.y > p1.y) {
			var4 = var2;
		} else if ((!(p2.x > p1.x) || !(p2.y < p1.y)) && (!(p2.x < p1.x) || !(p2.y < p1.y))) {
			if (p2.x < p1.x && p2.y > p1.y) {
				var4 = var2 + 360.0D;
			} else if (p2.x > p1.x && p2.y == p1.y) {
				var4 = 90.0D;
			} else if (p2.x < p1.x && p2.y == p1.y) {
				var4 = 270.0D;
			} else if (p2.x == p1.x && p2.y > p1.y) {
				var4 = 0.0D;
			} else if (p2.x == p1.x && p2.y < p1.y) {
				var4 = 180.0D;
			}
		} else {
			var4 = 180.0D - var2;
		}

		return var4;
	}



	/**
	 * @param paths 全部路径规划 ,point 当前点
	 * @return 预计所需距离单位m
	 */
	public static double getDistanceByPositionAndPathPlan(String point, List<Point> paths) {
		double distance = 0;
		if (paths != null) {
			Map.Entry<Integer, Double> entry = getNearestPoint(paths,point);
			if (entry != null) {
				int index = entry.getKey();
				if (index < paths.size() - 1) {
					distance = getDistance(point, paths.get(index + 1).getCoordinate());
                    List<Point> points = CollectionUtil.sub(paths, index + 1, paths.size());
                    distance = Arith.add(distance, getDistanceByPathPlan(points));
				}
			}
		}
		return distance;
	}

    /**
     * @param paths 路径规划
     * @return 路径规划实际距离单位m
     */
    public static double getDistanceByPathPlan(List<Point> paths) {
        double distance = 0D;
        if (CollectionUtil.isNotEmpty(paths)) {
            for (int i = 1; i < paths.size(); i++) {
                try {
                    String sp = paths.get(i - 1).getCoordinate();
                    String ep = paths.get(i).getCoordinate();
                    String[] s = sp.split(",");
                    String[] e = ep.split(",");
                    double x1 = Double.parseDouble(s[0]);
                    double y1 = Double.parseDouble(s[1]);
                    double x2 = Double.parseDouble(e[0]);
                    double y2 = Double.parseDouble(e[1]);
                    //距离终点的距离
                    distance = Arith.add(distance, getDistance(y1, x1, y2, x2));
                } catch (Exception e) {
                    log.error("路径点{}解析异常!", JSON.toJSONString(paths.get(i)));
                    log.error(e.getMessage(), e);
                }
            }
        }
        return distance;
    }




//    public static void main(String[] args) {
//        String sss = "121.644248552737 31.3364735578104,121.643734552737 31.3357625578104,121.643524 31.335872,121.644050555341 31.3365814979857,121.644248552737 31.3364735578104";
//        testZhtMapData();
//    }

}
