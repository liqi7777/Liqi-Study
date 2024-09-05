// package com.jz.test.redistest.test;
//
// import cn.hutool.core.collection.ListUtil;
// import cn.hutool.core.date.DatePattern;
// import cn.hutool.core.date.LocalDateTimeUtil;
// import com.alibaba.fastjson.JSONObject;
// import com.jz.utils.util.RandomUtil;
// import org.junit.Test;
//
// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.Map;
// import java.util.Set;
// import java.util.stream.Stream;
//
// /**
//  * @author liqi
//  * create  2024/6/12 1:44 下午
//  */
// public class JavaGe8Test {
//     @Test
//     public void testJava() {
//         // String result = "foo".transform(input -> input + " bar");
//
//         String json = """
//                 {
//                     "name":"mkyong",
//                     "age":38
//                 }
//                 """;
//         System.out.println(json);
//     }
//
//     @Test
//     public void testUuid() {
//         String randomString = RandomUtil.getRandom(32);
//         System.out.println("Random String: " + randomString);
//     }
//
//     @Test
//     public void testListUtil() {
//         List<String> list = List.of("a", "b", "c","d","e");
//         List<String> strings = list.subList(list.size()-4,list.size());
//         System.out.println(JSONObject.toJSONString(strings));
//     }
//
//     @Test
//     public void testLocaldate(){
//         System.out.println(LocalDateTimeUtil.format(LocalDateTime.now(), DatePattern.NORM_DATETIME_MS_PATTERN));
//     }
// }
