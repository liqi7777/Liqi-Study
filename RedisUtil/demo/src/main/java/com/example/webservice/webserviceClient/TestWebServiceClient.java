package com.example.webservice.webserviceClient;

import com.example.webservice.CxfService;
import com.example.webservice.webserviceClient.automaticallyGenerate.CxfService_Service;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sky
 * create 2020/01/05
 * email sky.li@ixiaoshuidi.com
 **/

@RestController
@RequestMapping("/testWebServiceClient")
public class TestWebServiceClient {

    //该种调用方式：服务端和客户端在一起，调用失败
    @GetMapping("CxfService01")
    public Object say(String username) {
        CxfService_Service cxfServiceService = new CxfService_Service();
        return cxfServiceService.getCxfServiceImplPort().sayHello(username);
    }


    public static void main(String[] args) {
        TestWebServiceClient.main1();
        TestWebServiceClient.main2();
    }


    /**
     * * 1.代理类工厂的方式,需要拿到对方的接口地址
     */
    public static void main1() {
        try {
            // 接口地址
            String address = "http://127.0.0.1:8081/services/CxfService?wsdl";
            // 代理工厂
            JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
            // 设置代理地址
            jaxWsProxyFactoryBean.setAddress(address);
            // 设置接口类型
            jaxWsProxyFactoryBean.setServiceClass(CxfService.class);
            // 创建一个代理接口实现
            CxfService us = (CxfService) jaxWsProxyFactoryBean.create();
            // 数据准备
            String username = "liqi";
            // 调用代理接口的方法调用并返回结果
            String result = us.sayHello(username);
            System.out.println("返回结果:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * * 2：动态调用
     */
    public static void main2() {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://127.0.0.1:8081/services/CxfService?wsdl");
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
        Object[] objects = new Object[0];
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("sayHello", "sky");
            System.out.println("返回数据:" + objects[0]);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }


}
