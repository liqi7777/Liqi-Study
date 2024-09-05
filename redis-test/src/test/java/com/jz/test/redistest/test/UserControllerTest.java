package com.jz.test.redistest.test;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import com.jz.iecs.constant.VehicleConstant;
import com.jz.iecs.entity.DTO.IecsAivVehicInfoDTO;
import com.jz.iecs.entity.VO.IecsATerSelectTaskVO;
import com.jz.test.redistest.RedisTestApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * @author liqi
 * create  2021-08-31 09:43
 */
@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = {RedisTestApplication.class})
@SpringBootTest
@Slf4j
@WebAppConfiguration
public class UserControllerTest {

    @Autowired
    @Qualifier("redisTemplate01")
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    @Qualifier("redisTemplate01")
    private RedisTemplate redisTemplate01;

    @Test
    public void test01() {
        String jsonStr = "{\"@class\":\"com.jz.iecs.entity.DTO.IecsAivVehicInfoDTO\",\"vehicleNo\":\"H305\",\"longitude\":121.64361379499793,\"latitude\":31.338566040509672,\"vehicleLoadState\":\"1\",\"vehicleState\":\"2\",\"vehicleArea\":\"XQ13\",\"vehicleDirection\":null,\"vehicleSpeed\":0.29,\"vehicleType\":\"ITK\",\"vehicleDrivingState\":\"1\",\"vehicleLane\":\"1657324672539165008\",\"trkServeradd\":\"localhost:8090\",\"heading\":\"296.82\",\"targetAreaDistance\":null,\"vehicleRoad\":\"721138899999523152\",\"scopeCoordinate\":\"121.64353025194688,31.338589154619072 121.64354257011628,31.338610075359753 121.64369733804894,31.338542926400265 121.64368501987957,31.33852200565956\",\"laneNum\":1,\"uwbTask\":\"2\",\"targetAreaCode\":null,\"acceleratedSpeed\":161.35,\"angularSpeed\":-16.01,\"lastlat\":[\"java.util.LinkedList\",[\"121.64998477663563 31.336387681896532 1629202479671\",\"121.6499815664367 31.336389441665624 1629202480078\",\"121.64997873627931 31.336390801463043 1629202480087\",\"121.64361339503026 31.338565650484103 1629253926598\",\"121.64361379499793 31.338566040509672 1629253926719\"]],\"energyLevel\":null,\"mapId\":null,\"idType\":null,\"curTime\":1629253926719}";
        IecsAivVehicInfoDTO iecsAivVehicInfoDTO = JSONObject.parseObject(jsonStr, IecsAivVehicInfoDTO.class);

        redisTemplate.opsForHash().put("vehicle_real_time_data_1", "H777", iecsAivVehicInfoDTO);
        Object h777 = redisTemplate.opsForHash().get("vehicle_real_time_data_1", "H777");
    }


