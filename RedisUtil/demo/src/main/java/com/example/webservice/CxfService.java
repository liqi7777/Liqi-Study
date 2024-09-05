package com.example.webservice;

import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @author Sky
 * create 2020/01/05
 * email sky.li@ixiaoshuidi.com
 **/
@WebService(name = "CxfService",targetNamespace = "http://webservice.example.com")
public interface CxfService {

    @WebMethod
    @WebResult(name = "String",targetNamespace = "")
    public String sayHello(@WebParam(name = "username") String username);
}
