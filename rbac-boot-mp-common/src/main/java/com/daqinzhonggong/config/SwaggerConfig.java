package com.daqinzhonggong.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于配置 Swagger 的 Spring 配置类，它使得应用程序能够提供 RESTful API 的文档化界面
 * api页面 /doc.html
 *
 * @author free
 */
// @Configuration：标识该类为配置类，Spring 容器会读取其内部的 Bean 定义。
@Configuration
// @EnableSwagger2：启用 Swagger 2 的自动配置。
@EnableSwagger2
public class SwaggerConfig {

    // tokenHeader：通过 @Value 注解从配置文件中读取 JWT 的 Header 名称
    @Value("${jwt.header}")
    private String tokenHeader;

    // enabled：通过 @Value 注解从配置文件中读取是否启用 Swagger 的标志。
    @Value("${swagger.enabled}")
    private Boolean enabled;

    // 该方法创建一个 Docket Bean，用于配置 Swagger
    @Bean
    @SuppressWarnings("all")
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // enable(enabled)：根据配置决定是否启用 Swagger
                .enable(enabled)
                // pathMapping("/")：设置基础路径映射
                .pathMapping("/")
                // apiInfo(apiInfo())：设置 API 的基本信息
                .apiInfo(apiInfo())
                // select().paths(...)：选择哪些路径的 API 文档被生成。这里使用了两个 paths 方法，其中 PathSelectors.regex("^(?!/error).*") 可能是为了排除错误路径，但紧接着的 PathSelectors.any() 实际上会覆盖前面的设置，选择所有路径。这里可能存在逻辑上的冗余或错误。
                .select()
                .paths(PathSelectors.regex("^(?!/error).*"))
                .paths(PathSelectors.any())
                .build()
                // 添加登陆认证:配置 API 的安全方案和安全上下文，用于添加登录认证信息。
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    // 该方法返回一个 ApiInfo 对象，用于设置 API 文档的基本信息，如描述、标题和版本
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .description("rbac-boot-mp 后台管理框架")
                .title("rbac-boot-mp 接口文档")
                .version("2023.01.21.mp")
                .build();
    }

    // 该方法返回一个 SecurityScheme 列表，这里添加了一个基于请求头的 API Key 安全方案，用于 API 的认证
    private List<SecurityScheme> securitySchemes() {
        // 设置请求头信息
        List<SecurityScheme> securitySchemes = new ArrayList<>();
        ApiKey apiKey = new ApiKey(tokenHeader, tokenHeader, "header");
        securitySchemes.add(apiKey);
        return securitySchemes;
    }

    // 该方法返回一个 SecurityContext 列表，用于配置哪些路径需要安全认证
    private List<SecurityContext> securityContexts() {
        // 设置需要登录认证的路径
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(getContextByPath());
        return securityContexts;
    }

    // 该方法创建一个 SecurityContext 对象，配置了需要认证的操作选择器和安全引用
    private SecurityContext getContextByPath() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                // 表示 /auth/code、/auth/login 接口不需要使用securitySchemes即不需要带token
                .operationSelector(o -> o.requestMappingPattern().matches("^(?!/auth/code|/auth/login).*$"))
                .build();
    }

    // 该方法返回一个 SecurityReference 列表，定义了认证的全局作用域和权限
    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> securityReferences = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        securityReferences.add(new SecurityReference(tokenHeader, authorizationScopes));
        return securityReferences;
    }

}
