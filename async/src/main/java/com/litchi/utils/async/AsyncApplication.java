package com.litchi.utils.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @description: 异步
 * @author: lizhi
 * @create: 2019/09/04
 **/
@SpringBootApplication
@EnableAsync
public class AsyncApplication extends SpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(AsyncApplication.class, args);
    }
}
