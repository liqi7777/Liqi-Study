package com.example.test;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import sun.awt.SunHints;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author Sky
 * create 2018/09/27
 * email sky.li@ixiaoshuidi.com
 **/

public class RedisTest {
    public static void main(String[] args) {
//        String message = Stream.of(MainframeErrorCodeEnum.values()).filter(mainframeErrorCodeEnum -> "0".equals(mainframeErrorCodeEnum.getCode()))
//                .findFirst().get().getMessage();
//        System.out.println(message);
//
//        ApartmentListVo apartmentListVo1 = new ApartmentListVo();
//        apartmentListVo1.setApartmentId(8760);
//        ApartmentListVo apartmentListVo2 = new ApartmentListVo();
//        apartmentListVo2.setApartmentId(8760);
//        List<ApartmentListVo> apartmentListVos = new ArrayList<>();
//        apartmentListVos.add(apartmentListVo1);
//        apartmentListVos.add(apartmentListVo2);
////        apartmentListVos = apartmentListVos.stream().collect
////                (Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<ApartmentListVo>((o1, o2) -> o1.getApartmentId().compareTo(o2.getApartmentId()))), ArrayList::new));
//
//        System.out.println(JSONObject.toJSONString(apartmentListVos));
//
//        apartmentListVos.stream().collect(Collectors.toMap(ApartmentListVo::getApartmentId, apartmentListVo -> apartmentListVo));

//        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
//        String newDate = sdf.format(new Date());
//        String result = "";
//        Random random = new Random();
//        for (int i = 0; i < 3; i++) {
//            result += random.nextInt(10);
//        }
//        System.out.println(newDate + result);
//
//
//        DateTime dateTime = DateUtil.parseDateTime("2019-08-29 15:23:43");
//        System.out.println(dateTime.getTime());
//
//        StringBuilder builder = new StringBuilder("hah");
//        String s =null;
//        builder.append(s!=null?s:"");
//        System.out.println(builder.toString());
//
        Integer i=3;
//        System.out.println(i.equals(3));
//        System.out.println("   ".equals(""));
        System.out.println(48093L!=48093);

        test02();
    }

    private static void test02() {
        List<String> ids = new ArrayList<>();
        ids.add("liqi");
        List<Person> persons = new ArrayList<>();
    }
}
