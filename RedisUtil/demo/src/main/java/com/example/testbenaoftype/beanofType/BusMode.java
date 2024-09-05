package com.example.testbenaoftype.beanofType;

import org.springframework.stereotype.Component;

/**
 * @author Sky
 * create 2019/06/24
 * email sky.li@ixiaoshuidi.com
 **/
@Component
public class BusMode implements TrafficMode{
    @Override
    public TrafficCode getCode() {
        return TrafficCode.BUS;
    }

    @Override
    public Integer getFee() {
        return 10000;
    }
}
