package com.daqinzhonggong.modules.mnt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.daqinzhonggong.modules.mnt.domain.DeployHistory;
import com.daqinzhonggong.modules.mnt.domain.vo.DeployHistoryQueryCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeployHistoryMapper extends BaseMapper<DeployHistory> {

    IPage<DeployHistory> findAll(@Param("criteria") DeployHistoryQueryCriteria criteria, Page<Object> page);

    List<DeployHistory> findAll(@Param("criteria") DeployHistoryQueryCriteria criteria);

}
