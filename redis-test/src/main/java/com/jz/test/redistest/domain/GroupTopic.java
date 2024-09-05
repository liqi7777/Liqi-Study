package com.jz.test.redistest.domain;

import lombok.Data;

import java.util.Objects;

@Data
public class GroupTopic {
    private String[] tops;
    private int[] qos;

    public int[] getQos() {
        if (qos == null || !Objects.equals(tops.length, qos.length)) {
            int[] temp = new int[tops.length];
            for (int i = 0; i < tops.length; i++) {
                if (qos != null && i < qos.length) {
                    temp[i] = qos[i];
                } else {
                    temp[i] = 1;
                }
            }
            qos = temp;
        }
        return qos;
    }
}