## 10、附近商户

### 10.1、附近商户-GEO数据结构的基本用法

GEO就是Geolocation的简写形式，代表地理坐标。Redis在3.2版本中加入了对GEO的支持，允许存储地理坐标信息，帮助我们根据经纬度来检索数据。常见的命令有：

> **GEO Geolocation**，代表地理位置，允许存储地理坐标。GEO 底层的实现原理是 ZSET，可以使用 ZSET 的命令操作 GEO。

* GEOADD：添加一个地理空间信息，包含：经度（longitude）、纬度（latitude）、值（member）
* GEODIST：计算指定的两个点之间的距离并返回
* GEOHASH：将指定member的坐标转为hash字符串形式并返回
* GEOPOS：返回指定member的坐标
* GEORADIUS：指定圆心、半径，找到该圆内包含的所有member，并按照与圆心之间的距离排序后返回。6.以后已废弃
* GEOSEARCH：在指定范围内搜索member，并按照与指定点之间的距离排序后返回。范围可以是圆形或矩形。6.2.新功能
* GEOSEARCHSTORE：与GEOSEARCH功能一致，不过可以把结果存储到一个指定的key。 6.2.新功能

### 10.2、 附近商户-导入店铺数据到GEO

```java
#自写在redis做一个测试
  将经纬度变成sorece存入zset
   

```

- `GEOADD key longitude latitude member [longitude latitude member ...]`：添加一个地理空间信息，包含：经度（longitude）、纬度（latitude）、值（member）；

```java
GEOADD China:City 116.40 39.90 Beijing
(integer) 1
GEOADD China:City 121.47 31.23 Shanghai 106.50 29.53 Chongqing 114.08 22.547 Shenzhen 120.15 30.28 Hangzhou 125.15 42.93 Xian 102.71 25.04 Kunming
```

- `GEODIST key member1 member2 [unit]`：计算指定的两个点之间的距离并返回；

```java
 GEODIST China:City Beijing Shanghai km
"1067.3788"
 GEODIST China:City Shanghai Kunming km
"1961.3500"

```

- `GEOHASH key member [member ...]`：将指定 member 的坐标转为 hash 字符串形式并返回；

  ```java
  # 降低内存存储压力，会损失一些精度，但是仍然指向同一个地区。
  127.0.0.1:6379> GEOHASH China:City Beijing Shanghai Kunming
  1) "wx4fbxxfke0"
  2) "wtw3sj5zbj0"
  3) "wk3n3nrhs60"
  
  ```

- `GEOPOS key member [member ...]`：返回指定 member 的坐标；

```java
127.0.0.1:6379> GEOPOS China:City Beijing
1) 1) "116.39999896287918091"
   2) "39.90000009167092543"
127.0.0.1:6379> GEOPOS China:City Shanghai Kunming Hangzhou
1) 1) "121.47000163793563843"
   2) "31.22999903975783553"
2) 1) "102.70999878644943237"
   2) "25.03999958679589355"
3) 1) "120.15000075101852417"
   2) "30.2800007575645509"

```

- GEORADIUS key longitude latitude radius [unit] [WITHCOORD] [WITHDIST] [WITHHASH] [COUNT count] [ASC|DESC]：指定圆心、半径，找到该圆范围内包含的所有 member，并按照与圆心的距离排序后返回（6.2 后弃用）；
- GEOSEARCH 是 Redis 6.2 版本引入的一个地理空间查询命令，用于在地理空间索引中根据指定的条件搜索成员。它旨在替代旧的 GEORADIUS 和 GEORADIUSBYMEMBER 命令，提供了更丰富和灵活的查询方式。以下是对 GEOSEARCH 命令的详细解释：
```text
GEOSEARCH key 
    [FROMLONLAT longitude latitude | FROMMEMBER member] 
    [BYRADIUS radius unit | BYBOX width height unit] 
    [ASC | DESC] 
    [COUNT count [ANY]] 
    [WITHCOORD] 
    [WITHDIST] 
    [WITHHASH]
参数说明
必选参数
1.key：存储地理空间数据的键名，该键应该是通过 GEOADD 命令添加了地理空间信息的有序集合。
可选参数组
起始点指定
2.FROMLONLAT longitude latitude：指定一个经纬度作为查询的中心点。longitude 是经度，latitude 是纬度。例如：FROMLONLAT 116.4074 39.9042。
FROMMEMBER member：指定一个已存在于地理空间索引中的成员作为查询的中心点。例如：FROMMEMBER beijing。
查询范围指定
3.BYRADIUS radius unit：指定一个圆形查询范围，radius 是半径值，unit 是距离单位。支持的单位有：
m：米
km：千米
ft：英尺
mi：英里
例如：BYRADIUS 10 km 表示以指定点为圆心，10 千米为半径的圆形范围。
4.BYBOX width height unit：指定一个矩形查询范围，width 是矩形的宽度，height 是矩形的高度，unit 是距离单位。例如：BYBOX 20 10 km 表示一个宽度为 20 千米，高度为 10 千米的矩形范围。
5.排序方式
ASC：按照与中心点的距离从小到大排序。
DESC：按照与中心点的距离从大到小排序。
6.结果数量限制
COUNT count：限制返回结果的数量，count 是一个正整数。例如：COUNT 5 表示最多返回 5 个结果。
ANY：与 COUNT 一起使用，当找到 count 个匹配结果后立即停止搜索，而不是继续搜索整个范围。这可以提高查询性能，特别是在数据量较大时。
7.附加信息返回
WITHCOORD：返回匹配成员的经纬度。
WITHDIST：返回匹配成员与中心点的距离，距离单位与查询范围指定的单位一致。
WITHHASH：返回匹配成员的 Geohash 值

```

