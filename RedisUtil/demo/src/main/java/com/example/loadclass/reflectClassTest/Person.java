package com.example.loadclass.reflectClassTest;

/**
 * @author Sky
 * create 2020/05/15
 * email sky.li@ixiaoshuidi.com
 **/
/*
@author :yinzhengjie
Blog:http://www.cnblogs.com/yinzhengjie/tag/Java%E5%9F%BA%E7%A1%80/
EMAIL:y1053419035@qq.com
*/

public class Person {
    //定义私有属性
    private String name;
    private int age;

    //定义空参构造
    public Person() {
    }

    //定义有参构造
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //定义私有的构造方法
    private Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    //定义默认的权限的构造方法
    Person(int age) {
        this.age = age;
        this.name = "yinzhengjie";
    }

    //定义Person特有的方法
    public void study() {
        System.out.println(this.name + " 正在学习！！！");
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

    //重写toString方法。
    @Override
    public String toString() {
        return "Person{" + " name = " + name + ", age = " + age + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (getAge() != person.getAge()) return false;
        return getName() != null ? getName().equals(person.getName()) : person.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + getAge();
        return result;
    }
}
