package com.jz.test.redistest.test;

import lombok.Data;
import org.apache.commons.compress.utils.Lists;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liqi
 * create  2021-08-19 15:38
 */
@Data
public class OutClass {
    private int i;
    private List<Object> lists = new ArrayList<>();


    public static void main(String[] args) {
        OutClass outClass = new OutClass();

        InputStream in;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<Object> objects = Lists.newArrayList();

        Long l = 1L;
        System.out.println(l==1L);

    }

}
