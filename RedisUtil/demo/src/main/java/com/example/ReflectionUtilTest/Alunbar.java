package com.example.ReflectionUtilTest;

import java.lang.reflect.Modifier;

/**
 * @author Sky
 * create  2021-02-18 15:10
 * email sky.li@ixiaoshuidi.com
 * 学习链接:
 * https://zhuanlan.zhihu.com/p/80519709
 *
 */
public class Alunbar {

    public static void main(String[] args) {
        Class alunbarClass = Alunbar.class;
        System.out.println(alunbarClass.getName());
        System.out.println(alunbarClass.getSimpleName());
        System.out.println(alunbarClass.getModifiers());
        System.out.println(Modifier.isPublic(alunbarClass.getModifiers()));

        Class birdClass = Bird.class;
        System.out.println(birdClass.getModifiers());
        System.out.println(birdClass.getPackage());
        System.out.println(Modifier.isPublic(birdClass.getModifiers()));

    }


    private class Bird{

    }
}
