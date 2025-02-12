package com.daqinzhonggong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daqinzhonggong.domain.GenConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 查询表配置
 *
 * @author free
 */
@Mapper
public interface GenConfigMapper extends BaseMapper<GenConfig> {

    GenConfig findByTableName(@Param("tableName") String tableName);

}
