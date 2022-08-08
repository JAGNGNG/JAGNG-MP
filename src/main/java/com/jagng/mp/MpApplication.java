package com.jagng.mp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
* @description: 启动类
* @author: JAGNG
* @date: 2022/8/7 12:47
**/
@SpringBootApplication
@EnableScheduling
public class MpApplication {

    public static void main(String[] args) {
        SpringApplication.run(MpApplication.class, args);
    }

}
