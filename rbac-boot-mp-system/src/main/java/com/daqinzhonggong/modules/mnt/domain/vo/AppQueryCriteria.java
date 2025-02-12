package com.daqinzhonggong.modules.mnt.domain.vo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;


@Data
public class AppQueryCriteria {

    private String name;

    private List<Timestamp> createTime;

}
