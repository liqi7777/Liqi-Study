package com.example.cloneTest.customAnnotationCopy;

import lombok.Data;

import java.util.Date;

/**
 * @author Sky
 * create 2020/03/17
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class OriginUser {


    /**
     * id
     */
    @CopyField(targetName="targetId")
    private Long originId;

    /**
     * 名称
     */
    @CopyField(targetName = "targetName")
    private String originName;

    /**
     * 密码
     */
    private String password;

    /**
     * 出生日期
     */
    private Date originBirthDay;

    /**
     * 是否健康
     */
    private Boolean originHealth;

    /**getter/setter省略*/
}
