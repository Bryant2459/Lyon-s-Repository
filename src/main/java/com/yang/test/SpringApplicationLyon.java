package com.yang.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @Author: Lyon
 * @Date: 2020/8/6 21:03
 * @Description:
 */

@MapperScan("com.yang.test.mapper")
@SpringBootApplication(scanBasePackages = {"com.yang"})
public class SpringApplicationLyon extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationLyon.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringApplicationLyon.class);
    }
}