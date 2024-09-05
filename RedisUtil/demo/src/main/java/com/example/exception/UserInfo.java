package com.example.exception;

/**
 * @author liqi
 * create  2021-07-02 11:05
 */

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;

@Data
public class UserInfo {
    @NotBlank(message = "年龄不为空")
    @Max(value = 18, message = "不能超过18岁")
    private String age;
    @NotBlank(message = "性别不能为空")
    private String gender;
}


