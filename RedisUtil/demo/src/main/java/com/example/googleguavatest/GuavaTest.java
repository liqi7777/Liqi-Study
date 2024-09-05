package com.example.googleguavatest;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Sky
 * create 2019/09/12
 * email sky.li@ixiaoshuidi.com
 **/
public class GuavaTest {

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder("嗨，");
// 字符串连接器，以|为分隔符，同时去掉null元素
        Joiner joiner1 = Joiner.on("|").skipNulls();
// 构成一个字符串jim|jack|kevin并添加到stringBuilder
        stringBuilder = joiner1.appendTo(stringBuilder, "jim", "jack", null, "kevin");
        System.out.println(stringBuilder);


        Map<String, String> testMap = Maps.newLinkedHashMap();
        testMap.put("Cookies", "12332");
        testMap.put("Content-Length", "30000");
        testMap.put("Date", "2018.07.04");
        testMap.put("Mime", "text/html");
        // 用:分割键值对，并用#分割每个元素，返回字符串
        String returnedString = Joiner.on("#").withKeyValueSeparator(":").join(testMap);
        System.out.println(returnedString);

        // 接上一个，内部类的引用，得到分割器，将字符串解析为map
        Splitter.MapSplitter ms = Splitter.on("#").withKeyValueSeparator(':');
        Map<String, String> ret = ms.split(returnedString);
        for (String it2 : ret.keySet()) {
            System.out.println(it2 + " -> " + ret.get(it2));
        }

        Collections.synchronizedList(new ArrayList<>());

        ArrayList<String> list = Lists.newArrayList("a", "b", "c");




    }
}
