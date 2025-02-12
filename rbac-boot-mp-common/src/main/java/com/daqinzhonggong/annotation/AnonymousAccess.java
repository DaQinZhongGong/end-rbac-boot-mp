package com.daqinzhonggong.annotation;

import java.lang.annotation.*;

/**
 * 用于标记匿名访问方法
 * 是一个自定义注解，用于标记允许匿名访问的方法。通过与Spring Security或其他安全框架结合使用，它可以方便地控制哪些方法不需要身份验证即可访问。这个注解的元注解定义了它的继承性、文档记录、应用目标以及保留策略，从而确保了它在Java应用中的灵活性和实用性。
 *
 * @author free
 */
// @Inherited：这个元注解表明@AnonymousAccess注解具有继承性。如果一个类使用了@AnonymousAccess注解，那么它的子类也会自动继承这个注解（如果子类没有显式地覆盖它）。
@Inherited
// @Documented：这个元注解表明@AnonymousAccess注解应该被javadoc工具记录。这样，在生成API文档时，包含@AnonymousAccess注解的方法会被特别标注出来。
@Documented
// @Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})：这个元注解指定了@AnonymousAccess注解可以应用的Java元素类型。在这个例子中，它可以被应用于方法（METHOD）和注解类型（ANNOTATION_TYPE）。后者允许创建自定义注解，这些自定义注解可以进一步标记匿名访问的方法。
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
// @Retention(RetentionPolicy.RUNTIME)：这个元注解指定了@AnonymousAccess注解的保留策略。RUNTIME意味着这个注解在运行时仍然可用，可以通过反射机制读取。这对于在运行时动态检查方法是否允许匿名访问非常有用。
@Retention(RetentionPolicy.RUNTIME)
public @interface AnonymousAccess {

}
