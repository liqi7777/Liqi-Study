package com.example.cloneTest.customAnnotationCopy;

/**
 * @author Sky
 * create 2020/03/17
 * email sky.li@ixiaoshuidi.com
 **/

import lombok.Data;

import java.util.Date;

/**
 * @author : wee
 * @version v1.0
 * @Description: 目标User
 */
@Data
public class TargetUser {
    /**
     * id
     */
    private Long targetId;

    /**
     * 名称
     */
    private String targetName;

    /**
     * 密码
     */
    private String password;

    /**
     * 出生日期
     */
    @CopyField(originName = "originBirthDay")
    private Date targetBirthDay;

    /**
     * 是否健康
     */
    @CopyField(originName = "originHealth")
    private Boolean targetHealth;

    /**getter/setter省略*/
}
