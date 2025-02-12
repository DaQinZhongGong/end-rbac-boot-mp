package com.daqinzhonggong.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * RequestMethodEnum是一个枚举类，用于定义HTTP请求的方法类型。通过定义枚举常量，可以方便地管理不同的HTTP请求方法。此外，通过提供find静态方法，可以方便地根据HTTP请求方法类型查找对应的枚举常量。如果传入的方法类型不在枚举中定义，则默认返回ALL枚举常量，表示所有请求方法都放行。Lombok注解的使用简化了代码编写，自动生成了getter方法和构造方法，提高了开发效率。这个枚举类在实际应用中可能用于权限控制、路由匹配等场景，以根据HTTP请求方法类型进行不同的处理。
 *
 * @author free
 **/
@Getter
@AllArgsConstructor
public enum RequestMethodEnum {

    // 表示GET请求方法, 搜寻 @AnonymousGetMapping
    GET("GET"),

    // 表示POST请求方法, 搜寻 @AnonymousPostMapping
    POST("POST"),

    // 表示PUT请求方法, 搜寻 @AnonymousPutMapping
    PUT("PUT"),

    // 表示PATCH请求方法, 搜寻 @AnonymousPatchMapping
    PATCH("PATCH"),

    // 表示DELETE请求方法, 搜寻 @AnonymousDeleteMapping
    DELETE("DELETE"),

    // 表示所有请求方法都放行, 否则就是所有 Request 接口都放行
    ALL("All");

    // 表示每个枚举常量对应的HTTP请求方法类型
    private final String type;

    // 功能: 根据传入的HTTP请求方法类型（type），遍历所有枚举常量，找到对应的枚举常量并返回。如果找不到对应的枚举常量，则返回ALL枚举常量。
    // 用途: 这个方法提供了一种便捷的方式来根据HTTP请求方法类型查找对应的枚举常量，如果传入的方法类型不在枚举中定义，则默认返回ALL，表示所有请求方法都放行。
    public static RequestMethodEnum find(String type) {
        for (RequestMethodEnum value : RequestMethodEnum.values()) {
            if (value.getType().equals(type)) {
                return value;
            }
        }
        return ALL;
    }

}
