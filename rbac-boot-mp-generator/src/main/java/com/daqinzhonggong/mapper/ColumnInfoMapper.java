package com.daqinzhonggong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.daqinzhonggong.domain.ColumnInfo;
import com.daqinzhonggong.domain.vo.TableInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 查询表信息
 *
 * @author free
 */
@Mapper
public interface ColumnInfoMapper extends BaseMapper<ColumnInfo> {

    IPage<TableInfo> getTables(@Param("tableName") String tableName, Page<Object> page);

    List<ColumnInfo> findByTableNameOrderByIdAsc(@Param("tableName") String tableName);

    List<ColumnInfo> getColumns(@Param("tableName") String tableName);

}
