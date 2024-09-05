package com.jz.test.redistest.test;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.math.MathUtil;
import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.thread.ThreadFactoryBuilder;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.google.common.base.Joiner;
import com.google.common.collect.Comparators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.jz.iecs.constant.VehicleConstant;
import com.jz.iecs.entity.PO.IecsOperateInstrMachinesPO;
import com.jz.iecs.entity.PO.IecsOperateInstrTrucksD;
import com.jz.iecs.entity.VO.IecsOperateInstrMachinesVO;
import com.jz.iecs.entity.VO.ObjBaypositionVO;
import com.jz.test.redistest.config.SpringContext;
import com.jz.test.redistest.domain.BusinessType;
import com.jz.test.redistest.domain.GroupTopic;
import com.jz.test.redistest.domain.IecsDeviceBehaviorLogDTO;
import com.jz.test.redistest.test.httpTest.HttpUtils;
import com.jz.utils.util.RandomUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.hadoop.hbase.classification.InterfaceAudience;
import org.apache.hadoop.io.Text;
import org.apache.poi.ss.formula.functions.T;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Assert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @version 1.0
 * @Description
 * @Author WANGJ-JZT
 * @date 2021/8/17 9:10
 */
@Component
@Slf4j
public class Test {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${rediscount}")
    private int rediscount;

    //    @Scheduled(fixedRate = 20000)
    public void test() {
        for (int i = 0; i < 2; i++) {
            StormService stormService = (StormService) SpringContext.getApplicationContext().getBean("stormServiceImpl");
            RedisTemplate redisTemplate = stormService.getRedisTemplate();
            Set<Object> set = redisTemplate.opsForHash().keys(VehicleConstant.VEHICLE_REAL_TIME_DATA);
            List<Object> list = redisTemplate.opsForHash().multiGet(VehicleConstant.VEHICLE_REAL_TIME_DATA, set);

            System.out.println(stormService);
            System.out.println("haha");

        }

        LocalDateTime start = LocalDateTime.now();

//        Set<Object> set = redisTemplate.opsForHash().keys(VehicleConstant.VEHICLE_REAL_TIME_DATA);
//        List<Object> list = redisTemplate.opsForHash().multiGet(VehicleConstant.VEHICLE_REAL_TIME_DATA, set);
//        LocalDateTime end = LocalDateTime.now();
//        System.out.println("redis消耗时间：" + Duration.between(start, end).toMillis() + "ms,车辆总数:" + list.size());
        /*for(int i=0;i<rediscount;i++){
            ThreadUtil.execute(() -> {
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }*/
    }

//    @Scheduled(fixedRate = 2000)
//    @Async
//    public void testSleep() throws InterruptedException {
//        LocalDateTime localDateTime = Instant.ofEpochMilli(System.currentTimeMillis()).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
//        log.info("Scheduled_test:" + localDateTime);
//        Thread.sleep(4000);
//    }

    public static void main(String[] args) {
        Long l = 10000L;
        System.out.println(l == 10000);

        System.out.println(2 * DateUnit.MINUTE.getMillis());

        Long testL = 1L;
        System.out.println(Optional.ofNullable(testL).orElse(System.currentTimeMillis()));


        String li = StrUtil.format("{}hello,liqi", new Object[]{});
        System.out.println(li);


        String msg = "{\"pid\":211,\"machNo\":\"H243\",\"machType\":\"RTG\",\"taskList\":[{\"taskId\":\"194660\",\"tosTaskId\":\"47945570\",\"taskSerial\":120,\"taskType\":0,\"soaTaskType\":0,\"taskStatus\":\"0\",\"operationStatus\":0,\"soatgId\":\"H243\",\"soaWorkType\":\"DSCH\",\"soaCntrnoHb\":\"TGSU4031253\",\"soaCntrPosition\":3,\"soaIsoHb\":\"42G1\",\"soaTruckHb\":\"H415\",\"soaTruckType\":\"ITK\",\"soaTruckPos\":\"1346\",\"soaScntrPos\":null,\"soaTcntrPos\":\"134622\",\"soaTgtbayMap\":null,\"transferPosition\":null,\"transferTime\":null}]}\t";
        JSONArray taskArray = JSONObject.parseObject(msg).getJSONArray("taskList");
        for (Object o : taskArray) {
            JSONObject paramJson = (JSONObject) o;
            String taskId = paramJson.getString("taskId");
            String s = paramJson.toJSONString(paramJson, SerializerFeature.WriteMapNullValue);
            System.out.println(s);
        }
        System.out.println("hah");

        String bay = "21";
        System.out.println(bay.substring(2, 2));
        int i = "ABCDE".indexOf(bay.substring(0, 1));
        System.out.println(i);
        String s = "02";
        System.out.println(Integer.parseInt(s));

        String s1 = GET_BAYNO("1321");
        System.out.println(s1);

        String newYLocation = "131122";
        newYLocation = newYLocation.substring(0, 2) + addZero((Integer.parseInt(newYLocation.substring(2, 4)) + 1)) + newYLocation.substring(4, 6);
        System.out.println(newYLocation);


    }


    public static String addZero(Integer num) {
        if (num < 10) {
            return "0" + num;
        } else {
            return num.toString();
        }
    }


    public static String GET_BAYNO(String bay) {
        String FunctionResult;
        Integer n_index;
        FunctionResult = bay;
        if (StringUtils.isEmpty(bay)) {
            return "";
        }
        n_index = "ABCDE".indexOf(bay.substring(0, 1));
        if (n_index > -1) {
            FunctionResult = 9 + n_index + bay.substring(2, 2);
        }
        return FunctionResult;
    }


    @org.junit.Test
    public void test01() {
        Set<String> rowSet = Sets.newTreeSet(Lists.newArrayList("1", "2", "4", "5", "6"));
        Set<String> allRowSet = Sets.newTreeSet(Lists.newArrayList("1", "2", "3", "4","7"));

        Sets.SetView<String> difference = Sets.difference(allRowSet, rowSet);
        System.out.println(difference.isEmpty());
        System.out.println(difference);
        Sets.SetView<String> union = Sets.union(rowSet, allRowSet);
        System.out.println(JSONObject.toJSONString(union));

        Sets.SetView<String> intersection = Sets.intersection(rowSet, allRowSet);
        System.out.println(JSONObject.toJSONString(intersection));

        Object[] a = new Object[]{1, 2};
        System.out.println(JSONObject.toJSONString(a));
    }

    @org.junit.Test
    public void test02() {
        String s = "135251";
        String substring = s.substring(0, 5);
        System.out.println(substring);
        List<String> allRow = Lists.newArrayList("135252", "135261");
        allRow = allRow.stream().filter(s1 -> !s1.startsWith(substring)).collect(Collectors.toList());
        System.out.println(JSONObject.toJSON(allRow));
    }

