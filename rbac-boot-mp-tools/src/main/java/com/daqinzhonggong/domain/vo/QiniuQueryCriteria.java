package com.daqinzhonggong.domain.vo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class QiniuQueryCriteria {

    private String key;

    private List<Timestamp> createTime;

}
