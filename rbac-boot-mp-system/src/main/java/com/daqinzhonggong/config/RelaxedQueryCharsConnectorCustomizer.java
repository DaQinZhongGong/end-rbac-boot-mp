package com.daqinzhonggong.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.annotation.Configuration;

/**
 * TomcatConnectorCustomizer
 * 这个配置类的作用是允许在Spring Boot应用中内嵌的Tomcat服务器处理包含特定特殊字符的URL查询参数。这在某些情况下是必要的，比如当你需要在URL中传递JSON格式的数据或者需要使用这些特殊字符来满足特定的业务需求时。
 *
 * @author free
 */
@Configuration(proxyBeanMethods = false)
public class RelaxedQueryCharsConnectorCustomizer implements TomcatConnectorCustomizer {

    @Override
    public void customize(Connector connector) {
        connector.setProperty("relaxedQueryChars", "[]{}");
    }

}
