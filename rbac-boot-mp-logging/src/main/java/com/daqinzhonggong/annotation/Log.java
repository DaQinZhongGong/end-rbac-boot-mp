package com.daqinzhonggong.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 这个元注解（meta-annotation）指定了Log注解可以应用的Java元素类型。在这里，它指定Log注解只能应用于方法（ElementType.METHOD）。
@Target(ElementType.METHOD)
// 这个元注解指定了Log注解的保留策略。RetentionPolicy.RUNTIME表示注解不仅被保留在.class文件中，而且在运行时可以通过反射被访问。
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    // Log注解具有一个名为value的属性，用于存储额外的信息，如日志消息。
    String value() default "";

}
