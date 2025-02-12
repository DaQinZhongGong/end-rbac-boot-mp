package com.daqinzhonggong;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// 这是一个用于Spring Boot测试环境的注解。webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT 指定了测试时的Web环境配置。RANDOM_PORT 表示Spring Boot应用将在一个随机端口上启动，这通常用于集成测试，以确保测试不会与其他服务或之前的测试实例冲突
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RbacBootMpSystemApplicationTests {

    @Test
    public void contextLoads() {
    }

    public static void main(String[] args) {
    }

}

