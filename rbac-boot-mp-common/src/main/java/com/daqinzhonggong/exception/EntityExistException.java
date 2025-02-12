package com.daqinzhonggong.exception;

import org.springframework.util.StringUtils;

/**
 * EntityExistException是一个自定义的运行时异常类，用于表示实体已存在的异常情况。它通过一个构造方法接收实体类、字段名和字段值作为参数，并生成一个描述性的异常消息。这个异常消息通过调用generateMessage静态方法生成，该方法利用StringUtils.capitalize方法将实体类名称的首字母大写，以提高消息的可读性。此类设计简洁且实用，非常适合在处理数据库操作时，当尝试插入或更新一个已存在的实体时抛出异常。
 *
 * @author free
 */
public class EntityExistException extends RuntimeException {

    // 功能: 根据传入的实体类名称、字段名和字段值，生成一个描述性的异常消息。这里使用了StringUtils.capitalize方法将实体类名称的首字母大写，以提高消息的可读性。生成的消息格式为：“[实体类名] with [字段名] [字段值] does not exist”
    public EntityExistException(Class clazz, String field, String val) {
        super(EntityExistException.generateMessage(clazz.getSimpleName(), field, val));
    }

    // 功能: 根据传入的实体类名称、字段名和字段值，生成一个描述性的异常消息。这里使用了StringUtils.capitalize方法将实体类名称的首字母大写，以提高消息的可读性。生成的消息格式为：“[实体类名] with [字段名] [字段值] existed”。
    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " " + val + " existed";
    }

}