package com.jz.test.redistest.test;

/**
 * @author liqi
 * create  2022/9/5 2:49 下午
 */
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultisetTest {

    public static void main(String[] args) {

        String str = "张三 李四 李四 王五 王五 王五";
        String[] strArr = str.split(" ");

        List<String> words = new ArrayList<String>(Arrays.asList(strArr));

        //创建一个HashMultiset集合，并将words集合数据放入
        Multiset<String> wordMultiset = HashMultiset.create();
        wordMultiset.addAll(words);

        //将不同的元素放在一个集合set中
        for (String key : wordMultiset.elementSet()) {
            //查看指定元素的个数
            System.out.println(key + "-->" + wordMultiset.count(key));
        }

        System.out.println("---------向集合中添加元素----------");
        //向集合中添加元素
        wordMultiset.add("李四");
        for (String key : wordMultiset.elementSet()) {
            System.out.println(key + "-->" + wordMultiset.count(key));
        }

        System.out.println("-------向集合中添加若干个元素------");
        //向集合中添加若干个元素
        wordMultiset.add("赵六", 10);
        for (String key : wordMultiset.elementSet()) {
            System.out.println(key + "-->" + wordMultiset.count(key));
        }

        System.out.println("--------向集合中移出一个元素------");
        //向集合中移出一个元素
        wordMultiset.remove("张三");
        for (String key : wordMultiset.elementSet()) {
            System.out.println(key + "-->" + wordMultiset.count(key));
        }

        System.out.println("------向集合中移出若干个元素------");
        //向集合中移出若干个元素,如果元素的个数小于要移除的个数，则会把该元素移除光
        wordMultiset.remove("赵六",5);
        for (String key : wordMultiset.elementSet()) {
            System.out.println(key + "-->" + wordMultiset.count(key));
        }


        System.out.println("-----设定某一个元素的重复次数-----");
        //设定某一个元素的重复次数
        wordMultiset.setCount("张三", 10);
        for (String key : wordMultiset.elementSet()) {
            System.out.println(key + "-->" + wordMultiset.count(key));
        }

        System.out.println("-----设置复合元素的个数设为新的重复次数-----");
        //设置复合元素的个数设为新的重复次数(条件是第二个参数的数量要是实际数量一致，否则无效)
        wordMultiset.setCount("and", 1,0);
        for (String key : wordMultiset.elementSet()) {
            System.out.println(key + "-->" + wordMultiset.count(key));
        }

        System.out.println("-------删除给定集合中的元素------");
        //删除给定集合中的元素
        wordMultiset.removeAll(words);
        for (String key : wordMultiset.elementSet()) {
            System.out.println(key + "-->" + wordMultiset.count(key));
        }
    }
}