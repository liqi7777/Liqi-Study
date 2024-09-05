
package com.example.webservice.hangzhouplatlease.company.service.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.lang.Exception;


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
 *         &lt;element name="RentQyServiceException" type="{http://impl.service.company.hzfc.com}Exception" minOccurs="0"/>
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
    "rentQyServiceException"
})
@XmlRootElement(name = "RentQyServiceException")
public class RentQyServiceException {

    @XmlElementRef(name = "RentQyServiceException", namespace = "http://impl.service.company.hzfc.com", type = JAXBElement.class, required = false)
    protected JAXBElement<java.lang.Exception> rentQyServiceException;

    /**
     * ��ȡrentQyServiceException���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link java.lang.Exception }{@code >}
     *     
     */
    public JAXBElement<java.lang.Exception> getRentQyServiceException() {
        return rentQyServiceException;
    }

    /**
     * ����rentQyServiceException���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link java.lang.Exception }{@code >}
     *     
     */
    public void setRentQyServiceException(JAXBElement<Exception> value) {
        this.rentQyServiceException = value;
    }

}
