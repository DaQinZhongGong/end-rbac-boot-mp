package com.daqinzhonggong.utils;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * DateUtil是一个功能强大的工具类，它提供了丰富的方法来处理Java 8中引入的新日期和时间API，以及与其他日期类型（如java.util.Date和java.sql.Timestamp）的互操作。通过使用这个工具类，可以简化日期和时间的处理代码，提高开发效率。
 *
 * @author free
 */
public class DateUtil {

    // 定义了日期时间格式为yyyy-MM-dd HH:mm:ss
    public static final DateTimeFormatter DFY_MD_HMS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    // 定义了日期格式为yyyy-MM-dd
    public static final DateTimeFormatter DFY_MD = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // 将LocalDateTime对象转换为时间戳（自1970年1月1日00:00:00 UTC以来的秒数）。
    public static Long getTimeStamp(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    // 将时间戳转换为LocalDateTime对象
    public static LocalDateTime fromTimeStamp(Long timeStamp) {
        return LocalDateTime.ofEpochSecond(timeStamp, 0, OffsetDateTime.now().getOffset());
    }

    // 将LocalDateTime对象转换为Date对象。注意，由于Date代表的是一个具体的瞬间，而LocalDateTime没有时区信息，因此在转换时需要指定时区（这里使用的是系统默认时区）
    // Jdk8 后 不推荐使用 {@link Date} Date
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    // 将LocalDate对象转换为Date对象。由于LocalDate只包含日期信息，不包含时间信息，因此在转换时会将时间部分设置为系统默认时区的当前时间。
    // Jdk8 后 不推荐使用 {@link Date} Date
    public static Date toDate(LocalDate localDate) {
        return toDate(localDate.atTime(LocalTime.now(ZoneId.systemDefault())));
    }


    // 将Date对象转换为LocalDateTime对象。同样，由于Date代表的是一个具体的瞬间，而LocalDateTime没有时区信息，因此在转换时需要指定时区（这里使用的是系统默认时区）
    // Jdk8 后 不推荐使用 {@link Date} Date
    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    // 日期 格式化 使用指定的模式（patten）将LocalDateTime对象格式化为字符串。
    public static String localDateTimeFormat(LocalDateTime localDateTime, String patten) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(patten);
        return df.format(localDateTime);
    }

    // 日期 格式化 使用指定的DateTimeFormatter对象将LocalDateTime对象格式化为字符串
    public static String localDateTimeFormat(LocalDateTime localDateTime, DateTimeFormatter df) {
        return df.format(localDateTime);
    }

    // 日期格式化 yyyy-MM-dd HH:mm:ss
    public static String localDateTimeFormatyMdHms(LocalDateTime localDateTime) {
        return DFY_MD_HMS.format(localDateTime);
    }

    // 获取当前时间/
    public static Timestamp getTimeStamp() {
        return Timestamp.valueOf(LocalDateTime.now());
    }

    // 日期格式化 yyyy-MM-dd
    public String localDateTimeFormatyMd(LocalDateTime localDateTime) {
        return DFY_MD.format(localDateTime);
    }

    // 字符串转 LocalDateTime ，字符串格式 yyyy-MM-dd
    public static LocalDateTime parseLocalDateTimeFormat(String localDateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.from(dateTimeFormatter.parse(localDateTime));
    }

    // 字符串转 LocalDateTime ，字符串格式 yyyy-MM-dd
    public static LocalDateTime parseLocalDateTimeFormat(String localDateTime, DateTimeFormatter dateTimeFormatter) {
        return LocalDateTime.from(dateTimeFormatter.parse(localDateTime));
    }

    // 字符串转 LocalDateTime ，字符串格式 yyyy-MM-dd HH:mm:ss
    public static LocalDateTime parseLocalDateTimeFormatyMdHms(String localDateTime) {
        return LocalDateTime.from(DFY_MD_HMS.parse(localDateTime));
    }

}
