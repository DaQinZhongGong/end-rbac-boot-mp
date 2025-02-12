package com.daqinzhonggong;

import io.swagger.annotations.Api;
import com.daqinzhonggong.annotation.rest.AnonymousGetMapping;
import com.daqinzhonggong.utils.SpringContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

/**
 * 开启审计功能 -> @EnableJpaAuditing
 *
 * @author free
 */
// 用于开启 Spring 的异步方法执行能力。当你在配置类（通常是一个用 @Configuration 注解标记的类）上添加 @EnableAsync 注解时，Spring 会为所有标记了 @Async 注解的方法创建一个异步执行的环境
@EnableAsync
@RestController
// 隐藏内部API，这些API不应该被外部消费者使用: 在Swagger/OpenAPI中，@Api注解用于标记一个Controller（在Spring MVC应用中），而hidden属性可以用来指示这个Controller不应该出现在自动生成的API文档中。
@Api(hidden = true)
@SpringBootApplication
// 通过启用声明式事务管理，开发者可以在不直接管理事务的情况下，通过注解或XML配置来控制事务的边界和行为
@EnableTransactionManagement
public class RbacBootMpSystemAppRun {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(RbacBootMpSystemAppRun.class);
        // 监控应用的PID，启动时可指定PID路径：--spring.pid.file=/home/java/app.pid
        // 或者在 application.yml 添加文件路径，方便 kill，kill `cat /home/java/app.pid`
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

    /**
     * 访问首页提示
     *
     * @return /
     */
    @AnonymousGetMapping("/")
    public String index() {
        return "rbac-boot-mp backend service started successfully";
    }

}