    /**
     * 页面Redis 和 redis环境里的车辆数比较
     */
    @Test
    public void test02() {
        String result = HttpRequest.post("http://10.140.20.163:31710/monitor/iecsRedis/getVehiclesFromRedis")
                .header(Header.CONTENT_TYPE, ContentType.JSON.getValue())
                .header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySW5mbyI6eyJ1c2VyTmFtZSI6Iui2hee6p-euoeeQhuWRmCIsInVzZXJJZCI6LTg4ODh9LCJ1c2VyX25hbWUiOiI4ODg4Iiwic2NvcGUiOlsiYWxsIl0sImV4cCI6MTYzMDQwMjUzOCwiYXV0aG9yaXRpZXMiOlsiQURNSU4iXSwianRpIjoiMzYzY2ZmZDQtM2M5Ny00YWZiLTk3ODItNTE2M2UwOTNlNzFiIiwiY2xpZW50X2lkIjoid2ViX2NsaWVudCJ9.SPwZZAdAm4_3_wos6ArL-FLQvb5pmLwcH5qc9X3E4Zhgzi96rtsFhqozpeJkXkUCgZPJVn65consx5IlPedD7kROTmtGhnXK5nVhxZ8YCwGSCt-mcQYs3E01vLf516tq2Jnn1kXd5h0sNXRtyrZARJdGE-lA-KciVNh_iE13TpTZ6mtxw9iW19n-Nz-gOG7awuOu7-kMdBRWb3NFdaXF7hAYfNQa4D5b_549nHbDFRWp1DOIvoCTq1UQKmKk8uayNG65TJ3Q54gxzdoI1Obg7YjTJT9-LOCZNospEXYYqvIzQ82SuxlxeJ3Wl560MzFFArDxaTOMTsGtPd9SIRBKiw")
                .execute().body();
        List<IecsAivVehicInfoDTO> vehicInfoDTOS = JSONObject.parseObject(result).getJSONArray("resultData").toJavaList(IecsAivVehicInfoDTO.class);
        List<String> vehicleNos = vehicInfoDTOS.stream().map(iecsAivVehicInfoDTO -> iecsAivVehicInfoDTO.getVehicleNo()).collect(Collectors.toList());

        List<String> redisVehicleNos = Lists.newArrayList();
        Map<Object, Object> entries = redisTemplate.opsForHash().entries("vehicle_real_time_data");
        entries.forEach((k, v) -> {
            redisVehicleNos.add((k.toString()));
        });
        Collection<String> subList = CollectionUtil.subtract(vehicleNos, redisVehicleNos);
        System.out.println(result);
    }

    @Test
    public void test03() {
        String jsonString = "{\"@class\":\"com.jz.iecs.entity.DTO.IecsAivVehicInfoDTO\",\"vehicleNo\":\"H404\",\"longitude\":121.6437976,\"latitude\":31.3395892,\"vehicleLoadState\":\"1\",\"vehicleState\":\"2\",\"vehicleArea\":\"\",\"vehicleDirection\":null,\"vehicleSpeed\":0.0,\"vehicleType\":\"ITK\",\"vehicleDrivingState\":\"0\",\"vehicleLane\":null,\"trkServeradd\":\"H404\",\"heading\":\"302.0\",\"targetAreaDistance\":null,\"vehicleRoad\":\"721138899999524237\",\"scopeCoordinate\":\"0\",\"laneNum\":1,\"uwbTask\":\"\",\"targetAreaCode\":null,\"acceleratedSpeed\":8.78,\"angularSpeed\":-0.47,\"lastlat\":[\"java.util.LinkedList\",[\"121.64526993356607 31.342682917105353 1630390010005\",\"121.64527512821932 31.34268050569516 1630390010190\",\"121.64528032287254 31.342678094285084 1630390010385\",\"121.64528806236498 31.342674514529506 1630390010570\",\"121.6437976 31.3395892 1630405735712\"]],\"energyLevel\":\"0\",\"mapId\":null,\"idType\":null,\"curTime\":1630406198061,\"courseAngle\":null}";
        //121.64387869,31.33960610
        IecsAivVehicInfoDTO iecsAivVehicInfoDTO = JSONObject.parseObject(jsonString, IecsAivVehicInfoDTO.class);
        String prefix = "JS";
        int count = 0;
        for (int i = 0; i < 50; i++) {
            String vehicleNo = prefix + count;
            iecsAivVehicInfoDTO.setLongitude(121.64387869d);
            iecsAivVehicInfoDTO.setLatitude(31.33960610d);
            iecsAivVehicInfoDTO.setVehicleNo(vehicleNo);

//            redisTemplate.opsForHash().put("vehicle_real_time_data", vehicleNo, iecsAivVehicInfoDTO);
            redisTemplate.opsForHash().delete("vehicle_real_time_data", vehicleNo);
            ++count;
        }

    }

