package com.daqinzhonggong.domain.vo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class LocalStorageQueryCriteria {

    private String blurry;

    private List<Timestamp> createTime;

}