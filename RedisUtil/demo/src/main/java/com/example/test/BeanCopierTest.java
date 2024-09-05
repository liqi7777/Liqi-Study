package com.example.test;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import net.sf.cglib.beans.BeanCopier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author Sky
 * create 2019/06/19
 * email sky.li@ixiaoshuidi.com
 **/
public class BeanCopierTest {

    public static void main(String[] args) {
        User target = new User();
        target.setUsername("liqi");
        target.setBigDecimal(new BigDecimal("20"));
        User source = new User();
        source.setUsername("zhangsan");
//        BeanCopier.create(User.class, User.class, false).copy(source, target, null);
        BeanUtil.copyProperties(source,target,new CopyOptions().setIgnoreNullValue(true));
        System.out.println(target);
//        System.out.println(LocalDateTime.now());
//        System.out.println(LocalDate.now());
//        System.out.println(LocalTime.now());
//        System.out.println(LocalDateTime.of(LocalDate.now(),LocalTime.now()));
    }
}
