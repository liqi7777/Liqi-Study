package com.example.web;


import com.example.util.HttpClientUtil;
import com.jz.digital.service.DigitalTwinService;
import com.jz.digital.service.impl.DigitalTwinServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.HashMap;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author liqi
 * @since 2019-05-06
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController {


    @PutMapping("/timeout1")
    public String testTimeout() {
        try {
            Thread.sleep(61000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "请求结束";
    }

    @GetMapping("/timeout2")
    public String timeout2() {
        System.out.println("start:" + System.currentTimeMillis());
        String s = HttpClientUtil.doPutUrlTimeOut("http://127.0.0.1:8081/sysUser/timeout1", new HashMap<>(), new HashMap<>(), 62000);
        System.out.println("end:" + System.currentTimeMillis());
        return s;
    }
}

