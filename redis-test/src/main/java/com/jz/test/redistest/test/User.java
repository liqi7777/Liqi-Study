package com.jz.test.redistest.test;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@Builder
public class User {

    @Builder.Default
    private Long id =1L;

    @JSONField(name = "Name")
    private String name;

    private String address;

    private BigDecimal priority;

    private User curUser;



    public BigDecimal getPriority() {
        return priority;
    }

    public void setPriority(BigDecimal priority) {
        this.priority = priority;
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
    }

}
