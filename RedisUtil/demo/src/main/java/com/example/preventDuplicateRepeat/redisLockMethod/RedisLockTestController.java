package com.example.preventDuplicateRepeat.redisLockMethod;

import com.example.test.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sky
 * create 2020/03/18
 * email sky.li@ixiaoshuidi.com
 **/
@RestController
public class RedisLockTestController {

    @CacheLock(prefix = "redislock-test01")
    @GetMapping("redislock/test01")
    public String test01(@CacheParam(name = "token") @RequestParam String token) {
        return "success - " + token;
    }

    @CacheLock(prefix = "redislock-test02")
    @PostMapping("redislock/test02")
    public String test02(@RequestBody RedisLockModel redisLockModel) {
        return "success - " + redisLockModel.toString();
    }

}
