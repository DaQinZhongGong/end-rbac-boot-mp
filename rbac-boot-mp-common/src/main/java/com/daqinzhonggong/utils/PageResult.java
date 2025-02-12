package com.daqinzhonggong.utils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * PageResult<T>是一个泛型类，用于表示分页查询的结果。它使用Lombok注解简化了代码编写，通过自动生成getter方法和构造器来减少重复代码。该类包含两个final类型的成员变量：content用于存储分页查询的结果列表，totalElements用于存储总记录数。这些信息对于前端展示分页数据和实现分页逻辑非常重要。
 *
 * @param <T>
 */
@Getter
// Lombok提供的注解，用于自动生成一个包含所有final字段和@NonNull注解字段的构造器。这里的access = AccessLevel.PACKAGE表示这个构造器的访问级别是包级别，即只能在同一个包内的类访问这个构造器。
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class PageResult<T> {

    // 这是一个final类型的成员变量，表示分页查询的结果列表。由于它是final的，所以一旦在构造器中初始化之后，就不能再被修改。
    private final List<T> content;
    // 这也是一个final类型的成员变量，表示分页查询结果的总记录数。同样，一旦在构造器中初始化之后，就不能再被修改。
    private final long totalElements;

}
