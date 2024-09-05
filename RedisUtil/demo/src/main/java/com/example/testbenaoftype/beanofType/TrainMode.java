package com.example.testbenaoftype.beanofType;

/**
 * @author Sky
 * create 2019/06/24
 * email sky.li@ixiaoshuidi.com
 **/

import org.springframework.stereotype.Component;

/**
 * 火车方式
 */
@Component
public class TrainMode implements TrafficMode {

    @Override
    public TrafficCode getCode() {
        return TrafficCode.TRAIN;
    }

    @Override
    public Integer getFee() {
        return 9000;
    }
}
