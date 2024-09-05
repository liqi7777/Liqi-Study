package com.example.test;

/**
 * <br>
 *
 * @author Porter
 * create by 2019/2/28
 * @Emial Porter.Wang@ixiaoshuidi.com
 */
public enum UserTypeEnum {

    ADMIN(1),
    PLATFORM(2),
    SUB_MERCHANTS(3);

    int userType;

    UserTypeEnum(int userType) {
        this.userType = userType;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
