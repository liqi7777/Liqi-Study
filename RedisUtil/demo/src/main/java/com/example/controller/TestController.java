package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.test.HttpClientUtil;
import com.example.test.RandomValidateCodeUtil;
import com.example.test.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Sky
 * create 2018/10/31
 * email sky.li@ixiaoshuidi.com
 **/
@RestController
public class TestController {

    @GetMapping(value = "test")
    public String test(HttpServletRequest request, HttpServletResponse response) {
//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie : cookies) {
//            System.out.println(cookie.getName() + "..." + cookie.getValue());
//        }
        //HttpServletResponse添加cooike
        Cookie cookie1 = new Cookie("cookie1", "测试cookie1");
        Cookie cookie2 = new Cookie("cookie2", "测试cookie2");
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        System.out.println("hahah");
        new Cookie("cookie3", "测试cookie3");
        return "hahaliqi2010000";
    }

    /**
     * 生成验证码
     */
    @RequestMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
        }
    }


    @RequestMapping(value = "/wrapper/request", method = RequestMethod.POST)
    @ResponseBody
    public String reqeustWrapper(@RequestBody User user) throws Exception {
        System.out.println(JSONObject.toJSONString(user));
        return "hello";
    }

    @RequestMapping(value = "/wrapper/response", method = RequestMethod.POST)
    public String responseWrapper(@RequestBody User user) throws Exception {
        return "hehehhe";
    }


    @PostMapping(value = "/test/httpclient/timeout")
    public String testHttpclientTimeout() {
        System.out.println("第" + (i++) + "次请求");
//        int j=1/0;
//        try {
//            Thread.sleep(1000000000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "error";
    }

    @GetMapping(value = "/test/timeout")
    public String testtimeout() {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        //最坏情况下4次
        int[] count = {4};
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (count[0] != 0) {
                    String result = HttpClientUtil.doPost("http://127.0.0.1:8080/test/httpclient/timeout");
                    System.out.println(result);
                    if (StringUtils.isNotBlank(result) && result.equals("success")) {
                        scheduledExecutorService.shutdown();
                    } else {
                        --count[0];
                        System.out.println(count[0]);
                    }
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
        return "hello";
    }

    @GetMapping("http/test")
    public String httpTest() {
        return "success";
    }

    private int i = 0;

    /**
     * jmete，test测试
     *
     * @param user
     * @return
     */
    @PostMapping("/jmeter/test")
    public String jmeterTest(@RequestBody User user) {
        System.out.println("nihao13" + (++i));
        return user.getUsername();
    }


    @PostMapping("/test/requestbodymap")
    public String requestbodymap(@RequestBody Map<String, Object> map) {
        System.out.println(map.get("name"));
        return "hello";
    }


    @GetMapping("/test/listtest")
    public String testlist(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "ids", required = false) List<String> ids) {
        System.out.println(id);
        Optional.ofNullable(ids).ifPresent(strings -> System.out.println(strings.toString()));
        return "listtest";
    }
}
