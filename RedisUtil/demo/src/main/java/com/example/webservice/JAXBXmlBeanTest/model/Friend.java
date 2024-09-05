package com.example.webservice.JAXBXmlBeanTest.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sky
 * create 2020/04/07
 * email sky.li@ixiaoshuidi.com
 **/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "friend")
public class Friend {

    @XmlElement(name = "age")
    private Integer age;


    public Friend() {
    }

    public Friend(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
