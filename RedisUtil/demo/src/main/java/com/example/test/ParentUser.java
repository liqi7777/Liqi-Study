package com.example.test;

import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * @author Sky
 * create 2019/04/11
 * email sky.li@ixiaoshuidi.com
 **/
public class ParentUser extends User {
    private String hello;

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public static void main(String[] args) {
        User u=new User();
        u.setBigDecimal(BigDecimal.valueOf(0.0));
        u.setUsername("liqi");
        ParentUser parentUser= new ParentUser();
        BeanUtils.copyProperties(u,parentUser);
        System.out.println(parentUser.toString());

        long l=1000000000L;
        System.out.println((int)l);
    }
}
