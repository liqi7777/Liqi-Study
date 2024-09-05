package com.example.java8MapnewFeatures;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Sky
 * create 2020/05/21
 * email sky.li@ixiaoshuidi.com
 **/
public class java8ReducingTest {

    public static void main(String[] args) {

    }


    @Test
    public void test01() {
        Optional accResult = Stream.of(1, 2, 3, 4)
                .reduce((acc, item) -> {
                    System.out.println("acc : " + acc);
                    acc += item;
                    System.out.println("item: " + item);
                    System.out.println("acc+ : " + acc);
                    System.out.println("--------");
                    return acc;
                });
        System.out.println("accResult: " + accResult.get());
        System.out.println("--------");
// 结果打印
//        --------
//                acc : 1
//        item: 2
//        acc+ : 3
//                --------
//        acc : 3
//        item: 3
//        acc+ : 6
//                --------
//        acc : 6
//        item: 4
//        acc+ : 10
//                --------
//        accResult: 10
//                --------
    }

    @Test
    public void test02() {
        int accResult = Stream.of(1, 2, 3, 4)
                .reduce(0, (acc, item) -> {
                    System.out.println("acc : " + acc);
                    acc += item;
                    System.out.println("item: " + item);
                    System.out.println("acc+ : " + acc);
                    System.out.println("--------");
                    return acc;
                });
        System.out.println("accResult: " + accResult);
        System.out.println("--------");
// 结果打印
//        acc : 0
//        item: 1
//        acc+ : 1
//                --------
//        acc : 1
//        item: 2
//        acc+ : 3
//                --------
//        acc : 3
//        item: 3
//        acc+ : 6
//                --------
//        acc : 6
//        item: 4
//        acc+ : 10
//                --------
//        accResult: 10
//                --------
    }


    @Test
    public void testReducing() {

        // sum: 是每次累计计算的结果，b是Function的结果
        System.out.println(Stream.of(1, 3, 4).collect(Collectors.reducing(0, x -> x + 1, (sum, b) -> {
            System.out.println(sum + "-" + b);
            return sum + b;
        })));


        // 下面代码是对reducing函数功能实现的描述，用于理解reducing的功能
        int sum = 0;
        List<Integer> list3 = Arrays.asList(1, 3, 4);
        for (Integer item : list3) {
            int b = item + 1;
            System.out.println(sum + "-" + b);
            sum = sum + b;
        }
        System.out.println(sum);


        // 注意reducing可以用于更复杂的累计计算，加减乘除或者更复杂的操作
        // result = 2 * 4 * 5 = 40
        System.out.println(Stream.of(1, 3, 4).collect(Collectors.reducing(1, x -> x + 1, (result, b) -> {
            System.out.println(result + "-" + b);
            return result * b;
        })));
    }


}
