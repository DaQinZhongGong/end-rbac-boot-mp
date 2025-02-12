package com.daqinzhonggong.modules.mnt.domain.vo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ServerQueryCriteria {

    private String blurry;

    private List<Timestamp> createTime;

}
