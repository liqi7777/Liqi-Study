package com.example.loadclass.classLoadTest;

/*
@author :yinzhengjie
Blog:http://www.cnblogs.com/yinzhengjie/tag/Java%E5%9F%BA%E7%A1%80/
EMAIL:y1053419035@qq.com
*/
public class Person {
    //定义私有属性
    private String name;
    private int age;

    //定义静态代码块，每次new Person类时，都会触发静态代码块
    {
        System.out.println("Welcome to use the Yin Zhengjie custom Person class !");
    }

    //定义空参构造
    public Person() {
    }

    //定义有参构造
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //为name和age变量定义get和set方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
