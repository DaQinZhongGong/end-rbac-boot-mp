package com.daqinzhonggong.annotation;

import com.daqinzhonggong.aspect.LimitType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解是一个用于限制方法访问频率的自定义注解。通过指定资源名称、key、前缀、时间周期、访问次数和限制类型等属性，它可以有效地控制对特定接口的访问。这个注解通常与AOP技术结合使用，在方法执行前后进行拦截和检查，以确保访问频率符合预设的限制条件。
 *
 * @author free
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {

    // 资源名称，用于描述接口功能
    String name() default "";

    // 资源 key
    String key() default "";

    // key prefix
    String prefix() default "";

    // 时间的，单位秒
    int period();

    // 限制访问次数
    int count();

    // 限制类型
    LimitType limitType() default LimitType.CUSTOMER;

}