    @org.junit.Test
    public void test03() {
        IecsOperateInstrTrucksD trucksD = new IecsOperateInstrTrucksD();
        trucksD.setTaskRemoveFlag(null);
        trucksD.setTask2RemoveFlag(1);
        Optional<IecsOperateInstrTrucksD> op = Optional.ofNullable(trucksD);
        IecsOperateInstrTrucksD trucksD1 = op.get();
        BiFunction<IecsOperateInstrTrucksD, Integer, Integer> taskRemoveFlagFun = (truckD, taskNum) -> {
            Integer taskRemoveFlag = truckD.getTaskRemoveFlag();
            Integer task2RemoveFlag = truckD.getTask2RemoveFlag();
            if (taskNum == 1) {
                if (taskRemoveFlag == null) {
                    if (task2RemoveFlag == null) {
                        return 0;
                    } else {
                        taskRemoveFlag = (++task2RemoveFlag);
                        truckD.setTask2RemoveFlag(null);
                        return taskRemoveFlag;
                    }
                } else {
                    return ++taskRemoveFlag;
                }
            } else {
                if (task2RemoveFlag == null) {
                    if (taskRemoveFlag == null) {
                        return 0;
                    } else {
                        task2RemoveFlag = (++taskRemoveFlag);
                        truckD.setTaskRemoveFlag(null);
                        return task2RemoveFlag;
                    }
                } else {
                    return ++taskRemoveFlag;
                }
            }
        };
        Integer apply = taskRemoveFlagFun.apply(trucksD, 1);
        op.map(iecsOperateInstrTrucksD -> taskRemoveFlagFun.apply(trucksD, 1));
        System.out.println(apply);
        System.out.println(JSONObject.toJSONString(trucksD));
        System.out.println(JSONObject.toJSONString(trucksD1));
    }

    @org.junit.Test
    public void test04() {
        double a = 0.0;
        System.out.println(a >= 0);
    }

    @org.junit.Test
    public void test05() {
//        AtomicLong atomicLong = new AtomicLong(0L);
//        atomicLong.addAndGet(5);
//        System.out.println(atomicLong.get());
//        String s = (String) "0";
//        System.out.println(s);
//        System.out.println("123431".length());
//        System.out.println(Integer.valueOf("120331".substring(2, 4)));
//
//        if (1 == 1) {
//            System.out.println("hh3");
//        } else if (1 == 1) {
//            System.out.println("hah");
//        }

        testhehe1();



    }

    public void testhehe1() {
        testhehe();
    }

    public void testhehe() {
        testhaha();
    }

