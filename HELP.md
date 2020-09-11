spingboot 整合 cxf 发布webservice服务

1.pom文件中引入cxf的start依赖
    <!-- CXF webservice -->
    <dependency>
        <groupId>org.apache.cxf</groupId>
        <artifactId>cxf-spring-boot-starter-jaxws</artifactId>
        <version>3.2.6</version>
    </dependency>
    <!-- CXF webservice -->
2.写需要发布的服务的接口，并用添加注解@WebService(name = "HelloServer", // 暴露服务名称
                        targetNamespace = "http://www.service.webservice.bobo.com" // 命名空间,一般是接口的包名倒序
                        )
3.编写接口的实现类，并添加注解@WebService(serviceName = "HelloServer",
                        targetNamespace = "http://www.service.webservice.bobo.com",
                        endpointInterface = "com.bobo.webservice.service.HelloServer"//要发布的服务接口全类名
                        )
4.编写服务发布的配置类
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

5.注意，springboot的版本号过高会出现报错，问题待查找（或许因为springboot和cxf的版本不匹配导致的）
    问题原因是springboot版本和cxf启动器版本不匹配导致的，
    报错信息：The Bean Validation API is on the classpath but no implementation could be found
        出问题的版本信息：
            springboot版本
            <parent>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-parent</artifactId>
                <version>2.3.3.RELEASE</version>
                <relativePath/> <!-- lookup parent from repository -->
            </parent>
            cxf的版本
            <dependency>
                <groupId>org.apache.cxf</groupId>
                <artifactId>cxf-spring-boot-starter-jaxws</artifactId>
                <version>3.2.6</version>
            </dependency>
        用springboot默认的最新版本和cxf的最新版本不会报错
        <parent>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-parent</artifactId>
            <version>2.3.3.RELEASE</version>
            <relativePath/>
        </parent>
        <!-- https://mvnrepository.com/artifact/org.apache.cxf/cxf-spring-boot-starter-jaxws -->
        <dependency>
            <groupId>org.apache.cxf</groupId>
            <artifactId>cxf-spring-boot-starter-jaxws</artifactId>
            <version>3.4.0</version>
        </dependency>
        
            