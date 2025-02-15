package com.daqinzhonggong.modules.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import com.daqinzhonggong.base.BaseEntity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@TableName("sys_dict_detail")
public class DictDetail extends BaseEntity implements Serializable {

    @NotNull(groups = Update.class)
    @ApiModelProperty(value = "ID", hidden = true)
    @TableId(value = "detail_id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "dict_id")
    @ApiModelProperty(hidden = true)
    private Long dictId;

    @TableField(exist = false)
    private Dict dict;

    @ApiModelProperty(value = "字典标签")
    private String label;

    @ApiModelProperty(value = "字典值")
    private String value;

    @ApiModelProperty(value = "排序")
    private Integer dictSort = 999;

}