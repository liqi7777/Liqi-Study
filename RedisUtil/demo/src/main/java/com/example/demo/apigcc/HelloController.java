package com.example.demo.apigcc;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 欢迎使用Apigcc
 *
 * @index 1
 */
@RestController
public class HelloController {

    /**
     * 示例接口
     *
     * @param name 名称
     * @return
     */
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(defaultValue = "apigcc") String name) {
        return "hello";
    }

}