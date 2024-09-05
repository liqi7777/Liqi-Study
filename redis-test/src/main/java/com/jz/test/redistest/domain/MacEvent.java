package com.jz.test.redistest.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class MacEvent implements Serializable {

    private Long pid;
    private String machNo;
    private String machType;
    private String curTime;
    private String eventType;
    private String data;
}
