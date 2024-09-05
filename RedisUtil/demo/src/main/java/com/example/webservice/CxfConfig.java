package com.example.webservice;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * 功能描述：
 *
 * @author liuchaoyong
 * @version 1.0
 * @date 2019-03-03 16:15
 */
@Configuration
public class CxfConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private CxfService cxfService;


    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, cxfService);
        //wsdl：web服务描述语言, 具体的地址为：http://127.0.0.1:8081/services/{addr}?wsdl
        endpoint.publish("/CxfService");
        return endpoint;
    }


}