package com.example.java8ActualCombat;

import com.example.test.Person;

import java.util.Optional;

/**
 * @author liqi
 * create  2021-06-23 10:07
 */
public class OptionalTest {
    public static void main(String[] args) {
        test01();
    }




    public static void test01(){
//        Person p =null;
        Person p = new Person(1,"liqi");
        Optional.ofNullable(p).filter(person -> person.getName().equals("liqi")).ifPresent(person -> System.out.println(person.getName()));

        Optional.ofNullable(p).map(person -> person.getName()).ifPresent(s -> System.out.println(s));
    }
}
