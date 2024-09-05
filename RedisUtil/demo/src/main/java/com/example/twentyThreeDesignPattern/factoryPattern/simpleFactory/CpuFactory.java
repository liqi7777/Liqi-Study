package com.example.twentyThreeDesignPattern.factoryPattern.simpleFactory;

import com.example.twentyThreeDesignPattern.factoryPattern.AmdCpu;
import com.example.twentyThreeDesignPattern.factoryPattern.Cpu;
import com.example.twentyThreeDesignPattern.factoryPattern.IntelCpu;

/**
 * @author Sky
 * create 2019/09/26
 * email sky.li@ixiaoshuidi.com
 **/
public class CpuFactory {
    public static Cpu createCpu(int type){
        Cpu cpu = null;
        if(type == 1){
            cpu = new IntelCpu(755);
        }else if(type == 2){
            cpu = new AmdCpu(938);
        }
        return cpu;
    }
}