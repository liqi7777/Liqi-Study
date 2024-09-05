package com.jz.test.redistest.test;

import cn.hutool.core.bean.BeanUtil;
import org.junit.Test;

import java.beans.PropertyDescriptor;
import java.util.Map;

/**
 * @author liqi
 * create  2024/7/18 8:22 下午
 */
public class PropertyDescriptorTest {
    @Test
    public void test() {
        // 定义一个示例类
        class Example {
            private String name;
            private int age;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }
        }

        // 获取属性描述符映射
        Map<String, PropertyDescriptor> propertys = BeanUtil.getPropertyDescriptorMap(Example.class, false);

        // 输出属性描述符映射的内容
        propertys.forEach((propertyName, propertyDescriptor) -> {
            System.out.println("Property: " + propertyName);
            System.out.println("Getter: " + propertyDescriptor.getReadMethod());
            System.out.println("Setter: " + propertyDescriptor.getWriteMethod());
            System.out.println();
        });
    }
}
