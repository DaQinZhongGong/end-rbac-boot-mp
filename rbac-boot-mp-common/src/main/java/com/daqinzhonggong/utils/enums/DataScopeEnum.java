package com.daqinzhonggong.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DataScopeEnum是一个枚举类，用于定义数据权限的类型。通过定义枚举常量，可以方便地管理不同的数据权限类型及其对应的值和描述信息。此外，通过提供find静态方法，可以方便地根据值查找对应的枚举常量。Lombok注解的使用简化了代码编写，自动生成了getter方法和构造方法，提高了开发效率。这个枚举类在实际应用中可能用于权限控制，以限制用户对不同数据范围的访问权限。
 *
 * @author free
 */
@Getter
@AllArgsConstructor
public enum DataScopeEnum {

    // 表示全部的数据权限，对应的值为"全部"
    ALL("全部", "全部的数据权限"),

    // 表示自己部门的数据权限，对应的值为"本级"
    THIS_LEVEL("本级", "自己部门的数据权限"),

    // 表示自定义的数据权限，对应的值为"自定义"
    CUSTOMIZE("自定义", "自定义的数据权限");

    // 表示每个枚举常量对应的值
    private final String value;
    // 表示每个枚举常量对应的描述信息
    private final String description;

    // 功能: 根据传入的值（val），遍历所有枚举常量，找到对应的枚举常量并返回。如果找不到对应的枚举常量，则返回null。
    // 用途: 这个方法提供了一种便捷的方式来根据值查找对应的枚举常量，而不需要手动编写复杂的条件判断逻辑。
    public static DataScopeEnum find(String val) {
        for (DataScopeEnum dataScopeEnum : DataScopeEnum.values()) {
            if (dataScopeEnum.getValue().equals(val)) {
                return dataScopeEnum;
            }
        }
        return null;
    }

}
