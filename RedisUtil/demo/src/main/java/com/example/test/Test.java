package com.example.test;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.Charsets;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author Sky
 * create 2019/02/21
 * email sky.li@ixiaoshuidi.com
 **/
public class Test {
    public static class Inner {

        public final static Test testInstance = new Test(3);

        static {
            System.out.println("TestInner Static!");
        }
    }

    public static Test getInstance() {
        return Inner.testInstance;
    }

    public Test(int i) {
        System.out.println("Test " + i + " Construct! ");
    }

    static {
        System.out.println("Test Stataic");
    }

    public static Test testOut = new Test(1);

    public static void main(String args[]) throws InterruptedException {
//        System.out.println(Arrays.asList(1, 2, 3).toString());
        System.out.println(JSONObject.toJSONString(Arrays.asList(1, 2, 3)));
        Date date = new Date(1576490076000L);
        System.out.println(DateUtil.formatDateTime(date));


        String i = "1";
        switch (i) {
            case "1":
            case "2":
                System.out.println("hehe");
        }

        JSONObject bodyParam = new JSONObject();
        bodyParam.put("current_page", 1);
        System.out.println(bodyParam.toJSONString());
        bodyParam.clear();
        System.out.println(bodyParam.toJSONString());

        String s = DateUtil.endOfMonth(DateUtil.parse("2020-01", "yyyy-MM")).toString();
        System.out.println(s);
        Date date1 = DateUtil.parseDate(s).toJdkDate();
        System.out.println(date1.getTime());

        System.out.println("9559980129236825011".length());
        System.out.println("9559980129236825011".substring(0, 6));

        String resJson = "[\n" +
                "    {\n" +
                "        \"start_time\":\"2020-06-10\",\n" +
                "        \"end_time\":\"2020-07-09\",\n" +
                "        \"is_rentfree_full_month\":true\n" +
                "    }\n" +
                "]";


        JSONObject rentFreeJson = JSONObject.parseArray(resJson).getJSONObject(0);
        Date start_time = rentFreeJson.getDate("start_time");
        Date end_time = rentFreeJson.getDate("end_time");

        System.out.println(start_time.getTime());
        System.out.println(end_time.getTime());

    }
}
