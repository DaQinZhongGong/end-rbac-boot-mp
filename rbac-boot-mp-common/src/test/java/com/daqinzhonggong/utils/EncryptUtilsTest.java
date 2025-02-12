package com.daqinzhonggong.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static com.daqinzhonggong.utils.EncryptUtils.desDecrypt;
import static com.daqinzhonggong.utils.EncryptUtils.desEncrypt;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class EncryptUtilsTest {

    /**
     * 对称加密
     */
    @Test
    public void testDesEncrypt() {
        try {
            assertEquals("7772841DC6099402", desEncrypt("123456"));
        } catch (Exception e) {
            log.error("加密失败", e);
        }
    }

    /**
     * 对称解密
     */
    @Test
    public void testDesDecrypt() {
        try {
            assertEquals("123456", desDecrypt("7772841DC6099402"));
        } catch (Exception e) {
            log.error("解密失败", e);
        }
    }
}
