package com.example.jwtDemo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sky
 * create 2019/06/17
 * email sky.li@ixiaoshuidi.com
 * @index 2
 **/
@RestController
@RequestMapping("jwt")
public class JwtDemoController {

    @PostMapping("getToken")
    public String getToken(@RequestBody User user) {
        String token = "";
        token = JWT.create().withAudience(user.getId())
                .sign(Algorithm.HMAC256("hello"));
        return token;
    }

    @GetMapping("verifyToken")
    @UserLoginToken
    public String verifyToken(@RequestHeader("token") String token) {
        System.out.println("haha");
        return  "success";
    }
}
