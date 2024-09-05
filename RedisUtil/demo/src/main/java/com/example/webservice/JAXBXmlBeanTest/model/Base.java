package com.example.webservice.JAXBXmlBeanTest.model;


import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
//xml文件的根元素
@XmlRootElement(name = "base")
@XmlType
@XmlSeeAlso(value = {AnthRoot.class, ArgsRoot.class})
public class Base implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * root元素实体
     */
//    @XmlElementRef(type = AnthRoot.class)
    @XmlElementRef(type = ArgsRoot.class)
    private Root root;

    public Base() {
        super();
    }

    public Base(Root root) {
        super();
        this.root = root;
    }

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
    }

}