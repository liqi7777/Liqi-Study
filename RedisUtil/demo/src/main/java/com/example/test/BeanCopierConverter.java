package com.example.test;

import net.sf.cglib.core.Converter;

import java.lang.reflect.Method;

/**
 * @author Sky
 * create 2019/06/19
 * email sky.li@ixiaoshuidi.com
 **/
public class BeanCopierConverter implements Converter {

    @Override
    public Object convert(Object o, Class aClass, Object o1) {
        return o;
    }
}
