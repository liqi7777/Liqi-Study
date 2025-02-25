## 12、UV统计

### 12.1 、UV统计-HyperLogLog

首先我们搞懂两个概念：

* UV：全称Unique Visitor，也叫独立访客量，是指通过互联网访问、浏览这个网页的自然人。1天内同一个用户多次访问该网站，只记录1次。
* PV：全称Page View，也叫页面访问量或点击量，用户每访问网站的一个页面，记录1次PV，用户多次打开页面，则记录多次PV。往往用来衡量网站的流量。

通常来说UV会比PV大很多，所以衡量同一个网站的访问量，我们需要综合考虑很多因素，所以我们只是单纯的把这两个值作为一个参考值

UV统计在服务端做会比较麻烦，因为要判断该用户是否已经统计过了，需要将统计过的用户信息保存。但是如果每个访问的用户都保存到Redis中，数据量会非常恐怖，那怎么处理呢？

Hyperloglog(HLL)是从Loglog算法派生的概率算法，用于确定非常大的集合的基数，而不需要存储其所有值。相关算法原理大家可以参考：https://juejin.cn/post/6844903785744056333#heading-0
Redis中的HLL是基于string结构实现的，单个HLL的内存**永远小于16kb**，**内存占用低**的令人发指！作为代价，其测量结果是概率性的，**有小于0.81％的误差**。不过对于UV统计来说，这完全可以忽略。

UV 统计在服务器端比较麻烦，因为要判断该用户是否已经统计过了，需要将统计过的用户信息保存；但是如果所有访问过该网站的用户都保存到 Redis 中，数据量会十分大。

> HyperLogLog（HLL） 用于确定非常大的集合的基数，而不需要存储其所有值。

> 基数：假设数据集 {1,3,5,7,5,7,8}，那么这个数据集的基数集为 {1,3,5,7,8}，基数（不重复的元素）为 5。
> Redis 中的 HyperLogLog 是基于 String 数据结构实现的，单个 HLL 的内存永远小于 16 KB，内存占用非常非常低。
> 但是它的测量存在小于 0.81% 的误差，不过对于 UV 统计而言，几乎可以忽略。
>
>

```
192.168.8.130:6379> PFADD hl1 e1 e2 e3 e4 e5
(integer) 1
192.168.8.130:6379> PFCOUNT hl1
(integer) 5
192.168.8.130:6379> PFADD hl1 e1 e2 e3 e4 e5
(integer) 0
192.168.8.130:6379> PFCOUNT hl1
(integer) 5
192.168.8.130:6379> 
意思是过滤复制

127.0.0.1:6379> pfadd set1 e1 e2 e3 e4 e5
(integer) 1
127.0.0.1:6379> pfadd set2 e4 e5 e6 e7 e8
(integer) 1
# 合并 set1 set2 得到并集 set3
127.0.0.1:6379> pfmerge set3 set1 set2
OK
127.0.0.1:6379> pfcount set3
(integer) 8

```



### 12.2 UV统计-测试百万数据的统计

测试思路：我们直接利用单元测试，向HyperLogLog中添加100万条数据，看看内存占用和统计效果如何

```
@Test
void millionDataHyperLogLogTest() {
    String[] users = new String[1000];
    int j = 0;
    for (int i = 0; i < 1000000; i++) {
        j = i % 1000;
        users[j] = "user_" + i;
        // 分批导入，每 1000 条数据写入一次
        if (j == 999) {
            stringRedisTemplate.opsForHyperLogLog().add("hll", users);
        }
    }
    Long hllSize = stringRedisTemplate.opsForHyperLogLog().size("hll");
    System.out.println("size = " + hllSize);    // size = 997593
}

```

> - 测试之前 和 测试之后的内存占用：1106056 、1118960；
> - HyperLogLog 占用内存：`(1118960 - 1106056) / 1024 = 12.6KB`

经过测试：我们会发生他的误差是在允许范围内，并且内存占用极小