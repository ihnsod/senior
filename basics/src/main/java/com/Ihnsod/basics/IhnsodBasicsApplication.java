package com.Ihnsod.basics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.Ihnsod")
public class IhnsodBasicsApplication {

    public static void main(String[] args) {

        {
//            Map.of("Hello", "World").forEach((k, v) -> System.err.println(k + v));
        }

        SpringApplication.run(IhnsodBasicsApplication.class, args);
//        List.of("Hello World").forEach(s -> System.err.println(s));
    }

}
