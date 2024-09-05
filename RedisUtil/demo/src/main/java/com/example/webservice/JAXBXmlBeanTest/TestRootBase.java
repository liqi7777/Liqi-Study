package com.example.webservice.JAXBXmlBeanTest;

import cn.hutool.core.util.XmlUtil;
import com.alibaba.fastjson.JSONObject;
import com.example.webservice.JAXBXmlBeanTest.model.*;
import org.apache.commons.lang3.CharSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sky
 * create 2020/04/07
 * email sky.li@ixiaoshuidi.com
 *
 *           <?xml version="1.0" encoding="UTF-8"?>
                <base>
                    <root>
                        <user></user><!—-登录账号 -->
                        <password></password><!—-登录密码 -->
                    </root>
                </base>

                <?xml version="1.0" encoding="UTF-8"?>
                <base>
                    <root>
                        <inspecMMe>
                            <name></name>
                            <sex></sex>
                            <friends>
                                <friend>
                                    <age></age>
                                </friend>
                            </friends>
                        </inspecMMe>
                    </root>
                </base>
 *
 **/
public class TestRootBase {

    public static void main(String[] args) throws Exception {
//        Root anthRoot = new AnthRoot("liqi","1234");
//        Base base01 = new Base(anthRoot);
//        JAXBContext context = JAXBContext.newInstance(base01.getClass());
//        Marshaller marshaller = context.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);//格式化xml输出
//        StringWriter writer = new StringWriter();
//        marshaller.marshal(base01, writer);
//        String outString = writer.toString();
//        System.out.println(outString);

        System.out.println("=============================================================");

        List<Friend> friends=new ArrayList<>();
        Friend friend01=new Friend(30);
        Friend friend02 = new Friend(20);
        friends.add(friend01);
        friends.add(friend02);
        InspecMMe inspecMMe = new InspecMMe("liqi", "男", friends);
        ArgsRoot argsRoot = new ArgsRoot(inspecMMe);
        argsRoot.setName("hello");
        Base base02 = new Base(argsRoot);
        JAXBContext context02 = JAXBContext.newInstance(base02.getClass());
        Marshaller marshaller02 = context02.createMarshaller();
        marshaller02.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);//格式化xml输出
        marshaller02.setProperty(Marshaller.JAXB_FRAGMENT, true);
        marshaller02.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");//格式化xml输出

        StringWriter writer02 = new StringWriter();
        writer02.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "\n  ");
        marshaller02.marshal(base02, writer02);
        String outString02 = writer02.toString();
        System.out.println(outString02);

        Unmarshaller unmarshaller = context02.createUnmarshaller();
        Base unmarshal = (Base) unmarshaller.unmarshal(new StringReader(outString02));
        System.out.println(JSONObject.toJSONString(unmarshal));


    }
}
