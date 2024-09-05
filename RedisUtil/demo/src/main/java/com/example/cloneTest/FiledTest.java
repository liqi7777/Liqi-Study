package com.example.cloneTest;

import com.alibaba.fastjson.JSONObject;
import com.example.test.User;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

/**
 * @author Sky
 * create 2020/05/09
 * email sky.li@ixiaoshuidi.com
 **/
public class FiledTest {

    public static void main(String[] args) throws IllegalAccessException {
        User user = new User();
//        user.setUsername("liqi");
        Field[] declaredFields = user.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Object value = field.get(user);
            Class<?> type = field.getType();
            System.out.println(type.getName());
            System.out.println(type.getClass());
            System.out.println(String.class);
            if (type == String.class && value == null) {
                field.set(user, "liqi");
            }
        }
        System.out.println(JSONObject.toJSONString(user));
    }
}
