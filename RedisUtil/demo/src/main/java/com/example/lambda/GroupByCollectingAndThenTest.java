package com.example.lambda;

import com.example.test.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Sky
 * create 2020/02/28
 * email sky.li@ixiaoshuidi.com
 **/
public class GroupByCollectingAndThenTest {

    public static void main(String[] args) {

        List<User> userList = new ArrayList<>();
        userList.add(new User("liqi",20,new BigDecimal(20)));
        userList.add(new User("liqi",30,new BigDecimal(30)));
        userList.add(new User("hudan",40,new BigDecimal(40)));

    }


}