    @Test
    public void test04() {
        //CROSSROADS_WORK:    CROSSROADS_NOT_WORK:
//        Set<Object> members = redisTemplate.opsForSet().members("CROSSROADS_NOT_WORK:");
//        for (Object member : members) {
//            IecsATerSelectTaskVO member1 = (IecsATerSelectTaskVO) member;
//            com.jz.test.redistest.test.IecsATerSelectTaskVO newvo=new com.jz.test.redistest.test.IecsATerSelectTaskVO();
//            BeanUtil.copyProperties(member1,newvo);
//            redisTemplate.opsForSet().add("CROSSROADS_WORK:",newvo);
//        }
//
        Set<Object> members = redisTemplate.opsForSet().members("CROSSROADS_WORK:");
        for (Object member : members) {
            com.jz.test.redistest.test.IecsATerSelectTaskVO member1 = (com.jz.test.redistest.test.IecsATerSelectTaskVO) member;
            redisTemplate.opsForSet().remove("CROSSROADS_WORK:", member1);

        }
    }

    @Test
    public void test05() throws InterruptedException {
        LinkedBlockingQueue<String> linkMessages = new LinkedBlockingQueue<String>();
        String poll = linkMessages.poll();
        System.out.println(poll);
        System.out.println("haha");
    }

    @Test
    public void test06() throws InterruptedException {
        List<User> users = Lists.newArrayList();
        users.add(new User().setName("liqi").setAddress("yulan"));
        users.add(new User().setName("liqi").setAddress("haha"));
        users.add(new User().setName("zhangsan").setAddress("yulan"));
        User user1 = users.stream().filter(user -> "liqi".equals(user.getName())).findFirst().orElse(null);
        user1.setName("liqi22");
        System.out.println(JSONObject.toJSONString(users));

        Map<String, String> collect = users.stream().collect(Collectors.toMap(user -> user.getName(), user -> user.getAddress(), (s, s2) -> s + "," + s2));
        LinkedBlockingQueue<String> linkMessages = new LinkedBlockingQueue<String>();
        String poll = linkMessages.poll();
        System.out.println(poll);
        System.out.println("haha");

        String s = "ItkLocationMqttBoltitk_location/H387";
        String[] split = s.split("/");
        System.out.println(split[1]);
    }


    @Test
    public void test07() throws Exception {
        User user = new User();
        user.setName("liqi");
        test07T(user);
        System.out.println(JSONObject.toJSONString(user));
    }

    <T> void test07T(T t) {
        String o = JSONObject.toJSONString(t);
        System.out.println(o);
        User t1 = (User) t;
        t1.setName("zhangsan");
    }

    @Test
    public void test08() {
        IecsAivVehicInfoDTO iecsAivVehicInfoDTO = (IecsAivVehicInfoDTO) redisTemplate.opsForHash().get(VehicleConstant.VEHICLE_REAL_TIME_DATA, "H309");
        System.out.println(iecsAivVehicInfoDTO);
    }


    @Test
    public void test09() {
//        redisTemplate.opsForList().rightPush("lqii",1L);
//        redisTemplate.opsForList().rightPush("lqii",2L);
//        redisTemplate.opsForList().rightPush("lqii",3L);
//        redisTemplate.opsForList().rightPushAll("lqii",10L,1L,2L);
//        redisTemplate01.opsForList().rightPushAll("liqi", ListUtil.toList(0L,9L,7L,8L,5L));

        Long liqi = redisTemplate.opsForList().remove("liqi", 0, 0);
        System.out.println(liqi);
    }

    @Test
    public void test10() {
//        redisTemplate.opsForList().rightPush("lqii",1L);
//        redisTemplate.opsForList().rightPush("lqii",2L);
//        redisTemplate.opsForList().rightPush("lqii",3L);
//        redisTemplate.opsForList().rightPushAll("lqii",10L,1L,2L);

//        Object lqii = redisTemplate01.opsForList().index("liqi", 0);
//        System.out.println(lqii.toString());


    }

}
