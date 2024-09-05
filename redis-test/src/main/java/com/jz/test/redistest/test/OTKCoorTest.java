package com.jz.test.redistest.test;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.google.common.collect.Lists;
import com.headingdata.iecs.APIService;
import com.headingdata.iecs.beans.LaneBean;
import com.headingdata.iecs.data.LngLat;
import com.jz.iecs.entity.VO.IecsYardTrucksVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import org.mortbay.log.Log;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author liqi
 * create  2022/5/24 3:34 下午
 */
@Slf4j
public class OTKCoorTest {

    public static void main(String[] args) {
        LngLat point = new LngLat(121.6488599, 31.32873);
        LaneBean laneBean = APIService.getLane(point, 0.0);
        System.out.println(laneBean);

    }

    @Test
    public void test01() {
        Resource resource = new ClassPathResource("application.yml");
        YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
        yamlFactory.setResources(resource);
        Properties properties = yamlFactory.getObject();

        String property = properties.getProperty("RealDataReport-spout.redisNodes");
        String[] split = property.split(",");
        Set<HostAndPort> nodes = new HashSet<>();
        for (String s : split) {
            String[] split1 = s.split(":");
            nodes.add(new HostAndPort(split1[0], Integer.valueOf(split1[1])));
        }
        int timeout = Integer.valueOf(properties.getProperty("spring.redis.timeout"));

        JedisCluster jedisCluster = new JedisCluster(nodes, timeout, timeout, 3, "admin", new GenericObjectPoolConfig());
        //直接使用JedisCluster操作redis,自带连接池,JedisCluster可以是单例的
        Map<String, String> map = jedisCluster.hgetAll("vehicle_info_data");
        List<String> collect = map.values().stream().collect(Collectors.toList());
        for (String s : collect) {
            System.out.println(s);
            IecsYardTrucksVO iecsYardTrucksVO = JSONObject.parseObject(s, IecsYardTrucksVO.class);
        }
        String vehicleNos = map.values().stream()
                .map(s -> JSONObject.parseObject(s, IecsYardTrucksVO.class))
                .filter(iecsYardTrucksVO -> Lists.newArrayList("ITK", "AIV").contains(iecsYardTrucksVO.getTrkType()))
                .map(iecsYardTrucksVO -> iecsYardTrucksVO.getTrkTrkno())
                .collect(Collectors.joining(","));
        System.out.println(vehicleNos);
    }


    @Test
    public void test02() {
        List<String> strings = Lists.<String>newArrayList();
        boolean haha = strings.stream().allMatch(s -> s.equals("haha"));
        System.out.println(haha);

        System.out.println(String.format("ahha%s", System.currentTimeMillis()));


        List<String> strings1 = Lists.newArrayList("16", "7");
        String join = StringUtils.join(strings1, ",");

        System.out.println(join.contains("6"));
    }


    @Test
    public void test03() {
        ThreadUtil.execute(() -> {
            System.out.println("haha");
        });

    }

    @Test
    public void test04() {
        String s = new StringBuilder("liqi").append("-").append("ahah").toString();
        String[] split = s.split("-");
        System.out.println(JSONObject.toJSONString(split));


        String s1 = "{\"OpinstrId\":57565122,\"CwpMachNo\":\"H109\",\"WkFlow\":\"1\",\"Opmode\":\"52\",\"QcWorkLane\":null,\"ContainerNo\":\"SLPU5006193\",\"ContainerType\":\"GP\",\"Vlocation\":\"180284\",\"Ylocation\":\"YQ0481\",\"WorkMoveSeq\":201,\"Opstatus\":\"C\",\"TruckNo\":\"H483\",\"RouteId\":\"2614\",\"ContainerID\":34051244,\"VpcContainerID\":92655254,\"Affg\":\"A\",\"ContainerSize\":\"40\",\"Opprc\":\"YV\",\"Driver\":\"1201\",\"RtgNo\":\"H505\"}";
        String s2 = new StringBuilder(s1).append("-").append("haha").toString();
        String[] split1 = s2.split("-");
        System.out.println(JSONObject.toJSONString(split1));
    }

    @Test
    public void test05() {
        String h805 = String.format("车号:%s,正在执行卸船任务(箱号:%s,TOSID:%s),尚未完成时收到TOS调度装船任务(箱号:%s,TOSID:%s),本次调度系统未处理", "h805", "1,1", "1,1", "1", 1L);
        String content = h805;
        System.out.println(content);

        String s = "haha";
        Long l = null;
        log.info("s:", l);

        String resultMsg = "{\"Success\":\"FALSE\",\"Msg\":\"处理失败ORA-20000: 两箱(TIIU2002856,TLLU3627942)总重超过50吨，集卡H372无拖运资质，不允许拖运！";
        resultMsg = resultMsg + "\"}";
        System.out.println(resultMsg);
    }

    @Test
    public void test06() {
        String[] strings = {"1", "2"};
        for (int i = 0; i < strings.length; i++) {
            if (i == 0) {
                strings[i] = "3";
            } else {
                strings[i] = "4";
            }
        }
        System.out.println(JSONObject.toJSONString(strings));
    }

    @Test
    public void test07() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = now.plusMinutes(-30);
        long l = Duration.between(localDateTime, now).toMinutes();
        System.out.println(l);

    }

    @Test
    public void test08() {
        String s = "real_data/report/H358";
        String[] split = s.split("real_data/report/");
        if (split.length == 2 && "+".equals(split[1])) {
            System.out.println("haha");
        }
    }

    @Test
    public void test09() {
        LocalDateTime of = LocalDateTimeUtil.of(System.currentTimeMillis());
        System.out.println(of);
    }

    @Test
    public void test10() {
        String s = "YQ2";
        Pattern compile = Pattern.compile("^[Y][Q][0-9]$");
        System.out.println(compile.matcher(s).find());

        String s1 ="JISUAN_H392_cc38b3018da7423da7b30c91bd64e63b";
        String s2 = s1.split("_")[1];
        System.out.println(s2);
    }

    @Test
    public void test11() {
        ArrayList<String> strings = Lists.newArrayList("d", "a", "b", "d", "c", "d");
        List<String> strings1 = strings.subList(0, strings.size() - 1);
        System.out.println(JSONObject.toJSONString(strings1));
        System.out.println(strings.removeIf(s -> s.equals("d")));
        System.out.println(JSONObject.toJSONString(strings));
    }

    @Test
    public void test12(){
        // String s =null;
        // boolean contains = Lists.newCopyOnWriteArrayList(Lists.newArrayList("1", "2", "3")).contains("2");
        // System.out.println(contains);

        String s2 = "{RTGSTAT_HANDLECONTROL_WARN_PANEL}:H237";
        String s1 = s2.split(":")[1];
        System.out.println(s1);

        String[] strings = {"1", "2"};
        strings[1]="3";
        System.out.println(JSONObject.toJSONString(strings));


        System.out.println(System.nanoTime());
    }

    @Test
    public void test13() {
        String s = "Hydra";
        System.out.println(s);

        System.out.println(Base64.getEncoder().encode("haha".getBytes()));

        System.out.println(JSONObject.toJSONString("11-22"));

        System.out.println(2%3);

        for (int i = 1; i <=5 ; i++) {
            if (i == 1) {
                i += 3;
            }
            System.out.println("haha");

        }
    }


}
