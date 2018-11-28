package com.outdd.aiRead;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan(basePackages = "com.outdd.aiRead.**.dao")
public class AiReadApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiReadApplication.class, args);
    }
}
