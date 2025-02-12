package com.daqinzhonggong.aspect;

/**
 * 限流枚举
 * LimitType是一个简单的枚举，用于表示两种不同的限流策略：基于用户的限流和基于IP地址的限流。通过为每种策略分配一个唯一的枚举值，代码可以更加清晰、易于维护且类型安全。在实际应用中，开发者可以根据需求选择合适的限流策略，并通过LimitType枚举来指定它。
 *
 * @author free
 */
public enum LimitType {

    // 默认
    CUSTOMER,
    //  by ip addr
    IP

}
