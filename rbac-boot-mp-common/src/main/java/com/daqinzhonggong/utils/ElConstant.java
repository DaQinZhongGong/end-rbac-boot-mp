package com.daqinzhonggong.utils;

/**
 * 操作系统识别: 在编写跨平台应用程序时，可能需要根据操作系统的不同来执行不同的代码逻辑。这时，可以使用ElConstant类中定义的WIN和MAC常量来识别当前操作系统。
 * <p>
 * 配置管理: 在一些配置文件中，可能需要根据操作系统来设置不同的配置参数。通过引用ElConstant类中的常量，可以使配置文件更加清晰易懂。
 * <p>
 * 日志记录: 在记录日志时，可能需要记录当前操作系统的信息。这时，可以使用WIN和MAC常量来标识操作系统类型。
 *
 * @author free
 */
public class ElConstant {

    // 这行代码定义了一个名为WIN的公共静态最终变量（常量），其类型为String，值为"win"。这个常量通常用于表示Windows操作系统。
    public static final String WIN = "win";

    // 这行代码定义了一个名为MAC的公共静态最终变量（常量），其类型为String，值为"mac"。这个常量通常用于表示macOS操作系统。
    public static final String MAC = "mac";

}
