package com.daqinzhonggong.modules.mnt.domain.vo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;


@Data
public class DeployHistoryQueryCriteria {

    private String blurry;

    private Long deployId;

    private List<Timestamp> deployDate;

}
