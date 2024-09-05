package com.example.java8MapnewFeatures;


import com.alibaba.fastjson.JSONObject;
import com.example.test.Person;
import com.example.test.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Sky
 * create  2020-09-23 16:18
 * email sky.li@ixiaoshuidi.com
 */
public class java8GroupingbyTest {

    public static void main(String[] args) {
        User user1 = new User("zhangsan", 10, BigDecimal.valueOf(10));
        User user2 = new User("zhangsan", 20, BigDecimal.valueOf(20));
        User user3 = new User("lisi", 30, BigDecimal.valueOf(30));
        List<User> list = new ArrayList<User>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        Map<String, Map<Integer, List<User>>> collect = list.stream().collect(
                Collectors.groupingBy(
                        User::getUsername, Collectors.groupingBy(User::getMoney)
                )
        );
        System.out.println(collect);


        List<Person> p = new ArrayList<>();
        p.add(new Person(1,"liqi"));
        p.add(new Person(2,"lili"));
        List<Person> collect1 = p.stream().filter(person -> person.getId() == 1).collect(Collectors.toList());
        for (Person person : collect1) {
            person.setName("ahah");
        }
        System.out.println(JSONObject.toJSONString(p));
        System.out.println(JSONObject.toJSONString(collect1));
    }
}
