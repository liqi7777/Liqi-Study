package com.example.twentyThreeDesignPattern.builderPattern.secondPattern;

/**
 * @author Sky
 * create 2019/09/26
 * email sky.li@ixiaoshuidi.com
 **/
public class GoodbyeBuilder extends Builder {

    public GoodbyeBuilder() {
        msg = new GoodbyeMessage();
    }

    @Override
    public void buildBody() {
        // TODO Auto-generated method stub
        msg.setBody("欢送内容");
    }

    @Override
    public void buildSubject() {
        // TODO Auto-generated method stub
        msg.setSubject("欢送标题");
    }

}