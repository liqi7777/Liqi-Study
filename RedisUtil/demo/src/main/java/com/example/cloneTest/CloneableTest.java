package com.example.cloneTest;

import cn.hutool.core.date.DateUtil;
import com.example.test.Person;

import java.util.Date;

/**
 * @author Sky
 * create 2019/09/12
 * email sky.li@ixiaoshuidi.com
 **/
public class CloneableTest {
    public static void main(String[] args) {
        Long time = 1568217600000L;
        long ti = time - 1000;
        System.out.println(ti);
        System.out.println(DateUtil.formatDateTime(new Date(ti)));
    }
}
