package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Sky
 * create 2019/03/26
 * email sky.li@ixiaoshuidi.com
 **/
@Controller
public class ViewController {

    @RequestMapping(value = "test01",method = RequestMethod.GET)
    public String test01(ModelAndView modelAndView){
        return  "test01";
    }
}
