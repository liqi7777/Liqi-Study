package com.example.webservice.JAXBXmlBeanTest.model;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author Sky
 * create 2020/04/07
 * email sky.li@ixiaoshuidi.com
 **/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "inspecMMe")
@XmlType
@Data
public class InspecMMe {
    private String name;

    @XmlElement(name="sex")
    private String sex;

    @XmlElementWrapper(name = "friends")
    @XmlElement(name = "friend")
    private List<Friend> friends;


    public InspecMMe() {
    }

    public InspecMMe(String name, String sex, List<Friend> friends) {
        this.name = name;
        this.sex = sex;
        this.friends = friends;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }
}
