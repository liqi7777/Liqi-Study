package com.example.java8ActualCombat;

import com.alibaba.fastjson.JSONObject;
import com.example.test.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * @author Sky
 * create  2020-09-24 14:17
 * email sky.li@ixiaoshuidi.com
 */
public class java8StreamTest {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));


//        test01(menu);
//        test02(menu);
//        test03(menu);
//        test04(menu);
//        test05(menu);
//        test06(menu);
//        test07(menu);
        test08(menu);
    }

    private static void test08(List<Dish> menu) {
        Map<Dish.Type, ArrayList<Dish>> map = menu.stream().collect(groupingBy(Dish::getType,
                collectingAndThen
                        (toCollection(() -> new TreeSet<Dish>(Comparator.comparing(Dish::getCalories))), ArrayList::new)));

        System.out.println(JSONObject.toJSONString(map));

    }


    public static void test01(List<Dish> menu) {
        Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
                menu.stream().collect(
                        groupingBy(Dish::getType,
                                groupingBy(dish -> {
                                    if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
                                    else return Dish.CaloricLevel.FAT;
                                })
                        )
                );

        System.out.println(dishesByTypeCaloricLevel);
    }


    public static void test02(List<Dish> menu) {
        Map<Dish.Type, Long> typesCount = menu.stream().collect(
                groupingBy(Dish::getType, counting()));
        System.out.println(typesCount);
    }


    public static void test03(List<Dish> menu) {
        Map<Dish.Type, Optional<Dish>> mostCaloricByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                maxBy(comparingInt(Dish::getCalories))));
        mostCaloricByType.entrySet().forEach(typeOptionalEntry -> System.out.println(
                "key:" + typeOptionalEntry.getKey() + "  value:" +
                        typeOptionalEntry.getValue().get()));
    }


    public static void test04(List<Dish> menu) {
        Map<Dish.Type, Dish> mostCaloricByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                collectingAndThen(
                                        maxBy(comparingInt(Dish::getCalories)),
                                        Optional::get)));
        System.out.println(mostCaloricByType);
    }

    public static void test05(List<Dish> menu) {
        Map<Dish.Type, Set<Dish.CaloricLevel>> caloricLevelsByType =
                menu.stream().collect(
                        groupingBy(Dish::getType, mapping(
                                dish -> {
                                    if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
                                    else return Dish.CaloricLevel.FAT;
                                },
                                toCollection(HashSet::new))));

        System.out.println(caloricLevelsByType);

    }

    //分区
    public static void test06(List<Dish> menu) {
        Map<Boolean, List<Dish>> partitionedMenu =
                menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println(partitionedMenu);
    }

    public static void test07(List<Dish> menu) {
        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType =
                menu.stream().collect(
                        partitioningBy(Dish::isVegetarian,
                                groupingBy(Dish::getType)));
        System.out.println(vegetarianDishesByType);
    }


}
