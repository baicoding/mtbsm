package org.msgtu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@MapperScan(basePackages = "org.msgtu.*.mapper.relational*")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
