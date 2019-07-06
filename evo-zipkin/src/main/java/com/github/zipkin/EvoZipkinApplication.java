package com.github.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin2.server.internal.EnableZipkinServer;

import javax.sql.DataSource;

@SpringBootApplication
@EnableZipkinServer //启用zipkin服务
public class EvoZipkinApplication {
    public static void main(String[] args) {
        SpringApplication.run(EvoZipkinApplication.class, args);
    }
}

