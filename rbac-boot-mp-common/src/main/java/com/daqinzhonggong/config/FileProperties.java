package com.daqinzhonggong.config;

import com.daqinzhonggong.utils.ElConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 用于配置与文件相关的属性
 *
 * @author free
 */
// @Data：这是Lombok库提供的一个注解，用于自动生成类的getter、setter、toString、equals和hashCode方法。这大大简化了代码，使得开发者不需要手动编写这些模板方法。
@Data
// @Configuration：这个注解表明FileProperties是一个配置类，它允许Spring容器通过Java配置的方式来管理Bean。然而
@Configuration
// @ConfigurationProperties(prefix = "file")：这个注解用于将配置属性绑定到Spring Boot应用程序的配置文件中。在这个例子中，prefix = "file"意味着FileProperties类中的属性将会从配置文件中以file.为前缀的属性中读取值。例如，如果配置文件中有一个属性file.maxSize=10MB，那么它将会被绑定到FileProperties类的maxSize属性上。
@ConfigurationProperties(prefix = "file")
public class FileProperties {

    // 文件大小限制
    private Long maxSize;

    // 头像大小限制
    private Long avatarMaxSize;

    // mac、linux、windows：这些属性都是ElPath类型的实例，用于存储与不同操作系统相关的文件路径配置。
    private ElPath mac;

    // mac、linux、windows：这些属性都是ElPath类型的实例，用于存储与不同操作系统相关的文件路径配置。
    private ElPath linux;

    // mac、linux、windows：这些属性都是ElPath类型的实例，用于存储与不同操作系统相关的文件路径配置。
    private ElPath windows;

    // getPath()：这是一个公共方法，用于根据当前操作系统的类型返回相应的文件路径配置。它首先通过System.getProperty("os.name")获取当前操作系统的名称，然后根据操作系统的名称返回windows、mac或linux属性中的ElPath实例。
    public ElPath getPath() {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith(ElConstant.WIN)) {
            return windows;
        } else if (os.toLowerCase().startsWith(ElConstant.MAC)) {
            return mac;
        }
        return linux;
    }

    // ElPath：这是一个静态内部类，用于封装与特定操作系统相关的文件路径信息。它包含两个属性：path和avatar，分别用于存储普通文件和头像文件的路径。
    @Data
    public static class ElPath {

        private String path;

        private String avatar;

    }

}
