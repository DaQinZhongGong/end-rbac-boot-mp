package com.daqinzhonggong.config.mybatis;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.daqinzhonggong.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * 代码定义了一个名为MyMetaObjectHandler的类，它实现了MyBatis Plus的MetaObjectHandler接口。这个类的目的是在插入或更新数据库记录时，自动填充一些通用字段，如创建时间、更新时间和操作人。
 *
 * @author free
 **/
// @Component：这个注解表明MyMetaObjectHandler是一个Spring组件，Spring容器会自动检测并注册这个类的实例。由于它实现了MetaObjectHandler接口，MyBatis Plus框架会自动检测到它，并在执行插入或更新操作时调用它的方法。
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 这个方法会在插入记录之前被调用。它做了以下几件事情：
     * <p>
     * 使用strictInsertFill方法自动填充createTime和updateTime字段为当前时间（DateTime.now().toTimestamp()）。
     * <p>
     * 尝试获取当前操作人的用户名（通过调用SecurityUtils.getCurrentUsername()方法），如果获取失败（例如抛出异常），则默认使用"System"作为操作人。然后，使用strictInsertFill方法自动填充createBy和updateBy字段为当前操作人的用户名。
     *
     * @param metaObject MetaObject是MyBatis的一个内部类，用于封装MyBatis对象的元数据。通过这个参数，我们可以访问和操作对象的属性。
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        /* 创建时间 */
        this.strictInsertFill(metaObject, "createTime", Timestamp.class, DateTime.now().toTimestamp());
        this.strictInsertFill(metaObject, "updateTime", Timestamp.class, DateTime.now().toTimestamp());
        /* 操作人 */
        String username = "System";
        try {
            username = SecurityUtils.getCurrentUsername();
        } catch (Exception ignored) {
        }
        this.strictInsertFill(metaObject, "createBy", String.class, username);
        this.strictInsertFill(metaObject, "updateBy", String.class, username);
    }

    /**
     * 这个方法会在更新记录之前被调用。它做了以下几件事情：
     * <p>
     * 使用strictUpdateFill方法自动填充updateTime字段为当前时间。
     * <p>
     * 尝试获取当前操作人的用户名，如果获取失败，则默认使用"System"。然后，使用strictUpdateFill方法自动填充updateBy字段为当前操作人的用户名。
     *
     * @param metaObject MetaObject是MyBatis的一个内部类，用于封装MyBatis对象的元数据。通过这个参数，我们可以访问和操作对象的属性。
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        /* 更新时间 */
        this.strictUpdateFill(metaObject, "updateTime", Timestamp.class, DateTime.now().toTimestamp());
        /* 操作人 */
        String username = "System";
        try {
            username = SecurityUtils.getCurrentUsername();
        } catch (Exception ignored) {
        }
        this.strictUpdateFill(metaObject, "updateBy", String.class, username);
    }

}

