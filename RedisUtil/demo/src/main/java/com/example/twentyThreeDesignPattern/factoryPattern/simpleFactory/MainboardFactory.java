package com.example.twentyThreeDesignPattern.factoryPattern.simpleFactory;

import com.example.twentyThreeDesignPattern.factoryPattern.AmdMainboard;
import com.example.twentyThreeDesignPattern.factoryPattern.IntelMainboard;
import com.example.twentyThreeDesignPattern.factoryPattern.Mainboard;

/**
 * @author Sky
 * create 2019/09/26
 * email sky.li@ixiaoshuidi.com
 **/
public class MainboardFactory {
    public static Mainboard createMainboard(int type){
        Mainboard mainboard = null;
        if(type == 1){
            mainboard = new IntelMainboard(755);
        }else if(type == 2){
            mainboard = new AmdMainboard(938);
        }
        return mainboard;
    }
}
