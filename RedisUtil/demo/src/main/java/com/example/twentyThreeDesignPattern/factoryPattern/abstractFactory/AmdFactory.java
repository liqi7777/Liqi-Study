package com.example.twentyThreeDesignPattern.factoryPattern.abstractFactory;

import com.example.twentyThreeDesignPattern.factoryPattern.Cpu;
import com.example.twentyThreeDesignPattern.factoryPattern.IntelCpu;
import com.example.twentyThreeDesignPattern.factoryPattern.IntelMainboard;
import com.example.twentyThreeDesignPattern.factoryPattern.Mainboard;

/**
 * @author Sky
 * create 2019/09/26
 * email sky.li@ixiaoshuidi.com
 **/
public class AmdFactory implements AbstractFactory {

    @Override
    public Cpu createCpu() {
        // TODO Auto-generated method stub
        return new IntelCpu(938);
    }

    @Override
    public Mainboard createMainboard() {
        // TODO Auto-generated method stub
        return new IntelMainboard(938);
    }

}