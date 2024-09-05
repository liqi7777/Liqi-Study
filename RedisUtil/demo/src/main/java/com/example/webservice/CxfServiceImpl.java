package com.example.webservice;

import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * 功能描述：
 *
 * @author liuchaoyong
 * @version 1.0
 * @date 2019-03-03 16:12
 */
@WebService(serviceName = "CxfService", targetNamespace = "http://webservice.example.com", endpointInterface = "com.example.webservice.CxfService")
@Component
public class CxfServiceImpl implements CxfService {

    @Override
    public String sayHello(String username) {
        return "hello：" + username;
    }
}
