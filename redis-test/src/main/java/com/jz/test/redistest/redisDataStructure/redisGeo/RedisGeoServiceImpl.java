package com.jz.test.redistest.redisDataStructure.redisGeo;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jz.test.redistest.redisDataStructure.redisBitMap.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.domain.geo.GeoReference;
import org.springframework.data.redis.domain.geo.Metrics;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author liqi
 * create  2025/2/25 1:21 下午
 */
@Service
public class RedisGeoServiceImpl {
    @Autowired
    private RedisTemplate redisTemplate;

    private static final String SHOP_GEO_KEY = "shop:geo:";

    public static final int DEFAULT_PAGE_SIZE = 5;

    public static final int MAX_PAGE_SIZE = 10;

    private String shopJson = "[{\"id\":1,\"name\":\"103茶餐厅\",\"typeId\":1,\"images\":\"https://qcloud.dpfile.com/pc/jiclIsCKmOI2arxKN1Uf0Hx3PucIJH8q0QSz-Z8llzcN56-_QiKuOvyio1OOxsRtFoXqu0G3iT2T27qat3WhLVEuLYk00OmSS1IdNpm8K8sG4JN9RIm2mTKcbLtc2o2vfCF2ubeXzk49OsGrXt_KYDCngOyCwZK-s3fqawWswzk.jpg,https://qcloud.dpfile.com/pc/IOf6VX3qaBgFXFVgp75w-KKJmWZjFc8GXDU8g9bQC6YGCpAmG00QbfT4vCCBj7njuzFvxlbkWx5uwqY2qcjixFEuLYk00OmSS1IdNpm8K8sG4JN9RIm2mTKcbLtc2o2vmIU_8ZGOT1OjpJmLxG6urQ.jpg\",\"area\":\"大关\",\"address\":\"金华路锦昌文华苑29号\",\"x\":120.149192,\"y\":30.316078,\"avgPrice\":80,\"sold\":4215,\"comments\":3035,\"score\":37,\"openHours\":\"10:00-22:00\",\"createTime\":\"2021-12-22T18:10:39\",\"updateTime\":\"2022-01-13T17:32:19\",\"distance\":null},{\"id\":2,\"name\":\"蔡馬洪涛烤肉·老北京铜锅涮羊肉\",\"typeId\":1,\"images\":\"https://p0.meituan.net/bbia/c1870d570e73accbc9fee90b48faca41195272.jpg,http://p0.meituan.net/mogu/397e40c28fc87715b3d5435710a9f88d706914.jpg,https://qcloud.dpfile.com/pc/MZTdRDqCZdbPDUO0Hk6lZENRKzpKRF7kavrkEI99OxqBZTzPfIxa5E33gBfGouhFuzFvxlbkWx5uwqY2qcjixFEuLYk00OmSS1IdNpm8K8sG4JN9RIm2mTKcbLtc2o2vmIU_8ZGOT1OjpJmLxG6urQ.jpg\",\"area\":\"拱宸桥/上塘\",\"address\":\"上塘路1035号（中国工商银行旁）\",\"x\":120.151505,\"y\":30.333422,\"avgPrice\":85,\"sold\":2160,\"comments\":1460,\"score\":46,\"openHours\":\"11:30-03:00\",\"createTime\":\"2021-12-22T19:00:13\",\"updateTime\":\"2022-01-11T16:12:26\",\"distance\":null},{\"id\":3,\"name\":\"新白鹿餐厅(运河上街店)\",\"typeId\":1,\"images\":\"https://p0.meituan.net/biztone/694233_1619500156517.jpeg,https://img.meituan.net/msmerchant/876ca8983f7395556eda9ceb064e6bc51840883.png,https://img.meituan.net/msmerchant/86a76ed53c28eff709a36099aefe28b51554088.png\",\"area\":\"运河上街\",\"address\":\"台州路2号运河上街购物中心F5\",\"x\":120.151954,\"y\":30.32497,\"avgPrice\":61,\"sold\":12035,\"comments\":8045,\"score\":47,\"openHours\":\"10:30-21:00\",\"createTime\":\"2021-12-22T19:10:05\",\"updateTime\":\"2022-01-11T16:12:42\",\"distance\":null},{\"id\":4,\"name\":\"Mamala(杭州远洋乐堤港店)\",\"typeId\":1,\"images\":\"https://img.meituan.net/msmerchant/232f8fdf09050838bd33fb24e79f30f9606056.jpg,https://qcloud.dpfile.com/pc/rDe48Xe15nQOHCcEEkmKUp5wEKWbimt-HDeqYRWsYJseXNncvMiXbuED7x1tXqN4uzFvxlbkWx5uwqY2qcjixFEuLYk00OmSS1IdNpm8K8sG4JN9RIm2mTKcbLtc2o2vmIU_8ZGOT1OjpJmLxG6urQ.jpg\",\"area\":\"拱宸桥/上塘\",\"address\":\"丽水路66号远洋乐堤港商城2期1层B115号\",\"x\":120.146659,\"y\":30.312742,\"avgPrice\":290,\"sold\":13519,\"comments\":9529,\"score\":49,\"openHours\":\"11:00-22:00\",\"createTime\":\"2021-12-22T19:17:15\",\"updateTime\":\"2022-01-11T16:12:51\",\"distance\":null},{\"id\":5,\"name\":\"海底捞火锅(水晶城购物中心店）\",\"typeId\":1,\"images\":\"https://img.meituan.net/msmerchant/054b5de0ba0b50c18a620cc37482129a45739.jpg,https://img.meituan.net/msmerchant/59b7eff9b60908d52bd4aea9ff356e6d145920.jpg,https://qcloud.dpfile.com/pc/Qe2PTEuvtJ5skpUXKKoW9OQ20qc7nIpHYEqJGBStJx0mpoyeBPQOJE4vOdYZwm9AuzFvxlbkWx5uwqY2qcjixFEuLYk00OmSS1IdNpm8K8sG4JN9RIm2mTKcbLtc2o2vmIU_8ZGOT1OjpJmLxG6urQ.jpg\",\"area\":\"大关\",\"address\":\"上塘路458号水晶城购物中心F6\",\"x\":120.15778,\"y\":30.310633,\"avgPrice\":104,\"sold\":4125,\"comments\":2764,\"score\":49,\"openHours\":\"10:00-07:00\",\"createTime\":\"2021-12-22T19:20:58\",\"updateTime\":\"2022-01-11T16:13:01\",\"distance\":null},{\"id\":6,\"name\":\"幸福里老北京涮锅（丝联店）\",\"typeId\":1,\"images\":\"https://img.meituan.net/msmerchant/e71a2d0d693b3033c15522c43e03f09198239.jpg,https://img.meituan.net/msmerchant/9f8a966d60ffba00daf35458522273ca658239.jpg,https://img.meituan.net/msmerchant/ef9ca5ef6c05d381946fe4a9aa7d9808554502.jpg\",\"area\":\"拱宸桥/上塘\",\"address\":\"金华南路189号丝联166号\",\"x\":120.148603,\"y\":30.318618,\"avgPrice\":130,\"sold\":9531,\"comments\":7324,\"score\":46,\"openHours\":\"11:00-13:50,17:00-20:50\",\"createTime\":\"2021-12-22T19:24:53\",\"updateTime\":\"2022-01-11T16:13:09\",\"distance\":null},{\"id\":7,\"name\":\"炉鱼(拱墅万达广场店)\",\"typeId\":1,\"images\":\"https://img.meituan.net/msmerchant/909434939a49b36f340523232924402166854.jpg,https://img.meituan.net/msmerchant/32fd2425f12e27db0160e837461c10303700032.jpg,https://img.meituan.net/msmerchant/f7022258ccb8dabef62a0514d3129562871160.jpg\",\"area\":\"北部新城\",\"address\":\"杭行路666号万达商业中心4幢2单元409室(铺位号4005)\",\"x\":120.124691,\"y\":30.336819,\"avgPrice\":85,\"sold\":2631,\"comments\":1320,\"score\":47,\"openHours\":\"00:00-24:00\",\"createTime\":\"2021-12-22T19:40:52\",\"updateTime\":\"2022-01-11T16:13:19\",\"distance\":null},{\"id\":8,\"name\":\"浅草屋寿司（运河上街店）\",\"typeId\":1,\"images\":\"https://img.meituan.net/msmerchant/cf3dff697bf7f6e11f4b79c4e7d989e4591290.jpg,https://img.meituan.net/msmerchant/0b463f545355c8d8f021eb2987dcd0c8567811.jpg,https://img.meituan.net/msmerchant/c3c2516939efaf36c4ccc64b0e629fad587907.jpg\",\"area\":\"运河上街\",\"address\":\"拱墅区金华路80号运河上街B1\",\"x\":120.150526,\"y\":30.325231,\"avgPrice\":88,\"sold\":2406,\"comments\":1206,\"score\":46,\"openHours\":\" 11:00-21:30\",\"createTime\":\"2021-12-22T19:51:06\",\"updateTime\":\"2022-01-11T16:13:25\",\"distance\":null},{\"id\":9,\"name\":\"羊老三羊蝎子牛仔排北派炭火锅(运河上街店)\",\"typeId\":1,\"images\":\"https://p0.meituan.net/biztone/163160492_1624251899456.jpeg,https://img.meituan.net/msmerchant/e478eb16f7e31a7f8b29b5e3bab6de205500837.jpg,https://img.meituan.net/msmerchant/6173eb1d18b9d70ace7fdb3f2dd939662884857.jpg\",\"area\":\"运河上街\",\"address\":\"台州路2号运河上街购物中心F5\",\"x\":120.150598,\"y\":30.325251,\"avgPrice\":101,\"sold\":2763,\"comments\":1363,\"score\":44,\"openHours\":\"11:00-21:30\",\"createTime\":\"2021-12-22T19:53:59\",\"updateTime\":\"2022-01-11T16:13:34\",\"distance\":null},{\"id\":10,\"name\":\"开乐迪KTV（运河上街店）\",\"typeId\":2,\"images\":\"https://p0.meituan.net/joymerchant/a575fd4adb0b9099c5c410058148b307-674435191.jpg,https://p0.meituan.net/merchantpic/68f11bf850e25e437c5f67decfd694ab2541634.jpg,https://p0.meituan.net/dpdeal/cb3a12225860ba2875e4ea26c6d14fcc197016.jpg\",\"area\":\"运河上街\",\"address\":\"台州路2号运河上街购物中心F4\",\"x\":120.149093,\"y\":30.324666,\"avgPrice\":67,\"sold\":26891,\"comments\":902,\"score\":37,\"openHours\":\"00:00-24:00\",\"createTime\":\"2021-12-22T20:25:16\",\"updateTime\":\"2021-12-22T20:25:16\",\"distance\":null}]";

