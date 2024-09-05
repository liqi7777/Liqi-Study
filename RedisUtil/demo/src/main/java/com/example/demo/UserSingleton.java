package com.example.demo;

/**
 * @author Sky
 * create  2020-11-13 14:36
 * email sky.li@ixiaoshuidi.com
 */
public class UserSingleton {

    private UserSingleton() {
    }

    public static UserSingleton getInstance() {
        return UserSignletonHolder.INSTANCE;
    }

    private static class UserSignletonHolder {
        private static final UserSingleton INSTANCE = new UserSingleton();
    }
}
