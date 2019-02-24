package com.zxx.senior;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Map;


@SpringBootApplication
@ComponentScan("com.zxx")
public class SeniorApplication {

    public static void main(String[] args) {

        {
//            Map.of("Hello", "World").forEach((k, v) -> System.err.println(k + v));
        }

        SpringApplication.run(SeniorApplication.class, args);
//        List.of("Hello World").forEach(s -> System.err.println(s));
    }

}
