package com.zigar.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Zigar
 * @createTime 2020-01-15 11:21:06
 * @description
 */

@SpringBootApplication
@MapperScan(value = "com.zigar.user.mapper")
@ComponentScan(basePackages = {"com.zigar"})
@EnableEurekaClient
@EnableDiscoveryClient
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
