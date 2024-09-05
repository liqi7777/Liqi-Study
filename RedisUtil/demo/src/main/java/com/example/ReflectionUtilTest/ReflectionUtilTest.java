package com.example.ReflectionUtilTest;

import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * @author Sky
 * create 2019/06/25
 * email sky.li@ixiaoshuidi.com
 **/
public class ReflectionUtilTest {

    public static void main(String[] args) throws Exception {
        AnnotatedElement annotatedElement = TestMethod.class;
        Annotation[] annotations = annotatedElement.getAnnotations();
        System.out.println(annotations.toString());
//        ReflectionUtils.doWithMethods(TestMethod.class, method -> {
//                    try {
//                        method.setAccessible(false);
//                        System.out.printf("方法名称:%s,结果:%s", method.getName(), ReflectionUtils.invokeMethod(method, new TestMethod()));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                },
//                method -> method.getParameterCount() == 0 ? true : false);
//    }
    }
}
