package com.daqinzhonggong.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;

/**
 * 当需要对敏感数据进行加密存储或传输时，可以使用EncryptUtils类提供的加密和解密功能。
 *
 * @author free
 */
public class EncryptUtils {

    /**
     * 注意事项
     * DES算法是一种较老的加密算法，其密钥长度较短（56位），可能不再满足现代安全需求。建议考虑使用更安全的加密算法，如AES。
     * <p>
     * 密钥和初始化向量硬编码在代码中可能带来安全风险。在实际应用中，应考虑使用更安全的方式来管理密钥和初始化向量。
     * <p>
     * 在进行加密和解密操作时，需要确保输入数据的格式和长度符合要求，以避免出现异常或错误结果。
     */

    // 这是一个静态常量，用作生成IvParameterSpec（初始化向量）和DESKeySpec（DES密钥规范）的字节数组的来源。
    private static final String STR_PARAM = "Passw0rd";
    // 这是一个Cipher对象，用于执行加密和解密操作。
    private static Cipher cipher;
    // 这是一个初始化向量，用于DES/CBC模式下的加密和解密。初始化向量必须是8字节长。
    private static final IvParameterSpec IV = new IvParameterSpec(STR_PARAM.getBytes(StandardCharsets.UTF_8));

    // 功能: 根据给定的字符串（source）生成一个DESKeySpec对象。如果source为空或长度为0，则返回null。
    // 实现: 使用STR_PARAM作为密钥，通过DESKeySpec构造函数生成密钥规范。
    private static DESKeySpec getDesKeySpec(String source) throws Exception {
        if (source == null || source.length() == 0) {
            return null;
        }
        cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        String strKey = "Passw0rd";
        return new DESKeySpec(strKey.getBytes(StandardCharsets.UTF_8));
    }

    // 功能: 对给定的字符串（source）进行DES加密
    public static String desEncrypt(String source) throws Exception {
        // 1. 调用getDesKeySpec方法获取DESKeySpec对象。
        DESKeySpec desKeySpec = getDesKeySpec(source);
        // 2. 使用SecretKeyFactory生成SecretKey对象。
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 3. 初始化cipher对象为加密模式。
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        // 4. 对source字符串进行加密。
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, IV);
        // 5. 将加密后的字节数组转换为16进制字符串并返回。
        return byte2hex(
                cipher.doFinal(source.getBytes(StandardCharsets.UTF_8))).toUpperCase();
    }

    // 对给定的十六进制字符串（source）进行DES解密
    public static String desDecrypt(String source) throws Exception {
        // 1. 将输入的十六进制字符串转换为字节数组。
        byte[] src = hex2byte(source.getBytes(StandardCharsets.UTF_8));
        // 2. 调用getDesKeySpec方法获取DESKeySpec对象。
        DESKeySpec desKeySpec = getDesKeySpec(source);
        // 3. 使用SecretKeyFactory生成SecretKey对象。
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 4. 初始化cipher对象为解密模式。
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        // 5. 对字节数组进行解密。
        cipher.init(Cipher.DECRYPT_MODE, secretKey, IV);
        // 6. 将解密后的字节数组转换为字符串并返回。
        byte[] retByte = cipher.doFinal(src);
        return new String(retByte);
    }

    // 功能: 将字节数组转换为十六进制字符串。
    private static String byte2hex(byte[] inStr) {
        // 实现: 遍历字节数组，将每个字节转换为两位十六进制字符串，并拼接起来。
        String stmp;
        StringBuilder out = new StringBuilder(inStr.length * 2);
        for (byte b : inStr) {
            stmp = Integer.toHexString(b & 0xFF);
            if (stmp.length() == 1) {
                // 如果是0至F的单位字符串，则添加0
                out.append("0").append(stmp);
            } else {
                out.append(stmp);
            }
        }
        return out.toString();
    }

    // 功能: 将十六进制字符串（以字节数组形式给出）转换为字节数组。
    private static byte[] hex2byte(byte[] b) {
        // 实现: 遍历字节数组，每两个字节表示一个十六进制数，将其转换为对应的字节，并存储在新的字节数组中。
        int size = 2;
        if ((b.length % size) != 0) {
            throw new IllegalArgumentException("长度不是偶数");
        }
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += size) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }

}
