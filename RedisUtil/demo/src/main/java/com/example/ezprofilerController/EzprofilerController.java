package com.example.ezprofilerController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liqi
 * create  2021-07-19 10:35
 */
@RestController
@RequestMapping("/ezprofiler")
public class EzprofilerController {

    @GetMapping("/testEprofiler")
    public String testEprofiler(){

        return "haha";
    }
}
