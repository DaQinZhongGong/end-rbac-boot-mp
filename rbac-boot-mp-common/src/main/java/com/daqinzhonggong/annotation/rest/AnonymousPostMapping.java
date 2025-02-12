package com.daqinzhonggong.annotation.rest;

import com.daqinzhonggong.annotation.AnonymousAccess;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

/**
 * Annotation for mapping HTTP {@code POST} requests onto specific handler
 * methods.
 * 支持匿名访问 PostMapping
 * AnonymousPostMapping是一个结合了Spring MVC的@RequestMapping注解和自定义的@AnonymousAccess注解的自定义注解。它用于将HTTP的POST请求映射到支持匿名访问的处理器方法上，从而方便地创建RESTful API端点，允许匿名用户提交数据或创建资源。
 *
 * @author free
 * @see AnonymousGetMapping
 * @see AnonymousPostMapping
 * @see AnonymousPutMapping
 * @see AnonymousDeleteMapping
 * @see RequestMapping
 */
// @AnonymousAccess：这是一个自定义注解，表明被注解的方法支持匿名访问。
@AnonymousAccess
// @Target(ElementType.METHOD)：指定这个注解只能用于方法上。
@Target(ElementType.METHOD)
// @Retention(RetentionPolicy.RUNTIME)：表明这个注解在运行时仍然保留，可以通过反射机制读取。
@Retention(RetentionPolicy.RUNTIME)
// @Documented：指示这个注解应该被javadoc工具记录。
@Documented
// @RequestMapping(method = RequestMethod.POST)：这是Spring MVC的@RequestMapping注解，用于将HTTP请求映射到处理器方法上。这里指定了method = RequestMethod.POST，表示该方法处理POST请求。
@RequestMapping(method = RequestMethod.POST)
public @interface AnonymousPostMapping {

    // name()：这是@RequestMapping注解中name属性的别名，用于指定映射的名称。默认值为空字符串。
    @AliasFor(annotation = RequestMapping.class)
    String name() default "";

    // value()和path()：这两个属性是@RequestMapping注解中value和path属性的别名，用于指定请求映射的路径。它们可以互换使用，默认值为空数组。
    @AliasFor(annotation = RequestMapping.class)
    String[] value() default {};

    // value()和path()：这两个属性是@RequestMapping注解中value和path属性的别名，用于指定请求映射的路径。它们可以互换使用，默认值为空数组。
    @AliasFor(annotation = RequestMapping.class)
    String[] path() default {};

    // params()：这是@RequestMapping注解中params属性的别名，用于指定请求必须包含的参数。默认值为空数组。
    @AliasFor(annotation = RequestMapping.class)
    String[] params() default {};

    // headers()：这是@RequestMapping注解中headers属性的别名，用于指定请求必须包含的请求头。默认值为空数组。
    @AliasFor(annotation = RequestMapping.class)
    String[] headers() default {};

    // consumes()：这是@RequestMapping注解中consumes属性的别名，用于指定处理请求的提交内容类型（Content-Type）。默认值为空数组。
    @AliasFor(annotation = RequestMapping.class)
    String[] consumes() default {};

    // produces()：这是@RequestMapping注解中produces属性的别名，用于指定返回的内容类型。默认值为空数组。
    @AliasFor(annotation = RequestMapping.class)
    String[] produces() default {};

}
