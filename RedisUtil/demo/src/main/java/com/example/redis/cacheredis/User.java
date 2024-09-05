package com.example.redis.cacheredis;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: MaoLin
 * @Date: 2019/3/24 14:36
 * @Version 1.0
 */

@Data
public class User implements Serializable {

    private int userId;

    private String userName;

    private String userPassword;

    public User(int userId, String userName, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }

    public String testToString() {
        return this.userId + "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}

