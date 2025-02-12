package com.daqinzhonggong.modules.system.mapper;

import com.daqinzhonggong.modules.system.domain.Job;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

@Mapper
public interface UserJobMapper {

    void insertData(@Param("userId") Long userId, @Param("jobs") Set<Job> jobs);

    void deleteByUserId(@Param("userId") Long userId);

    void deleteByUserIds(@Param("userIds") Set<Long> userIds);

}
