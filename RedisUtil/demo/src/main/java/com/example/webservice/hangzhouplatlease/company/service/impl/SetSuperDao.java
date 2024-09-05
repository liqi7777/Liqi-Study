
package com.example.webservice.hangzhouplatlease.company.service.impl;

import com.example.webservice.hangzhouplatlease.core.dao.xsd.SuperDao;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="superDao" type="{http://dao.core.hzfc.com/xsd}SuperDao" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "superDao"
})
@XmlRootElement(name = "setSuperDao")
public class SetSuperDao {

    @XmlElementRef(name = "superDao", namespace = "http://impl.service.company.hzfc.com", type = JAXBElement.class, required = false)
    protected JAXBElement<SuperDao> superDao;

    /**
     * ��ȡsuperDao���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SuperDao }{@code >}
     *     
     */
    public JAXBElement<SuperDao> getSuperDao() {
        return superDao;
    }

    /**
     * ����superDao���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SuperDao }{@code >}
     *     
     */
    public void setSuperDao(JAXBElement<SuperDao> value) {
        this.superDao = value;
    }

}
