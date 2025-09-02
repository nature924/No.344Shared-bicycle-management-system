package com.southwind;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.southwind.mapper")
public class SharedBikeSeparateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SharedBikeSeparateApplication.class, args);
    }

}
