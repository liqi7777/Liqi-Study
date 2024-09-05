package com.example.cloneTest.customAnnotationCopy;

/**
 * @author Sky
 * create 2020/03/17
 * email sky.li@ixiaoshuidi.com
 **/

import java.util.Date;

/**
 * @author : wee
 * @version v1.0
 * @Description:
 * @Date 2019-03-23  09:48
 */
public class MainTest {
    public static void main(String[] args) throws Exception {
        OriginUser originUser = new OriginUser();
        originUser.setOriginId(1000L);
        originUser.setOriginName("张四");
        originUser.setPassword("123456");
        originUser.setOriginBirthDay(new Date());
        originUser.setOriginHealth(true);

        TargetUser targetUser = new TargetUser();
        //拷贝
        CopyBeanUtils.copyBean(originUser, targetUser);
        System.out.println(targetUser);
    }
}
