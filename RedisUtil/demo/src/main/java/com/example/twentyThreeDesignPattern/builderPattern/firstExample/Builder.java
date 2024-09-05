package com.example.twentyThreeDesignPattern.builderPattern.firstExample;

/**
 * @author Sky
 * create 2019/09/26
 * email sky.li@ixiaoshuidi.com
 **/
public interface Builder {
    public void buildPart1();
    public void buildPart2();
    public Product retrieveResult();
}