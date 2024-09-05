package com.example.webservice.JAXBXmlBeanTest.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
//xml文件的根元素
@XmlRootElement(name="root")
//控制JAXB 绑定类中属性和字段的排序
@XmlType(propOrder = {"user","password"})
public class AnthRoot extends Root{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 登录帐号
     */
    private String user;

    /**
     * 登录密码
     */
    private String password;

    public AnthRoot() {
        super();
    }

    public AnthRoot(String user, String password) {
        super();
        this.user = user;
        this.password = password;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}