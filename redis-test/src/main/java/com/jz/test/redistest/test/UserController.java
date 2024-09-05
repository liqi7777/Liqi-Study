package com.jz.test.redistest.test;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
//import com.baomidou.lock.annotation.Lock4j;
import com.jz.iecs.constant.CommonConstant;
import com.jz.iecs.constant.VehicleConstant;
import com.jz.iecs.entity.DTO.IecsAivVehicInfoDTO;
import com.jz.test.redistest.config.redisLock.annoation.Lock4j;
import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.context.SpatialContextFactory;
import com.spatial4j.core.distance.DistanceUtils;
import com.spatial4j.core.shape.Rectangle;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author liqi
 * create  2021-08-18 12:11
 */
@Controller
@RequestMapping("/redis")
@Slf4j
public class UserController {

    @Autowired
    @Qualifier("redisTemplate01")
    private RedisTemplate redisTemplate;

//    @Resource
//    private RedisTemplate redisTemplate;


    @GetMapping("/test")
    @ResponseBody
    public String queryAnimal() {
        LocalDateTime start = LocalDateTime.now();
//        Set<Object> set = redisTemplate.opsForHash().keys(VehicleConstant.VEHICLE_REAL_TIME_DATA);
//        List<Object> list = redisTemplate.opsForHash().multiGet(VehicleConstant.VEHICLE_REAL_TIME_DATA, set);
//        redisTemplate.opsForHash().entries(VehicleConstant.VEHICLE_REAL_TIME_DATA);
//        List map_barrier = redisTemplate.opsForList().range("map_barrier", 0, -1);
//        Map<Object, Object> entries = redisTemplate.opsForHash().entries(VehicleConstant.VEHICLE_REAL_TIME_DATA);
//        LocalDateTime end = LocalDateTime.now();
//        System.out.println("redis消耗时间：" + Duration.between(start, end).toMillis() + "ms");
        String isInvokeIecsYtTy = (String)redisTemplate.opsForHash().get(CommonConstant.SYS_PARAMETER, "isInvokeIecsYtTy");
        System.out.println(isInvokeIecsYtTy);
        return "end";
    }


    @GetMapping("/test02")
    @ResponseBody
    public String testSerail() {
        LocalDateTime start = LocalDateTime.now();

        String jsonStr = "{\"@class\":\"com.jz.iecs.entity.DTO.IecsAivVehicInfoDTO\",\"vehicleNo\":\"H305\",\"longitude\":121.64361379499793,\"latitude\":31.338566040509672,\"vehicleLoadState\":\"1\",\"vehicleState\":\"2\",\"vehicleArea\":\"XQ13\",\"vehicleDirection\":null,\"vehicleSpeed\":0.29,\"vehicleType\":\"ITK\",\"vehicleDrivingState\":\"1\",\"vehicleLane\":\"1657324672539165008\",\"trkServeradd\":\"localhost:8090\",\"heading\":\"296.82\",\"targetAreaDistance\":null,\"vehicleRoad\":\"721138899999523152\",\"scopeCoordinate\":\"121.64353025194688,31.338589154619072 121.64354257011628,31.338610075359753 121.64369733804894,31.338542926400265 121.64368501987957,31.33852200565956\",\"laneNum\":1,\"uwbTask\":\"2\",\"targetAreaCode\":null,\"acceleratedSpeed\":161.35,\"angularSpeed\":-16.01,\"lastlat\":[\"java.util.LinkedList\",[\"121.64998477663563 31.336387681896532 1629202479671\",\"121.6499815664367 31.336389441665624 1629202480078\",\"121.64997873627931 31.336390801463043 1629202480087\",\"121.64361339503026 31.338565650484103 1629253926598\",\"121.64361379499793 31.338566040509672 1629253926719\"]],\"energyLevel\":null,\"mapId\":null,\"idType\":null,\"curTime\":1629253926719}";
        IecsAivVehicInfoDTO iecsAivVehicInfoDTO = JSONObject.parseObject(jsonStr, IecsAivVehicInfoDTO.class);

        redisTemplate.opsForHash().put("vehicle_real_time_data_1", "H777", iecsAivVehicInfoDTO);
        Object h777 = redisTemplate.opsForHash().get("vehicle_real_time_data_1", "H777");

        OutClass outClass = new OutClass();
        List<Object> list = new ArrayList<>();
        IecsAivVehicInfoDTO iecsAivVehicInfoDTO02 = JSONObject.parseObject(jsonStr, IecsAivVehicInfoDTO.class);
        IecsAivVehicInfoDTO iecsAivVehicInfoDTO03 = JSONObject.parseObject(jsonStr, IecsAivVehicInfoDTO.class);
        list.add(iecsAivVehicInfoDTO02);
        list.add(iecsAivVehicInfoDTO03);
        outClass.setLists(list);
        redisTemplate.opsForHash().put("vehicle_real_time_data_1", "H888", outClass);
        Object h888 = redisTemplate.opsForHash().get("vehicle_real_time_data_1", "H888");

        System.out.println(JSONObject.toJSONString(h777));


//        Map<Object, Object> entries = redisTemplate.opsForHash().entries(VehicleConstant.VEHICLE_REAL_TIME_DATA);
//        LocalDateTime end = LocalDateTime.now();
//        System.out.println("redis消耗时间：" + Duration.between(start, end).toMillis() + "ms");
        return "end";
    }


