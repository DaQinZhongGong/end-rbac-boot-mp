package com.daqinzhonggong.annotation.rest;

import com.daqinzhonggong.annotation.AnonymousAccess;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

/**
 * Annotation for mapping HTTP {@code GET} requests onto specific handler
 * methods.
 * <p>
 * 支持匿名访问   GetMapping
 * 代码是一个Java注解（Annotation），名为AnonymousGetMapping。这个注解的目的是将HTTP的GET请求映射到特定的处理器方法上，并且支持匿名访问。
 * AnonymousGetMapping是一个自定义的Spring MVC注解，它结合了@RequestMapping注解和@AnonymousAccess注解的功能，用于将HTTP的GET请求映射到支持匿名访问的处理器方法上。通过使用这个注解，开发者可以方便地创建支持匿名访问的RESTful API端点。
 *
 * @author free
 * @see RequestMapping
 */
// @AnonymousAccess：这是一个自定义注解，表明这个注解标记的方法支持匿名访问。即，用户无需进行身份验证即可访问该方法。
@AnonymousAccess
// @Target(ElementType.METHOD)：指定这个注解只能用于方法上。
@Target(ElementType.METHOD)
// @Retention(RetentionPolicy.RUNTIME)：指定这个注解在运行时仍然保留，可以通过反射机制读取。
@Retention(RetentionPolicy.RUNTIME)
// @Documented：指定这个注解会被javadoc工具记录，从而在生成的API文档中显示。
@Documented
// @RequestMapping(method = RequestMethod.GET)：这是一个Spring MVC的注解，表明这个注解标记的方法用于处理HTTP的GET请求。
@RequestMapping(method = RequestMethod.GET)
public @interface AnonymousGetMapping {

    // name()：这是一个别名属性，对应于@RequestMapping注解的name属性。它用于指定映射的名称，但在这个注解中默认值为空字符串。
    @AliasFor(annotation = RequestMapping.class)
    String name() default "";

    // value()和path()：这两个属性都是别名属性，分别对应于@RequestMapping注解的value和path属性。它们用于指定请求映射的路径。这两个属性可以互换使用，默认值为空数组。
    @AliasFor(annotation = RequestMapping.class)
    String[] value() default {};

    // value()和path()：这两个属性都是别名属性，分别对应于@RequestMapping注解的value和path属性。它们用于指定请求映射的路径。这两个属性可以互换使用，默认值为空数组。
    @AliasFor(annotation = RequestMapping.class)
    String[] path() default {};

    // params()：这是一个别名属性，对应于@RequestMapping注解的params属性。它用于指定请求必须包含的参数。默认值为空数组。
    @AliasFor(annotation = RequestMapping.class)
    String[] params() default {};

    // headers()：这是一个别名属性，对应于@RequestMapping注解的headers属性。它用于指定请求必须包含的请求头。默认值为空数组。
    @AliasFor(annotation = RequestMapping.class)
    String[] headers() default {};

    // consumes()：这是一个别名属性，对应于@RequestMapping注解的consumes属性。它用于指定处理请求的提交内容类型（Content-Type）。这个属性自Spring 4.3.5版本开始可用，默认值为空数组。
    @AliasFor(annotation = RequestMapping.class)
    String[] consumes() default {};

    // produces()：这是一个别名属性，对应于@RequestMapping注解的produces属性。它用于指定返回的内容类型。默认值为空数组。
    @AliasFor(annotation = RequestMapping.class)
    String[] produces() default {};

}
