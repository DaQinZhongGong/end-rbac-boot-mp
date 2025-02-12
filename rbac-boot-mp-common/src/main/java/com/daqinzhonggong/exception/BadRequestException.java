package com.daqinzhonggong.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * BadRequestException是一个自定义的运行时异常类，用于处理HTTP请求中的错误情况。它提供了两个构造方法，允许在创建异常实例时指定错误消息和可选的HTTP状态码。通过继承RuntimeException，BadRequestException可以方便地在代码中被抛出和处理。此外，@Getter注解自动生成了status成员变量的getter方法，使得外部可以方便地获取异常对应的HTTP状态码。
 *
 * @author free
 */
@Getter
public class BadRequestException extends RuntimeException {

    // status: private Integer status = BAD_REQUEST.value(); 定义了一个私有成员变量status，并初始化为HTTP状态码400（即BAD_REQUEST的值）。这个变量用于存储异常对应的HTTP状态码。
    private Integer status = BAD_REQUEST.value();

    // 功能: 接收一个字符串参数msg作为错误消息，并调用父类RuntimeException的构造方法将其设置为异常的消息。此时，status成员变量保持默认值400。
    public BadRequestException(String msg) {
        super(msg);
    }

    // 功能: 接收一个HttpStatus枚举和一个字符串参数msg。HttpStatus参数用于指定异常对应的HTTP状态码，msg参数作为错误消息。构造方法首先调用父类RuntimeException的构造方法设置异常消息，然后将status成员变量设置为传入的HttpStatus值的整数值。
    public BadRequestException(HttpStatus status, String msg) {
        super(msg);
        this.status = status.value();
    }

}
