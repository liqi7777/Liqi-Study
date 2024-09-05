package com.example.httpRedirectTest;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Sky
 * create  2020-11-27 10:14
 * email sky.li@ixiaoshuidi.com
 */
@Controller
@RequestMapping(path = "redirect")
public class RedirectRest {
    @ResponseBody
    @GetMapping(path = "index")
    public String index(HttpServletRequest request) {
        return "重定向访问! " + JSON.toJSONString(request.getParameterMap());
    }

    @GetMapping(path = "r1")
    public String r1() {
        return    "redirect:/redirect/index?base=r1";
//        return  "redirect:http://www.shuidiguanjia.com";
    }

    @GetMapping(path = "r2")
    public String r2(RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("name","liqi");
        return    "redirect:/redirect/index?base=r1";
//        return  "redirect:http://www.shuidiguanjia.com";
    }


}
