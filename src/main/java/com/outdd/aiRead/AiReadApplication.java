package com.outdd.aiRead;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ServletComponentScan
public class AiReadApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiReadApplication.class, args);
    }
}
