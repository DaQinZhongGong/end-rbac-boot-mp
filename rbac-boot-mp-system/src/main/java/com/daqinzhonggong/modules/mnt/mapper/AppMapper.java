package com.daqinzhonggong.modules.mnt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.daqinzhonggong.modules.mnt.domain.App;
import com.daqinzhonggong.modules.mnt.domain.vo.AppQueryCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AppMapper extends BaseMapper<App> {

    IPage<App> queryAll(@Param("criteria") AppQueryCriteria criteria, Page<Object> page);

    List<App> queryAll(@Param("criteria") AppQueryCriteria criteria);

}
