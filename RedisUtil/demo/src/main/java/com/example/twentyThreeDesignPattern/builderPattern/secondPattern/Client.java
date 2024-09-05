package com.example.twentyThreeDesignPattern.builderPattern.secondPattern;

/**
 * @author Sky
 * create 2019/09/26
 * email sky.li@ixiaoshuidi.com
 **/
public class Client {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Builder builder = new WelcomeBuilder();
        Director director = new Director(builder);
        director.construct("toAddress@126.com", "fromAddress@126.com");

    }

}