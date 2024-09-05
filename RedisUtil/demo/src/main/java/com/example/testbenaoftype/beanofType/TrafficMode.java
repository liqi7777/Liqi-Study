package com.example.testbenaoftype.beanofType;

/**
 * @author Sky
 * create 2019/06/24
 * email sky.li@ixiaoshuidi.com
 **/
public interface TrafficMode {
    /**
     * 查询交通方式编码
     * @return 编码
     */
    TrafficCode getCode();

    /**
     * 查询交通方式的费用，单位：分
     * @return 费用
     */
    Integer getFee();
}
