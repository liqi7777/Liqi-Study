package com.example.preventDuplicateRepeat.localLockMethod;

import com.example.test.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sky
 * create 2020/03/18
 * email sky.li@ixiaoshuidi.com
 **/
@RestController
public class LocalLockTestController {

    @GetMapping("/locallock/test01")
    public String test01(@RequestParam("name") String name) {
        return name;
    }

    @PostMapping("/locallock/test02")
    @Resubmit(delaySeconds = 5)
    public String test02(@RequestBody User user) {
        return user.toString();
    }
}