```java
127.0.0.1:6379> GEOSEARCH China:City FROMLONLAT 116.397904 39.909005 BYRADIUS 1000 km WITHDIST
1) 1) "Beijing"
   2) "1.0174"
2) 1) "Xian"
   2) "803.0689"

127.0.0.1:6379> GEOSEARCH China:City FROMLONLAT 116.397904 39.909005 BYBOX 2000 2000 km WITHDIST
1) 1) "Shanghai"
   2) "1068.3526"
2) 1) "Beijing"
   2) "1.0174"
3) 1) "Xian"
   2) "803.0689

127.0.0.1:6379> GEOSEARCH China:City FROMMEMBER Beijing BYBOX 2000 2000 km WITHDIST
1) 1) "Shanghai"
   2) "1067.3788"
2) 1) "Beijing"
   2) "0.0000"
3) 1) "Xian"
   2) "803.3746"

```

- `GEOSEARCHSTORE `：与 `GEOSEARCH` 功能一致，不过可以把结果存储到一个指定的 Key（6.2 新功能）。



HmDianPingApplicationTests

```java
@Test
void loadShopData() {
    // 1.查询店铺信息
    List<Shop> list = shopService.list();
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
            // stringRedisTemplate.opsForGeo().add(key, new Point(shop.getX(), shop.getY()), shop.getId().toString());
            locations.add(new RedisGeoCommands.GeoLocation<>(
                    shop.getId().toString(),
                    new Point(shop.getX(), shop.getY())
            ));
        }
        stringRedisTemplate.opsForGeo().add(key, locations);
    }
}
```

### 10.3 附近商户-实现附近商户功能

SpringDataRedis的2.3.9版本并不支持Redis 6.2提供的GEOSEARCH命令，因此我们需要提示其版本，修改自己的POM

**将数据库中的数据导入到 Redis 中**：按照商家类型分组，类型相同的商家作为一组，以 `typeId` 为 Key，商家地址为 Value。

### 可以安装一个dependency analyzer

第一步：导入pom

```java
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
    <exclusions>
        <exclusion>
            <artifactId>spring-data-redis</artifactId>
            <groupId>org.springframework.data</groupId>
        </exclusion>
        <exclusion>
            <artifactId>lettuce-core</artifactId>
            <groupId>io.lettuce</groupId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-redis</artifactId>
    <version>2.6.2</version>
</dependency>
<dependency>
    <groupId>io.lettuce</groupId>
    <artifactId>lettuce-core</artifactId>
    <version>6.1.6.RELEASE</version>
</dependency>
```

第二步：

ShopController

```java
@GetMapping("/of/type")
public Result queryShopByType(
        @RequestParam("typeId") Integer typeId,
        @RequestParam(value = "current", defaultValue = "1") Integer current,
        @RequestParam(value = "x", required = false) Double x,
        @RequestParam(value = "y", required = false) Double y
) {
   return shopService.queryShopByType(typeId, current, x, y);
}
```

ShopServiceImpl

```java
@Override
    public Result queryShopByType(Integer typeId, Integer current, Double x, Double y) {
        // 1.判断是否需要根据坐标查询
        if (x == null || y == null) {
            // 不需要坐标查询，按数据库查询
            Page<Shop> page = query()
                    .eq("type_id", typeId)
                    .page(new Page<>(current, SystemConstants.DEFAULT_PAGE_SIZE));
            // 返回数据
            return Result.ok(page.getRecords());
        }

        // 2.计算分页参数
        int from = (current - 1) * SystemConstants.DEFAULT_PAGE_SIZE;
        int end = current * SystemConstants.DEFAULT_PAGE_SIZE;

        // 3.查询redis、按照距离排序、分页。结果：shopId、distance
        String key = SHOP_GEO_KEY + typeId;
        GeoResults<RedisGeoCommands.GeoLocation<String>> results = stringRedisTemplate.opsForGeo() // GEOSEARCH key BYLONLAT x y BYRADIUS 10 WITHDISTANCE
                .search(
                        key,
                        GeoReference.fromCoordinate(x, y),
                        new Distance(5000),
                        RedisGeoCommands.GeoSearchCommandArgs.newGeoSearchArgs().includeDistance().limit(end)
                );
        // 4.解析出id
        if (results == null) {
            return Result.ok(Collections.emptyList());
        }
        List<GeoResult<RedisGeoCommands.GeoLocation<String>>> list = results.getContent();
        if (list.size() <= from) {
            // 没有下一页了，结束
            return Result.ok(Collections.emptyList());
        }
        // 4.1.截取 from ~ end的部分
        List<Long> ids = new ArrayList<>(list.size());
        Map<String, Distance> distanceMap = new HashMap<>(list.size());
        list.stream().skip(from).forEach(result -> {
            // 4.2.获取店铺id
            String shopIdStr = result.getContent().getName();
            ids.add(Long.valueOf(shopIdStr));
            // 4.3.获取距离
            Distance distance = result.getDistance();
            distanceMap.put(shopIdStr, distance);
        });
        // 5.根据id查询Shop
        String idStr = StrUtil.join(",", ids);
        List<Shop> shops = query().in("id", ids).last("ORDER BY FIELD(id," + idStr + ")").list();
        for (Shop shop : shops) {
            shop.setDistance(distanceMap.get(shop.getId().toString()).getValue());
        }
        // 6.返回
        return Result.ok(shops);
    }
```