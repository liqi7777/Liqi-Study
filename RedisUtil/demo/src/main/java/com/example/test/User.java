package com.example.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.bytebuddy.asm.Advice;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;

/**
 * @author Sky
 * create 2018/11/06
 * email sky.li@ixiaoshuidi.com
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class User implements Cloneable {
    private String username;
    private Integer money;
    private BigDecimal bigDecimal;


    public void incrMoney() {
        ++money;
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", money=" + money +
                ", bigDecimal=" + bigDecimal +
                '}';
    }
}