    @GetMapping("/test03")
    @ResponseBody
    public String testHttpAsync() throws InterruptedException {
//        Thread.sleep(3000);
        System.out.println("haha");
        return "hehe";
    }


//    /**
//     * 数字孪生接口测试
//     */
//    private DigitalTwinServiceImpl digitalTwinService = new DigitalTwinServiceImpl();
//
//    @GetMapping("/test04")
//    @ResponseBody
//    public List<AivDTO> testDigital() {
//        try {
//            List<AivDTO> card = digitalTwinService.getCard();
//            return card;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @GetMapping("/test05")
    @ResponseBody
    public Long testTimerDelay(){
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("haha" + LocalDateTime.now());
                timer.cancel();
            }
        },10000);
        return System.currentTimeMillis();
    }


    public static void main(String[] args) {
        String result = HttpRequest.post("http://127.0.0.1:8099/redis/test03")
                .header(Header.CONTENT_TYPE, ContentType.JSON.getValue())
                .executeAsync().body();
        System.out.println(result);
        System.out.println("haha");
    }


    @PostMapping("/test05")
    @Lock4j(keys ={"#user.toString()"} ,expire = 30000)
    @ResponseBody
    public String test05(User user) throws Exception{
        MDC.put("uuid", UUID.randomUUID().toString());
        log.info("ahah");
        ThreadUtil.execute(() -> {
            log.info("hehehe");
        });
        long curTime = System.currentTimeMillis();
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                if (System.currentTimeMillis() - curTime > 5000) {
//                    log.info("timer,hahah");
//                }
//            }
//        }, 0, 1000);
        Thread.sleep(30000);
        return  "hehehe";
    }


    @PostMapping("/test06")
    @Lock4j(keys ={"#user"} ,expire = 30000)
    @ResponseBody
    public String test06(String user) throws Exception{
        MDC.put("uuid", UUID.randomUUID().toString());
        log.info("ahah");
        ThreadUtil.execute(() -> {
            log.info("hehehe");
        });
        long curTime = System.currentTimeMillis();
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                if (System.currentTimeMillis() - curTime > 5000) {
//                    log.info("timer,hahah");
//                }
//            }
//        }, 0, 1000);
        Thread.sleep(30000);
        return  "hehehe";
    }

    @PostMapping("/test07")
    @ResponseBody
    public String test07(@RequestBody Long[] ids){
        System.out.println(JSONObject.toJSONString(ids));
        return "haha";
    }


    @GetMapping("/test08")
    @ResponseBody
    public void test08(){
        //121.644321，31.342865
        Double longitude = 121.644321d;
        Double latitude = 31.342865d;
        //121.64542876344942,31.3426279844988
        Double lat = 31.3426279844988d;
        Double lon = 121.64542876344942d;
        double d =100;
        SpatialContext geo = SpatialContext.GEO;
        Rectangle rectangle = geo.getDistCalc().calcBoxByDistFromPt(
                geo.makePoint(longitude, latitude), (d/1000) * DistanceUtils.KM_TO_DEG, geo, null);
        double minlat = rectangle.getMinY();
        double maxlat = rectangle.getMaxY();
        double minlng = rectangle.getMinX();
        double maxlng = rectangle.getMaxX();
        System.out.println(minlat);
        System.out.println(maxlat);
        System.out.println(minlng);
        System.out.println(maxlng);

        Rectangle rectangle2 = geo.getDistCalc().calcBoxByDistFromPt(
                geo.makePoint(longitude, latitude), (d*2/1000) * DistanceUtils.KM_TO_DEG, geo, null);
        double minlat2 = rectangle2.getMinY();
        double maxlat2 = rectangle2.getMaxY();
        double minlng2 = rectangle2.getMinX();
        double maxlng2 = rectangle2.getMaxX();
        System.out.println(minlat2);
        System.out.println(maxlat2);
        System.out.println(minlng2);
        System.out.println(maxlng2);
        // 121.642215035731，31.341066359264477


        if ((minlat <= lat && lat <= maxlat) && (minlng <= lon && lon <= maxlng)) {
            System.out.println("haha");
        }
//        121.58192269510106,31.197227746604643
        //121.64326801786555,31.34196567963224  121.64326801786555,31.34376432036776 121.64537398213446,31.34196567963224 121.64537398213446,31.34376432036776 121.64426801786555,31.34226567963224

    }




}
