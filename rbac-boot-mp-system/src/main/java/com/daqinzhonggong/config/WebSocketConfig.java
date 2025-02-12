package com.daqinzhonggong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 通过这个配置类，Spring应用会自动注册所有使用@ServerEndpoint注解的类，使得它们可以作为WebSocket服务端的端点。
 * 这样，开发者就可以在Spring应用中方便地使用WebSocket进行实时通信，而不需要手动配置WebSocket的端点。
 *
 * @author free
 */
// 标注这个类为配置类，Spring Boot会自动扫描并加载配置类中的Bean
@Configuration
public class WebSocketConfig {

    // 标注这个方法返回的对象将被Spring容器管理为一个Bean
    @Bean
    // 定义一个名为serverEndpointExporter的方法，返回类型为ServerEndpointExporter
    public ServerEndpointExporter serverEndpointExporter() {
        // 实例化ServerEndpointExporter对象并返回
        return new ServerEndpointExporter();
    }

}

