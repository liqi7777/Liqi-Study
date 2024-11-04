package com.jz.test.redistest.mapStudy;

import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.osgeo.proj4j.BasicCoordinateTransform;
import org.osgeo.proj4j.CRSFactory;
import org.osgeo.proj4j.CoordinateReferenceSystem;
import org.osgeo.proj4j.ProjCoordinate;

@Slf4j
public class TransForm {
    //84地理坐标系 4326
//    CRS_84 = CRSFactory.("CRS:84");
////            CRS_84 = CRS.decode("EPSG:4326");
//    CRS_MCT = CRS.decode("EPSG:3857");
////            CRS_GS = CRS.decode("EPSG:4549");
//    CRS_GS = CRS.decode("EPSG:2416");
    private final static CRSFactory crsFactory = new CRSFactory();
    private final static CoordinateReferenceSystem CRS_84 = crsFactory.createFromName("EPSG:4326");
    private final static CoordinateReferenceSystem CRS_MCT = crsFactory.createFromName("EPSG:3857");
    private final static CoordinateReferenceSystem CRS_GS = crsFactory.createFromName("EPSG:2416");
    private final static BasicCoordinateTransform T_84_GS;
    private final static BasicCoordinateTransform T_GS_84;

    static {
        T_84_GS = new BasicCoordinateTransform(CRS_84, CRS_GS);
        T_GS_84 = new BasicCoordinateTransform(CRS_GS, CRS_84);
    }

    public static Coordinate toGs(Coordinate wgs84) {
        BasicCoordinateTransform T_84_GS = new BasicCoordinateTransform(CRS_84, CRS_GS);
//        synchronized (T_84_GS) {
            ProjCoordinate from = new ProjCoordinate(wgs84.x, wgs84.y);
            ProjCoordinate to = new ProjCoordinate();
            T_84_GS.transform(from, to);
            return new Coordinate(to.x, to.y);
//        }
    }

    public static Coordinate to84(Coordinate gs) {
        BasicCoordinateTransform T_GS_84 = new BasicCoordinateTransform(CRS_GS, CRS_84);
//        synchronized (T_GS_84) {
            ProjCoordinate from = new ProjCoordinate(gs.x, gs.y);
            ProjCoordinate to = new ProjCoordinate();
            T_GS_84.transform(from, to);
            return new Coordinate(to.x, to.y);
//        }
    }

    public static Coordinate[] to84(Coordinate... gss) {
        Coordinate[] coordinates = new Coordinate[gss.length];
        for (int i = 0; i < gss.length; i++) {
            coordinates[i] = to84(gss[i]);
        }
        return coordinates;
    }


}