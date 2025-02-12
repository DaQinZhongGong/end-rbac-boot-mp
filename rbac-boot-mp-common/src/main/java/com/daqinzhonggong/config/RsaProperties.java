package com.daqinzhonggong.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 用于配置RSA加密所需的私钥
 *
 * @author free
 **/
@Data
@Component
public class RsaProperties {

    // 这是一个公开的静态字符串属性，用于存储RSA加密的私钥。由于它是静态的，所以它的值将在整个应用程序的生命周期内保持不变，所以它的值将在整个应用程序中是共享的。这意味着一旦RsaProperties的Bean被创建并设置了私钥值，这个值就无法再被更改了。
    public static String privateKey;

    // 这个注解用于将配置文件中rsa.private_key属性的值注入到setPrivateKey方法的参数中。这样，当Spring容器创建RsaProperties的Bean时，它会自动调用setPrivateKey方法，并将配置文件中指定的私钥值传递给它。
    @Value("${rsa.private_key}")
    public void setPrivateKey(String privateKey) {
        // 这是一个公开的setter方法，用于设置privateKey属性的值。这个方法使用了@Value注解来从应用程序的配置文件中读取rsa.private_key属性的值，并将其设置为privateKey属性的值。
        RsaProperties.privateKey = privateKey;
    }

}