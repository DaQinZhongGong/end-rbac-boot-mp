package com.daqinzhonggong.config.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 这个配置类通常用于需要分页查询的Spring Boot项目中，特别是在使用MyBatis Plus作为ORM框架时。通过配置这个拦截器，开发者可以很方便地实现分页功能，而不需要手动编写分页SQL语句。
 * 配置流程
 * 1. 创建一个MybatisPlusInterceptor实例。
 * <p>
 * 2. 通过调用addInnerInterceptor方法，向MybatisPlusInterceptor实例中添加一个PaginationInnerInterceptor实例，并指定数据库类型为MYSQL。
 * <p>
 * 3. 将配置好的MybatisPlusInterceptor实例作为Bean注册到Spring容器中，这样MyBatis Plus在执行SQL查询时就会使用这个拦截器来实现分页功能。
 *
 * @author free
 **/
// @Configuration：这个注解表明MybatisPlusConfig类是一个配置类，Spring容器会在启动时自动扫描并加载这个类中的配置信息
@Configuration
public class MybatisPlusConfig {

    /**
     * MyBatisPlus拦截器（用于分页）
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        // MybatisPlusInterceptor是MyBatis Plus提供的一个拦截器容器，可以添加多个内部拦截器（InnerInterceptor）来实现不同的功能。在这个例子中，它用于添加分页功能。
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 根据使用的数据库类型，正确设置DbType的值。添加MySQL的分页拦截器
        // PaginationInnerInterceptor是MyBatis Plus提供的一个分页内部拦截器，它实现了分页查询的功能。在构造PaginationInnerInterceptor时，需要指定数据库的类型（DbType），以便它能够正确地生成分页SQL语句。在这个例子中，数据库的类型被指定为MYSQL。
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}
