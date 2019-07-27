package com.github.framework.evo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * User: Guosd
 * Date: 2019-07-27 08:48
 */
@MapperScan(basePackages = { "com.github.framework.evo"})
@SpringBootApplication(scanBasePackages = { "com.github.framework.evo"})
public class EvoAutoDeployApplication {
    public static void main(String[] args) {
        SpringApplication.run(EvoAutoDeployApplication.class, args);
    }
}
