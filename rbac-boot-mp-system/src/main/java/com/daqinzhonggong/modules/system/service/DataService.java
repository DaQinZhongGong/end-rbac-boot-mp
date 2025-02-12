package com.daqinzhonggong.modules.system.service;

import com.daqinzhonggong.modules.system.domain.User;

import java.util.List;

public interface DataService {

    /**
     * 获取数据权限
     *
     * @param user /
     * @return /
     */
    List<Long> getDeptIds(User user);

}
