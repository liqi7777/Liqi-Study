package com.example.twentyThreeDesignPattern.factoryPattern.abstractFactory;

import com.example.twentyThreeDesignPattern.factoryPattern.Cpu;
import com.example.twentyThreeDesignPattern.factoryPattern.Mainboard;

/**
 * @author Sky
 * create 2019/09/26
 * email sky.li@ixiaoshuidi.com
 **/
public interface AbstractFactory {
    /**
     * 创建CPU对象
     * @return CPU对象
     */
    public Cpu createCpu();
    /**
     * 创建主板对象
     * @return 主板对象
     */
    public Mainboard createMainboard();
}