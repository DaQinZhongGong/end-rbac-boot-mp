package com.daqinzhonggong.modules.mnt.domain.vo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class DeployQueryCriteria {

    private String appName;

    private List<Timestamp> createTime;

    private Long offset;

    private Long size;

}
