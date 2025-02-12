package com.daqinzhonggong.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * CodeBiEnum是一个枚举类，用于定义验证码业务场景。它包含两个枚举常量，每个常量都有一个唯一的代码和描述信息。通过@Getter和@AllArgsConstructor注解，Lombok自动为枚举类生成了getter方法和包含所有字段的构造方法。此外，还提供了一个静态的find方法，用于根据代码查找对应的枚举常量。
 *
 * @author free
 */
@Getter
@AllArgsConstructor
public enum CodeBiEnum {

    // 枚举类CodeBiEnum定义了两个枚举常量：ONE和TWO, 表示旧邮箱修改邮箱的业务场景，对应的代码为1
    ONE(1, "旧邮箱修改邮箱"),

    // 枚举类CodeBiEnum定义了两个枚举常量：ONE和TWO, 表示通过邮箱修改密码的业务场景，对应的代码为2
    TWO(2, "通过邮箱修改密码");

    // 表示每个枚举常量对应的代码
    private final Integer code;
    // 表示每个枚举常量对应的描述信息
    private final String description;

    // 功能: 根据传入的代码（code），遍历所有枚举常量，找到对应的枚举常量并返回。如果找不到对应的枚举常量，则返回null。
    public static CodeBiEnum find(Integer code) {
        for (CodeBiEnum value : CodeBiEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

}
