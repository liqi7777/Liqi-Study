package com.example.preventDuplicateRepeat.lock4JStarterMethod;

import com.example.preventDuplicateRepeat.lock4JStarterMethod.annotation.Lock4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sky
 * create 2020/04/13
 * email sky.li@ixiaoshuidi.com
 **/
@RestController
@RequestMapping("lock4j")
public class Lock4JTestController {

    @GetMapping("test01")
    @Lock4j(expire = 60000)
    public String test01(@RequestParam("name") String name) throws Exception {
        Thread.sleep(10000L);
        return "test01";
    }

}
