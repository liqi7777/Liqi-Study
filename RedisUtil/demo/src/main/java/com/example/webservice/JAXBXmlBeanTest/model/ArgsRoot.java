package com.example.webservice.JAXBXmlBeanTest.model;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
//xml文件的根元素
@XmlRootElement(name="root")
//控制JAXB 绑定类中属性和字段的排序
@XmlType
public class ArgsRoot extends Root implements Serializable {

    @XmlAttribute(name = "name")
    private String name;
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private InspecMMe inspecMMe;

    public ArgsRoot() {
        super();
    }

    public ArgsRoot(InspecMMe inspecMMe) {
        super();
        this.inspecMMe = inspecMMe;
    }

    public InspecMMe getInspecMMe() {
        return inspecMMe;
    }

    public void setInspecMMe(InspecMMe inspecMMe) {
        this.inspecMMe = inspecMMe;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
