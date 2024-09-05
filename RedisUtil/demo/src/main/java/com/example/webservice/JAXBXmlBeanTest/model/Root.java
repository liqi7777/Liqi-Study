package com.example.webservice.JAXBXmlBeanTest.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
//xml文件的根元素
@XmlRootElement(name = "root")
//控制JAXB 绑定类中属性和字段的排序
@XmlType
public class Root implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

}