    public void loadShopData() {
        // 1.查询店铺信息
        List<Shop> list = JSONObject.parseArray(shopJson, Shop.class);
        System.out.println(JSONObject.toJSONString(list));
        // 2.把店铺分组，按照typeId分组，typeId一致的放到一个集合
        Map<Long, List<Shop>> map = list.stream().collect(Collectors.groupingBy(Shop::getTypeId));
        // 3.分批完成写入Redis
        for (Map.Entry<Long, List<Shop>> entry : map.entrySet()) {
            // 3.1.获取类型id
            Long typeId = entry.getKey();
            String key = SHOP_GEO_KEY + typeId;
            // 3.2.获取同类型的店铺的集合
            List<Shop> value = entry.getValue();
            List<RedisGeoCommands.GeoLocation<String>> locations = new ArrayList<>(value.size());
            // 3.3.写入redis GEOADD key 经度 纬度 member
            for (Shop shop : value) {
                // redisTemplate.opsForGeo().add(key, new Point(shop.getX(), shop.getY()), shop.getId().toString());
                locations.add(new RedisGeoCommands.GeoLocation<>(
                        shop.getId().toString(),
                        new Point(shop.getX(), shop.getY())
                ));
            }
            redisTemplate.opsForGeo().add(key, locations);
        }
    }

