package com.yang.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(scanBasePackages = {"com.yang"})
public class TestApplication {

    public static void main(String[] args) {

        SpringApplication.run(TestApplication.class, args);

    }

}
