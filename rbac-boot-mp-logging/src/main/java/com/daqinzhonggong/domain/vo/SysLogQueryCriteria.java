package com.daqinzhonggong.domain.vo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * 日志查询类
 *
 * @author free
 */
@Data
public class SysLogQueryCriteria {

    private String blurry;

    private String username;

    private String logType;

    private List<Timestamp> createTime;

}
