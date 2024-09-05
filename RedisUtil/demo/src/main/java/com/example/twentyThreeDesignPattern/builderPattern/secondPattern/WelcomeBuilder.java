package com.example.twentyThreeDesignPattern.builderPattern.secondPattern;

/**
 * @author Sky
 * create 2019/09/26
 * email sky.li@ixiaoshuidi.com
 **/
public class WelcomeBuilder extends Builder {
    public WelcomeBuilder() {
        msg = new WelcomeMessage();
    }

    @Override
    public void buildBody() {
        // TODO Auto-generated method stub
        msg.setBody("欢迎内容");
    }

    @Override
    public void buildSubject() {
        // TODO Auto-generated method stub
        msg.setSubject("欢迎标题");
    }

}