    public Result queryShopByType(Integer typeId, Integer current, Double x, Double y) {

        // 3.查询redis、按照距离排序、分页。结果：shopId、distance
        String key = SHOP_GEO_KEY + typeId;
        //redis 版本 6.2以上可用！
        // GeoResults<RedisGeoCommands.GeoLocation<String>> results = redisTemplate.opsForGeo() // GEOSEARCH key BYLONLAT x y BYRADIUS 10 WITHDISTANCE
        //         .search(
        //                 key,
        //                 GeoReference.fromCoordinate(x, y),
        //                 new Distance(5000),
        //                 RedisGeoCommands.GeoSearchCommandArgs.newGeoSearchArgs().includeDistance().limit(MAX_PAGE_SIZE)
        //         );

        GeoResults<RedisGeoCommands.GeoLocation<String>> results = redisTemplate.opsForGeo()
                .radius(
                        key,
                        new Circle(new Point(x, y), new Distance(1000, Metrics.METERS)),
                        RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs()
                                .includeDistance()
                                .limit(MAX_PAGE_SIZE)
                );

        // 4.解析出id
        if (results == null) {
            return Result.ok(Collections.emptyList());
        }
        List<GeoResult<RedisGeoCommands.GeoLocation<String>>> list = results.getContent();
        List<Long> ids = new ArrayList<>(list.size());
        Map<String, Distance> distanceMap = new HashMap<>(list.size());
        list.stream().forEach(result -> {
            // 4.2.获取店铺id
            String shopIdStr = result.getContent().getName();
            ids.add(Long.valueOf(shopIdStr));
            // 4.3.获取距离
            Distance distance = result.getDistance();
            distanceMap.put(shopIdStr, distance);
        });
        // 5.根据id查询Shop
        String idStr = StrUtil.join(",", ids);

        // 6.返回
        return Result.ok(distanceMap);
    }
}
