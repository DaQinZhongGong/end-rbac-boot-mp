package com.daqinzhonggong.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;
import java.io.File;

/**
 * 用于配置Spring Boot应用程序中的文件上传相关设置
 *
 * @author free
 */
@Slf4j
// @Configuration：这个注解表明MultipartConfig是一个配置类，Spring容器会将其中的@Bean注解的方法作为Bean的定义来处理。
@Configuration
public class MultipartConfig {

    // 文件上传临时路径
    // multipartConfigElement()：这是一个带有@Bean注解的方法，它的作用是创建一个MultipartConfigElement的Bean，该Bean用于配置文件上传的相关参数。
    @Bean
    MultipartConfigElement multipartConfigElement() {
        // 在multipartConfigElement()方法中，首先创建了一个MultipartConfigFactory的实例。MultipartConfigFactory是Spring提供的一个工厂类，用于创建MultipartConfigElement对象。
        MultipartConfigFactory factory = new MultipartConfigFactory();
        String location = System.getProperty("user.home") + "/.daqinzhonggong/file/tmp";
        File tmpFile = new File(location);
        // 代码检查这个临时目录是否存在，如果不存在，则尝试创建它。如果创建失败（例如，由于权限问题），则会在控制台输出一条消息。
        if (!tmpFile.exists()) {
            if (!tmpFile.mkdirs()) {
                log.info("临时文件目录创建失败");
            }
        }
        // 使用factory.setLocation(location)设置了文件上传的临时目录，并通过factory.createMultipartConfig()方法创建了一个MultipartConfigElement对象并返回。
        factory.setLocation(location);
        return factory.createMultipartConfig();
    }

}