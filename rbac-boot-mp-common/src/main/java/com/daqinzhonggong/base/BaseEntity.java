package com.daqinzhonggong.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Timestamp;

/**
 * 通用字段， is_del 根据需求自行添加
 * 这个类被设计为实体类的基类，提供了一些通用的字段和方法
 *
 * @author free
 */
@Getter
@Setter
public class BaseEntity implements Serializable {

    // createBy：用于记录创建实体的用户。它被标记为在插入记录时自动填充（通过@TableField(fill = FieldFill.INSERT)），并且在使用Swagger生成API文档时会被隐藏（通过@ApiModelProperty(hidden = true)）。
    // FieldFill枚举来自MyBatis Plus框架，它用于指定字段的自动填充策略。但是，为了使自动填充功能正常工作，通常还需要配置一个实现MetaObjectHandler接口的类来提供填充逻辑。
    // ApiModelProperty注解来自Swagger框架，用于控制API文档的生成。hidden = true属性表示在生成的API文档中隐藏该字段。
    @CreatedBy
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建人", hidden = true)
    private String createBy;

    // updateBy：用于记录最后更新实体的用户。它被标记为在插入或更新记录时自动填充（通过@TableField(fill = FieldFill.INSERT_UPDATE)），并且在使用Swagger生成API文档时会被隐藏。
    @LastModifiedBy
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新人", hidden = true)
    private String updateBy;

    // createTime：用于记录实体的创建时间。它被标记为在插入记录时自动填充，并且在使用Swagger生成API文档时会被隐藏。
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Timestamp createTime;

    // updateTime：用于记录实体的最后更新时间。它被标记为在插入或更新记录时自动填充，并且在使用Swagger生成API文档时会被隐藏。
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间", hidden = true)
    private Timestamp updateTime;

    // 分组校验 - Create和Update：这两个是自定义的注解，用于分组校验。在实际使用中，它们可能与其他验证注解（如@NotNull、@Size等）结合使用，以指定在创建或更新实体时应应用的验证规则。
    public @interface Create {
    }

    // 分组校验 - Create和Update：这两个是自定义的注解，用于分组校验。在实际使用中，它们可能与其他验证注解（如@NotNull、@Size等）结合使用，以指定在创建或更新实体时应应用的验证规则。
    public @interface Update {
    }

    // BaseEntity类重写了Object类的toString方法，使用ToStringBuilder来生成一个包含所有字段名称和值的字符串表示。这在调试和日志记录时非常有用。
    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (Field f : fields) {
                f.setAccessible(true);
                builder.append(f.getName(), f.get(this)).append("\n");
            }
        } catch (Exception e) {
            builder.append("toString builder encounter an error");
        }
        return builder.toString();
    }

}
