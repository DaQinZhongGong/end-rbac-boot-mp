package com.daqinzhonggong.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * ThrowableUtil类提供了一个方便的方法来获取异常的堆栈跟踪信息，这在日志记录和调试时非常有用
 *
 * @author free
 */
public class ThrowableUtil {

    /**
     * 获取堆栈信息:
     * 定义了一个公共静态方法getStackTrace，它接受一个Throwable对象作为参数（通常是异常或错误）。方法内部，它创建了一个StringWriter实例来捕获输出流，然后使用PrintWriter将Throwable对象的堆栈跟踪信息写入到这个字符串流中。try-with-resources语句确保PrintWriter在使用后会被自动关闭。最后，方法返回包含堆栈跟踪信息的字符串。
     */
    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }

}
