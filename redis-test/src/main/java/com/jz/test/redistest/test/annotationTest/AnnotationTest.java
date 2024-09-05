package com.jz.test.redistest.test.annotationTest;

import cn.hutool.crypto.SecureUtil;

import java.lang.annotation.Annotation;

/**
 * @author liqi
 * create  2022/3/14 9:16 下午
 */
public class AnnotationTest {
    public static void main(String[] args) {
        Annotation[] annotations = DataSource.class.getAnnotations();
        for (Annotation annotation : annotations) {
            Class<? extends Annotation> aClass = annotation.annotationType();
        }
        System.out.println(annotations);

        String s = SecureUtil.md5().digestHex("{\"OpinstrId\":50249771,\"CwpMachNo\":\"1863\",\"WkFlow\":\"1\",\"Opmode\":\"53\",\"QcWorkLane\":null,\"ContainerNo\":\"CAIU2065297\",\"ContainerType\":\"GP\",\"Vlocation\":\"999999\",\"Ylocation\":\"186334\",\"WorkMoveSeq\":null,\"Opstatus\":\"C\",\"TruckNo\":\"H363\",\"RouteId\":\"3007\",\"ContainerID\":30734454,\"VpcContainerID\":65866198,\"Affg\":\"F\",\"ContainerSize\":\"20\",\"Opprc\":\"YV\",\"Driver\":\"1182\",\"RtgNo\":\"H224\"}");
        String s1 = SecureUtil.md5().digestHex("{\"OpinstrId\":50249771,\"CwpMachNo\":\"1863\",\"WkFlow\":\"1\",\"Opmode\":\"53\",\"QcWorkLane\":null,\"ContainerNo\":\"CAIU2065297\",\"ContainerType\":\"GP\",\"Vlocation\":\"999999\",\"Ylocation\":\"186334\",\"WorkMoveSeq\":null,\"Opstatus\":\"C\",\"TruckNo\":\"H363\",\"RouteId\":\"3007\",\"ContainerID\":30734454,\"VpcContainerID\":65866198,\"Affg\":\"F\",\"ContainerSize\":\"20\",\"Opprc\":\"YV\",\"Driver\":\"1182\",\"RtgNo\":\"H224\"}");
        System.out.println(s);
        System.out.println(s1);
        System.out.println(s.equals(s1));

    }
}
