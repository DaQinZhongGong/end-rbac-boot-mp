package com.daqinzhonggong.exception;

import org.springframework.util.StringUtils;

/**
 * EntityNotFoundException是一个自定义的运行时异常类，用于表示实体未找到的异常情况。它通过一个构造方法接收实体类、字段名和字段值作为参数，并生成一个描述性的异常消息。这个异常消息通过调用generateMessage静态方法生成，该方法利用StringUtils.capitalize方法将实体类名称的首字母大写，以提高消息的可读性。此类设计简洁且实用，非常适合在处理数据库操作时，当尝试访问一个不存在的实体时抛出异常。
 *
 * @author free
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class clazz, String field, String val) {
        super(EntityNotFoundException.generateMessage(clazz.getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        // org.springframework.util.StringUtils: Spring框架提供的一个工具类，包含了许多处理字符串的静态方法。在这里，使用了StringUtils.capitalize方法来将字符串的首字母大写。
        return StringUtils.capitalize(entity)
                + " with " + field + " " + val + " does not exist";
    }

}