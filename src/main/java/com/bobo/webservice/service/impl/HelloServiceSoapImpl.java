package com.bobo.webservice.service.impl;

import com.bobo.webservice.service.HelloServer;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

/**
 * 接口具体实现
 * @author tangkb
 * @date 2020-09-10 14:32
 **/
@WebService(serviceName = "HelloServer",
        targetNamespace = "http://www.service.webservice.bobo.com",
        endpointInterface = "com.bobo.webservice.service.HelloServer"
)
@BindingType(value = SOAPBinding.SOAP12HTTP_BINDING)
@Component
public class HelloServiceSoapImpl implements HelloServer {
    /**
     * soap1.1接口实现
     * @param name
     * @return
     */
    @Override
    public String sayHelloSoap(String name) {
        System.out.println("hello" + name);
        return "hello" + name;
    }
}
