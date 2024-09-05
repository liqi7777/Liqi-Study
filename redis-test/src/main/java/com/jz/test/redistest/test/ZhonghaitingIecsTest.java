package com.jz.test.redistest.test;

import com.google.common.base.Joiner;
import com.headingdata.iecs.APIService;
import com.headingdata.iecs.beans.LaneBean;
import com.headingdata.iecs.beans.RoadBean;
import com.headingdata.iecs.data.LngLat;
import com.headingdata.iecs.data.Pixel;

/**
 * @author liqi
 * create  2021-09-24 15:27
 */
public class ZhonghaitingIecsTest {
    public static void main(String[] args) {
        LngLat point=new LngLat(121.64549065,31.33797059);
        Pixel pixel = point.toPixel();
        pixel.getX();
        pixel.getY();

//        LngLat lngLat = pixel.toLngLat();
//        LaneBean lane = APIService.getLane(point, 112);
//        String roadId = lane.getRoadId();
//        APIService.getRoadById(roadId);
//        RoadBean road = APIService.getRoad(point);
//        road.getId();
//        lane.getId();


        String join = Joiner.on(",").join(new Double[]{pixel.getX(), pixel.getY()});
        System.out.println(join);

//        PlanningService.transform()

    }
}
