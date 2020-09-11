package com.bobo.webservice.config;

import com.bobo.webservice.service.HelloServer;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * cxf配置文件
 * @author tangkb
 * @date 2020-09-10 15:23
 **/
@Configuration
public class CxfConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private HelloServer helloServer;

    /**
     * 发布服务名称，要注意这里方法名称不能用dispatcherServlet，否则会导致Controller服务无法正常响应
     * @return
     */
    @Bean
    public ServletRegistrationBean webServiceDispatcherServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/soap/*");
    }

    @Bean
    public Endpoint endpoint() {

        EndpointImpl endpoint = new EndpointImpl(bus, helloServer);
        endpoint.publish("/helloServer");

        return endpoint;
    }
}
