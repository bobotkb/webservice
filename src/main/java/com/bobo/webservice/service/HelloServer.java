package com.bobo.webservice.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @author tangkb
 * @date 2020-09-10 14:15
 **/
@WebService(name = "HelloServer", // 暴露服务名称
        targetNamespace = "http://www.service.webservice.bobo.com" // 命名空间,一般是接口的包名倒序
        )
public interface HelloServer {
    /**
     * @param name
     * @return
     */
    @WebMethod
    @WebResult(name = "String")
    public String sayHelloSoap(@WebParam(name = "name") String name);
}
