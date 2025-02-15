package com.daqinzhonggong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.daqinzhonggong.domain.LocalStorage;
import com.daqinzhonggong.domain.vo.LocalStorageQueryCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LocalStorageMapper extends BaseMapper<LocalStorage> {

    IPage<LocalStorage> findAll(@Param("criteria") LocalStorageQueryCriteria criteria, Page<Object> page);

    List<LocalStorage> findAll(@Param("criteria") LocalStorageQueryCriteria criteria);

}
