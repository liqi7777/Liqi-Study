package com.example.test;


import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sky
 * create 2018/12/14
 * email sky.li@ixiaoshuidi.com
 **/
public class HutoolTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add(0, "c");
        System.out.println(list.toString());
        StringBuffer sb = new StringBuffer("hahahha");
        String s1 = null;
        String s2 = null;
        sb.append(s1).append(s2);
        System.out.println(sb.toString());

        Map<String, String> map = new HashMap<>();
        map.put(null, "ahh");
        map.put("hee", "aha");
        System.out.println(String.valueOf((int) ((Math.random() * 9 + 1) * 100000)));
        System.out.println("hah".equals(null));


        int[] count = {5};
        --count[0];
        System.out.println(count[0]);


        String jsonstring="{\"datetime\":1598198400000,\"value2\":\"532.07\",\"shareValue\":\"0\",\"customeraccount\":\"158812292115\",\"value1\":\"0\",\"value4\":\"385.46\",\"value3\":\"0\",\"customerid\":\"37894\",\"meteraddress\":\"821912260090\",\"meterCode\":\"00821912260090\",\"customername\":\"瑞金大厦224电\",\"value\":\"917.53\"}";
        JSONObject jsonObject = JSONObject.parseObject(jsonstring);
        BigDecimal shareValue = jsonObject.getBigDecimal("shareValue");
        System.out.println(shareValue.toString());

        System.out.println(getV4IP());
    }



    public static String getV4IP() {
        String ip = "";
        String chinaz = "http://ip.chinaz.com";

        StringBuilder inputLine = new StringBuilder();
        String read = "";
        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            url = new URL(chinaz);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            while ((read = in.readLine()) != null) {
                inputLine.append(read + "\r\n");
            }
            //System.out.println(inputLine.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }


        Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
        Matcher m = p.matcher(inputLine.toString());
        if (m.find()) {
            String ipstr = m.group(1);
            ip = ipstr;
            //System.out.println(ipstr);
        }
        return ip;
    }
}
