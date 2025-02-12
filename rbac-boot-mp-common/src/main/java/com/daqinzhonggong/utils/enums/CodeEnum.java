package com.daqinzhonggong.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * CodeEnum是一个枚举类，用于定义验证码业务场景对应的Redis中的key。通过定义枚举常量，可以方便地管理不同业务场景下的Redis key前缀和描述信息。Lombok注解的使用简化了代码编写，自动生成了getter方法和构造方法，提高了开发效率。
 * <p>
 * 这个枚举类在实际应用中可能用于构建Redis key时作为前缀，以确保不同业务场景下的验证码数据能够存储在Redis中的不同位置，便于管理和区分。
 *
 * @author free
 */
@Getter
@AllArgsConstructor
public enum CodeEnum {

    // 表示通过手机号码重置邮箱的验证码业务场景，对应的Redis key前缀为phone_reset_email_code_。
    PHONE_RESET_EMAIL_CODE("phone_reset_email_code_", "通过手机号码重置邮箱"),

    // 表示通过旧邮箱重置邮箱的验证码业务场景，对应的Redis key前缀为email_reset_email_code_。
    EMAIL_RESET_EMAIL_CODE("email_reset_email_code_", "通过旧邮箱重置邮箱"),

    // 表示通过手机号码重置密码的验证码业务场景，对应的Redis key前缀为phone_reset_pwd_code_。
    PHONE_RESET_PWD_CODE("phone_reset_pwd_code_", "通过手机号码重置密码"),

    // 表示通过邮箱重置密码的验证码业务场景，对应的Redis key前缀为email_reset_pwd_code_。
    EMAIL_RESET_PWD_CODE("email_reset_pwd_code_", "通过邮箱重置密码");

    // 表示每个枚举常量对应的Redis key前缀
    private final String key;
    // 表示每个枚举常量对应的描述信息
    private final String description;

}
