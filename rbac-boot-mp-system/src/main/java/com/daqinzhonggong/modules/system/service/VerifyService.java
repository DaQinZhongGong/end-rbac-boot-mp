package com.daqinzhonggong.modules.system.service;

import com.daqinzhonggong.domain.vo.EmailVo;

public interface VerifyService {

    /**
     * 发送验证码
     *
     * @param email /
     * @param key   /
     * @return /
     */
    EmailVo sendEmail(String email, String key);


    /**
     * 验证
     *
     * @param code /
     * @param key  /
     */
    void validated(String key, String code);

}
