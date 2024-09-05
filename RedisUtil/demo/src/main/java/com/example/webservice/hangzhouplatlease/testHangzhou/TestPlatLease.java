package com.example.webservice.hangzhouplatlease.testHangzhou;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.webservice.hangzhouplatlease.company.service.impl.RentQyService;
import com.example.webservice.hangzhouplatlease.company.service.impl.RentQyServiceException_Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * @author Sky
 * create 2020/01/06
 * email sky.li@ixiaoshuidi.com
 **/
public class TestPlatLease {

    private static final Logger logger = LoggerFactory.getLogger(TestPlatLease.class);

    public static void main(String[] args) {
        String token = UUID.randomUUID().toString();
        JSONObject htjson = new JSONObject();
        htjson.put("qyhtbh", "ZKZLHT202001090004");
        htjson.put("fwcode", "413700");
        htjson.put("htqdsj", "2020-01-09");
        htjson.put("zlqssj", "2020-01-09");
        htjson.put("fkzq", "3");
        htjson.put("fkfs", "3");
        htjson.put("jzfs", "2");
        htjson.put("khh", "江苏银行");
        htjson.put("jzyhzh", "18320188000337289");
        htjson.put("yjje", "1000");
        htjson.put("zjje", "1000");
        htjson.put("zzjje", "1000");
        String htjsonstr = htjson.toString();
        logger.info("htjsonstr:{}", htjsonstr);
        //当事人
        JSONArray dsrlist = new JSONArray();
        //房东
        JSONObject fd = new JSONObject();
        fd.put("dsrmc", "XX 有限公司");
        fd.put("dsrlx", "1");
        fd.put("zjlb", "2013");
        fd.put("zjhm", "MA2018IJDHXXXX");
        fd.put("sjhm", "1300000000");
        fd.put("frdbxm", "李四");
        fd.put("frdbzjlb", "2000");
        fd.put("frdbzjhm", "330105198711051531");
        fd.put("dsrlb", "1");
        fd.put("gx", "0");
        dsrlist.add(fd);
        JSONObject zk = new JSONObject();
        zk.put("dsrmc", "张三");
        zk.put("dsrlx", "2");
        zk.put("zjlb", "2000");
        zk.put("zjhm", "330105198711061533");
        zk.put("sjhm", "1300000001");
        zk.put("dsrlb", "2");
        zk.put("gx", "0");
        dsrlist.add(zk);
        String dsrjsonstr = dsrlist.toString();
        logger.info("htjsonstr:{}", dsrjsonstr);
        RentQyService rentQyService = new RentQyService();
        try {
            token = "d8XWpkusVX9EcdqDqMKN72";
//            htjsonstr = "{\n" +
//                    "        \"khh\":\"江苏银行\",\n" +
//                    "        \"jzyhzh\":\"18320188000337289\",\n" +
//                    "        \"fkzq\":3,\n" +
//                    "        \"htqdsj\":\"2020-01-09\",\n" +
//                    "        \"zljzsj\":\"2020-02-08\",\n" +
//                    "        \"fwcode\":\"413700\",\n" +
//                    "        \"qyhtbh\":\"ZKZLHT202001090004\",\n" +
//                    "        \"fkfs\":3,\n" +
//                    "        \"zjje\":1000,\n" +
//                    "        \"jzfs\":2,\n" +
//                    "        \"zzjje\":1000,\n" +
//                    "        \"yjje\":1000,\n" +
//                    "        \"htqddd\":\"杭州拱墅和睦西湖路1号\",\n" +
//                    "        \"zlqssj\":\"2020-01-09\"\n" +
//                    "    }";
//            dsrjsonstr = "[\n" +
//                    "        {\n" +
//                    "            \"zjlb\":2000,\n" +
//                    "            \"dsrlb\":2,\n" +
//                    "            \"zjhm\":\"000000000000000000\",\n" +
//                    "            \"dsrmc\":\"杭州宁巢测试1\",\n" +
//                    "            \"sjhm\":\"13211111111\",\n" +
//                    "            \"dsrlx\":2,\n" +
//                    "            \"gx\":0\n" +
//                    "        }\n" +
//                    "    ]";
            String result = rentQyService.getRentQyServiceHttpSoap11Endpoint().saveHt(token, htjsonstr, dsrjsonstr);
            System.out.println("调用返回结果:" + result);
        } catch (RentQyServiceException_Exception e) {
            e.printStackTrace();
        }
    }
}
