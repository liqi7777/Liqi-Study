package com.example.preventDuplicateRepeat.redisLockMethod;

import lombok.Data;

/**
 * @author Sky
 * create 2020/03/18
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class RedisLockModel {
    @CacheParam(name = "redisName")
    private String name;
    @CacheParam(name = "redisCode")
    private String code;
}
