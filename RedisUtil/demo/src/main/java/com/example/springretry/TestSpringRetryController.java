package com.example.springretry;

import com.example.springretry.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestSpringRetryController {

    @Autowired
    private RemoteService remoteService;

    @RequestMapping("/show")
    public String show(){
        try {
            remoteService.call();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }
        return "Hello World";
    }
}