package com.daqinzhonggong.modules.mnt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.daqinzhonggong.modules.mnt.domain.Database;
import com.daqinzhonggong.modules.mnt.domain.vo.DatabaseQueryCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DatabaseMapper extends BaseMapper<Database> {

    IPage<Database> findAll(@Param("criteria") DatabaseQueryCriteria criteria, Page<Object> page);

    List<Database> findAll(@Param("criteria") DatabaseQueryCriteria criteria);

}