    public void testhaha() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getClassName() + "_" + Thread.currentThread().getStackTrace()[2].getMethodName());
    }


    @org.junit.Test
    public void test06() {
        HashMap<String, Object> stringVHashMap = Maps.<String, Object>newHashMap();
        stringVHashMap.put("triggerShipNo", "1001");
        stringVHashMap.put("triggerVno", "H310");
        stringVHashMap.put("taskid", "1366401");

//        HttpResponse execute = HttpRequest.post("https://10.140.20.163:31710/iecs/uwb/qcDispatch").body(JSONObject.toJSONString(stringVHashMap)).execute();
//        System.out.println(execute);

        Map<String, Object> stringObjectMap = HttpUtils.sendPostHttp("https://10.140.20.163:31710/iecs/uwb/qcDispatch", stringVHashMap, "");
        System.out.println(stringObjectMap);

        Map<String, Object> map = Maps.newHashMap();
        map.put("grant_type", "client_credentials");
        map.put("scope", "vehicle");
        map.put("client_id", "TrIpiivbkZdlnFGQcEZHtivQu0xobpUL");
        map.put("client_secret", "genesis_secret");
        String execute = HttpRequest.post("https://10.140.20.163:31720/oauth").form(map).timeout(3000).execute().body();
    }


    @org.junit.Test
    public void test07() {
//        ArrayList<String> strings = Lists.newArrayList("1", "2", "3");
//        strings.forEach(s -> {
//            if("1".equals(s)){
//                return;
//            }
//            System.out.println(s);
//        });

        int i = Integer.parseInt("02");
        System.out.println(i);

        Integer j = 5;
        if (++j <= 5) System.out.println(j);
        System.out.println(j);


        List<User> users = new ArrayList<>();
        List<User> liqi = users.stream().filter(user -> user.getName().equals("liqi")).collect(Collectors.toList());
        System.out.println(liqi);
        System.out.println(User.builder().id(1L).build().toString());

    }

    @org.junit.Test
    public void test08() {
        String hostName = SystemUtils.getHostName();
        System.out.println(hostName);
    }

    @org.junit.Test
    public void test09() {
//        boolean 沪A8888 = isContainChinese("H806");
//        System.out.println(沪A8888);
        List<String> AivLaneNumList = new ArrayList<>();
        List<String> ItkLaneNumList = new ArrayList<>();
//        Map<String, List<String>> build = MapUtil.builder(new HashMap<String, List<String>>()).put("ITK", ItkLaneNumList)
//                .put("AIV", AivLaneNumList).build();
        Map<String, String> build = MapUtil.builder(new HashMap<String, String>())
                .put("key1", "value1")
                .put("key3", "value3")
                .put("key2", "value2").build();
        System.out.println(JSONObject.toJSON(build));
        String join1 = MapUtil.sortJoin(build, "&", "=", false);
        System.out.println(join1);

    }

    /**
     * 字符串是否包含中文
     *
     * @param str 待校验字符串
     * @return true 包含中文字符 false 不包含中文字符
     */
    public static boolean isContainChinese(String str) {
        if (StringUtils.isEmpty(str)) {
        }
        Pattern p = Pattern.compile("[\u4E00-\u9FA5|\\！|\\，|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }


    @org.junit.Test
    public void test10() {
        ArrayList<Integer> integers = Lists.newArrayList(1, 2);
        Double collect = integers.stream().collect(Collectors.averagingInt(Integer::intValue));
        String s = new BigDecimal(collect).setScale(0, BigDecimal.ROUND_HALF_UP).toString();
        System.out.println(s);

        String format = String.format("%.0f", collect);
        System.out.println(format);


        String warnMessage = String.format("距离最近依次收到TOS数据已超过%s分钟", 5);
        System.out.println(warnMessage);


        System.out.println(560 / 60);

        int i = 1;
        System.out.println(++i);
        int j = 2;
        System.out.println(++i);

    }


    @org.junit.Test
    public void test11() {

//        String url = "http://10.140.20.201:18681/other/queue/info";
//        String iotMessage = HttpUtil.get(url);
        String iotMessage = "{\n" +
                "    \"resultCode\":\"200\",\n" +
                "    \"resultMsg\":\"操作成功\",\n" +
                "    \"resultTimestamp\":1642507379886,\n" +
                "    \"resultData\":[\n" +
                "        {\n" +
                "            \"time\":\"2022-01-12 13:55:55,333\",\n" +
                "            \"clientId\":null,\n" +
                "            \"queueId\":\"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"time\":null,\n" +
                "            \"clientId\":null,\n" +
                "            \"queueId\":\"Ln4YQUrNQyWubUtWJXDcEQAAAAAAAbpv\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"time\":null,\n" +
                "            \"clientId\":null,\n" +
                "            \"queueId\":\"Ln4YQUrNQyWubUtWJXDcEQAAAAAAAfFU\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        JSONObject resultJsonObject = JSONObject.parseObject(iotMessage);
        JSONArray resultData = resultJsonObject.getJSONArray("resultData");
        List<Test11> test11s = JSONObject.parseArray(resultData.toJSONString(), Test11.class);

        resultJsonObject.get("resultData");
    }

    @org.junit.Test
    public void test12() {
        String content = "|PLANTYPE|%,;|GOODTYPE|%,;|CNTRNO|%,;|CSIZECD|40,;|ISOCD|GP,;|CSTATUS|%,;|COPERCD|SIT,;|DNGGNO|-,;|OVLMTCD|N,;|TRAN_MODE|%,;|BDFG|N,;|CHEIGHTCD|N,;|STTMPT|N,;|INSPECTID|%,;|BILLNO|%,;|EVSCHEDULE|%,;|VSLTYPE|%,;|SPECIALOPERATE|-,K,;";
        Pattern compile = Pattern.compile("(\\|[a-zA-Z0-9]+\\|[a-zA-Z0-9|%][,;])+");
        Matcher matcher = compile.matcher(content);
        if (matcher.find()) {
            System.out.println("haha");
            String[] split = content.split(",;");
            for (String s : split) {
//                System.out.println(s);
                if (s.indexOf("CSIZECD") > 0) {
                    String[] split01 = s.split("\\|");
                    for (String s1 : split01) {
                        System.out.println(s1);
                    }
                }
                if (s.indexOf("CSTATUS") > 0) {
                    String[] split01 = s.split("\\|");
                    String s1 = split01[2];
                    if (!"%".equals(s1)) {
                        String s2 = s1.split(",")[0];
                        System.out.println(s2);
                    }
                }
            }
        }

//        Pattern compile1 = Pattern.compile("\\|[a-zA-Z0-9]+\\|[a-zA-Z0-9|%]+");
//        Matcher matcher1 = compile1.matcher("|PLANTYPE|%");
//        if(matcher1.find()){
//            System.out.println("hehe");
//        }

    }


    @org.junit.Test
    public void test13() {
        String s = "1A";
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            System.out.println(aChar);
        }
        Integer integer = Integer.valueOf("01");
        System.out.println(integer);

        String s1 = "W4A1";
        String s2 = "W4A2";
        int i = s1.compareTo(s2);
        System.out.println(i);

        String tmp = s2;
        s2 = s1;
        s1 = tmp;
        System.out.println(s1 + "," + s2);
    }


    @org.junit.Test
    public void test14() {
        String s1 = "A1";
        char[] chars = s1.toCharArray();
        for (char aChar : chars) {
            String s = String.valueOf(aChar);
            System.out.println(aChar);
            System.out.println(s);
        }
    }


    @org.junit.Test
    public void test15() {
//        Object i = 1;
//        Class<?> aClass = i.getClass();
//        System.out.println(aClass);
//        String s = null;
//        Optional.ofNullable(s).filter(s1 -> s1.equals("Y")).ifPresent(s1 -> {
//            System.out.println(s1);
//        });


        LocalDateTime localDateTime = Instant.ofEpochMilli(System.currentTimeMillis()).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
        String format = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS"));
        System.out.println(localDateTime);
        System.out.println(format);

    }

    @org.junit.Test
    public void test16() {
        ArrayList<String> strings = Lists.newArrayList("A", "B", "C", "D", null);

        ArrayList<Integer> integers = Lists.newArrayList(3, 1, 2, 4, 8, 7);


        Object[] removeKeys = strings.toArray();
        removeKeys = Arrays.stream(removeKeys).filter(Objects::nonNull).toArray();
        System.out.println(removeKeys);

        Collections.reverse(strings);
        Collections.reverse(integers);
        System.out.println(strings);

        List<String> strings1 = Lists.newArrayList("132111", "133122");
        strings1.sort(Comparator.comparing(String::trim).reversed());
        System.out.println(strings1);


    }


    @org.junit.Test
    public void test17() {
        User user01 = new User(2L, "zhangsan");
//        User user02 = new User(2L, "zhangsan");
//        user01.setCurUser(user02);
        System.out.println(JSONObject.toJSONString(user01));
        User user03 = new User(1L, null);
        ArrayList<User> objects = Lists.newArrayList();
        objects.add(user01);
//        objects.add(user02);
        objects.add(user03);
        objects.stream().collect(Collectors.toMap(User::getId, User::getName));

//        objects = objects.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
//                () -> new TreeSet<>(Comparator.comparing(User::getId))), ArrayList::new));
//        System.out.println(objects);

//        List<User> collect = objects.stream().sorted(Comparator.comparing(User::getName, Comparator.nullsLast(String::compareTo)).thenComparing(Comparator.comparing(User::getId))).collect(Collectors.toList());
//        List<User> result = objects.stream().collect(Collectors.toMap(User::getId, a -> a, (o1, o2) -> {
//            o1.setName(Joiner.on(",").skipNulls().join(new String[]{o1.getName(), o2.getName()}));
//            return o1;
//        })).values().stream().collect(Collectors.toList());
//        System.out.println(result);
//
//        Long ONE_LONG = 1L;
//        System.out.println(ONE_LONG.equals(1L));
//
//        List<String> strings = Lists.newArrayList("1", "2");
//        strings.remove(1L);
//        System.out.println(strings);
//
//        Date cwpAendtm = null;
//        ZoneId zoneId = ZoneId.systemDefault();
//        LocalDateTime cwpAendtmLdt = LocalDateTime.ofInstant(cwpAendtm.toInstant(), zoneId);
    }


    @org.junit.Test
    public void test18() {
        User user01 = new User(1L, null);
        User user02 = new User(2L, "zhangsan");
        User user03 = new User(1L, "lisi");
        BeanUtil.copyProperties(user01, user02, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
        System.out.println(JSONObject.toJSONString(user02));

        Long getWorkCntrNum = null;
        if (!(Long.valueOf(1).equals(getWorkCntrNum) || Long.valueOf(2).equals(getWorkCntrNum))) {
            System.out.println(true);
        }

        String s = "明23";
        System.out.println(s.length());


        String areaBay = "haha";
        Long iycCntrid = null;
        Long oisOpinstrid = 2L;
        String join = Joiner.on("").skipNulls().join(new Object[]{areaBay, iycCntrid, oisOpinstrid});
        System.out.println(join);
        Object a = null;
        String a1 = (String) a;
        System.out.println(a1);

        String batch = "78,89";
        System.out.println(batch.substring(1));


    }

    @org.junit.Test
    public void test19() {
        long sub = 1;
        BigDecimal bigDecimal = BigDecimal.valueOf(sub)
                .divide(BigDecimal.valueOf(60), 0, BigDecimal.ROUND_UP);

        System.out.println(bigDecimal);

        String warnMessage = String.format("距离最近依次收到TOS数据已超过%s分钟", BigDecimal.valueOf(sub)
                .divide(BigDecimal.valueOf(60), 0, BigDecimal.ROUND_UP));
        System.out.println(warnMessage);


        User user = new User();

        Boolean bool = Boolean.TRUE;
        System.out.println(bool.equals(Boolean.TRUE));


        TaskSendFlagDO taskSendFlagDO = new TaskSendFlagDO(false, false, false);
        changetaskSendFlagDO(taskSendFlagDO);
        System.out.println(taskSendFlagDO);

    }

    private void changetaskSendFlagDO(TaskSendFlagDO taskSendFlagDO) {
        taskSendFlagDO.setQcFlag(true);
        taskSendFlagDO.setVehicleFlag(true);
    }


    @org.junit.Test
    public void test20() {
        LocalDateTime start = LocalDateTimeUtil.parse("2020-02-02T00:00:00");
        LocalDateTime end = LocalDateTimeUtil.parse("2020-02-02T00:00:01");

        Duration between = LocalDateTimeUtil.between(start, end);
        between.toDays();
        System.out.println(between.getSeconds());
    }


    @org.junit.Test
    public void test21() {
        Type userClass = User.class;
        System.out.println(userClass.equals(User.class));
    }


    @org.junit.Test
    public void test22() {
        System.out.println(judge(new int[]{1, 3}, new int[]{3, 5}));
        System.out.println(judge(new int[]{1, 3}, new int[]{4, 5}));
        System.out.println(judge(new int[]{3, 5}, new int[]{4, 8}));

    }

    public static boolean judge(int[] A, int[] B) {
        return Math.max(A[0], B[0]) <= Math.min(A[1], B[1]);
    }


    @org.junit.Test
    public void test23() {
        ArrayList<String> strings = Lists.newArrayList("1", "2");
        System.out.println(strings.size());
        strings.clear();
        System.out.println(strings.size());

    }

    @org.junit.Test
    public void test24() {
        Map<Object, Object> colorMap = MapUtil.of(new String[][]{
                {"RED", "#FF0000"},
                {"GREEN", "#00FF00"},
                {"BLUE", "#0000FF"}
        });
        System.out.println(colorMap.toString());


        Map<String, String> build = MapUtil.builder(new HashMap<String, String>())
                .put("key1", "value1")
                .put("key3", "value3")
                .put("key2", "value2").build();
        System.out.println(build.toString());

        Integer i = 0;
        if (i != null && 0 == i) {
            System.out.println("hah");
        }

        String param = "XQ12,XQ13";
        System.out.println(param.startsWith("XQ"));

        boolean contains = param.contains("14");
        System.out.println(contains);

    }


    @org.junit.Test
    public void test25() {
        User user1 = null;
        System.out.println((User) user1);
        User user = new User();
        user.setAddress("liqi");
        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(user);

        System.out.println(stringObjectMap);


    }


    @org.junit.Test
    public void test26() {
        String s1 = "vehicle,vehicleRealData,electronics_enclosure/send,taskLog/receipt,real_data/receipt,task/Issue/aiv,receipt_result/aiv,electronics_enclosure/single_send,truck_interactive/car/request_receipt,truck_interactive/car/info,truck_interactive/car/rerequest,traffic_control,uwb_path,uwb_position,workFlow/car/receipt,workFlow/car/feedback,laneClose/car/feedback,task/iecs_receipt/rtg,navigateQcInf/send,slingsState/send/feedback";
        String[] split1 = s1.split(",");
        System.out.println(split1.length);


        String s2 = "0,0,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1";
        String[] split2 = s2.split(",");
        System.out.println(split2.length);

    }


    @org.junit.Test
    public void test27() throws Exception {
//        test27(new User());

        String area1 = "3L";
        String area2 = "3K";
        String collect = Lists.newArrayList(area1, area2).stream().sorted((o1, o2) -> o1.compareTo(o2)).collect(Collectors.joining(","));
        System.out.println(collect);

        LocalDateTime with = LocalDateTime.now().with(LocalTime.MIN);
        System.out.println(with);

    }


    public <T> void test27(T t) {
        System.out.println("haha");
    }

//    public void test27(Object t){
//        System.out.println("hehe");
//    }


    @org.junit.Test
    public void test28() throws Exception {
        Pattern pattern = Pattern.compile("Java");
        String test = "123Java456Java789Java";
        String[] result = pattern.split(test, 2);
        for (String s : result)
            System.out.println(s);

        result = pattern.split(test, 10);
        System.out.println(result.length);

        result = pattern.split(test, -2);
        System.out.println(result.length);

        result = pattern.split(test, 0);
        System.out.println(result.length);


        String test1 = "Java";
        String test2 = "Java123456";

        System.out.println(Pattern.matches("Java", test1));//返回true
        System.out.println(Pattern.matches("Java", test2));//返回false

    }


    @org.junit.Test
    public void test29() throws Exception {
        System.out.println(BusinessType.CLEAN.ordinal());
        Long l1 = 1L;
        Long l2 = 2L;
        System.out.println(l1.compareTo(l2));

        User user = new User();
        user.setAddress("ahah");
        System.out.println(JSONObject.toJSONString(user));
    }


    @org.junit.Test
    public void test30() throws Exception {
        String json = "\"{\"resultCode\":\"200\",\"resultTimestamp\":null,\"resultMsg\":\"选位失败:堆场策划系统异常:\\r\\nORA-20532: \\r箱区 33 内集卡已满\\r箱区 38 内集卡已满\\r箱区 3G 内集卡已满\\r箱区 45 内集卡已满\\r箱区 18 内集卡已满\\r箱区 47 内集卡已满\\r箱区 34 内集卡已满\\r箱区 2E 内集卡已满\\r箱区 3H 内集卡已满\\r箱区 17 内集卡已满\\r箱区 26 内集卡已满\\r箱区 37 内集卡已满\\r箱区 3E 内集卡已满\\r箱区 46 内集卡已满\\r箱区 4E 内集卡已满\\r箱区 4G 内集卡已满\\r箱区 33 内集卡已满\\r箱区 38 内集卡已满\\r箱区 3G 内集卡已满\\r箱区 45 内集卡已满\\r箱区 18 内集卡已满\\r箱区 34 内集卡已满\\r箱区 2E 内集卡已满\\r箱区 3H 内集卡已满\\r箱区 26 内集卡已满\\r箱区 37 内集卡已满\\r箱区 4\\nORA-06512: 在 \\\"SHSECT.YPS_INBOUND_PAK\\\", line 884\\nORA-06512: 在 line 1\\n\",\"resultData\":null}\"";

        String outPut = StrUtil.removeAllLineBreaks(json);
        String param = outPut.replaceAll("\"SHSECT.YPS_INBOUND_PAK\"", "SHSECT.YPS_INBOUND_PAK");
        if (param.length() > 2 && (param.startsWith("\"") || param.endsWith("\""))) {
            param = param.substring(1, param.length() - 1);
        }

        System.out.println(param);


        Object s = "{\"resultCode\":\"200\",\"resultTimestamp\":null,\"resultMsg\":\"选位成功\",\"resultData\":\"3K7413\"}";
        String s1 = JSONObject.toJSONString(s);
        System.out.println(s1);
        JSONObject jsonObject = JSONObject.parseObject(s1);
        System.out.println(jsonObject);
    }


    @org.junit.Test
    public void test31() throws Exception {
        LocalTime now = LocalTime.now();
        int i = LocalDateTime.now().toLocalTime().toSecondOfDay();
        System.out.println(i / 3600);
    }


    @org.junit.Test
    public void test32() throws Exception {
//        ArrayList<Long> longs = Lists.newArrayList(2L, 1L);
//        longs.sort((o1, o2) -> o1.compareTo(o2));
//        System.out.println(longs);
//        Integer defaultValue = null;
//        System.out.println((Integer)defaultValue);

        toInt(null, null);
    }


    public Integer toInt(Object value, Integer defaultValue) {
        Integer hello = (value == null ? defaultValue : Integer.parseInt(value.toString()));
        return hello;
    }

    @org.junit.Test
    public void test33() {
        User user = new User();
        user.setName("liqi");
        user.setAddress("shanghai");
        User[] users = new User[2];

    }


    @org.junit.Test
    public void test34() {
        User user = User.builder().id(1001L).name("haha").build();
        System.out.println(user);
    }


    @org.junit.Test
    public void test35() {
        Joiner.on(",").join(new Object[]{null, null});
    }

    @org.junit.Test
    public void test36() {
        User user = new User();
        user.setAddress("haha");

        User user02 = new User();

        BeanUtils.copyProperties(user02, user);

        System.out.println(user02);


        String join = Joiner.on("/").join(new Object[]{"liqi", "haha"});
        System.out.println(join);

    }


    @org.junit.Test
    public void test37() {
        BigDecimal bigDecimal = BigDecimal.valueOf(1000);
        log.info("{}", bigDecimal);
        User user = new User();
        user.setAddress("liqi");


        String s = JSONUtil.toJsonStr(user);
        System.out.println(s);

        List<String> lists = new ArrayList<>();
        lists.add(null);
        System.out.println(lists.size());
        List<String> collect = lists.stream().filter(Objects::nonNull).collect(Collectors.toList());
        System.out.println(JSONUtil.toJsonStr(collect));


        int i = BigDecimal.valueOf(0).compareTo(BigDecimal.valueOf(0));
        System.out.println(i);
    }


    @org.junit.Test
    public void test38() {
        long millis = DateUnit.SECOND.getMillis() * 30;
        System.out.println(millis);

        System.out.println(3 / 2);

        String s1 = "real_data/receipt,task/Issue/aiv,receipt_result/aiv,electronics_enclosure/single_send,truck_interactive/car/request_receipt,truck_interactive/car/info,truck_interactive/car/rerequest,traffic_control,uwb_path,uwb_position,workFlow/car/receipt,workFlow/car/feedback,laneClose/car/feedback,taskLog/receipt,task/iecs_receipt/rtg,navigateQcInf/send,task/aiv_time/receipt,task_select/aiv_receipt,electronics_enclosure/request_receive,uwb_path/send_receive,task/aiv_reset/request,task/aiv_report/inoutBoxArea_report_receipt,task/aiv_obstruction/request,task/aiv_obstruction/receipt";
        int length = s1.split(",").length;
        System.out.println(length);

        String s2 = "0,0,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1";
        int length1 = s2.split(",").length;
        System.out.println(length1);
    }

    @org.junit.Test
    public void test39() {
        ArrayList<String> strings = Lists.newArrayList("1", "2", "3");
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if ("2".equals(next))
                iterator.remove();
        }
        System.out.println(strings);


        Long costTimeLong = BigDecimal.valueOf(157.5649818182d).setScale(0, RoundingMode.HALF_UP).longValue();
        System.out.println(costTimeLong);
        String str = "2022-09-17 10:54:11";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(str, dateTimeFormatter);
        parse = parse.plusSeconds(0L - costTimeLong).plusMinutes(0L - 3L);

        System.out.println(parse);

        Integer i = 0;
        System.out.println(Objects.equals(i, 0));
    }


    @org.junit.Test
    public void test40() {
        test41();
        DateTime dateTime = DateUtil.parseDateTime("2022-09-23 09:30:05:658");
        long time = dateTime.getTime();
        System.out.println(time);
    }

    public void test41() {
        Thread.currentThread().getStackTrace();
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println(methodName);

    }

    @org.junit.Test
    public void test42() {
        String s = JSON.toJSONString(null);
        System.out.println(s);

        Object user = null;
        User user1 = (User) user;


        System.out.println(JSONObject.toJSONString(null));


        String ylocation = "1234";
        String substring = ylocation.substring(0, 4);
    }

    @org.junit.Test
    public void test43() {
        String area = "121.6537861991744,31.33834794308752 121.65096081,31.33971588 121.650795844602,31.3399708420844";
        String[] split = area.split("\\s+");
        System.out.println(split);
    }

    @org.junit.Test
    public void test44() {
        String area = "BZ01,BZ02,H300,H3003,H301,H302,H303,H304,H305,H306,H307,H308,H309,H310,H311,H312,H313,H314,H315,H316,H317,H318,H319,H320,H321,H322,H323,H324,H325,H326,H327,H328,H329,H330,H331,H332,H333,H334,H335,H336,H337,H338,H339,H340,H341,H342,H343,H344,H345,H346,H347,H348,H349,H350,H351,H352,H353,H354,H355,H356,H357,H358,H359,H360,H361,H362,H363,H364,H365,H366,H367,H368,H369,H370,H371,H372,H373,H374,H375,H376,H377,H378,H379,H382,H383,H384,H385,H386,H387,H388,H389,H390,H391,H392,H393,H394,H395,H396,H397,H399,H401,H402,H403,H404,H405,H406,H407,H408,H409,H410,H411,H412,H413,H415,H416,H417,H418,H419,H420,H421,H422,H423,H424,H426,H427,H428,H429,H430,H431,H432,H433,H435,H436,H437,H439,H440,H441,H442,H443,H444,H445,H446,H447,H451,H452,H453,H454,H455,H456,H4566,H4567,H4568,H462,H463,H481,H482,H483,H496,H497,H498,H499,H617,H734,H801,H802,H803,H804,H805,H806,H807,H808,H809,H810,H811,H812,H813,H815,H816,H817,H818,H819,H820,H830,H831,H832,H833,H834,H835,H836,H837,H838,H839,H901,H902,H903,H904,H905,H906,H907,H908,H909,H999,XN01,XN02,XN03,XN04,XN05,测321";
        String[] split = area.split(",");
        System.out.println(split);

        Long l = 1L;
        System.out.println(l == 1);

        String oisOpmode = String.format("主小车当前水平位移,PLC数据道路侧车号校验失败,校验作业模式:%s,containerLocation:%s,AIV装箱状态正常,通过", "oisOpmode", 1L);
        System.out.println(oisOpmode);
    }


    @org.junit.Test
    public void test45() {
        int fxTier = 2;

        for (int i = fxTier - 1; i >= 1; i--) {
            System.out.println("ah");
        }
    }


    @org.junit.Test
    public void test46() {
//        User user = new User();
//        user.setAddress("liqi");
//        user.setPriority(BigDecimal.valueOf(5));
//        User2 use2 = new User2();
//
//        BeanUtil.copyProperties(user, use2);
//        System.out.println(JSONObject.toJSONString(use2));
//        String  s ="{\"Success\":\"FALSE\",\"Msg\":\"处理成功H00000G0000\u000653934514\u000632281266\u0006H255\u0006179051\u0006沪XM760L\u0006F\",\"Data\":\"null\"}\n";
//        System.out.println(s.contains("FALSE"));

        System.out.println("8".compareTo("11"));

        Long l = 0L;
        if (l != 0) {
            System.out.println("aahha");
        }

        User user = new User();
        user.setId(l++);
        System.out.println(user);
    }

    @org.junit.Test
    public void test47() throws Exception {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<?> scheduledFuture01 = scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("haha01");
            }
        }, 0, 1, TimeUnit.SECONDS);

        ScheduledFuture<?> scheduledFuture02 = scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("haha02");
            }
        }, 0, 1, TimeUnit.SECONDS);

        Thread.sleep(3000);
        System.out.println("heheheh");
        scheduledFuture01.cancel(false);


        Thread.sleep(10000);


