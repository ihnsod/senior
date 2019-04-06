package com.Ihnsod.spring;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: Ihnsod
 * @create: 2018/12/20 22:50
 **/
@SpringBootApplication
@MapperScan("com.Ihnsod.spring.mapper")
public class IhnsodSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(IhnsodSpringApplication.class);
    }
}
