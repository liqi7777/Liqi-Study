package com.example.test;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.example.ab.MD5Util;
import com.google.common.collect.Maps;
import org.springframework.util.DigestUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Sky
 * create 2018/09/27
 * email sky.li@ixiaoshuidi.com
 **/

public class test01 {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        List<String> Stringlists = new ArrayList<>();
        Stringlists.add("a");
        Stringlists.add("b");
        Stringlists.add("c");
        List<String> a1 = Stringlists.stream().filter(s -> s.equals("a")).collect(Collectors.toList());
        System.out.println(a1);

        String he = "aaa";
        boolean contains = he.contains("");
        System.out.println(contains);

//        PageBean<String> pageBean = PageBean.generatePageBean(Stringlists, 1, 2);
//        System.out.println(JSONObject.toJSONString(pageBean));
//        List<Integer> integerList = new ArrayList<>();
////        integerList.add(1);
////        integerList.add(2);
////        integerList.add(3);
////        integerList.add(4);
////        integerList.add(5);
//        PageBean<Integer> integerPageBean = PageBean.generatePageBean(integerList, 1, 2);
//        System.out.println(JSONObject.toJSONString(integerPageBean));
//        Double a1 =0.0;
//        System.out.println(a1.equals(0.0));
//        String haha="0";
//        System.out.println(haha.equals(0));

        Long a2 = 48093L;
        System.out.println(a2 == 48093);
//        int i = new Double("20.52").intValue();
//        System.out.println(i);
////        DateTime yesterday = DateUtil.yesterday();
////        DateUtil.now();
        DateTime yyyyMM = DateUtil.parse("202011", "yyyyMM");
        DateTime dateTime = DateUtil.beginOfDay(yyyyMM);
        DateTime dateTime1 = DateUtil.endOfDay(yyyyMM);
        System.out.println(dateTime.toString());
        System.out.println(dateTime1.toString());
        System.out.println(yyyyMM.getTime());
        long between = DateUtil.between(dateTime, dateTime1, DateUnit.DAY);
        System.out.println(between);
////
////        User user = new User();
////        user.setUsername("liqi");
////        user.setMoney(20);
////        System.out.println(user.toString());
////
////        System.out.println(DateUtil.beginOfDay(DateUtil.parse("20200228", "yyyyMMdd")).toString());
////        System.out.println(DateUtil.endOfDay(DateUtil.parse("20200228", "yyyyMMdd")).toString());
////
////        System.out.println(DateUtil.formatDate(new Date()));
////
////
////        BigDecimal totalFeeAmt = new BigDecimal("0.0001").multiply(new BigDecimal("26")).multiply(new BigDecimal(247500)).setScale(0, BigDecimal.ROUND_HALF_UP);
////        System.out.println(totalFeeAmt.toString());
////
////        System.out.println(Double.valueOf("2425.25"));
////        System.out.println(Double.parseDouble("12.13") + 6.305);
////        double d = Double.valueOf("2425") - Double.parseDouble("12.13") + 6.305;
////        System.out.println(d);
////
////        double v = Double.parseDouble("2421.01") * Double.parseDouble("0.26") / 100;
////        System.out.println(v);
//
//        System.out.println("3fa0cce45cff1f81b1f49fe29bcb7822".length());
//        String s="{\"meterReading\":0.322}";
//        double d =JSONObject.parseObject("{\"meterReading\":0.322}").getDouble("meterReading");
//        System.out.println(d);
//        Double d1=0.322;
//        Double d2=0.3;
//        System.out.println(d1.compareTo(d2));
//        String stackTrace = Thread.currentThread().getStackTrace()[1].getMethodName();
//
//        System.out.println(Thread.currentThread().getStackTrace());
//
//        System.out.println(Expiration.from(5L, TimeUnit.SECONDS).getExpirationTimeInMilliseconds());
//
//        System.out.println(StringUtils.isBlank(""));
//        System.out.println(DateUtil.parseDate("2020-02-28 23:59:59").toJdkDate().getTime());
        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
        System.out.println(jaxbObjectToXML(new User().setUsername("liqi").setBigDecimal(BigDecimal.valueOf(0.0))));
        int i = 8;
        System.out.println();
        Double a = new Double(0);
        int i1 = a.compareTo(0.0);
        System.out.println(i1);
//        String format = String.format("%1$0" + 16 + "d", 0);
//        System.out.println(format);
        Double d = 100.0;
        System.out.println(d.compareTo(new BigDecimal(100.0).doubleValue()));

        BigDecimal one = new BigDecimal("0");
        BigDecimal two = new BigDecimal("0");
        BigDecimal three = BigDecimal.ZERO;
        System.out.println(one.equals(three));

        String s1 = "11";
        String s2 = "0";
        System.out.println(s1.compareTo(s2));


        BigDecimal totalFeeAmt = new BigDecimal("0.0001").multiply(new BigDecimal("25")).multiply(new BigDecimal(4596)).setScale(0, BigDecimal.ROUND_HALF_UP);
        System.out.println(totalFeeAmt.toString());

        BigDecimal thirdAmount = new BigDecimal(45.96).multiply(new BigDecimal(0.25)).multiply(new BigDecimal("0.01"));
        System.out.println(thirdAmount.toString());
        String reulst = new BigDecimal(30).subtract(new BigDecimal(0.15)).add(thirdAmount).toString();
        System.out.println(reulst);


