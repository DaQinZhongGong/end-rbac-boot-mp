package com.daqinzhonggong.utils;

import java.io.Closeable;

/**
 * CloseUtil是一个工具类，提供了两个静态方法来安全地关闭实现了Closeable或AutoCloseable接口的对象。这两个方法通过检查null值和静默处理异常来简化资源关闭的过程。在实际编程中，这样的工具类可以帮助开发者避免资源泄露和提高代码的健壮性。
 *
 * @author free
 **/
public class CloseUtil {

    // 参数: Closeable closeable，这是一个实现了Closeable接口的对象，需要被关闭。
    // 功能: 检查传入的closeable对象是否为null，如果不是，则尝试调用其close方法以释放资源。如果在关闭过程中发生异常，则捕获异常并不做任何处理（静默关闭）。
    public static void close(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (Exception e) {
                // 静默关闭
            }
        }
    }

    // 参数: AutoCloseable closeable，这是一个实现了AutoCloseable接口的对象，需要被关闭。
    // 功能: 与上一个方法类似，但这个方法接受的是实现了AutoCloseable接口的对象。AutoCloseable是Closeable的子接口，它在Java 7中引入，用于简化资源管理（例如，在try-with-resources语句中使用）。
    // 注意: 在实际编程中，由于Closeable接口已经包含了AutoCloseable接口的所有方法，因此通常只需要实现或重载一个方法即可。这里的两个方法可能是为了保持与旧代码的兼容性或为了明确区分。
    public static void close(AutoCloseable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (Exception e) {
                // 静默关闭
            }
        }
    }

}
