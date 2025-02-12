package com.daqinzhonggong.modules.mnt.domain.vo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;


@Data
public class DatabaseQueryCriteria {

    private String name;

    private String jdbcUrl;

    private List<Timestamp> createTime;

}
