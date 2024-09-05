package com.example.java8MapnewFeatures;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @author Sky
 * create 2020/01/08
 * email sky.li@ixiaoshuidi.com
 **/
public class java8MapnewFeatures {

    public static void main(String[] args) {
        //map 新特性
        Map<Integer, String> map = new HashMap<Integer, String>();
        for (int i = 0; i < 6; i++) {
            map.put(i, "val_" + i);
        }
        map.put(10, null);


        //1:遍历
        map.forEach((key, value) -> System.out.println(key + ":" + value));


        //2:V getOrDefault(key,defaultValue):获取key值,如果key不存在则用defaultValue
        System.out.println("3-->" + map.getOrDefault(3, "val_66"));//3-->val_3
        System.out.println("10-->" + map.getOrDefault(10, "val_66"));//10-->null
        System.out.println("11-->" + map.getOrDefault(11, "val_66"));//11-->val_66


        //3:V putIfAbsent(K key, V value):根据key匹配Node,如果匹配不到则增加key-value,返回null,如果匹配到Node,如果oldValue不等于null则不进行value覆盖，返回oldValue
        System.out.println(map.putIfAbsent(3, "val_66"));//val_3
        System.out.println(map.putIfAbsent(10, "val_66"));//null
        System.out.println(map.putIfAbsent(11, "val_66"));//null
        System.out.println(map.get(3) + "--" + map.get(10) + "--" + map.get(11));//val_3--val_66--val_66

        //5:boolean replace(K key, V oldValue, V newValue):根据key匹配node,如果value也相同则使用newValue覆盖返回true，否则返回false
        map.put(11, null);
        map.replace(3, "3", "33");
        map.replace(10, "val_66", "val_666666");
        map.replace(11, null, "val_11");
        map.replace(11, null, "val_11");
        System.out.println(map.toString());//{0=val_0, 1=val_1, 2=val_2, 3=val_3, 4=val_4, 5=val_5, 10=val_666666, 11=val_11}


        /** 6:
         * void replaceAll(BiFunction function)：调用此方法时重写BiFunction的Object apply(Object o, Object o2)方法，
         * 其中o为key，o2为value，根据重写方法逻辑进行重新赋值。
         */
        map.replaceAll((key, value) -> {
            if (key == 2) {
                return value + "222";
            }
            return value;
        });
        System.out.println(map.toString());//{0=val_0, 1=val_1, 2=val_2222, 3=val_3, 4=val_4, 5=val_5, 10=val_666666, 11=val_11}


        /** 7:
         * V compute(K key,BiFunction remappingFunction)：根据key做匹配，根据BiFunction的apply返回做存储的value。
         * 匹配到Node做value替换，匹配不到新增node。apply的返回值如果为null则删除该节点，否则即为要存储的value。
         */
        System.out.println("---------------------- compute -----------------------");
        System.out.println(map.compute(3, new BiFunction() {
            @Override
            public Object apply(Object key, Object value) {
                return key + ":" + value;
            }
        }));//3:val_3 -》用返回值覆盖原来的值，这里用了java7的编码方式，以下均采用java8的lanbda表达式
        System.out.println(map.compute(10, (key, value) -> {
            return value.split("_")[1];
        }));//666666 -》用返回值覆盖原来的值
        System.out.println(map.compute(6, (key, value) -> null));//null -》返回值为null，则删除该key值
        System.out.println(map.toString());//{0=val_0, 1=val_1, 2=val_2, 3=3:val_3, 4=val_4, 5=val_5, 10=666666, 11=val_11}


        /** 8:
         * merge(K key, V value,BiFunctionsuper V, ? super V, ? extends V> remappingFunction):
         * 功能大部分与compute相同，不同之处在于BiFunction中apply的参数，入参为oldValue、value，
         * 调用merge时根据两个value进行逻辑处理并返回value。
         */
        System.out.println(map.merge(3, "val_3", (value, newValue) -> newValue));//val_3  --》返回值覆盖原来的value
        System.out.println(map.merge(10, "33334", (a, b) -> (Integer.valueOf(a) + Integer.valueOf(b)) + ""));//700000
        System.out.println(map.merge(8, "88", (oldValue, newValue) -> oldValue + newValue));//88 -》key不存在则新增
        System.out.println(map.merge(11, "11", (old, newValue) -> null));//null -》返回值为null,删除该节点
        System.out.println(map.toString());//{0=val_0, 1=val_1, 2=val_2, 3=val_3, 4=val_4, 5=val_5, 8=88, 10=700000}


    }
}
