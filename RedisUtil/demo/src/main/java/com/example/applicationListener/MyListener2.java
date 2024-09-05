package com.example.applicationListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author Sky
 * create 2019/06/24
 * email sky.li@ixiaoshuidi.com
 **/
@Component
public class MyListener2 implements ApplicationListener<MyEvent> {
    Logger logger = LoggerFactory.getLogger(MyListener4.class);

    @Override
    public void onApplicationEvent(MyEvent event) {
        logger.info(String.format("%s监听到事件源：%s.", MyListener2.class.getName(), event.getSource()));
    }
}