        String dirPath = "file:/app/sddc/sddc/spring-cloud-sddc-gateway/target/spring.cloud.sddc.gateway-1.0.0-SNAPSHOT.jar!/BOOT-INF/classes!/autoCheckTemplate/autoCheckTemplate.xls";
        String jarPath = dirPath.substring(0, dirPath.indexOf("!/"));
        String targetPath = jarPath.substring(0, jarPath.lastIndexOf("/")).replace("file:", "");
        System.out.println(jarPath);
        System.out.println(new StringBuilder(targetPath).append("/classes/autoCheckTemplate/autoCheckTemplate.xls"));

        System.out.println("√".equals("√"));


        System.out.println("b2a1adfc9f9a44b57731440e31710740".length());

        /**
         *
         */
        Map<String, String> originMap = str2Map("MERCHANTID=105320148140002&POSID=100001135&BRANCHID=320000000&ORDERID=88487&PAYMENT=0.01&CURCODE=01&TXCODE=530590&REMARK1=&REMARK2=&TYPE=1&GATEWAY=0&CLIENTIP=128.128.80.125&REGINFO=xiaofeixia&PROINFO=digital&REFERER=&SMERID=111&SMERNAME=%u5DE5%u827A%u7F8E%u672F%u5546%u5E97&SMERTYPEID=112&SMERTYPE=%uRSAECipher.jar5BBE%u9986%u9910%u5A31%u7C7B&TRADECODE=001&TRADENAME=%u6D88%u8D39&SMEPROTYPE=1&PRONAME=%u5DE5%u827A%u54C1&TIMEOUT=20161028101226&TRADE_TYPE=MINIPRO&SUB_APPID=wx8888888888888888&SUB_OPENID=oUpF8uMuAJO_M2pxb1Q9zNjWeS6o&WX_CHANNELID=wx902937628837&USERPARAM=1U2xb%2FdMepRIs0KcM53xns%2Chdg2xBh3qwJ%2F%2F%2FHi%2FjMfPcbUYjQdxJKe%2CnoHnBgXppyQqPVPdDf8p%0D%0AEwxoLdkWjvdj2QUXJ5Hb&RETURN_FIELD=10000000000000000000");
        System.out.println(getSignContent(originMap).equals("MERCHANTID=105320148140002&POSID=100001135&BRANCHID=320000000&ORDERID=88487&PAYMENT=0.01&CURCODE=01&TXCODE=530590&REMARK1=&REMARK2=&TYPE=1&GATEWAY=0&CLIENTIP=128.128.80.125&REGINFO=xiaofeixia&PROINFO=digital&REFERER=&SMERID=111&SMERNAME=%u5DE5%u827A%u7F8E%u672F%u5546%u5E97&SMERTYPEID=112&SMERTYPE=%uRSAECipher.jar5BBE%u9986%u9910%u5A31%u7C7B&TRADECODE=001&TRADENAME=%u6D88%u8D39&SMEPROTYPE=1&PRONAME=%u5DE5%u827A%u54C1&TIMEOUT=20161028101226&TRADE_TYPE=MINIPRO&SUB_APPID=wx8888888888888888&SUB_OPENID=oUpF8uMuAJO_M2pxb1Q9zNjWeS6o&WX_CHANNELID=wx902937628837&USERPARAM=1U2xb%2FdMepRIs0KcM53xns%2Chdg2xBh3qwJ%2F%2F%2FHi%2FjMfPcbUYjQdxJKe%2CnoHnBgXppyQqPVPdDf8p%0D%0AEwxoLdkWjvdj2QUXJ5Hb&RETURN_FIELD=10000000000000000000"));

        String md5Str = DigestUtils.md5DigestAsHex(getSignContent(originMap).getBytes());
        System.out.println(md5Str);

        System.out.println(MD5Util.MD5Encode(getSignContent(originMap)));

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] md5 = md.digest(getSignContent(originMap).getBytes("utf-8"));


        Map<Long,String> sortMap= Maps.newTreeMap((o1, o2) -> o1.compareTo(o2));
        sortMap.put(10000L,"1");
        sortMap.put(10001L,"2");
        System.out.println(sortMap.toString());
        sortMap.clear();
        System.out.println(sortMap.toString());



    }

    private static int sayhello() throws Exception {
        int i = 1;
        int j = i / 0;
        return j;
    }

    private static String jaxbObjectToXML(User customer) {
        String xmlString = "";
        try {
            JAXBContext context = JAXBContext.newInstance(User.class);
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

            StringWriter sw = new StringWriter();
            m.marshal(customer, sw);
            xmlString = sw.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return xmlString;
    }


    public static String getSignContent(Map<String, String> sortedParams) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(sortedParams.keySet());
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = sortedParams.get(key);
            if (null != key && null != value) {
                content.append((index == 0 ? "" : "&") + key + "=" + value);
                index++;
            }
        }
        return content.toString();
    }


    private static Map<String, String> str2Map(String str) {
        Map<String, String> result = new LinkedHashMap<>();
        String[] results = str.split("&");
        if (results != null && results.length  > 0) {
            for (int var = 0; var < results.length; ++var) {
                String pair = results[var];
                String[] kv = pair.split("=", 2);
                if (kv != null && kv.length == 2) {
                    result.put(kv[0], kv[1]);
                }
            }
        }
        return result;
    }
}