//        String s3 = "ahah";
//        System.out.println(swap(s3));
//        System.out.println(s3);
//        String oisOpprc = "YY";
//        String oisOpmodeN = "62";
//        String oisTruckNO = "";
//        if ((Lists.newArrayList("YV", "VY", "YT", "TY").contains(oisOpprc) || ("YY".equals(oisOpprc) && Lists.newArrayList("61", "62").contains(oisOpmodeN))) && StrUtil.isBlank(oisTruckNO)) {
//            System.out.println("hhahhehef");
//        }
//
//        String format = String.format("%s,%s", 0L, null);
//        System.out.println(format);

        System.out.println(Objects.equals(StrUtil.sub(null, 0, 2), StrUtil.sub(null, 0, 2)));

    }

    public String swap(String s) {
        s = "hehe";
        return s;
    }


    @org.junit.Test
    public void test48() {
        User2 user2 = new User2();
        User2.User2Inner user2Inner = new User2.User2Inner();
        user2Inner.setName("liqi");
        user2.setUser2Inner(user2Inner);
        User2 user3 = new User2();
        BeanUtil.copyProperties(user2, user3);
        System.out.println(JSONObject.toJSONString(user3));
        User2Extend user2Extend = new User2Extend();
        BeanUtil.copyProperties(user2, user2Extend);
        System.out.println(JSONObject.toJSONString(user2Extend));
    }

    @org.junit.Test
    public void test49() {


        List<String> strings = Lists.newArrayList("121.64271321139682,31.3437529861084", "121.64289212920147,31.344031752181763");
        String collect = strings.stream().map(s -> s.replace(",", " ")).collect(Collectors.joining(","));
        System.out.println(collect);
        System.out.println("9JdL2UT0OJ9VGzWqoWZ4nH9Nj8KKZKUz".length());


        String s = Convert.toStr(1334);
        System.out.println(s);


        long l = LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(8));
        System.out.println(l);


        List<String> strings1 = Lists.newCopyOnWriteArrayList(Lists.newArrayList("haha", "hehe", "heihei", "hehe", "hhaahah"));
        for (int i = strings1.size() - 1; i >= 0; i--) {
            String s1 = strings1.get(i);
            if ("hehe".equals(s1)) {
                strings1.remove(i);
            }
        }
        System.out.println(JSONObject.toJSONString(strings1));


        byte[] bytes = "https://10.140.20.163:30900/vehicle-report/20230323-142337.570-%E6%97%A0%E8%BD%A6%E7%89%8C-26.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=admin%2F20230323%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20230323T062337Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=a4468593cc7be2ececd90defc01895d15ff8c5bb2c7ee645d219ffbedba0bc98".getBytes(StandardCharsets.UTF_8);
        System.out.println(bytes);
    }

    public static User user = new User(1L, "liqi");

    @org.junit.Test
    public void test50() throws Exception {
        ScheduledExecutorService iecsAivTaskReceiptScheduledExecutorService = Executors.newScheduledThreadPool(3);
        ThreadPoolExecutor iecsAivNodeMonitorExecutorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                10L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        for (int i = 0; i < 20; i++) {
            iecsAivTaskReceiptScheduledExecutorService.schedule(() -> {
                iecsAivNodeMonitorExecutorService.execute(() -> {
                    System.out.println("时间:" + System.currentTimeMillis());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }, 5, TimeUnit.SECONDS);
        }
        printThreadPoolStatus(iecsAivNodeMonitorExecutorService);
        Thread.sleep(3000000);
    }

    public static void printThreadPoolStatus(ThreadPoolExecutor threadPool) {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            log.info("=========================");
            log.info("ThreadPool Size: [{}]", threadPool.getPoolSize());
            log.info("Active Threads: {}", threadPool.getActiveCount());
            log.info("Number of Tasks : {}", threadPool.getCompletedTaskCount());
            log.info("Number of Tasks in Queue: {}", threadPool.getQueue().size());
            log.info("=========================");
        }, 0, 1, TimeUnit.SECONDS);
    }

    public void changes() {
        user = new User(2L, "hehe");
    }

    public User gets() {
        return user;
    }

    @org.junit.Test
    public void test51() throws Exception {
        String s = "成功HTTP/1.1 200 OK";
        System.out.println(s.contains("成功HTTP/1.1"));

        System.out.println(Math.abs(1 - (-9999)));

        HashMap<String, Object> stringStringHashMap = Maps.<String, Object>newHashMap();
        User haha = (User) stringStringHashMap.get("haha");
        System.out.println(haha);

        System.out.println(Integer.valueOf("01"));

        Long l = 5L;
        System.out.println(l > 0L);
    }

    @org.junit.Test
    public void test52() throws Exception {
        List<ObjBaypositionVO> objBaypositionVOS = Lists.<ObjBaypositionVO>newArrayList();
        ObjBaypositionVO objBaypositionVO = new ObjBaypositionVO();
        objBaypositionVO.setArea("12");
        objBaypositionVO.setBayno(null);

        objBaypositionVOS.add(objBaypositionVO);

        ObjBaypositionVO objBaypositionVO1 = new ObjBaypositionVO();
        objBaypositionVO1.setArea("12");
        objBaypositionVO1.setBayno("15");
        objBaypositionVOS.add(objBaypositionVO1);

        ObjBaypositionVO objBaypositionVO2 = new ObjBaypositionVO();
        objBaypositionVO2.setArea("12");
        objBaypositionVO2.setBayno("16");
        objBaypositionVOS.add(objBaypositionVO2);

        ObjBaypositionVO objBaypositionVO3 = new ObjBaypositionVO();
        objBaypositionVO3.setArea("12");
        objBaypositionVO3.setBayno("17");
        objBaypositionVOS.add(objBaypositionVO3);

        ObjBaypositionVO objBaypositionVO4 = new ObjBaypositionVO();
        objBaypositionVO4.setArea("13");
        objBaypositionVO4.setBayno("16");
        objBaypositionVOS.add(objBaypositionVO4);
        List<ObjBaypositionVO> objBaypositionVOS1 = objBaypositionVOS.subList(0, 4);
        System.out.println(objBaypositionVOS.subList(0, 4));

        Map<String, ObjBaypositionVO> collect = objBaypositionVOS.stream().filter(i -> i.getArea() != null).sorted(Comparator.comparing(ObjBaypositionVO::getBayno, Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.groupingBy(i -> i.getArea(), Collectors.collectingAndThen(Collectors.toList(), value -> value.get(0))));
        System.out.println(JSONObject.toJSONString(collect));


        Map<String, ObjBaypositionVO> collect1 = objBaypositionVOS.stream().filter(i -> i.getArea() != null).sorted(Comparator.comparing(ObjBaypositionVO::getBayno, Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.groupingBy(i -> i.getArea(), Collectors.collectingAndThen(Collectors.toList(), value -> value.get(0))));
        System.out.println(JSONObject.toJSONString(collect1));

        JSONObject.toJSONString(null);

    }


    @org.junit.Test
    public void test53() throws Exception {
//        String s ="false";
        String s = "{\n" +
                "    \"acceleratedSpeed\":0.14,\n" +
                "    \"angularSpeed\":87.69,\n" +
                "    \"areaType\":\"5,8\"\n" +
                "}";
        System.out.println(JSONUtil.isJson(s));

        System.out.println(DateUtil.offsetHour(new Date(), 1));

        String 好2 = String.format("可驶离车号为空,重新找车号,", "好2");


        List<String> a = new ArrayList<>();
        a.add("H103");
        a.add("H101");
        a.add("H102");
        a.sort((o1, o2) -> o1.compareTo(o2));
        List<String> strings = a.subList(0, a.size() - 1);
        System.out.println("ha");
        System.out.println(JSONObject.toJSONString(a));

    }


    @org.junit.Test
    public void test54() throws Exception {
        IecsDeviceBehaviorLogDTO build = IecsDeviceBehaviorLogDTO.builder().iecsDeviceBehaviorType("ahah").build();
        System.out.println(JSONObject.toJSONString(build));
        System.out.println(StrUtil.sub("123145", 4, 5));

        System.out.println(3 * DateUnit.MINUTE.getMillis());
        //121.64999411776024,31.33189535615008,121.65016812831686,31.331802368654365
        System.out.println((Double.valueOf("121.64999411776024") + Double.valueOf("121.65016812831686")) / 2 + "," + (Double.valueOf("31.33189535615008") + Double.valueOf("31.331802368654365")) / 2);

    }


    @org.junit.Test
    public void test55() throws Exception {
        //format – 日期格式，类似于yyyy-MM-dd HH:mm:ss,SSS
        LocalDateTime parse1 = LocalDateTimeUtil.parse("2023-05-10 14:44:54.119000", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
        LocalDateTime parse2 = LocalDateTimeUtil.parse("2023-05-10 14:45:26.098000", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
        long milliseconds1 = parse1.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        long milliseconds2 = parse2.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        System.out.println(milliseconds2 - milliseconds1);

    }

    @org.junit.Test
    public void test56() throws Exception {
        LinkedHashSet<String> hashSet = new LinkedHashSet();
        hashSet.add("a");
        hashSet.add("a");
        hashSet.add("d");
        hashSet.add("c");
        hashSet.add("c");
        System.out.println(hashSet.toString());

        System.out.println(3 * DateUnit.MINUTE.getMillis());

        System.out.println(System.currentTimeMillis());
        String[] tops = {"1", "2", "3"};
        GroupTopic groupTopic = new GroupTopic();
        groupTopic.setTops(tops);
        String[] tops2 = new String[tops.length];
        for (int i = 0; i < tops.length; i++) {
            tops2[i] = tops[i] + 1;
        }
        System.out.println(tops2.toString());
        groupTopic.setTops(tops2);
        System.out.println(JSONObject.toJSONString(groupTopic));


        String paramJson1 = "{\"pid\":116,\"machNo\":\"H103\",\"machType\":\"QC\",\"resultHost\":\"192.168.1.24\",\"resultPort\":\"8080\",\"shipHoldNo\":null,\"voyVoyage\":null,\"curTime\":null,\"taskId\":\"7521624\",\"taskStatus\":\"2\",\"departureStatus\":1,\"soiId\":\"56816255\",\"soiWorkflow\":1,\"soiWorkType\":\"DSCH\",\"soiCntrno\":\"EMCU1544053\",\"soiIso\":\"45G1\",\"soaTruck\":\"H820\",\"soiTgtbayMap\":\"\",\"soiRecognizeTm\":\"1684466375572\",\"soiCreatedt\":\"1684466375572\",\"soiUpdatedt\":\"1684466375572\",\"soiWorkStat\":\"1\",\"soiArchivefg\":\"1\",\"grabBoxNum\":1,\"taskImages\":null,\"cntrSize\":\"2\",\"workCntrNum\":1,\"haslock\":\"1\",\"dataSource\":\"1\",\"soiCntrno1\":null,\"soiCntrno2\":null,\"tosTaskId\":null,\"oisYlocation\":null,\"lockMark1\":null,\"lockMark2\":null,\"laneNo\":\"2\",\"vehicleTaskId\":\"14144915\",\"soaCntrPosition\":3,\"oisReceiptWorktype\":1,\"rpsVLocation\":null,\"empNo\":\"0779\",\"vlocation\":\"\"}";
        String paramJson2 = "{\"pid\":116,\"machNo\":\"H103\",\"machType\":\"QC\",\"resultHost\":\"192.168.1.24\",\"resultPort\":\"8080\",\"shipHoldNo\":null,\"voyVoyage\":null,\"curTime\":null,\"taskId\":\"7521624\",\"taskStatus\":\"2\",\"departureStatus\":1,\"soiId\":\"56816255\",\"soiWorkflow\":1,\"soiWorkType\":\"DSCH\",\"soiCntrno\":\"EMCU1544053\",\"soiIso\":\"45G1\",\"soaTruck\":\"H820\",\"soiTgtbayMap\":\"\",\"soiRecognizeTm\":\"1684466375572\",\"soiCreatedt\":\"1684466375572\",\"soiUpdatedt\":\"1684466375572\",\"soiWorkStat\":\"1\",\"soiArchivefg\":\"1\",\"grabBoxNum\":1,\"taskImages\":null,\"cntrSize\":\"2\",\"workCntrNum\":1,\"haslock\":\"1\",\"dataSource\":\"1\",\"soiCntrno1\":null,\"soiCntrno2\":null,\"tosTaskId\":null,\"oisYlocation\":null,\"lockMark1\":null,\"lockMark2\":null,\"laneNo\":\"2\",\"vehicleTaskId\":\"14144915\",\"soaCntrPosition\":3,\"oisReceiptWorktype\":1,\"rpsVLocation\":null,\"empNo\":\"0779\",\"vlocation\":\"\"}";
        String s1 = SecureUtil.md5().digestHex(paramJson1);
        String s2 = SecureUtil.md5().digestHex(paramJson2);
        Assert.assertEquals(s1, s2);

        Long aLong = Long.valueOf("3");
        System.out.println(aLong == 3);

        String s = "haha";
        Object s11 ="haha";
        System.out.println(s.equals(s11));
    }


    @org.junit.Test
    public void test57() throws Exception {
        List<ObjBaypositionVO> objBaypositionVOS = Lists.<ObjBaypositionVO>newArrayList();
        ObjBaypositionVO objBaypositionVO = new ObjBaypositionVO();
        objBaypositionVO.setArea("12");
        objBaypositionVO.setBayno(null);

        objBaypositionVOS.add(objBaypositionVO);

        ObjBaypositionVO objBaypositionVO1 = new ObjBaypositionVO();
        objBaypositionVO1.setArea("12");
        objBaypositionVO1.setBayno("15");
        objBaypositionVOS.add(objBaypositionVO1);

        ObjBaypositionVO objBaypositionVO2 = new ObjBaypositionVO();
        objBaypositionVO2.setArea("12");
        objBaypositionVO2.setBayno("16");
        objBaypositionVOS.add(objBaypositionVO2);

        ObjBaypositionVO objBaypositionVO3 = new ObjBaypositionVO();
        objBaypositionVO3.setArea("12");
        objBaypositionVO3.setBayno("17");
        objBaypositionVOS.add(objBaypositionVO3);

        ObjBaypositionVO objBaypositionVO4 = new ObjBaypositionVO();
        objBaypositionVO4.setArea("13");
        objBaypositionVO4.setBayno("16");
        objBaypositionVOS.add(objBaypositionVO4);
        for (ObjBaypositionVO baypositionVO : objBaypositionVOS) {
            objBaypositionVOS.stream().filter(objBaypositionVO5 -> "13".equals(objBaypositionVO5.getArea()))
                    .forEach(objBaypositionVO5 -> objBaypositionVO5.setArea("133"));
            System.out.println(JSONUtil.toJsonStr(objBaypositionVOS));
        }
    }


    @org.junit.Test
    public void test58() throws Exception {
//        Long l = Long.valueOf(1);
//        System.out.println(l==1);
//
//        System.out.println(Objects.equals(1L,l));

//        Long l = null;
//        String s = String.valueOf(null);
////        System.out.println(s);
//
//        Boolean flag =true;
//        boolean equals = Objects.equals(Boolean.TRUE, flag);
//        System.out.println(equals);

        HashMap<String, String> stringStringHashMap = Maps.<String, String>newHashMap();
        stringStringHashMap.put("1","b");
        System.out.println(stringStringHashMap.get(1L));

        Object o = null;
        Map<String, Map<String, String>> o1 = (Map<String, Map<String, String>>) o;
        System.out.println(o1);

    }

    @org.junit.Test
    public void test59(){
        int number = 123456;

        String s = Convert.digitToChinese(123456);
        System.out.println(s);

        User user = new User();
        user.setName("liqi");
        user.setAddress("haha");

        String[] excludeProperties = {"name"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludefilter = filters.addFilter();
        excludefilter.addExcludes(excludeProperties);
        System.out.println(JSONObject.toJSONString(user, excludefilter));
        System.out.println(user);


        String str = "有12只猫和13只狗。";
        Pattern pattern = Pattern.compile("\\d+");
        String group = pattern.matcher(str).group();
        System.out.println(group);


    }

    @org.junit.Test
    public void test60() {
        //d System.out.println("real_data/report/H850".contains("real_data/report/"));
        // Integer i  = new Integer(2);
        // System.out.println(Objects.equals(2, i));

        // for (int i = 1, j = 1; i <= 5; i++, j++) {
        //     System.out.println(i + " " + j);
        //     i += 3;
        // }


        boolean vplu = StringUtils.containsAny("VPLU", "BHCU3131245".substring(0, 4));
        System.out.println(vplu);
    }


}

